package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import coinquiorganizer.smtpmailsender.COMailSender;

public class ChiSiamoActivity extends AppCompatActivity {

    private EditText emailPersonale,oggetto,messaggio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_siamo);
        emailPersonale = findViewById(R.id.contact_me_email_personale);
        oggetto = findViewById(R.id.contact_me_oggetto);
        messaggio = findViewById(R.id.contact_me_message);
    }
    public void sendMessageToPierpaolo(View view) {
        String messaggioFinale = "Messaggio inviato da: " +emailPersonale.getText().toString() + System.lineSeparator() + "MESSAGGIO: " + messaggio.getText().toString();
        COMailSender mailSender = new COMailSender();
        mailSender.setAllData(this,"pierpaolo.sestito.1999@gmail.com",oggetto.getText().toString(),messaggioFinale);
        mailSender.execute();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}