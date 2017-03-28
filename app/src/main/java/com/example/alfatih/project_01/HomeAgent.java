package com.example.alfatih.project_01;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class HomeAgent extends AppCompatActivity {
    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;
    String namaAgent = null;
    int usiaAgent = 0;
    String statusAgent = null;
    int anakAgent = 0;
    String alamatAgent = null;
    String pekerjaanAgent = null;
    String teleponAgent = null;
    String sumbernamaAgent = null;
    int pointAgent = 0;

    TextView ID_Agent;
    TextView Nama_Lengkap;
    TextView Pekerjaan;
    TextView Point;
    TextView Tanggal;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_agent);
        setTitle("Beranda");

        //Get ID
        openDB();
        wp = myDB.getAgent(String.valueOf(1));
        getDataByID(wp.getStats());
        Toast.makeText(getBaseContext(), "Selamat Datang "+ wp.getNama(), Toast.LENGTH_LONG).show();

        // Set Tanggal
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        ID_Agent = (TextView)findViewById(R.id.ID_agent);
        Nama_Lengkap = (TextView)findViewById(R.id.nama_lengkap);
        Pekerjaan = (TextView)findViewById(R.id.pekerjaan);
        Point = (TextView)findViewById(R.id.point);
        Tanggal = (TextView)findViewById(R.id.kegiatan);

        ID_Agent.setText(String.valueOf(wp.getStats()));
        Nama_Lengkap.setText(namaAgent);
        Pekerjaan.setText(pekerjaanAgent);
        Point.setText(String.valueOf("Point :" + pointAgent));
        Tanggal.setText(thisDate);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);



    }

    public void getDataByID(int id) {


        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(data.getAgentById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);

                namaAgent = jsonChildNode.optString("Nama_Agent");
                usiaAgent = jsonChildNode.optInt("Usia_Agent");
                statusAgent = jsonChildNode.optString("Nama_Agent");
                anakAgent = jsonChildNode.optInt("Anak_Agent");
                alamatAgent = jsonChildNode.optString("Alamat_Agent");
                pekerjaanAgent = jsonChildNode.optString("Pekerjaan_Agent");
                teleponAgent = jsonChildNode.optString("telepon_Agent");
                sumbernamaAgent = jsonChildNode.optString("SumberNama_Agent");
                pointAgent = jsonChildNode.optInt("Point");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);



        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.rekrut:
                intent = new Intent(this, Rekrut.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.prospek:
                intent = new Intent(this, Prospek.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.jfk:
                intent = new Intent(this, JFK.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.followup:
                intent = new Intent(this, FollowUp.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.servisnasabah:
                intent = new Intent(this, ServisNasabah.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.training:
                intent = new Intent(this, Training.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.meeting:
                intent = new Intent(this, Meeting.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.approaching:
                intent = new Intent(this, Approaching.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.monitoring:
                intent = new Intent(this, AgentMonitoring.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.closing:
                intent = new Intent(this, Closing.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.aaji:
                intent = new Intent(this, AAJI.class);
                finish();
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
