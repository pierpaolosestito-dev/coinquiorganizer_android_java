package coinquiorganizer.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.model.SplitcashEntity;
import firstexample.helloworld.R;
import firstexample.helloworld.SplitcashActivity;

public class FirstAccessInputDialog extends AppCompatDialogFragment {

    private EditText username,password,passwordRipetuta,rispostaDomanda;
    private TextView domandaCasa;
    AuthenticatedInfo info = AuthenticatedInfo.getInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.firstaccess_inputdialog,null);
        username = view.findViewById(R.id.firstAccess_cambioUsername);
        password = view.findViewById(R.id.firstAccess_cambioPassword);
        rispostaDomanda = view.findViewById(R.id.firstAccess_rispostaPersonale);
        domandaCasa = view.findViewById(R.id.firstAccess_domandaCasa);
        domandaCasa.setText(info.getDomandaCasa());
        builder.setView(view);
        builder.setTitle("Heylà, benvenuto! E' il momento di impostare il tuo account: ");
        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Effettua modifiche", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldUsername = info.getUsername();
                boolean modificheFatte = false;
                if(!username.getText().toString().equals("")){
                    info.setUsername(username.getText().toString());
                    modificheFatte=true;
                }

                if(!password.getText().toString().equals("")){
                   info.setPasswordAttuale(password.getText().toString());
                   modificheFatte = true;
                }
                if(!rispostaDomanda.getText().toString().equals("")){
                    info.setRispostaPersonale(rispostaDomanda.getText().toString());
                    modificheFatte=true;
                }

                info.setFirstAccess("false");
                if(modificheFatte){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference();
                    CoinquilinoEntity c = new CoinquilinoEntity(info.getNomeCasa(),info.getUsername(),info.getPasswordAttuale(),info.getEmail(),info.getDomandaCasa(),info.getRispostaPersonale(),info.getFirstAccess());
                    myRef.child("Coinquilino").child(oldUsername + " " + info.getNomeCasa()).setValue(null);
                    myRef.child("Coinquilino").child(c.getUsername() + " " + c.getNomeCasa()).setValue(c);
                }
            }
        });


        return builder.create();
    }
}
