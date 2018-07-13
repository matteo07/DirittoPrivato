package com.example.pier.dirittoprivato;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pier.dirittoprivato.db.DbAdapter;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TestActivity extends AppCompatActivity {

    private ArrayList<Domanda> domande = new ArrayList<>();
    public ArrayList<String> sbagliate = new ArrayList<>();
    private int index = 0;

    private Button btA;
    private Button btB;
    private Button btC;
    private Button btD;
    DbAdapter dbAdapter;
    ProgressBar prg;
    static int counter;
    private Chronometer chronometer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        counter =0;

        int quizType = getIntent().getIntExtra("quizType",0);

        dbAdapter = DbAdapter.getInstance(this);
        dbAdapter.open();

        if(quizType == 0){
            for(int i =1; i <= 10; i++) {
                domande.addAll(dbAdapter.selectDomandeFromCap(i, "3"));
                setLayout(domande.get(index));
            }
        } else {
            for(int i =1; i <= 10; i++) {
                domande.addAll(dbAdapter.selectDomandeFromCap(i, "1"));
                setLayout(domande.get(index));
            }
        }

        btA = (Button) findViewById(R.id.btA);
        btB = (Button) findViewById(R.id.btB);
        btC = (Button) findViewById(R.id.btC);
        btD = (Button) findViewById(R.id.btD);

        setButtonsListener();

        prg = (ProgressBar)findViewById(R.id.progressBar);


    }

    @Override
    public void onBackPressed() {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(TestActivity.this);
                a_builder.setMessage("Sicuro di voler terminare il Quiz ?").setCancelable(false)
                        .setPositiveButton("Termina", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("Prosegui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = a_builder.create();
                alert.setTitle("");
                alert.show();
    }

    public void setLayout(Domanda d){
        TextView domanda = (TextView) findViewById(R.id.Domanda);
        domanda.setText(d.getDomanda());

        TextView rx1 = (TextView) findViewById(R.id.rx1);
        rx1.setText("A. " + d.getRx_1());

        TextView rx2 = (TextView) findViewById(R.id.rx2);
        rx2.setText("B. " + d.getRx_2());

        TextView rx3 = (TextView) findViewById(R.id.rx3);
        rx3.setText("C. " + d.getRx_3());

        TextView rx4 = (TextView) findViewById(R.id.rx4);
        rx4.setText("D. " + d.getRx_4());
    }

    public void checkAnswer(int i){
        Domanda domanda = domande.get(index);
        if(isWrongAnswer(i)){
            sbagliate.add(domanda.getDomanda().concat(" ").concat(domanda.getRispostaData(i)));

            dbAdapter.incrementError(domanda.getCapitolo());
        }
        if(isNotLastQuestion()) {

            Log.e(TAG,"carico domanda");
            setLayout(domande.get(index));
        } else {
            Intent intent = new Intent(this,ResultsActivity.class);
            intent.putStringArrayListExtra("SBAGLIATE", sbagliate);
            intent.putExtra("NUMERO_ERRORI",sbagliate.size());
            startActivity(intent);
            this.finish();
        }
    }


    private boolean isNotLastQuestion() {
        return ++index + 1 <= domande.size();
    }

    private boolean isWrongAnswer(int i) {
        return !domande.get(index).isCorrect(i);
    }


    public void setButtonsListener() {

        btA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(1);
                counter += 1;
                prg.setProgress(counter);
            }
        });


        btB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(2);
                counter += 1;
                prg.setProgress(counter);
            }
        });


        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(3);
                counter += 1;
                prg.setProgress(counter);
            }
        });


        btD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(4);
                counter += 1;
                prg.setProgress(counter);
            }
        });
    }

    public Chronometer getChronometer() {
        return chronometer;
    }
}
