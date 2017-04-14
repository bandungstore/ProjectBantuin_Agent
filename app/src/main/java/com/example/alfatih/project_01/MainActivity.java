package com.example.alfatih.project_01;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {
    Button buttonLogin;
    EditText usernameLogin;
    EditText passwordLogin;
    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;
    String namaAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        openDB();
        wp = myDB.getAgent(String.valueOf(1));

        //jika belum login
        if (wp.getTipe().equals("tipe")  ){
            Toast.makeText(getBaseContext(), "Silahkan login", Toast.LENGTH_LONG).show();
        }else{
            //jika login user
            if(wp.getTipe().equals("user")) {
                Intent intent = new Intent(this, HomeAgent.class);
                finish();
                startActivity(intent);
            }else{//jika login admin
                Intent intent = new Intent(this, Admin.class);
                finish();
                Toast.makeText(getBaseContext(), "Anda Login Sebagai Leader", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        usernameLogin = (EditText)findViewById(R.id.usernameLogin);
        passwordLogin = (EditText)findViewById(R.id.passwordLogin);
    }

    public void loginOnClick(View view) {

        String signinResult = data.login(usernameLogin.getText().toString(), passwordLogin.getText().toString());

        try {
            JSONObject jsonObject = new JSONObject(signinResult);

            // Log.v(tag, "json object length : " +
            // jsonObject.length());

            if (jsonObject.length() > 0) {

                String tipe = jsonObject.getString("Tipe_Login");
                int id_Agent = jsonObject.getInt("ID_Agent");
                //AdapterUser.setId_user(id_user);

                //String name = jsonObject.getString("name");
                //AdapterUser.setName(name);
                Toast.makeText(getBaseContext(), "Berhasil Login", Toast.LENGTH_LONG).show();
                getDataByID(id_Agent);
                myDB.updateRow(1,namaAgent,id_Agent,tipe,0);
                //jika login user
                if(tipe.equals("user")) {
                    Intent intent = new Intent(this, HomeAgent.class);
                    finish();
                    startActivity(intent);
                }else{//jika login admin
                    Intent intent = new Intent(this, Admin.class);
                    finish();
                    Toast.makeText(getBaseContext(), "Anda Login Sebagai Leader", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
               // startActivity(new Intent(getBaseContext(), Home.class));
            } else {
                Toast.makeText(getBaseContext(), "Username dan Password anda salah", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();
    }

    public void getDataByID(int id) {


        JSONArray arrayPersonal;

        try {

            arrayPersonal = new JSONArray(data.getAgentById(id));

            for (int i = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);

                namaAgent = jsonChildNode.optString("Nama_Agent");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}