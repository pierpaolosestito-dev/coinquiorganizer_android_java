package firstexample.helloworld;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.CoinquilinoEntity;
import coinquiorganizer.model.CronologiaSplitEntity;
import coinquiorganizer.model.PuliziaEntity;
import coinquiorganizer.model.SplitcashEntity;
import coinquiorganizer.model.utility.Conteggio;
import coinquiorganizer.utility.DialogFactory;
import coinquiorganizer.utility.SplitcashAdapter;
import coinquiorganizer.utility.SplitcashInputDialog;

public class SplitcashActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private SplitcashAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    int i=0;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private String sommaDaAggiungere = null;
    public static ArrayList<SplitcashEntity> exampleList;

    private boolean clicked = false;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitcash);
        AuthenticatedInfo info = AuthenticatedInfo.getInstance();
        findViewById(R.id.primoBtn).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(clicked == false){
                    findViewById(R.id.btnAggiungi).setVisibility(View.VISIBLE);
                    findViewById(R.id.btnCalculator).setVisibility(View.VISIBLE);
                    findViewById(R.id.historySplit).setVisibility(View.VISIBLE);
                    clicked = true;
                    return;
                }if(clicked == true){
                    findViewById(R.id.btnAggiungi).setVisibility(View.INVISIBLE);
                    findViewById(R.id.btnCalculator).setVisibility(View.INVISIBLE);
                    findViewById(R.id.historySplit).setVisibility(View.INVISIBLE);
                    clicked = false;
                    return;
                }
            }
        });

        findViewById(R.id.btnAggiungi).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SplitcashInputDialog dialog = new SplitcashInputDialog();
                dialog.show(getSupportFragmentManager(),"example dialog");

                Log.i("PROVA","PROVA " + SplitcashInputDialog.motivazioneInserita + " " + SplitcashInputDialog.sommaInserita);
            }
        });

        findViewById(R.id.btnCalculator).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(exampleList.size()==0){
                    AlertDialog dialog = DialogFactory.createDialog(SplitcashActivity.this,"Non ci sono elementi nella lista!",DialogFactory.WARNING);
                    dialog.show();
                    return;
                }
                int sommaTotale = 0;
                AuthenticatedInfo info = AuthenticatedInfo.getInstance();
                for(SplitcashEntity it : exampleList){
                    sommaTotale += it.getSomma();
                }
                int quantoDeveMettereOgniCoinq = sommaTotale / info.getNumeroCoinquilini();
                //DialogFactory.createDialog(SplitcashActivity.this,"Somma totale: " + sommaTotale + System.lineSeparator() + "Quantita ogni coinquilino: " + quantoDeveMettereOgniCoinq + System.lineSeparator() + "Numero coinquilini: " + info.getNumeroCoinquilini(),DialogFactory.INFO).show();

                ArrayList<Conteggio> conteggiOttenuti = new ArrayList<Conteggio>();
                for(int i=0;i<exampleList.size();i++){
                    String coinquilinoDonatore = exampleList.get(i).getCoinquilinoDonatore();
                    int sommaCoinq = 0;
                    for(int j=0;j<exampleList.size();j++) {
                        if (!coinquilinoDonatore.equals("*")) {
                            if (exampleList.get(j).getCoinquilinoDonatore().equals(coinquilinoDonatore)) {
                                sommaCoinq += exampleList.get(j).getSomma();
                                exampleList.get(j).setCoinquilinoDonatore("*");

                            }
                        }
                    }
                    if(!coinquilinoDonatore.equals("*")) {
                        Conteggio c = new Conteggio(coinquilinoDonatore,sommaCoinq);
                        conteggiOttenuti.add(c);
                    }
                }

                StringBuilder builder = new StringBuilder();
                for(Conteggio c : conteggiOttenuti){
                    int somma = quantoDeveMettereOgniCoinq - c.getSomma();
                    if(somma<0){
                        builder.append(c.getCoinquilino() + " si aspetta " + somma*-1 + "€" + System.lineSeparator());
                    }else{
                        builder.append(c.getCoinquilino() + " deve " + somma + "€" + System.lineSeparator());
                    }
                }

                DialogFactory.createDialog(SplitcashActivity.this,builder.toString(),DialogFactory.SUCCESS).show();
                CronologiaSplitEntity toAddtoCronology = new CronologiaSplitEntity(info.getNomeCasa(),exampleList.get(0).getDataVersamento(),exampleList.get(exampleList.size()-1).getDataVersamento(),builder.toString());
                myRef.child("CronologiaSoldi").child(toAddtoCronology.getNomeCasa() + " " + toAddtoCronology.getDataInizio() + " " + toAddtoCronology.getDataFine()).setValue(toAddtoCronology);

            }
        });

        findViewById(R.id.historySplit).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CronologiaSplitActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
                Toast.makeText(SplitcashActivity.this,"Hai premuto il bottone di rimuovi",Toast.LENGTH_LONG).show();
            }
        });
        exampleList = new ArrayList<>();



            //Questa lettura da DB va cambiata in un select specifico by value
            myRef.child("SuddivisioneSpese").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot post : snapshot.getChildren()) {
                        String nomeCasa = post.child("nomeCasa").getValue().toString();
                        String coinquilinoDonatore = post.child("coinquilinoDonatore").getValue().toString();
                        Integer somma = Integer.parseInt(post.child("somma").getValue().toString());
                        String motivazione = post.child("motivazione").getValue().toString();
                        String dataVersamento = post.child("dataVersamento").getValue().toString();
                        String orario = post.child("orario").getValue().toString();

                        if (nomeCasa.equals(info.getNomeCasa())) {
                            exampleList.add(new SplitcashEntity(nomeCasa, coinquilinoDonatore, somma, motivazione, dataVersamento,orario));
                            mAdapter.notifyDataSetChanged();

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        //exampleList.add(new SplitcashEntity("CasaParanoia307","Pierparanoia", 12,"Spesa al Vivo", LocalDate.now().toString()));
        buildRecyclerView();
    }

    private void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SplitcashAdapter(exampleList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(new SplitcashAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                //Questa idea è da usare nello storico dei splitcash
                //AlertDialog dialog = DialogFactory.createDialog(SplitcashActivity.this,exampleList.get(position).getNomeCasa() + " " + exampleList.get(position).getUsername(), DialogFactory.INFO);
                //dialog.show();
                //mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onDeleteClick(int position) {
                SplitcashEntity splitToRemove = exampleList.get(position);
                myRef.child("SuddivisioneSpese").child(splitToRemove.getNomeCasa() + " " + splitToRemove.getCoinquilinoDonatore() + " " + splitToRemove.getDataVersamento()+":"+splitToRemove.getOrario()).setValue(null);

                exampleList.remove(position);

                mAdapter.notifyDataSetChanged();

            }
        });
    }

}