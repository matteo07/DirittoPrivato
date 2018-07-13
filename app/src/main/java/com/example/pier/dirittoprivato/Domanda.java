package com.example.pier.dirittoprivato;

import android.content.ContentValues;

import com.example.pier.dirittoprivato.db.DbContract;

/**
 * Created by pier on 22/09/17.
 */

public class Domanda {
    private String domanda;
    private String rx_1;
    private String rx_2;
    private String rx_3;
    private String rx_4;
    private int correct;
    private int capitolo ;

    public Domanda(){}  //pier

    public Domanda(String domanda, String rx_1, String rx_2, String rx_3, String rx_4, int correct, int capitolo) {
        this.domanda = domanda;
        this.rx_1 = rx_1;
        this.rx_2 = rx_2;
        this.rx_3 = rx_3;
        this.rx_4 = rx_4;
        this.correct = correct;
        this.capitolo = capitolo;
    }

    public String getDomanda() {
        return domanda;
    }

    public String getRispostaData(int i) {
        switch (i) {
            case (1):
                return "\n" + getRx_1().toUpperCase();
            case (2):
                return "\n" + getRx_2().toUpperCase();
            case (3):
                return "\n" + getRx_3().toUpperCase();
            case (4):
                return "\n" + getRx_4().toUpperCase();
            default:
                return null;
        }
    }

    public String getRx_1() {
        return rx_1;
    }

    public String getRx_2() {
        return rx_2;
    }

    public String getRx_3() {
        return rx_3;
    }

    public String getRx_4() {
        return rx_4;
    }

    public int getCapitolo() {
        return capitolo;
    }

    public int getCorrect() {
        return correct;
    }



    // Setters

    public void setDomanda(String domanda) {
        this.domanda = domanda;
    }

    public void setRx_1(String rx_1) {
        this.rx_1 = rx_1;
    }

    public void setRx_2(String rx_2) {
        this.rx_2 = rx_2;
    }

    public void setRx_3(String rx_3) {
        this.rx_3 = rx_3;
    }

    public void setRx_4(String rx_4) {
        this.rx_4 = rx_4;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public void setCapitolo(int capitolo) {
        this.capitolo = capitolo;
    }

    //



    public ContentValues asContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DbContract.DomandaItem.COLUMN_NAME_DOMANDA, this.domanda );
        cv.put(DbContract.DomandaItem.COLUMN_NAME_RX1, this.rx_1 );
        cv.put(DbContract.DomandaItem.COLUMN_NAME_RX2, this.rx_2 );
        cv.put(DbContract.DomandaItem.COLUMN_NAME_RX3, this.rx_3 );
        cv.put(DbContract.DomandaItem.COLUMN_NAME_RX4, this.rx_4 );
        cv.put(DbContract.DomandaItem.COLUMN_NAME_CORRETTA, this.correct );
        cv.put(DbContract.DomandaItem.COLUMN_NAME_CAPITOLO, this.capitolo );
        return cv;
    }

    public boolean isCorrect(int i) {
        return i == this.correct;
    }
}
