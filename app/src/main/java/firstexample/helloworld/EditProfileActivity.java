package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.utility.DialogFactory;

public class EditProfileActivity extends AppCompatActivity {

    private TextView emailAttuale,usernameAttuale;
    private EditText email,username,oldPassword,password;
    AuthenticatedInfo info = AuthenticatedInfo.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        emailAttuale = (TextView) findViewById(R.id.emailAttuale);
        usernameAttuale = (TextView) findViewById(R.id.usernameAttuale);


        email = (EditText) findViewById(R.id.email1);
        username = (EditText) findViewById(R.id.username1);
        oldPassword = (EditText) findViewById(R.id.oldPassword);
        password = (EditText) findViewById(R.id.password1);

        emailAttuale.setText(info.getEmail());
        usernameAttuale.setText(info.getUsername());



    }

    public void goToEditHome(View view) {
        String oldUsername = "Da impostare";
        boolean modificheFatte = false;
        if(!email.getText().toString().equals("")){
            if(isEmailValid(email.getText().toString())){
                info.setEmail(email.getText().toString());
                modificheFatte = true;
            }else{
                DialogFactory.createDialog(this,"Hai inserito un e-mail non valida",DialogFactory.WARNING).show();
                return;
            }
        }
        if(!username.getText().toString().equals("")){
            oldUsername = info.getUsername();
            info.setUsername(username.getText().toString());
            modificheFatte = true;

        }
        if(!password.getText().toString().equals("")){
            if(!oldPassword.getText().toString().equals(info.getPasswordAttuale())){
                DialogFactory.createDialog(this,"La password attuale non è corretta",DialogFactory.WARNING).show();
                return;
            }else{
                info.setPasswordAttuale(password.getText().toString());
                modificheFatte=true;
            }
        }

        CoinquilinoEntity c = new CoinquilinoEntity(info.getNomeCasa(),info.getUsername(),info.getPasswordAttuale(),info.getEmail(),info.getDomandaCasa(),info.getRispostaPersonale(),info.getFirstAccess());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("Coinquilino").child(oldUsername + " " + c.getNomeCasa()).setValue(null);
        myRef.child("Coinquilino").child(c.getUsername() + " " + c.getNomeCasa()).setValue(c);

        if(modificheFatte)
       Toast.makeText(this,"Prime modifiche avvenute con successo",Toast.LENGTH_LONG).show();
        if(!modificheFatte)
            Toast.makeText(this,"Non abbiamo modificato nulla",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(),EditProfileTwo.class));
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
    private boolean isEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();}
}