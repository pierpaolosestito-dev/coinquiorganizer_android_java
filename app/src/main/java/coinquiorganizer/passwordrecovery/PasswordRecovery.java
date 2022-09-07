package coinquiorganizer.passwordrecovery;


import android.content.Context;
import android.util.Log;

import java.util.Random;

import coinquiorganizer.smtpmailsender.COMailSender;

public class PasswordRecovery {
    public static String numeroCodiceRandom;
    public PasswordRecovery(){
        this.numeroCodiceRandom = "";
    }

    public void generaRandomEInviaMail(Context context,String email){
        Random r = new Random();
        //random.nextInt(max - min + 1) + min
        Integer numeroCodice = r.nextInt(1000000-10000+1) + 10000;
        this.numeroCodiceRandom = ""+numeroCodice;

        COMailSender mailSender = new COMailSender();
        mailSender.setAllData(context,email,"Codice da inserire per il recupero dati","Il codice da inserire per il recupero dati è : " + this.numeroCodiceRandom);
        mailSender.execute();
        Log.i("INFO","INFO-> SONO ARRIVATO QUA");

    }
}
