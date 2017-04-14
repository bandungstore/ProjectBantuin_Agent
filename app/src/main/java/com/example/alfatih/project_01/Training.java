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
import com.example.alfatih.project_01.TimeTable.TimeTable;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.R.attr.data;

public class Training extends AppCompatActivity {

    RadioGroup radiogroup;
    String jenis;
    EditText jumlahhadir;
    EditText tanggal;
    EditText tempat;
    java.text.SimpleDateFormat currentDate;
    String thisDate;
    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        setTitle("Training");

        radiogroup =  (RadioGroup) findViewById(R.id.radiogroup1);
        jumlahhadir =(EditText)findViewById(R.id.jumlahhadir);
        tanggal = (EditText)findViewById(R.id.tanggal_training);
        tempat = (EditText)findViewById(R.id.tempat);

        // Set Tanggal
        currentDate = new java.text.SimpleDateFormat("dd-MM-yyyy");
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
                        jenis = "Mengikuti Training";
                        break;
                    case R.id.radioButton2:
                        // do operations specific to this selection
                        jenis = "Mengajar di Kelas";
                        break;

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, TimeTable.class);
        finish();
        startActivity(intent);
    }


    public void selesaiOnClick(View view) {
        if( jenis.equalsIgnoreCase("")) {
            Toast.makeText(getBaseContext(), "Pilih jenis kegiatan", Toast.LENGTH_LONG).show();
        }else if( jumlahhadir.getText().toString().trim().equals("")){
            jumlahhadir.setError( "Harus di isi!" );
        }else if( tempat.getText().toString().trim().equals("")){
            tempat.setError( "Harus di isi!" );
        }else {

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE");
            Date d = new Date();
            String dayOfTheWeek = sdf.format(d);

            currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = new Date();
            String thisDate = currentDate.format(todayDate);

            String laporan = data.insertTraining(wp.getId(), wp.getNama(), jenis, jumlahhadir.getText().toString(), tempat.getText().toString(), thisDate, dayOfTheWeek);
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
