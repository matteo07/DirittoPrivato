package dir.exam.pier.dirittoprivato;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {


    TextView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information);


        information = (TextView)findViewById(R.id.information);

        information.setText("L'app è disposta per preparare lo studente alla prova scritta di Diritto Pubblico del corso di \nEconomia e Managment dell'Università Degli Studi dell'Insubria.\n" +
                "Il quiz si compone di 30 domande a scelta multipla, selezionate casualmente da un archivio di circa 150 domande. \n" +
                "\nAl termine del quiz è disponibile un riepilogo degli ERRORI  commessi.\n" +
                "Infatti,è opportuno far sì che lo studente possa concentrarsi sulle domande a cui è stata data una risposta sbagliata.\n" +
                "\nLe domande e le risposte sono state selezionate ad hoc dal documento presente sulla piattaforma e-learning che il professore mette a disposizione ogni anno.\n" +
                "Inoltre l'app fornisce una sezione in cui è possibile verificare quanti errori vengono commessi in base all'argomento di studio. \n" +
                "\nATTENZIONE: in molti casi esiste come possibile risposta :\n "+
                "'tutte le precedenti affermazioni sono corrette', in questo caso al termine del quiz potrebbe essere segnalata come errore una risposta che è effettivamente corretta nel merito \n" +
                "ma non corretta ai fini della richiesta. ");
    }

}