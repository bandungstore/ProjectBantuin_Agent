package com.example.alfatih.project_01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;
import com.example.alfatih.project_01.TimeTable.TimeTable;

import java.util.ArrayList;
import java.util.List;

public class Admin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;

    private ListView lvProduct;
    private ProductListAdapterNotif adapter;
    private List<Product> mProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setTitle("Timeline");
        openDB();
        wp = myDB.getAgent(String.valueOf(1));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tahap lebih lanjut semua fitur berjalan", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        lvProduct = (ListView)findViewById(R.id.listview_product);

        mProductList = new ArrayList<>();
        //Add sample data for list
        //We can get data from DB, webservice here
        mProductList.add(new Product(1, "Fikry", 1, "Follow Up"));
        mProductList.add(new Product(3, "Agung", 2, "Rekrut"));
        mProductList.add(new Product(4, "Zakka", 1, "Follow Up"));
        mProductList.add(new Product(5, "Fikry", 2, "Training"));
        mProductList.add(new Product(6, "Tieboys", 3, "AAJI"));
        mProductList.add(new Product(7, "Agung", 3, "Closing"));
        mProductList.add(new Product(8, "Rei", 1, "JFK"));
        mProductList.add(new Product(9, "Faisal", 1, "Monitoring"));
        mProductList.add(new Product(10, "Ridwan", 1, "Aprroaching"));
        mProductList.add(new Product(11, "Agung", 1, "Follow Up"));

        //Init adapter
        adapter = new ProductListAdapterNotif(getApplicationContext(), mProductList);
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Do something
                //Ex: display msg with product id get from view.getTag
                Toast.makeText(getApplicationContext(), "Ketika di klik akan membuka detailnya =" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(this, BuatAkun.class);
            //finish();
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this, DaftarAgent.class);
            //finish();
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this, CalonAgent.class);
            //finish();
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, CalonNasabah.class);
            //finish();
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            myDB.updateRow(1,"siapa",0,"tipe",0);
            Toast.makeText(getBaseContext(), "Logout berhasil", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();
    }
}
