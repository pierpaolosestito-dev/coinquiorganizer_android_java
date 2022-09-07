package coinquiorganizer.fakeservlet;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import coinquiorganizer.model.PuliziaEntity;
import coinquiorganizer.model.SplitcashEntity;
import coinquiorganizer.utility.DialogFactory;
import firstexample.helloworld.DashboardActivity;
import firstexample.helloworld.LoginActivity;
import firstexample.helloworld.R;

public class AuthenticatedInfo {
    private String nomeCasa = null;
    private String username = null;
    private String email = null;
    private String passwordAttuale = null;
    private String groupWAPP = null;
    private String domandaCasa = null;
    private String rispostaCasa = null;
    private Integer numeroCoinquilini;
    private String rispostaPersonale;
    private String firstAccess;
    public static boolean primoAccessoGiornalieroAppSpese = true;

    public static void setPrimoAccessoGiornalieroAppSpese(boolean primoAccessoGiornalieroAppSpese) {
        AuthenticatedInfo.primoAccessoGiornalieroAppSpese = primoAccessoGiornalieroAppSpese;
    }

    private static AuthenticatedInfo instance = null;

    private AuthenticatedInfo(){
        this.nomeCasa = "Da impostare";
        this.username = "Da impostare";
        this.numeroCoinquilini = 0;
        this.email = "Da impostare";
        this.groupWAPP = "Da impostare";
        this.domandaCasa = "Da impostare";
        this.rispostaCasa = "Da impostare";
        this.passwordAttuale = "Da impostare";
        this.firstAccess = "Da impostare";
        this.rispostaPersonale = "Da impostare";
    }

    public String getGroupWAPP() {
        return groupWAPP;
    }

    public void setGroupWAPP(String groupWAPP) {
        this.groupWAPP = groupWAPP;
    }

    public void setRispostaPersonale(String rispostaPersonale) {
        this.rispostaPersonale = rispostaPersonale;
    }

    public String getRispostaPersonale() {
        return rispostaPersonale;
    }

    public static AuthenticatedInfo getInstance(){
        if(instance == null)
            instance = new AuthenticatedInfo();
        return instance;
    }

    public void setPasswordAttuale(String passwordAttuale) {
        this.passwordAttuale = passwordAttuale;
    }

    public String getPasswordAttuale() {
        return passwordAttuale;
    }

    public void setFirstAccess(String firstAccess) {
        this.firstAccess = firstAccess;
    }

    public void setNomeCasa(String nomeCasa) {
        this.nomeCasa = nomeCasa;

    }

    public String getRispostaCasa() {
        return rispostaCasa;
    }

    public String getDomandaCasa() {
        return domandaCasa;
    }

    public String getFirstAccess() {
        return firstAccess;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHomeData(String nomeCasa) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("Casa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if(post.child("nomeCasa").getValue().equals(nomeCasa)){
                       numeroCoinquilini = Integer.parseInt(post.child("numeroCoinquilini").getValue().toString());
                       groupWAPP = post.child("groupWAPP").getValue().toString();
                       domandaCasa = post.child("domanda").getValue().toString();
                       rispostaCasa = post.child("risposta").getValue().toString();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public Integer getNumeroCoinquilini() {
        return numeroCoinquilini;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public String getUsername() {
        return username;
    }
}
