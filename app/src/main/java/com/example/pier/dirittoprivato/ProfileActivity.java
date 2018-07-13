package com.example.pier.dirittoprivato;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pier.dirittoprivato.db.DbAdapter;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ProfileActivity extends AppCompatActivity {

    DbAdapter dbAdapter;
    private final String[] CAPITOLI = {"Stato e Costituzione","Lo Stato e le sue forme","Le fonti del diritto","La Costituzione e le leggi Costituzionali","La legge ordinaria statale","Gli atti con valore o forza di legge","La legge ordinaria e statuaria","L’adattamento al diritto internazionale e le fonti comunitarie","Il Parlamento","Il Governo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        dbAdapter = DbAdapter.getInstance(this);
        dbAdapter.open();

        setContentView(R.layout.activity_profile);

        final ListView listView = (ListView)findViewById(R.id.stats);

        ArrayList<String> capitoli = dbAdapter.getCapitoliOrderedByErrori();
        ArrayList<String> sbagliate = new ArrayList<String>();

        for(String numeroCapitolo : capitoli){
            int errori = dbAdapter.getErroriInCapitolo(numeroCapitolo);
            if(errori != 0) {
                sbagliate.add(CAPITOLI[Integer.parseInt(numeroCapitolo) - 1] + "\n" + "ERRORI: " + errori);
                Log.d("CAPITOLO " + numeroCapitolo, errori + "");
                Log.d("TITOLO CAPITOLO ", CAPITOLI[Integer.parseInt(numeroCapitolo) - 1]);
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sbagliate);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        TextView quizSvolti = (TextView)findViewById(R.id.quiz_svolti);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.storico), Context.MODE_PRIVATE);
        int numQuiz = sharedPref.getInt(getString(R.string.quiz_svolti),0);
        quizSvolti.setText(getString(R.string.quiz_svolti_textView) + " : " + numQuiz);

    }
}
