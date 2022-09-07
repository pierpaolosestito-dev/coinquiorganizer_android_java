package firstexample.helloworld;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import at.favre.lib.crypto.bcrypt.BCrypt;
import coinquiorganizer.observer.ObserverNotifier;
import coinquiorganizer.smtpmailsender.COMailSender;
import coinquiorganizer.utility.FirstAccessInputDialog;


public class MainActivity extends AppCompatActivity {


    //DaCancellare
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       // ObserverNotifier.sendWhatsAppWelcomeMessage(MainActivity.this,"C696my6X876LLbbKaWLze0");
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        findViewById(R.id.registraButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegistrationFormActivityOne.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        

        ImageView logoView = (ImageView)findViewById(R.id.logoView);
        Date date = new Date();
        int orario = date.getHours();
        if((orario+1)>=17 || (orario+1)<=06)
            logoView.setImageResource(R.drawable.logonotte); //Che sarà logo notte
        else logoView.setImageResource(R.drawable.logogiorno);




    }


    public void contact_me(View view) {
        startActivity(new Intent(getApplicationContext(), ChiSiamoActivity.class));
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}