package firstexample.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import coinquiorganizer.fakeservlet.RegistrationInfo;
import coinquiorganizer.utility.DialogFactory;

public class RegistrationFormActivityOne extends AppCompatActivity {

    private EditText formOne_nomeCasa,formOne_username,formOne_password,formOne_passwordRipetuta,formOne_numeroCoinquilini,formOne_email;
    private boolean campiObbligatoriVuoti = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form_one);

        formOne_nomeCasa = findViewById(R.id.formOne_nomeCasa);
        formOne_username = findViewById(R.id.formOne_username);
        formOne_password = findViewById(R.id.formOne_password);
        formOne_passwordRipetuta = findViewById(R.id.formOne_passwordRipetuta);
        formOne_numeroCoinquilini = findViewById(R.id.formOne_numeroCoinquilini);
        formOne_email = findViewById(R.id.emailRegistrazione);
    }

    @SuppressLint("ResourceAsColor")
    public void checkFieldnGoToNextStep(View view) {
        if(formOne_nomeCasa.getText().toString().equals("")){
            formOne_nomeCasa.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti=false;

        if(!formOne_nomeCasa.getText().toString().equals("")){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            myRef.child("Casa").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot data : snapshot.getChildren()){
                        if(data.child("nomeCasa").getValue().equals(formOne_nomeCasa.getText().toString())){
                            formOne_nomeCasa.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
                            DialogFactory.createDialog(RegistrationFormActivityOne.this,"Esiste gia un nome con questa casa",DialogFactory.WARNING).show();
                            return;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        if(formOne_username.getText().toString().equals("")){
            formOne_username.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti = false;

        if(formOne_password.getText().toString().equals("")){
            formOne_password.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti = false;

        if(formOne_passwordRipetuta.getText().toString().equals("")){
            formOne_passwordRipetuta.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti = false;

        if(formOne_numeroCoinquilini.getText().toString().equals("")){
            formOne_numeroCoinquilini.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti = false;
        if(formOne_email.getText().toString().equals("")){
            formOne_email.setHintTextColor(getResources().getColor(R.color.design_default_color_error));
            campiObbligatoriVuoti = true;
        }else campiObbligatoriVuoti = false;



        if(campiObbligatoriVuoti){
            AlertDialog dialog = DialogFactory.createDialog(RegistrationFormActivityOne.this,"Ci sono dei campi obbligatori non riempiti!" + System.lineSeparator() + "Sono stati segnati in rosso per facilitarne l'inserimento",DialogFactory.WARNING);
            dialog.show();
            return;
        }else{
            if(!formOne_password.getText().toString().equals(formOne_passwordRipetuta.getText().toString())){
                AlertDialog dialog = DialogFactory.createDialog(RegistrationFormActivityOne.this,"Le due password non coincidono" + System.lineSeparator() + "Sono stati segnati in rosso per facilitarne l'inserimento",DialogFactory.WARNING);
                dialog.show();
                formOne_password.setTextColor(R.color.design_default_color_error);
                formOne_passwordRipetuta.setTextColor(R.color.design_default_color_error);
                return;
            }else{
                formOne_password.setTextColor(R.color.black);
                formOne_passwordRipetuta.setTextColor(R.color.black);
            }

            RegistrationInfo info = RegistrationInfo.getInstance();
            info.setFirstForm(formOne_nomeCasa.getText().toString(),formOne_username.getText().toString(),formOne_password.getText().toString(),formOne_email.getText().toString(),Integer.parseInt(formOne_numeroCoinquilini.getText().toString()));

            startActivity(new Intent(getApplicationContext(),RegistrationFormActivityTwo.class));
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

        }



    }
}