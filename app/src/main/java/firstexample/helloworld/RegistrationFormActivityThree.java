package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import coinquiorganizer.fakeservlet.RegistrationInfo;
import coinquiorganizer.utility.DialogFactory;

public class RegistrationFormActivityThree extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerDay;
    public static CheckBox pulizieInsieme;
    private boolean pulizieInsiemeOn = true;
    private String giornoScelto = null;
    private RadioGroup radioGroupRooms;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form_three);
        pulizieInsieme = findViewById(R.id.formThree_togetherCheck);
        radioGroupRooms = findViewById(R.id.radioGroupRooms);

        pulizieInsieme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(pulizieInsieme.isChecked()){
                    for(int i=0;i<radioGroupRooms.getChildCount();i++)
                        radioGroupRooms.getChildAt(i).setEnabled(false);
                    return;
                }
                if(!pulizieInsieme.isChecked()){
                    for(int i=0;i< radioGroupRooms.getChildCount();i++)
                        radioGroupRooms.getChildAt(i).setEnabled(true);
                    return;
                }
            }
        });

        findViewById(R.id.goToFinallyStep).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            RegistrationInfo info = RegistrationInfo.getInstance();
                if(!pulizieInsieme.isChecked()){
                    int selectedIndex = radioGroupRooms.getCheckedRadioButtonId();
                    RadioButton button = (RadioButton) findViewById(selectedIndex);
                    if(button == null){
                        DialogFactory.createDialog(RegistrationFormActivityThree.this,"Errore",DialogFactory.WARNING).show();
                        return;
                    }
                    Toast.makeText(RegistrationFormActivityThree.this,giornoScelto,Toast.LENGTH_LONG).show();
                    pulizieInsiemeOn = false;
                    info.setThirdForm(pulizieInsieme.isChecked(),giornoScelto,button.getText().toString());

                }else info.setThirdForm(pulizieInsieme.isChecked(),giornoScelto,"Tutti");

                startActivity(new Intent(getApplicationContext(), RegistrationFormActivityFour.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);


            }



        });

        spinnerDay = findViewById(R.id.spinnerDay);
        spinnerDay.setOnItemSelectedListener(this);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.days_list,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinnerDay.setAdapter(adapter);



    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.spinnerDay){
            this.giornoScelto = parent.getItemAtPosition(position).toString();
            Log.i("Ciao","ABCDE " + giornoScelto);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}