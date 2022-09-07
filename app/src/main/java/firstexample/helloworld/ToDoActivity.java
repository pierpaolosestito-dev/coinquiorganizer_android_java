package firstexample.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.SplitcashEntity;
import coinquiorganizer.model.ToDoEntity;
import coinquiorganizer.utility.DialogFactory;
import coinquiorganizer.utility.SplitcashAdapter;
import coinquiorganizer.utility.ToDoAdapter;
import coinquiorganizer.utility.ToDoInputDialog;

public class ToDoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ToDoAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static ArrayList<ToDoEntity> toDoEntities;

    HashMap<String,Object> map = new HashMap<>();

    private boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        AuthenticatedInfo info = AuthenticatedInfo.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        toDoEntities = new ArrayList<>();
        myRef.child("ListaAttivita").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot post : snapshot.getChildren()) {
                    String nomeCasa = post.child("nomeCasa").getValue().toString();
                    String descrizioneAttivita = post.child("descrizioneAttivita").getValue().toString();
                    String nomeAttivita = post.child("nomeAttivita").getValue().toString();
                    boolean importante = Boolean.parseBoolean(post.child("importante").getValue().toString());
                    boolean segnato = Boolean.parseBoolean(post.child("segnato").getValue().toString());
                    String orario = post.child("dataOrario").getValue().toString();

                    if(nomeCasa.equals(info.getNomeCasa())){
                        toDoEntities.add(new ToDoEntity(nomeCasa,nomeAttivita,descrizioneAttivita,importante,segnato,orario));
                        Collections.sort(toDoEntities,ToDoEntity.comparator);
                        mAdapter.notifyDataSetChanged();

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        findViewById(R.id.toDoPrimoBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(clicked==false){
                    findViewById(R.id.toDoRemoveAll).setVisibility(View.VISIBLE);
                    findViewById(R.id.toDoAdd).setVisibility(View.VISIBLE);
                    clicked = true;
                    return;
                }
                if(clicked==true){
                    findViewById(R.id.toDoRemoveAll).setVisibility(View.INVISIBLE);
                    findViewById(R.id.toDoAdd).setVisibility(View.INVISIBLE);
                    clicked=false;
                    return;
                }
            }
        });
        findViewById(R.id.toDoRemoveAll).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(ToDoEntity it : toDoEntities){
                    if(it.isSegnato()){
                        map.put(it.getNomeCasa() + " " + it.getNomeAttivita() + " " + it.getDataOrario(),null);
                        toDoEntities.remove(it);
                        mAdapter.notifyDataSetChanged();
                    }


                }
                toDoEntities.clear();
                myRef.child("ListaAttivita").updateChildren(map);
            }
        });
        findViewById(R.id.toDoAdd).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ToDoInputDialog dialog = new ToDoInputDialog();
                dialog.show(getSupportFragmentManager(),"EXAMPLE");
            }
        });



        buildRecyclerView();
    }

    private void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.toDoRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ToDoAdapter(toDoEntities);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(new ToDoAdapter.OnItemClickListener() {
            @Override
            public void onEyeClick(int position) {
                AlertDialog dialog = DialogFactory.createDialog(ToDoActivity.this,toDoEntities.get(position).getDescrizioneAttivita(),DialogFactory.INFO);
                dialog.show();
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}