package firstexample.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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


import java.util.ArrayList;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.CredenzialiEntity;
import coinquiorganizer.utility.DialogFactory;



public class LoginActivity extends AppCompatActivity {

    EditText nomeCasaComponent,usernameComponent,passwordComponent = null;
    String nomeCasa,username,password = null;
    public static ArrayList<CredenzialiEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        list = new ArrayList<>();
        nomeCasaComponent = (EditText)findViewById(R.id.nomeCasa);
        usernameComponent = (EditText)findViewById(R.id.username);
        passwordComponent = (EditText)findViewById(R.id.password);

    }

    public void checkFieldnLogin(View view) {

        nomeCasa = nomeCasaComponent.getText().toString();
        username = usernameComponent.getText().toString();
        password = passwordComponent.getText().toString();


        if(!isOnline()){

                AlertDialog dialog = DialogFactory.createDialog(this, "Non sei connesso ad Internet!", DialogFactory.WARNING);
                dialog.show();
                return;

        }
        if (nomeCasa.equals("") || username.equals("") || password.equals("")) {
            AlertDialog dialog = DialogFactory.createDialog(this, "Dai un'occhiata ai tuoi dati", DialogFactory.WARNING);
            dialog.show();
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("Coinquilino").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {


                    if (post.child("nomeCasa").getValue().equals(nomeCasa) && post.child("password").getValue().equals(password) && post.child("username").getValue().equals(username)) {

                        AuthenticatedInfo loggedInfo = AuthenticatedInfo.getInstance();
                        loggedInfo.setNomeCasa(nomeCasa);
                        loggedInfo.setUsername(username);
                        loggedInfo.setHomeData(nomeCasa);
                        loggedInfo.setRispostaPersonale(post.child("rispostaCasa").getValue().toString());
                        loggedInfo.setPasswordAttuale(password);
                        loggedInfo.setFirstAccess(post.child("firstAccess").getValue().toString());
                        //loggedInfo.setFirstAccess(Boolean.parseBoolean(post.child("firstAccess").getValue().toString()));
                        loggedInfo.setEmail(post.child("email").getValue().toString());
                       // boolean firstAccess = Boolean.parseBoolean(post.child("firstAccess").getValue().toString());
                        //oggedInfo.setFirstAccess(firstAccess);
                        Toast.makeText(LoginActivity.this, "Login effettuato con successo",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                        return;

                    }
                }
                DialogFactory.createDialog(LoginActivity.this,"Login non riuscito",DialogFactory.WARNING).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info!=null && info.isConnected();

    }

    public void recuperoPassword(View view) {
        startActivity(new Intent(getApplicationContext(), RecoveryPasswordForm_One.class));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }


}