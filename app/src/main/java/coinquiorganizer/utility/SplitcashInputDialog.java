package coinquiorganizer.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.SplitcashEntity;
import firstexample.helloworld.R;
import firstexample.helloworld.SplitcashActivity;

public class SplitcashInputDialog extends AppCompatDialogFragment {
    private EditText edit_somma;
    private EditText edit_motivazione;
    public static String sommaInserita = null;
    public static String motivazioneInserita = null;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.splitcash_inputdialog,null);
        builder.setView(view);
        builder.setTitle("Inserisci euro: ");
        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Inserisci", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AuthenticatedInfo loggedInfo = AuthenticatedInfo.getInstance();
                SplitcashEntity splitToAdd = new SplitcashEntity(loggedInfo.getNomeCasa(),loggedInfo.getUsername(),Integer.parseInt(edit_somma.getText().toString()),edit_motivazione.getText().toString(), LocalDate.now().toString(), LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond());
                SplitcashActivity.exampleList.clear();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("SuddivisioneSpese");

                myRef.child(splitToAdd.getNomeCasa() + " " + splitToAdd.getCoinquilinoDonatore() + " " + splitToAdd.getDataVersamento()+":"+splitToAdd.getOrario()).setValue(splitToAdd);

            }
        });
        edit_somma=view.findViewById(R.id.edit_somma);
        edit_motivazione=view.findViewById(R.id.edit_motivazione);

        return builder.create();
    }


}
