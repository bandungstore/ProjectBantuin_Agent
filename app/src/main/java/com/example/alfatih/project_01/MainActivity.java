package com.example.alfatih.project_01;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {
    Button buttonLogin;
    EditText usernameLogin;
    EditText passwordLogin;
    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekrut);

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
                String id_Agent = jsonObject.getString("ID_Agent");
                //AdapterUser.setId_user(id_user);

                //String name = jsonObject.getString("name");
                //AdapterUser.setName(name);
                Toast.makeText(getBaseContext(), "Berhasil Login" + tipe +" " + id_Agent, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, HomeAgent.class);
                finish();
                startActivity(intent);
               // startActivity(new Intent(getBaseContext(), Home.class));
            } else {
                Toast.makeText(getBaseContext(), "Username dan Password tidak Sesuai", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
