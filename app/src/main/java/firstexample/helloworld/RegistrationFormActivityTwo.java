package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import coinquiorganizer.fakeservlet.RegistrationInfo;
import coinquiorganizer.utility.DialogFactory;

public class RegistrationFormActivityTwo extends AppCompatActivity {

    private EditText groupwAppID,domandaRecuperoPW,rispostaRecuperoPW;
    private CheckBox wAppCheck;
    private boolean campiObbligatoriVuoti = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form_two);
        groupwAppID = findViewById(R.id.formTwo_wAppGroupID);
        domandaRecuperoPW = findViewById(R.id.formTwo_domandaRecuperoPW);
        rispostaRecuperoPW = findViewById(R.id.formTwo_rispostaRecuperoPW);

        wAppCheck = findViewById(R.id.formTwo_checkbox);

        if(!whatsAppInstalledOrNot()){
            groupwAppID.setHint("Non hai WhatsApp installato");
            groupwAppID.setEnabled(false);
            wAppCheck.setText("Non hai WhatsApp installato");
            wAppCheck.setEnabled(false);

        }
    }

    public void checkFieldnGoToThirdStep(View view) {
        if(wAppCheck.isChecked()){
            if(groupwAppID.getText().toString().equals(""))
            {
                groupwAppID.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
                campiObbligatoriVuoti = true;
                AlertDialog dialog = DialogFactory.createDialog(this,"Devi inserire il GroupID del gruppo WhatsApp dei coinquilini", DialogFactory.WARNING);
                dialog.show();
                return;
            }else campiObbligatoriVuoti=false;

        }
        if(domandaRecuperoPW.getText().toString().equals("")){
            domandaRecuperoPW.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti = false;
        if(rispostaRecuperoPW.getText().toString().equals("")){
            rispostaRecuperoPW.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti = false;

        if(campiObbligatoriVuoti){
            AlertDialog dialog = DialogFactory.createDialog(RegistrationFormActivityTwo.this,"Ci sono dei campi obbligatori non riempiti!" + System.lineSeparator() + "Sono stati segnati in rosso per facilitarne l'inserimento",DialogFactory.WARNING);
            dialog.show();
            return;
        }else{
            RegistrationInfo info = RegistrationInfo.getInstance();

            info.setSecondForm(groupwAppID.getText().toString(),domandaRecuperoPW.getText().toString(),rispostaRecuperoPW.getText().toString());

            if(!whatsAppInstalledOrNot() || groupwAppID.getText().toString().equals("")){
                info.setSecondForm("Non specificato",domandaRecuperoPW.getText().toString(),rispostaRecuperoPW.getText().toString());
            }
            startActivity(new Intent(getApplicationContext(),RegistrationFormActivityThree.class));
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
        }


    }
    private boolean whatsAppInstalledOrNot(){
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try{
            pm.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES);
            app_installed=true;
        }catch(PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }
}