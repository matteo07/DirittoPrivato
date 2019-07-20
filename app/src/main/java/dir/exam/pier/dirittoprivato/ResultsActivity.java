package dir.exam.pier.dirittoprivato;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    private final int ActivityRequestCode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView textView = (TextView)findViewById(R.id.textView2);
        int s = getIntent().getIntExtra("NUMERO_ERRORI",0);
        textView.setText(getResources().getString(R.string.risultato)+ ": "+s + "/30"+
                "\nin MAIUSCOLO è indicata la risposta errata che hai selezionato durante il quiz");


        showDomandeSbagliate(getIntent().getStringArrayListExtra("SBAGLIATE"));

        incrementDoneQuiz();

    }


    //implementarlo per non richiamare il quiz precedente
    //senza, riorna a quello prima e decrementa di 1 i quiz svolti!!!!
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




    private void incrementDoneQuiz(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.storico),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if(sharedPref.contains(getString(R.string.quiz_svolti))){
            int value = sharedPref.getInt(getString(R.string.quiz_svolti),0);
            editor.putInt(getString(R.string.quiz_svolti), value + 1);
            editor.apply();
            //Log.d("INT SI", String.valueOf(value));
        }else{
            editor.putInt(getString(R.string.quiz_svolti), 1);
            editor.apply();
            //Log.d("INT NO", "CIAO");
        }
    }


    public void showDomandeSbagliate(ArrayList<String> sbagliate){  //per listView
        final ListView listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sbagliate);
        listView.setAdapter(adapter);
    }
}
