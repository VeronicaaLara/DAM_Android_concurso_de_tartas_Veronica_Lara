package es.escuelaestech.chopo.concursotartas;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name, lastname, age, competition;
    private TextInputLayout layoutName, layoutLastname, layoutAge, layoutCompetition;
    private Button sign;
    private CheckBox veteran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializeUI();

        veteran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkParticipation();
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void inicializeUI(){
        name = findViewById(R.id.name);
        lastname = findViewById(R.id.lastname);
        age = findViewById(R.id.age);
        layoutName = findViewById(R.id.layout_name);
        layoutLastname = findViewById(R.id.layout_lastname);
        layoutAge = findViewById(R.id.layout_age);
        sign = findViewById(R.id.sign);
        veteran = findViewById(R.id.veteran);
        competition = findViewById(R.id.contest);
        layoutCompetition = findViewById(R.id.layout_contest);
        layoutCompetition.setVisibility(View.GONE);
    }

    private void submitForm(){
        if( validateName() && validateLastname() && validateAge() && validateCompetition())
            Toast.makeText(getApplicationContext(),
                    "Inscripción realizada correctamente.", Toast.LENGTH_SHORT).show();

    }

    private boolean validateName(){
        String naming = name.getText().toString().trim();
        if (naming.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.name_error, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            layoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateLastname(){
        String naming = lastname.getText().toString().trim();
        if (naming.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.lastname_error, Toast.LENGTH_SHORT).show();

            return false;
        } else {
            layoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAge(){
        String naming = age.getText().toString().trim();

        if (naming.isEmpty()) {
            Toast.makeText(getApplicationContext(), getString(R.string.age_format_error), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if(Integer.parseInt(naming) < 18){
                Toast.makeText(getApplicationContext(), getString(R.string.age_error), Toast.LENGTH_SHORT).show();
                return false;
            }else{
                layoutAge.setErrorEnabled(false);
            }
        }

        return true;
    }

    private boolean validateCompetition(){
        String naming = competition.getText().toString().trim();

        if((naming.isEmpty() && veteran.isChecked())){
            Toast.makeText(getApplicationContext(), getString(R.string.contest_error), Toast.LENGTH_SHORT).show();
            return false;
        }else{
            layoutCompetition.setErrorEnabled(false);
        }

        return true;
    }

    private void checkParticipation(){
        if(veteran.isChecked()){
            layoutCompetition.setVisibility(View.VISIBLE);
        }else{
            layoutCompetition.setVisibility(View.GONE);
        }
    }
}
