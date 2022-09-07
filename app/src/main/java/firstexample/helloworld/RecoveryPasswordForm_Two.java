package firstexample.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.passwordrecovery.PasswordRecovery;
import coinquiorganizer.smtpmailsender.COMailSender;
import coinquiorganizer.utility.DialogFactory;

public class RecoveryPasswordForm_Two extends AppCompatActivity {

    private EditText codiceInserito;
    private Integer codiceInteger;
    private static int numeroTentativi;
    private Integer codiceDaVerificare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery_password_form__two);
        this.codiceInserito = (EditText) findViewById(R.id.codice_recovery_password);


    }

    public void controllo_validita_codice(View view) {
        if(!codiceInserito.getText().toString().equals(PasswordRecovery.numeroCodiceRandom)) {
            DialogFactory.createDialog(this,"Il codice non è giusto.",DialogFactory.WARNING).show();
            numeroTentativi+=1;
            if(numeroTentativi==3){
                Toast.makeText(this,"Hai superato i tentativi",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                numeroTentativi=0;
                return;
            }
            Toast.makeText(this,"Mancano " + (3-numeroTentativi) + " tentativi",Toast.LENGTH_LONG).show();
        }else{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            myRef.child("Coinquilino").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot post : snapshot.getChildren()) {

                        if (!RecoveryPasswordForm_One.nomeCasa.equals("")) {
                            if (post.child("email").getValue().equals(RecoveryPasswordForm_One.emailInserita) && post.child("nomeCasa").getValue().equals(RecoveryPasswordForm_One.nomeCasa)) {


                                String username = post.child("username").getValue().toString();
                                String password = post.child("password").getValue().toString();
                                COMailSender mailSender = new COMailSender();
                                mailSender.setAllData(RecoveryPasswordForm_Two.this,RecoveryPasswordForm_One.emailInserita,"CoinquiOrganizer : Credenziali recuperate","NOME CASA: " + RecoveryPasswordForm_One.nomeCasa + System.lineSeparator() + "USERNAME: " + username + System.lineSeparator() + "PASSWORD: " + password );
                                mailSender.execute();
                                Toast.makeText(RecoveryPasswordForm_Two.this, "Ti stiamo inviando le credenziali recuperate tramite E-MAIL", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                                return;


                            }
                        }else{
                            if (post.child("email").getValue().equals(RecoveryPasswordForm_One.emailInserita)) {


                                String nomeCasa = post.child("nomeCasa").getValue().toString();
                                String username = post.child("username").getValue().toString();
                                String password = post.child("password").getValue().toString();
                                COMailSender mailSender = new COMailSender();
                                mailSender.setAllData(RecoveryPasswordForm_Two.this,RecoveryPasswordForm_One.emailInserita,"CoinquiOrganizer : Credenziali recuperate","NOME CASA: " + nomeCasa + System.lineSeparator() + "USERNAME: " + username + System.lineSeparator() + "PASSWORD: " + password );
                                mailSender.execute();
                                Toast.makeText(RecoveryPasswordForm_Two.this, "Ti stiamo inviando le credenziali recuperate tramite E-MAIL", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                                return;
                            }
                        }
                    }
                    DialogFactory.createDialog(RecoveryPasswordForm_Two.this,"Non esiste l'account che stai cercando, ci dispiace",DialogFactory.WARNING).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    }
}