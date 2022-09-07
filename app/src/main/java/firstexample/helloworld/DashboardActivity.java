package firstexample.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import coinquiorganizer.fakeservlet.AuthenticatedInfo;
import coinquiorganizer.model.CredenzialiEntity;
import coinquiorganizer.utility.FirstAccessInputDialog;

public class DashboardActivity extends AppCompatActivity {

    AuthenticatedInfo info = AuthenticatedInfo.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(info.getFirstAccess().equals("true")){
                FirstAccessInputDialog inputDialog = new FirstAccessInputDialog();
                inputDialog.show(getSupportFragmentManager(),"Esempio");

        }
        ArrayList<CredenzialiEntity> list = LoginActivity.list;
        for (int i=0;i<list.size();i++){
            Log.i("PROVA","BBB " + list.get(i).getNomeCasa() + list.get(i).getUsername());
        }

        findViewById(R.id.salvadanaioButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SplitcashActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        findViewById(R.id.pulizieButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PuliziaActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        findViewById(R.id.toDoButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ToDoActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });

        findViewById(R.id.profileButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EditProfileActivity.class));
                overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);
            }
        });
    }
}