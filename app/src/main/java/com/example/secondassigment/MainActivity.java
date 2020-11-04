package com.example.secondassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Wight;
    private EditText Hight;
    private EditText Res;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private String FLAG ="FLAG";
    private String NAME ="NAME";
    private String WIGHT ="WIGHT";
    private String HIGHT ="HIGHT";
    private String GENDER="GENDER";
    private Spinner spinner;
    private Button nextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextbutton=findViewById(R.id.Nextbtn);
        Name=findViewById(R.id.edName);
        Wight=findViewById(R.id.edWight);
        Hight=findViewById(R.id.edHight);
        spinner=findViewById(R.id.GenderS);
        Res=findViewById(R.id.BMIres);
        addToSpinner();
        setupSharedprf();
        checkpref();

    }
    public String BMIcal() {
        String result="";
        double res=0;
        double lenght= Double.parseDouble(Hight.getText().toString())/100;
        double wight=Double.parseDouble(Wight.getText().toString());
        res=wight/(lenght*lenght);
        if (res<18){
            return result+="Name :"+Name.getText().toString()+"\n"+"Your gender :"+spinner.getSelectedItem().toString()+"\n"+"your BMI is "+res+" that means you are underwight";
        }else if(res>18 && res<24.9){
            return result+="Name :"+Name.getText().toString()+"\n"+"Your gender :"+spinner.getSelectedItem().toString()+"\n"+"your BMI is "+res+" that means you are Normal";
        }else if(res>25 && res<29.9){
            return result+="Name :"+Name.getText().toString()+"\n"+"Your gender :"+spinner.getSelectedItem().toString()+"\n"+"your BMI is "+res+" that means you are overwight";
        }else if(res>30){
            return result+="Name :"+Name.getText().toString()+"\n"+"Your gender :"+spinner.getSelectedItem().toString()+"\n"+"your BMI is "+res+" that means you are having Obesity";
        }
        return "missing information";
    }

    public void BMIcalbtn(View view) {
        Res.setText(BMIcal());
    }



    public void checkpref(){

        boolean flag =prefs.getBoolean(FLAG,false);
        if(flag){
            String name =prefs.getString(NAME,"");
            String wight=prefs.getString(WIGHT,"");
            String hight=prefs.getString(HIGHT,"");
            String gnender=prefs.getString(GENDER,"");
            Name.setText(name);
            Wight.setText(wight);
            Hight.setText(hight);
            spinner.setSelected(Boolean.parseBoolean(gnender));
        }
    }
        public void setupSharedprf() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
        }

    public void Saveonclick(View view) {


        String name=Name.getText().toString();
        String hight =Hight.getText().toString();
        String wight = Wight.getText().toString();
        String gender =spinner.getSelectedItem().toString();
        editor.putString(NAME,name);
        editor.putString(HIGHT,hight);
        editor.putString(WIGHT,wight);
        editor.putString(GENDER,gender);
        editor.putBoolean(FLAG,true);
        editor.commit();
    }
    private void addToSpinner(){
        String[] arraySpinner = new String[] {
                "Male", "Female"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void nextpage(View view) {
        Intent intent = new Intent( this,MainActivity2.class);
        startActivity(intent);
    }
}