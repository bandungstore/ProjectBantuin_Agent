package com.example.alfatih.project_01.TimeTable;

/**
 * Created by ACER V5 on 3/29/2017.
 */


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alfatih.project_01.AAJI;
import com.example.alfatih.project_01.AgentMonitoring;
import com.example.alfatih.project_01.Approaching;
import com.example.alfatih.project_01.Closing;
import com.example.alfatih.project_01.Database.DBadapter;
import com.example.alfatih.project_01.Database.Data;
import com.example.alfatih.project_01.FollowUp;
import com.example.alfatih.project_01.HomeAgent;
import com.example.alfatih.project_01.JFK;
import com.example.alfatih.project_01.Meeting;
import com.example.alfatih.project_01.Prospek;
import com.example.alfatih.project_01.R;
import com.example.alfatih.project_01.Rekrut;
import com.example.alfatih.project_01.ServisNasabah;
import com.example.alfatih.project_01.Training;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Kamis extends Fragment {
    public View v;
    Data data = new Data();
    DBadapter myDB;
    DBadapter.Agent wp;
    List<String> list = new ArrayList<String>();
    JSONArray arrayBiodata;
    Intent intent;

    @Override
    public void onResume(){
        //RefreshList();
        super.onResume();
    }

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_senin, container, false);


        openDB();
        wp = myDB.getAgent(String.valueOf(1));


        RefreshList();
        return v;
        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
//        return inflater.inflate(R.layout.senin, container, false);
    }

    public void RefreshList(){
        ListView lstItems = (ListView)v.findViewById(R.id.listView1);

        try {

            arrayBiodata = new JSONArray(data.getTimeTable(wp.getId(),"Kamis",wp.getMinggu(),wp.getTipe()));


            for (int i = 0; i < arrayBiodata.length(); i++) {
                JSONObject jsonChildNode = arrayBiodata.getJSONObject(i);

                list.add(jsonChildNode.optString("ID_Kegiatan") + " / "+jsonChildNode.optString("Nama_Agen")+ " / "+jsonChildNode.optString("Point"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> allItemsAdapter = new ArrayAdapter<String>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1,list);

        lstItems.setAdapter(allItemsAdapter);
        Button tambahdata = (Button)v.findViewById(R.id.button2);
        tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] dialogitem = {"Rekrut", "Prospek", "JFK", "Follow Up", "Servis Nasabah" , "Training", "Meeting" , "Approaching", "Agent Monitoring" , "Closing" , "AAJI"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                intent = new Intent(getActivity(), Rekrut.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 1 :
                                intent = new Intent(getActivity(), Prospek.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 2 :
                                intent = new Intent(getActivity(), JFK.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 3 :
                                intent = new Intent(getActivity(), FollowUp.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 4 :
                                intent = new Intent(getActivity(), ServisNasabah.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 5 :
                                intent = new Intent(getActivity(), Training.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 6 :
                                intent = new Intent(getActivity(), Meeting.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 7 :
                                intent = new Intent(getActivity(), Approaching.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 8 :
                                intent = new Intent(getActivity(), AgentMonitoring.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 9 :
                                intent = new Intent(getActivity(), Closing.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                            case 10 :
                                intent = new Intent(getActivity(), AAJI.class);
                                RefreshList();
                                startActivity(intent);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

    }
    private void openDB () {
        myDB = new DBadapter(getActivity());
        myDB.open();
    }
}
