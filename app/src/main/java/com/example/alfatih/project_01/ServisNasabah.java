package com.example.alfatih.project_01;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServisNasabah extends AppCompatActivity {

    Spinner spinner;

    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;
    List<String> list = new ArrayList<String>();
    JSONArray arrayBiodata;
    String[] calon_nasabah;
    String[] tanggal_lahir;
    String[] telp;
    String[] pekerjaan;
    String[] sumber_nama;
    String[] alamat;

    EditText Tanggal;

    TextView Nama;
    TextView Tanggal_Lahir;
    TextView Telepon;
    TextView Pekerjaan;
    TextView Sumber_Nama;
    TextView Alamat;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servis_nasabah);
        setTitle("Servis Nasabah");



        Tanggal = (EditText)findViewById(R.id.kegiatan);

        Nama = (TextView) findViewById(R.id.nama);
        Tanggal_Lahir = (TextView) findViewById(R.id.tanggal_lahir);
        Telepon = (TextView) findViewById(R.id.telepon);
        Pekerjaan= (TextView) findViewById(R.id.pekerjaan);
        Sumber_Nama = (TextView) findViewById(R.id.sumber);
        Alamat = (TextView) findViewById(R.id.alamat);
        openDB();
        wp = myDB.getAgent(String.valueOf(1));

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MM-yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        Tanggal.setText(thisDate);
        spinner = (Spinner) findViewById(R.id.spinner);
        try {

            arrayBiodata = new JSONArray(data.getAllDataProspek(wp.getId()));
            calon_nasabah = new String[arrayBiodata.length()];
            tanggal_lahir = new String[arrayBiodata.length()];
            telp = new String[arrayBiodata.length()];
            pekerjaan = new String[arrayBiodata.length()];
            sumber_nama = new String[arrayBiodata.length()];
            alamat = new String[arrayBiodata.length()];

            for (int i = 0; i < arrayBiodata.length(); i++) {
                JSONObject jsonChildNode = arrayBiodata.getJSONObject(i);
                calon_nasabah[i] = jsonChildNode.optString("Calon_Nasabah");
                tanggal_lahir[i] = jsonChildNode.optString("Tanggal_Lahir");
                telp[i] = jsonChildNode.optString("Telp");
                pekerjaan[i] = tanggal_lahir[i] = jsonChildNode.optString("Pekerjaan");
                sumber_nama[i] = jsonChildNode.optString("Sumber_Nama");
                alamat[i] = jsonChildNode.optString("Alamat");

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

                Nama.setText(calon_nasabah[i]);
                Tanggal_Lahir.setText(tanggal_lahir[i]);
                Telepon.setText(telp[i]);
                Pekerjaan.setText(pekerjaan[i]);
                Sumber_Nama.setText(sumber_nama[i]);
                Alamat.setText(alamat[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeAgent.class);
        finish();
        startActivity(intent);
    }

    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void selesaiOnClick(View view) {
        if( spinner.getSelectedItem().toString().trim().equals("")) {
            Toast.makeText(getBaseContext(), "Nasabah kosong", Toast.LENGTH_LONG).show();
        }else {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE");
            Date d = new Date();
            String dayOfTheWeek = sdf.format(d);

            SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = new Date();
            String thisDate = currentDate.format(todayDate);

            String laporan = data.insertServis(wp.getId(), wp.getNama(), Nama.getText().toString(), thisDate, dayOfTheWeek);
            Toast.makeText(getBaseContext(), laporan, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, HomeAgent.class);
            finish();
            startActivity(intent);
        }
    }
}
