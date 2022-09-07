package firstexample.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.CronologiaSplitEntity;
import coinquiorganizer.model.SplitcashEntity;
import coinquiorganizer.utility.CronologiaAdapter;
import coinquiorganizer.utility.DialogFactory;
import coinquiorganizer.utility.SplitcashAdapter;

public class CronologiaSplitActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CronologiaAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    AuthenticatedInfo info = AuthenticatedInfo.getInstance();

    public static ArrayList<CronologiaSplitEntity> splitEntities;
    private boolean clicked=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronologia_split);

        findViewById(R.id.historyPrimoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked == false) {
                    findViewById(R.id.historyRemoveAll).setVisibility(View.VISIBLE);
                    clicked = true;
                    return;
                }
                if (clicked == true) {
                    findViewById(R.id.historyRemoveAll).setVisibility(View.INVISIBLE);
                    clicked = false;
                    return;
                }
            }
        });

        splitEntities = new ArrayList<>();
        myRef.child("CronologiaSoldi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot post : snapshot.getChildren()) {
                    String nomeCasa = post.child("nomeCasa").getValue().toString();
                    String dataInizio = post.child("dataInizio").getValue().toString();
                    String dataFine = post.child("dataFine").getValue().toString();
                    String situazioneSplit = post.child("situazioneSplit").getValue().toString();

                    if (nomeCasa.equals(info.getNomeCasa())) {
                       splitEntities.add(new CronologiaSplitEntity(nomeCasa,dataInizio,dataFine,situazioneSplit));
                       mAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buildRecyclerView();
    }

    private void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.historyRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CronologiaAdapter(splitEntities);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(new CronologiaAdapter.onItemClickListenerCrono() {
            @Override
            public void onEyeClick(int position) {
                AlertDialog dialog = DialogFactory.createDialog(CronologiaSplitActivity.this,splitEntities.get(position).getSituazioneSplit(),DialogFactory.INFO);
                dialog.show();
                return;
            }
        });
    }
}
