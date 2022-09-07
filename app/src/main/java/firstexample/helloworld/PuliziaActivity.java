package firstexample.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.PuliziaEntity;
import coinquiorganizer.utility.PuliziaAdapter;
import coinquiorganizer.utility.SplitcashAdapter;

public class PuliziaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<PuliziaEntity> list;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulizia);

        list = new ArrayList<>();
        AuthenticatedInfo info = AuthenticatedInfo.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        //Questa lettura da DB va cambiata in un select specifico by value
        myRef.child("PulizieCasa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot post : snapshot.getChildren()){
                    String nomeCasa = post.child("nomeCasa").getValue().toString();
                    String coinquilinoAddetto = post.child("coinquilinoAddetto").getValue().toString();
                    String postoCasa = post.child("postoCasa").getValue().toString();
                    String giornoPulizia = post.child("giornoPulizia").getValue().toString();
                    if(nomeCasa.equals(info.getNomeCasa())){
                        list.add(new PuliziaEntity(nomeCasa,postoCasa,coinquilinoAddetto,giornoPulizia));
                        Collections.sort(list,PuliziaEntity.comparator);
                        mAdapter.notifyDataSetChanged();

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //list.add(new PuliziaEntity("Ciao","ciao","ciao","Bagno"));

        buildRecyclerView();
    }
    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView3);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PuliziaAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}