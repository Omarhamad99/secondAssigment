package com.example.secondassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity2 extends MainActivity {
    private Spinner hspin;
    private Spinner mspin;
    private Spinner sspin;
    private int sec=0;
    private int p=0;
    private boolean isruning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        checkSavedInstance(savedInstanceState);
        hspin=findViewById(R.id.HSpin);
        mspin=findViewById(R.id.MSpin);
        sspin=findViewById(R.id.SSpin);
        hadd();
        sadd();
        madd();

    }

    private void checkSavedInstance(Bundle savedInstanceState){
        if(savedInstanceState !=null){
            sec = savedInstanceState.getInt("sec");
            isruning = savedInstanceState.getBoolean("isrunning");
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",sec);
        outState.putBoolean("running",isruning);
    }


    private void runTimer(){

        final TextView txtTimer = (TextView) findViewById(R.id.txtTimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {

            @Override
            public void run() {
                int hours = sec/3600;
                int minutes = sec % 3600 /60;
                int snds = sec % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, snds);
                txtTimer.setText(time);


                if(isruning && sec!=0){
                    --sec;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    public void start(View view) {
//        Toast.makeText(this, "sssss", Toast.LENGTH_LONG).show();
        sec=Integer.parseInt(hspin.getSelectedItem().toString())*3600+Integer.parseInt(mspin.getSelectedItem().toString())*60
                +Integer.parseInt(sspin.getSelectedItem().toString());

        Toast.makeText(this, sec+"", Toast.LENGTH_LONG).show();
        isruning=true;
        runTimer();
    }
    public void pause(View view) {
        p=sec;
        isruning=false;

    }

    public void stop(View view) {
        isruning=false;
        sec=0;
    }

    private void hadd(){
        String[] arraySpinner = new String[24];
        for (int i =0; i<arraySpinner.length;i++){
            arraySpinner[i]=i+"";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hspin.setAdapter(adapter);
    }
    private void madd(){
        String[] arraySpinner = new String[60];
        for (int i =0; i<arraySpinner.length;i++){
            arraySpinner[i]=i+"";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspin.setAdapter(adapter);
    }
    private void sadd(){
        String[] arraySpinner = new String[60];
        for (int i =0; i<arraySpinner.length;i++){
            arraySpinner[i]=i+"";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sspin.setAdapter(adapter);
    }




}