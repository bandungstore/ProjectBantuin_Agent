package com.example.alfatih.project_01.TimeTable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ACER V5 on 3/29/2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Senin senin1 = new Senin();
                return senin1;

            case 1:
                Selasa selasa1 = new Selasa();
                return selasa1;
            case 2:
                Rabu rabu1 = new Rabu();
                return rabu1;
            case 3:
                Kamis kamis1 = new Kamis();
                return kamis1;
            case 4:
                Jumat jumat1 = new Jumat();
                return jumat1;
            case 5:
                Sabtu sabtu1 = new Sabtu();
                return sabtu1;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}