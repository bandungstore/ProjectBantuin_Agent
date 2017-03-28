package com.example.alfatih.project_01;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AgentMonitoring extends AppCompatActivity {

    RadioGroup radiogroup;
    String monitoring = "";
    EditText tanggal;
    EditText jumlah_orang;
    SimpleDateFormat currentDate;
    String thisDate;
    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_monitoring);
        setTitle("Monitoring");

        radiogroup =  (RadioGroup) findViewById(R.id.radiogroup1);
        tanggal = (EditText)findViewById(R.id.tanggal);
        jumlah_orang = (EditText)findViewById(R.id.jumlah_orang);

        // Set Tanggal
        currentDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        thisDate = currentDate.format(todayDate);
        tanggal.setText(thisDate);

        openDB();
        wp = myDB.getAgent(String.valueOf(1));

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.radioButton:
                        // do operations specific to this selection
                        monitoring = "Project 30 Leader";
                        break;
                    case R.id.radioButton2:
                        // do operations specific to this selection
                        monitoring = "Project 30 Agent";
                        break;
                    case R.id.radioButton3:
                        // do operations specific to this selection
                        monitoring = "Follow Up";
                        break;
                    case R.id.radioButton4:
                        // do operations specific to this selection
                        monitoring = "Peti Es";
                        break;

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeAgent.class);
        finish();
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void selesaiOnClick(View view) {
        if( monitoring.equalsIgnoreCase("")) {
            Toast.makeText(getBaseContext(), "Pilih Monitoring", Toast.LENGTH_LONG).show();
        }else if( jumlah_orang.getText().toString().trim().equals("")){
                jumlah_orang.setError( "Harus di isi!" );
        }else {

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE");
            Date d = new Date();
            String dayOfTheWeek = sdf.format(d);

            android.icu.text.SimpleDateFormat currentDate = new android.icu.text.SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = new Date();
            String thisDate = currentDate.format(todayDate);

            String laporan = data.insertMonitoring(wp.getId(), wp.getNama(), monitoring, jumlah_orang.getText().toString(), thisDate, dayOfTheWeek);
            Toast.makeText(getBaseContext(), laporan, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, HomeAgent.class);
            finish();
            startActivity(intent);
        }

    }

    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();
    }

}
