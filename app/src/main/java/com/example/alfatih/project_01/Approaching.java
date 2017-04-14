package com.example.alfatih.project_01;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;
import com.example.alfatih.project_01.TimeTable.TimeTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Approaching extends AppCompatActivity {

    Spinner spinner;

    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;
    List<String> list = new ArrayList<String>();
    JSONArray arrayBiodata;
    String[] calon_nasabah;
    RadioGroup radiogroup;
    String nama;
    String metode = "";

    EditText Tanggal;

    java.text.SimpleDateFormat currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approaching);
        setTitle("Approaching");

        Tanggal = (EditText)findViewById(R.id.tanggal);
        radiogroup =  (RadioGroup) findViewById(R.id.radiogroup1);
        openDB();
        wp = myDB.getAgent(String.valueOf(1));

        currentDate = new java.text.SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        Tanggal.setText(thisDate);
        spinner = (Spinner) findViewById(R.id.spinner);
        try {

            arrayBiodata = new JSONArray(data.getAllDataProspek(wp.getId()));
            calon_nasabah = new String[arrayBiodata.length()];


            for (int i = 0; i < arrayBiodata.length(); i++) {
                JSONObject jsonChildNode = arrayBiodata.getJSONObject(i);
                calon_nasabah[i] = jsonChildNode.optString("Calon_Nasabah");


                list.add(calon_nasabah[i]);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                nama = calon_nasabah[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.radioButton:
                        // do operations specific to this selection
                        metode = "Melalui Telepon";
                        break;
                    case R.id.radioButton2:
                        // do operations specific to this selection
                        metode = "Ketemu Langsung";
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

    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();
    }


    public void selesaiOnClick(View view) {
        if( spinner.getSelectedItem().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Calon nasabah kosong", Toast.LENGTH_LONG).show();
        }else if( metode.equalsIgnoreCase("")) {
            Toast.makeText(getBaseContext(), "Pilih metode", Toast.LENGTH_LONG).show();

        }else {

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE");
            Date d = new Date();
            String dayOfTheWeek = sdf.format(d);

            currentDate = new java.text.SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = new Date();
            String thisDate = currentDate.format(todayDate);

            String laporan = data.insertApproaching(wp.getId(), wp.getNama(), nama, metode, thisDate, dayOfTheWeek);
            Toast.makeText(getBaseContext(), laporan, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, HomeAgent.class);
            finish();
            startActivity(intent);
        }

    }

}
