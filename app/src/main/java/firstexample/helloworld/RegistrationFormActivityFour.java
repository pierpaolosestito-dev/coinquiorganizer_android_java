package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import coinquiorganizer.fakeservlet.RegistrationInfo;
import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.model.PuliziaEntity;
import coinquiorganizer.observer.ObserverNotifier;
import coinquiorganizer.utility.DialogFactory;

public class RegistrationFormActivityFour extends AppCompatActivity {


    public ArrayList<EditText> emailCoinquilini;
    public ArrayList<AutoCompleteTextView> giorniPulizia;
    public ArrayList<AutoCompleteTextView> stanzePulizia;

    private ArrayList<String> usernamesCoinquilini;
    private ArrayList<String> passwordsCoinquilini;


    private LinearLayout myLayout2;

    private int counters = 0;
    private int countersOnCreate = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provola);
        myLayout2 = (LinearLayout) findViewById(R.id.linearLayout1);
        emailCoinquilini = new ArrayList<>();
        giorniPulizia = new ArrayList<>();
        stanzePulizia = new ArrayList<>();

        usernamesCoinquilini = new ArrayList<>();
        passwordsCoinquilini = new ArrayList<>();

        RegistrationInfo registrationInfo = RegistrationInfo.getInstance();
        String[] giorni = getResources().getStringArray(R.array.days_list);
        String[] stanze = getResources().getStringArray(R.array.rooms_list);
        if(!RegistrationFormActivityThree.pulizieInsieme.isChecked()) {
            while (countersOnCreate < registrationInfo.numeroCoinquilini) {
                EditTextCoinquilino();
                AutoCompleteTextPulizia(giorni, R.drawable.calendar, "Scegli un giorno");
                AutoCompleteTextPulizia(stanze, R.drawable.pulizie, "Soggiorno/Cucina/Bagno");
                countersOnCreate += 1;
            }
        }else{
            while(countersOnCreate<registrationInfo.numeroCoinquilini){
                EditTextCoinquilino();
                countersOnCreate+=1;
            }
        }
        //Toast.makeText(this, "A: " + editTextsNomi.size(), Toast.LENGTH_SHORT).show();

        FinalButton();

    }

        private void EditTextCoinquilino() {

            //return toAdd;
            LinearLayout toReturn = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            toReturn.setBackgroundColor(getResources().getColor(R.color.white));
            toReturn.setOrientation(LinearLayout.HORIZONTAL);
            if (counters == 0)
                params.setMargins(0, 30, 0, 0);
            counters += 1;
            toReturn.setLayoutParams(params);


            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.ic_coinq_icon);
            iv.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            toReturn.addView(iv);

            EditText toAdd = new EditText(this);
            //coinquiliniNomiCompleti.add(toAdd);

            toAdd.setHintTextColor(getResources().getColor(R.color.black));
            toAdd.setHint("Nome completo (es. Pierpaolo Sestito):");
            toAdd.setTextColor(getResources().getColor(R.color.black));
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            toAdd.setLayoutParams(params1);

            //toAdd.setId(idNomi);
            int generatedId = View.generateViewId();
            toAdd.setId(generatedId);

            toReturn.addView(toAdd);


            myLayout2.addView(toReturn);

            EditText text = (EditText) findViewById(generatedId);
            emailCoinquilini.add(text);


        }

        private void AutoCompleteTextPulizia(String[] arrays, int image, String hint) {
            LinearLayout toReturn = new LinearLayout(this);
            toReturn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            toReturn.setOrientation(LinearLayout.HORIZONTAL);
            toReturn.setBackgroundColor(getResources().getColor(R.color.white));
            ImageView iv = new ImageView(this);
            iv.setImageResource(image);
            iv.setLayoutParams(new LinearLayout.LayoutParams(80, 80));
            toReturn.addView(iv);

            AutoCompleteTextView toAdd = new AutoCompleteTextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            toAdd.setLayoutParams(params);
            toAdd.setBackgroundColor(getResources().getColor(R.color.purple_700));
            toAdd.setHint(hint);



            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrays);
            toAdd.setAdapter(adapter);

            //toAdd.setHintTextColor(getResources().getColor(R.color.black));
            //toAdd.setTextColor(getResources().getColor(R.color.black));
            //toAdd.setHint("Giorno di pulizia");


            int generatedId = View.generateViewId();
            toAdd.setId(generatedId);

            if(RegistrationFormActivityThree.pulizieInsieme.isChecked())
                toAdd.setEnabled(false);

            toReturn.addView(toAdd);

            myLayout2.addView(toReturn);
            AutoCompleteTextView toAddToArray = (AutoCompleteTextView) findViewById(generatedId);
            if(hint.equals("Scegli un giorno")){
                giorniPulizia.add(toAddToArray);
            }else{
                stanzePulizia.add(toAddToArray);
            }
        }


        private void FinalButton() {
            LinearLayout toReturn = new LinearLayout(this);
            toReturn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            toReturn.setOrientation(LinearLayout.HORIZONTAL);
            toReturn.setBackgroundColor(getResources().getColor(R.color.white));

            Button toAdd = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 20, 0, 30);
            toAdd.setLayoutParams(params);
            toAdd.setText(R.string.next_step);
            toAdd.setBackgroundColor(getResources().getColor(R.color.orange));
            toAdd.setTextColor(getResources().getColor(android.R.color.white));
            toAdd.setTextSize(30);
            toAdd.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {


                                             RegistrationInfo info = RegistrationInfo.getInstance();
                                             ArrayList<PuliziaEntity> organizzazionePuliziaa = new ArrayList<>();
                                             ArrayList<CoinquilinoEntity> coinquilinoEntities = new ArrayList<>();

                                             for (int i = 0; i < emailCoinquilini.size(); i++)
                                                 if (emailCoinquilini.get(i).getText().toString().equals("") || !isEmailValid(emailCoinquilini.get(i).getText().toString())) {
                                                     DialogFactory.createDialog(RegistrationFormActivityFour.this, "Hai lasciato qualche campo obbligatorio vuoto, oppure con un e-mail non valida", DialogFactory.WARNING).show();
                                                     return;
                                                 }

                                             if (info.lavaggioInsieme) {

                                                 for (EditText it : emailCoinquilini) {
                                                     String usernameGenerato = info.generaUsername(it.getText().toString());
                                                     String passwordGenerata = info.generaPassword(8);
                                                     Log.i("COINQ", "PASSWORD GENERATA: " + passwordGenerata);

                                                     CoinquilinoEntity coinquilinoEntity = new CoinquilinoEntity(info.nomeCasaInserito, usernameGenerato, passwordGenerata, it.getText().toString(), info.domandaCasaInserita, info.rispostaCasaInserita, "true");
                                                     PuliziaEntity daAggiungere = new PuliziaEntity(info.nomeCasaInserito, info.stanzaPuliziaCoinqRegistrante, usernameGenerato, info.giornoPuliziaCoinqRegistrante);
                                                     Log.i("COINQPULIZIAENTITY", daAggiungere.toString());
                                                     Log.i("CIAO", coinquilinoEntity.toString());
                                                     organizzazionePuliziaa.add(daAggiungere);
                                                     coinquilinoEntities.add(coinquilinoEntity);
                                                 }

                                             }else{
                                                 for(int i=0;i<giorniPulizia.size();i++){
                                                            if(giorniPulizia.get(i).getText().toString().equals("") || stanzePulizia.get(i).getText().equals("")){
                                                                DialogFactory.createDialog(RegistrationFormActivityFour.this, "Devi compilare tutti i campi, se sono uguali assumiamo che puliate 2 stanze diverse!", DialogFactory.WARNING).show();
                                                                return;
                                                            }
                                                     }
                                                 for (int i=0;i<emailCoinquilini.size();i++) {
                                                     String usernameGenerato = info.generaUsername(emailCoinquilini.get(i).getText().toString());
                                                     String passwordGenerata = info.generaPassword(8);


                                                     CoinquilinoEntity coinquilinoEntity = new CoinquilinoEntity(info.nomeCasaInserito, usernameGenerato, passwordGenerata, emailCoinquilini.get(i).getText().toString(), info.domandaCasaInserita, info.rispostaCasaInserita, "true");
                                                     PuliziaEntity daAggiungere = new PuliziaEntity(info.nomeCasaInserito, stanzePulizia.get(i).getText().toString(), usernameGenerato, giorniPulizia.get(i).getText().toString());

                                                     organizzazionePuliziaa.add(daAggiungere);
                                                     coinquilinoEntities.add(coinquilinoEntity);
                                                 }



                                                 }
                                             coinquilinoEntities.add(new CoinquilinoEntity(info.nomeCasaInserito, info.usernameInserito, info.passwordInserito, info.emailInserita, info.domandaCasaInserita, info.rispostaCasaInserita, "false"));
                                             organizzazionePuliziaa.add(new PuliziaEntity(info.nomeCasaInserito,info.stanzaPuliziaCoinqRegistrante,info.usernameInserito,info.giornoPuliziaCoinqRegistrante));
                                             info.setCoinquiliniInseriti(coinquilinoEntities);
                                             info.setOrganizzazionePuliziaFromFourthForm(organizzazionePuliziaa);


                                             ObserverNotifier.sendWelcomeMessageToRoomMates(RegistrationFormActivityFour.this,coinquilinoEntities);


                                             startActivity(new Intent(getApplicationContext(),RegistrationFinallyForm.class));
                                             overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);


                                         }

                                     });


                    toReturn.addView(toAdd);
            myLayout2.addView(toReturn);

            }


            private boolean isEmailValid(CharSequence email){
                    return Patterns.EMAIL_ADDRESS.matcher(email).matches();}



}
