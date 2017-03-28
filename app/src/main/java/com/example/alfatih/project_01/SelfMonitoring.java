package com.example.alfatih.project_01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelfMonitoring extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_monitoring);
        setTitle("Self Monitoring");
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeAgent.class);
        finish();
        startActivity(intent);
    }
}
