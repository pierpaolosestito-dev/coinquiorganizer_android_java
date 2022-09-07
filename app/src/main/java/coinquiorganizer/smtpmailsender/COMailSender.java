package coinquiorganizer.smtpmailsender;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import coinquiorganizer.model.CoinquilinoEntity;

public class COMailSender extends AsyncTask<Void,Void,Void>  {

    //Add those line in dependencies
    //implementation files('libs/activation.jar')
    //implementation files('libs/additionnal.jar')
    //implementation files('libs/mail.jar')

    //Need INTERNET permission

    //Variables
    private Context mContext;
    private Session mSession;

    private String mEmail;
    private String mSubject;
    private String mMessage;
    private String result;





    private ProgressDialog mProgressDialog;

    //Constructor
    public COMailSender() {

    }


    public void setAllData(Context mContext, String mEmail, String mSubject, String mMessage){
        this.mContext = mContext;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mMessage = mMessage;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Show progress dialog while sending email
        mProgressDialog = ProgressDialog.show(mContext,"Invio e-mail in corso", "Aspettare,prego...",false,false);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismiss progress dialog when message successfully send
        mProgressDialog.dismiss();

        //Show success toast
        Toast.makeText(mContext,result,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params) {


        // Sender's email ID needs to be mentioned


        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties object
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.port","587");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");

        // Get the default Session object.
        Session mailSession = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(mailSession);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(Config.EMAIL));

            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(mEmail));

            // Set Subject: header field
            message.setSubject(mSubject);

            // Now set the actual message
            message.setText(mMessage,"utf-8","html");

            // Send message
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(Config.EMAIL,Config.PASSWORD);
            transport.sendMessage(message,message.getAllRecipients());

            this.result = "CORRETTO: Messaggio inviato con successo";

        }catch (SMTPAddressFailedException mex) {
            mex.printStackTrace();
            Log.i("A","ERRORACCIO SENDFAILEDEXCEPTION: " + mex.toString());
            this.result = "ERRORE: Probabilmente hai l'antivirus che blocca l'app, i consensi di Google, o non hai linea Internet";
        }catch(MessagingException mex){
            mex.printStackTrace();
            Log.i("A","ERRORACCIO SENDFAILEDEXCEPTION: " + mex.toString());
            this.result = "ERRORE: Probabilmente hai l'antivirus che blocca l'app, i consensi di Google, o non hai linea Internet";
        }
        return null;
    }
}