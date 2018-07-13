package com.example.pier.dirittoprivato.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;

/**
 * Created by pier on 22/09/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = "DBOpenHelper";

    private static DbHelper sInstance;
    private DbAdapter dbAdapter;
    Context context;

    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new DbHelper(context.getApplicationContext());
        return sInstance;
    }

    public static final String SQL_CREATE_TABLE_DOMANDA = "create table " //
            + DbContract.DomandaItem.TABLE_NAME + " (" //
            + DbContract.DomandaItem.COLUMN_NAME_DOMANDA + " text not null, " //
            + DbContract.DomandaItem.COLUMN_NAME_RX1 + " text not null, " //
            + DbContract.DomandaItem.COLUMN_NAME_RX2 + " text not null, " //
            + DbContract.DomandaItem.COLUMN_NAME_RX3 + " text not null, " //
            + DbContract.DomandaItem.COLUMN_NAME_RX4 + " text not null, " //
            + DbContract.DomandaItem.COLUMN_NAME_CORRETTA + " int not null, " //
            + DbContract.DomandaItem.COLUMN_NAME_CAPITOLO + " int not null " //
            + ");";

    public static final String SQL_CREATE_TABLE_ERRORI = "create table "
            + DbContract.ErroriItem.TABLE_NAME + " ("
            + DbContract.ErroriItem.COLUMN_NAME_ERRORI + " int not null, " //
            + DbContract.ErroriItem.COLUMN_NAME_CAPITOLO + " int not null " //
            + ");";

    public void initialize(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE_DOMANDA);
        db.execSQL(SQL_CREATE_TABLE_ERRORI);
        initializeErrors(db);

        dbAdapter = DbAdapter.getInstance(context);

        try {
            dbAdapter.importCSV(context, db);
            Log.d("IMPORT CSV", "ENDED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeErrors(SQLiteDatabase db) {
        for(int i = 1; i <= 10; i++){
            insertCap(i , db);
        }
    }

    private void insertCap(int i, SQLiteDatabase db) {
        String insertQuery = "insert into " + DbContract.ErroriItem.TABLE_NAME
                + " values (0 ," + i + " )";
        db.execSQL(insertQuery);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            initialize(db);
            Log.v("Database","Created correctly");
        } catch (SQLException e) {
            Log.e(LOG_TAG, e.getMessage());
            throw e;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + DbContract.DomandaItem.TABLE_NAME);
        db.execSQL("drop table " + DbContract.ErroriItem.TABLE_NAME);
        initialize(db);
        Log.d("Database","Upgraded correctly");
    }
}
