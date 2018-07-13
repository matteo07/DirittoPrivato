package com.example.pier.dirittoprivato.db;

import android.provider.BaseColumns;

/**
 * Created by pier on 22/09/17.
 */

public class DbContract {

    static final String DATABASE_NAME = "domande.db";
    static final int DATABASE_VERSION = 14;

    private DbContract() {}

    public static abstract class DomandaItem implements BaseColumns {
        public static final String TABLE_NAME = "domanda";
        public static final String COLUMN_NAME_DOMANDA = "domanda";
        public static final String COLUMN_NAME_RX1 = "rx1";
        public static final String COLUMN_NAME_RX2 = "rx2";
        public static final String COLUMN_NAME_RX3 = "rx3";
        public static final String COLUMN_NAME_RX4 = "rx4";
        public static final String COLUMN_NAME_CORRETTA = "corretta";
        public static final String COLUMN_NAME_CAPITOLO = "capitolo";
    }

    public static abstract class ErroriItem implements BaseColumns {
        public static final String TABLE_NAME = "punteggio";
        public static final String COLUMN_NAME_CAPITOLO = "capitolo";
        public static final String COLUMN_NAME_ERRORI = "errori";
    }
}
