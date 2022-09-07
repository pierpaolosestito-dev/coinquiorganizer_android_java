package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.CasaEntity;
import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.utility.DialogFactory;

public class EditProfileTwo extends AppCompatActivity {

    private TextView wAppAttuale,domandaAttuale;
    private EditText wAppNuovo, rispostaNuova;
    AuthenticatedInfo info = AuthenticatedInfo.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_two);

        wAppAttuale = (TextView) findViewById(R.id.wAppAttuale);
        domandaAttuale = (TextView) findViewById(R.id.domancaCasaAttuale);

        wAppNuovo = (EditText) findViewById(R.id.wApp);
        rispostaNuova = (EditText) findViewById(R.id.rispostaCasaPersonaleNuova);

        wAppAttuale.setText(info.getGroupWAPP());
        domandaAttuale.setText(info.getDomandaCasa());

    }

    public void endUpdates(View view) {
        boolean modificheFatte = false;
        if(!wAppNuovo.getText().toString().equals("")){
            info.setGroupWAPP(wAppNuovo.getText().toString());
            modificheFatte=true;
        }
        if (!rispostaNuova.getText().toString().equals("")) {
            info.setRispostaPersonale(rispostaNuova.getText().toString());
            modificheFatte=true;
        }

        CoinquilinoEntity c = new CoinquilinoEntity(info.getNomeCasa(),info.getUsername(),info.getPasswordAttuale(),info.getEmail(),info.getDomandaCasa(),info.getRispostaPersonale(),info.getFirstAccess());
        CasaEntity casa = new CasaEntity(info.getNomeCasa(),info.getDomandaCasa(),info.getRispostaCasa(),info.getGroupWAPP(),info.getNumeroCoinquilini());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("Coinquilino").child(c.getUsername() + " " + c.getNomeCasa()).setValue(c);
        myRef.child("Casa").child(casa.getNomeCasa()).setValue(casa);

        if(modificheFatte)
            Toast.makeText(this,"Prime modifiche avvenute con successo",Toast.LENGTH_LONG).show();
        if(!modificheFatte)
            Toast.makeText(this,"Non abbiamo modificato nulla",Toast.LENGTH_LONG).show();

        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
    }
}