package sg.edu.rp.c346.id19045104.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText etWeight, etHeight;
    Button btnCal,btnReset;
    TextView date,BMI,currBMI;
    boolean Savestate = false;
    boolean reset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.editTextHeight);
        etWeight = findViewById(R.id.editTextWeight);

        date = findViewById(R.id.DateText);
        BMI = findViewById(R.id.BMI);
        currBMI = findViewById(R.id.CurrBMI);

        btnCal = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);
        date = findViewById(R.id.DateText);
        BMI = findViewById(R.id.BMI);
        Savestate = false;
        reset = false;


        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Savestate = true;
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset = true;
            }
        });

    }




    @Override
    protected void onPause() {
        super.onPause();

        Date currdate = Calendar.getInstance().getTime();

        Float ftweight = Float.parseFloat(etWeight.getText().toString());
        Float ftheight = Float.parseFloat(etHeight.getText().toString());
        Float BMI = (ftweight/ftheight) * ftheight;


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        if(BMI < 18.5){
            prefEdit.putString("currbmi","You are underweight");

        }else if(BMI >18.5 && BMI <=24.9){
            prefEdit.putString("currbmi","You are normal");
        }else if(BMI >25 && BMI <=29.9){
            prefEdit.putString("currbmi","You are overweight");
        }else if(BMI >=30){
            prefEdit.putString("currbmi","You are obese");
        }

        prefEdit.putFloat("bmi",BMI);
        prefEdit.putString("time",currdate.toString());
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Float bmi =prefs.getFloat("bmi",0);
        String time = prefs.getString("time","Time not found");
        String curbmi = prefs.getString("currbmi","BMI not found");

        date.setText(time);
        BMI.setText(bmi.toString());
        currBMI.setText(curbmi);



    }
}
