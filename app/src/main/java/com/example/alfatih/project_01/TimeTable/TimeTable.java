package com.example.alfatih.project_01.TimeTable;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;
import com.example.alfatih.project_01.HomeAgent;
import com.example.alfatih.project_01.R;

import java.sql.Time;

public class TimeTable extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;
    Intent intent;
    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);



        openDB();
        wp = myDB.getAgent(String.valueOf(1));

        if(wp.getMinggu() == 0 ){
            setTitle("TimeTable Minggu Ini");
        }else if(wp.getMinggu() == 1 ){
            setTitle("TimeTable 1 Minggu Lalu");
        }else if(wp.getMinggu() == 2 ){
            setTitle("TimeTable 2 Minggu Lalu");
        }else if(wp.getMinggu() == 3 ){
            setTitle("TimeTable 3 Minggu Lalu");
        }
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Senin"));
        tabLayout.addTab(tabLayout.newTab().setText("Selasa"));
        tabLayout.addTab(tabLayout.newTab().setText("Rabu"));
        tabLayout.addTab(tabLayout.newTab().setText("Kamis"));
        tabLayout.addTab(tabLayout.newTab().setText("Jum'at"));
        tabLayout.addTab(tabLayout.newTab().setText("Sabtu"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_time_table1,menu);



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()){
            case R.id.mingguini:
                myDB.updateRow(1,wp.getNama(),wp.getStats(),wp.getTipe(),0);
                intent = new Intent(this, TimeTable.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.minggu1lalu:
                myDB.updateRow(1,wp.getNama(),wp.getStats(),wp.getTipe(),1);
                intent = new Intent(this, TimeTable.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.minggu2lalu:
                myDB.updateRow(1,wp.getNama(),wp.getStats(),wp.getTipe(),2);
                intent = new Intent(this, TimeTable.class);
                finish();
                startActivity(intent);
                return true;
            case R.id.minggu3lalu:
                myDB.updateRow(1,wp.getNama(),wp.getStats(),wp.getTipe(),3);
                intent = new Intent(this, TimeTable.class);
                finish();
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void openDB () {
        myDB = new DBadapter(this);
        myDB.open();
    }
}
