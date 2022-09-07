package coinquiorganizer.fakeservlet;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.StringTokenizer;

import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.model.PuliziaEntity;
import firstexample.helloworld.RegistrationFormActivityOne;

public class RegistrationInfo {
    /*Informazioni casa*/
    public String nomeCasaInserito;
    public String domandaCasaInserita;
    public String rispostaCasaInserita;
    public String groupIDWhatsApp = null;
    public Integer numeroCoinquilini;

    /*Informazioni coinquilinoRegistrato*/
    //nomeCasaInserito;
    public String usernameInserito, passwordInserito;
    public String giornoPulizia, stanzaPulizia;
    public String emailInserita;

    /*Terza form*/
    public boolean lavaggioInsieme;
    public String giornoPuliziaCoinqRegistrante;
    public String stanzaPuliziaCoinqRegistrante;


    /*Organizzazione pulizia e altri coinquilini */
    public ArrayList<PuliziaEntity> organizzazionePulizia;
    public ArrayList<CoinquilinoEntity> coinquiliniInseriti;




    private static RegistrationInfo instance = null;
    private RegistrationInfo(){
        this.nomeCasaInserito = "Da inserire";
        this.domandaCasaInserita = "Da inserire";
        this.rispostaCasaInserita = "Da inserire";
        this.groupIDWhatsApp = "Da inserire";
        this.numeroCoinquilini = -1;
        this.usernameInserito = "Da inserire";
        this.passwordInserito = "Da inserire";
        this.giornoPulizia = "Da inserire";
        this.stanzaPulizia = "Da inserire";


    }



    public void setFirstForm(String nomeCasa, String usernameInserito, String passwordInserito, String emailInserita,Integer numeroCoinquilini){
        this.nomeCasaInserito = nomeCasa;
        this.usernameInserito = usernameInserito;
        this.passwordInserito = passwordInserito;
        this.emailInserita = emailInserita;
        this.numeroCoinquilini = numeroCoinquilini;
    }

    public void setSecondForm(String groupIDWhatsApp,String domandaCasaInserita,String rispostaCasaInserita){
        this.groupIDWhatsApp = groupIDWhatsApp;
        this.domandaCasaInserita = domandaCasaInserita;
        this.rispostaCasaInserita = rispostaCasaInserita;
    }

    public void setThirdForm(boolean lavaggioInsieme,String giornoPuliziaCoinqRegistrante,String stanzaPulizia){
        this.lavaggioInsieme=lavaggioInsieme;
        this.giornoPuliziaCoinqRegistrante = giornoPuliziaCoinqRegistrante;
        this.stanzaPuliziaCoinqRegistrante = stanzaPulizia;
    }
    public void setOrganizzazionePuliziaFromFourthForm(ArrayList<PuliziaEntity> puliziaEntities){
        this.organizzazionePulizia = puliziaEntities;
    }
    public void setCoinquiliniInseriti(ArrayList<CoinquilinoEntity> coinquilinoEntities){
        this.coinquiliniInseriti = coinquilinoEntities;
    }



    public static RegistrationInfo getInstance(){
        if(instance == null){
            instance = new RegistrationInfo();
        }
        return instance;
    }

    public String generaUsername(String email){
        String username = null;
        StringTokenizer tokenizer = new StringTokenizer(email,"@");
        while(tokenizer.hasMoreTokens()){
            username = tokenizer.nextToken();
            return username;
        }
        return username;
    }

    public String generaPassword(int len){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345678910";
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();

        for(int i=0;i<len;i++){
            int randomIndex = random.nextInt(chars.length());
            builder.append(chars.charAt(randomIndex));
        }
        return builder.toString().toLowerCase();

    }


}
