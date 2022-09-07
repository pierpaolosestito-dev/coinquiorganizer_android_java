package firstexample.helloworld;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import coinquiorganizer.fakeservlet.RegistrationInfo;
import coinquiorganizer.model.CasaEntity;
import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.model.PuliziaEntity;
import coinquiorganizer.model.SplitcashEntity;
import coinquiorganizer.model.ToDoEntity;
import coinquiorganizer.observer.ObserverNotifier;
import coinquiorganizer.smtpmailsender.COMailSender;

public class RegistrationFinallyForm extends AppCompatActivity {

    private TextView nomeCasa, numeroCoinquilini, domanda, risposta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_finally_form);
        nomeCasa = findViewById(R.id.finallyForm_nomeCasa);
        numeroCoinquilini = findViewById(R.id.finallyForm_numCoinquilini);
        domanda = findViewById(R.id.finallyForm_domanda);
        risposta = findViewById(R.id.finallyForm_risposta);


        RegistrationInfo info = RegistrationInfo.getInstance();
        nomeCasa.setText(info.nomeCasaInserito);
        numeroCoinquilini.setText(info.numeroCoinquilini.toString());
        domanda.setText(info.domandaCasaInserita);
        risposta.setText(info.rispostaCasaInserita);



        findViewById(R.id.finallyForm_go).setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                RegistrationInfo info = RegistrationInfo.getInstance();
                CasaEntity daInserire = new CasaEntity(info.nomeCasaInserito,info.domandaCasaInserita,info.rispostaCasaInserita,info.groupIDWhatsApp,info.numeroCoinquilini);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();


                myRef.child("Casa").child(daInserire.getNomeCasa()).setValue(daInserire);

                ArrayList<CoinquilinoEntity> coinquiliniInseriti = info.coinquiliniInseriti;

                for(CoinquilinoEntity it : coinquiliniInseriti){
                    myRef.child("Coinquilino").child(it.getUsername() + " " + it.getNomeCasa()).setValue(it);
                }

                ArrayList<PuliziaEntity> pulizieDaInserire = info.organizzazionePulizia;

                for(PuliziaEntity it : pulizieDaInserire){
                    myRef.child("PulizieCasa").child(it.getNomeCasa() + " "  +it.getCoinquilinoAddetto()).setValue(it);
                }

                ToDoEntity firstToDoEntity = new ToDoEntity(info.nomeCasaInserito,"Inserisci la prima attività!","Benvenuto nella To-do list di CoinquiOrganizer! Qui puoi appuntare i tuoi impegni con i coinquilini",true,false,LocalDate.now().toString()+":" +LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond());
                myRef.child("ListaAttivita").child(firstToDoEntity.getNomeCasa() + " " + firstToDoEntity.getNomeAttivita() + " " + firstToDoEntity.getDataOrario()).setValue(firstToDoEntity);
                //Vedere se crea un child senza fare un add dopo

                SplitcashEntity splitToAdd = new SplitcashEntity(info.nomeCasaInserito,"CoinquiOrganizer",0,"Benvenuto nello split-cash di CoinquiOrganizer", LocalDate.now().toString(), LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond());
                myRef.child("SuddivisioneSpese").child(splitToAdd.getNomeCasa() + " " + splitToAdd.getCoinquilinoDonatore() + " " + splitToAdd.getDataVersamento()+":"+splitToAdd.getOrario()).setValue(splitToAdd);

                //if(!info.groupIDWhatsApp.equals("Non specificato"))
                ObserverNotifier.sendWhatsAppWelcomeMessage(RegistrationFinallyForm.this,"C696my6X876LLbbKaWLze0");

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            }
        });
    }
}