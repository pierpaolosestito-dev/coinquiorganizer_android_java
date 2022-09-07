package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import coinquiorganizer.passwordrecovery.PasswordRecovery;
import coinquiorganizer.utility.DialogFactory;

public class RecoveryPasswordForm_One extends AppCompatActivity {

    public static String emailInserita;
    private EditText email;
    public static String nomeCasa;
    private EditText nomeCasaEdit;
    public static Integer numeroGenerato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password_form__one);
        this.email= (EditText) findViewById(R.id.email_recupero_password);
        this.nomeCasaEdit = (EditText)findViewById(R.id.nomeCasa_recupero_password);

    }

    public void prossimo_step_inserimento_codice(View view) {
        PasswordRecovery recoveryPassword = new PasswordRecovery();
        Log.i("INFO","INFO -> " + emailInserita);
        this.emailInserita = email.getText().toString();
        this.nomeCasa = nomeCasaEdit.getText().toString();
        if(emailInserita.equals("")){
            DialogFactory.createDialog(this,"Devi inserire un e-mail valida, non puoi lasciare il campo vuoto",DialogFactory.WARNING).show();
            return;
        }
        Log.i("INFO","INFO -> " + emailInserita);
        recoveryPassword.generaRandomEInviaMail(this,emailInserita);
        //Toast.makeText(this, "Codice inviato con successo alla tua mail",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), RecoveryPasswordForm_Two.class));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}