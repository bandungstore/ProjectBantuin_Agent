package com.example.alfatih.project_01;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Rekrut extends AppCompatActivity {

    EditText calonagent;
    EditText telp;
    RadioGroup radiogroup1;
    EditText datePicker;
    RadioGroup radiogroup2;
    EditText jumlahanak;
    EditText sumbernama;
    EditText pekerjaan;
    EditText alamat;
    EditText keterangan;
    String Kelamin = "";
    String Status  = "";
    Data data = new Data();
    String thisDate;
    private DatePickerDialog fromDatePickerDialog;
    SimpleDateFormat currentDate;

    DBadapter myDB;
    DBadapter.Agent wp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekrut);
        setTitle("Rekrut");

        calonagent = (EditText)findViewById(R.id.calonagent);
        telp = (EditText)findViewById(R.id.telp);
        radiogroup1 =  (RadioGroup) findViewById(R.id.radiogroup1);
        datePicker = (EditText)findViewById(R.id.datePicker);
        datePicker.setInputType(InputType.TYPE_NULL);

        radiogroup2 =  (RadioGroup) findViewById(R.id.radiogroup2);
        jumlahanak = (EditText)findViewById(R.id.jumlahanak);
        sumbernama = (EditText)findViewById(R.id.sumbernama);
        pekerjaan = (EditText)findViewById(R.id.pekerjaan);
        alamat = (EditText)findViewById(R.id.alamat);
        keterangan = (EditText)findViewById(R.id.keterangan);


        // Set Tanggal
        currentDate = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();
        thisDate = currentDate.format(todayDate);
        datePicker.setText(thisDate);

        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){
                    case R.id.radioButton:
                        // do operations specific to this selection
                        Kelamin = "Laki-Laki";
                        break;
                    case R.id.radioButton2:
                        // do operations specific to this selection
                        Kelamin = "Perempuan";
                        break;

                }
            }
        });

        radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int j) {
                switch(j){
                    case R.id.radioButton3:
                        // do operations specific to this selection
                        Status = "Lajang";
                        break;
                    case R.id.radioButton4:
                        // do operations specific to this selection
                        Status = "Menikah";
                        break;

                }
            }
        });

        setDateTimeField();
    }

    public void selesaiOnClick(View view) {
        openDB();
        wp = myDB.getAgent(String.valueOf(1));

        if( calonagent.getText().toString().trim().equals("")){
            calonagent.setError( "Harus di isi!" );
        }else if( telp.getText().toString().trim().equals("")){
            telp.setError( "Harus di isi!" );
        }else if( Kelamin.equalsIgnoreCase("")){
            Toast.makeText(Rekrut.this,"Jenis Kelamin Belum Di isi", Toast.LENGTH_SHORT).show();
        }else if( Status.equalsIgnoreCase("")){
           Toast.makeText(Rekrut.this, "Status Belum Di isi", Toast.LENGTH_SHORT).show();
        }else
        if( jumlahanak.getText().toString().trim().equals("")){
            jumlahanak.setError( "Harus di isi!" );
        }else
        if( sumbernama.getText().toString().trim().equals("")){
            sumbernama.setError( "Harus di isi!" );
        }else
        if( pekerjaan.getText().toString().trim().equals("")){
            pekerjaan.setError( "Harus di isi!" );
        }else
        if( alamat.getText().toString().trim().equals("")){
            alamat.setError( "Harus di isi!" );
        }else
        if( keterangan.getText().toString().trim().equals("")){
            keterangan.setError( "Harus di isi!" );
        }else {

            String Calonagent = calonagent.getText().toString();
            int Telp = Integer.parseInt(telp.getText().toString());
            String DatePicker = datePicker.getText().toString();
            int Jumlahanak = Integer.parseInt(jumlahanak.getText().toString());
            String Sumbernama = sumbernama.getText().toString();
            String Pekerjaan = pekerjaan.getText().toString();
            String Alamat = alamat.getText().toString();
            String Keterangan = keterangan.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            Date d = new Date();
            String dayOfTheWeek = sdf.format(d);

            String laporan = data.insertRekrut(wp.getStats(), wp.getNama(), Calonagent, Telp, Kelamin, DatePicker, Status, Jumlahanak, Sumbernama, Pekerjaan, Alamat, Keterangan, dayOfTheWeek,thisDate);
            Toast.makeText(Rekrut.this, laporan, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, HomeAgent.class);
            finish();
            startActivity(intent);
        }
    }

    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();


    }

    private void setDateTimeField() {

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromDatePickerDialog.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                datePicker.setText(currentDate.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeAgent.class);
        finish();
        startActivity(intent);
    }
}

