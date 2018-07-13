package com.example.pier.dirittoprivato;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pier.dirittoprivato.db.DbAdapter;

public class MainActivity extends AppCompatActivity {
    private final int ActivityRequestCode = 1;
    private DbAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = DbAdapter.getInstance(this);

        Button startButton30 = (Button) findViewById(R.id.startButton30);
        startButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTest(0);
            }
        });

    }

    public void startTest(int i){
        Intent intent = new Intent(this,TestActivity.class);
        intent.putExtra("quizType",i);
        startActivityForResult(intent, ActivityRequestCode);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu) {
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
    //call ProfileActivity through floating
    public void startProfileActivity(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }

    //call InformationActivity through floating
    public void startInformationActivity(View view){
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }
    //call deleteStats through floating
    public void deleteStas(View view){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        a_builder.setMessage("Sicuro di voler cancellare le statistiche relative ai quiz svolti fino ad ora?").setCancelable(false)
                .setPositiveButton("Sì", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.storico), Context.MODE_PRIVATE);
                        sharedPref.edit().clear().commit();
                        clearHistory();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                       dialog.cancel();

                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("");
        alert.show();
    }

    private void clearHistory() {
        dbAdapter.deleteErrori();
    }
}
