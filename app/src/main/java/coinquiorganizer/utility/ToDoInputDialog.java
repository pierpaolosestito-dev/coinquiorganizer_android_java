package coinquiorganizer.utility;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDialogFragment;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import at.favre.lib.crypto.bcrypt.BCrypt;
import coinquiorganizer.fakeservlet.AuthenticatedInfo;

import coinquiorganizer.model.ToDoEntity;
import firstexample.helloworld.R;
import firstexample.helloworld.ToDoActivity;


public class ToDoInputDialog extends AppCompatDialogFragment {
    private EditText titoloAttivita,descrizioneAttivita;
    private CheckBox checkBoxImportant;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.todo_inputdialog,null);
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
                ToDoEntity firstToDoEntity = new ToDoEntity(loggedInfo.getNomeCasa(),titoloAttivita.getText().toString(),descrizioneAttivita.getText().toString(),checkBoxImportant.isChecked(),false, LocalDate.now().toString()+":" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond());
                ToDoActivity.toDoEntities.clear();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                myRef.child("ListaAttivita").child(firstToDoEntity.getNomeCasa() + " " + firstToDoEntity.getNomeAttivita() + " " + firstToDoEntity.getDataOrario()).setValue(firstToDoEntity);



            }
        });
       titoloAttivita = view.findViewById(R.id.edit_titoloAttivita);
       descrizioneAttivita = view.findViewById(R.id.edit_descrizioneAttivita);
       checkBoxImportant = view.findViewById(R.id.importantCheckBox);

        return builder.create();


    }
}
