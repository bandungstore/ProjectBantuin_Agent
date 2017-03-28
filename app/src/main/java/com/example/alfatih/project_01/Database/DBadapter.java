package com.example.alfatih.project_01.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ACER V5 on 3/19/2017.
 */

public class DBadapter {

    public static class Agent{
        public int id;
        public String nama;
        public int stats;
        public String tipe;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setNama(String nama){
            this.nama = nama;
        }
        public String getNama(){
            return this.nama;
        }
        public void setStats(int stats){this.stats = stats;}
        public int getStats(){ return this.stats;}
        public void setTipe(String tipe){
            this.tipe = tipe;
        }
        public String getTipe(){
            return this.tipe;
        }
    }

    private static final String TAG = "DBAdapter"; //used for logging database version changes

    // Field Names:
    public static final String KEY_ROWID = "_id";
    public static final String KEY_Name = "Name";
    public static final String stats = "ID_Agent";
    public static final String tipe = "Tipe";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_Name,stats};


    // Info database:
    public static final String DATABASE_NAME = "DBAgent";
    public static final String DATABASE_TABLE = "Agent";
    public static final int DATABASE_VERSION = 1; // The version number must be incremented each time a change to DB structure occurs.

    //SQL untuk membuat database
    private static final String DATABASE_CREATE_SQL =
            "CREATE TABLE " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " INTEGER PRIMARY KEY, "
                    + KEY_Name + " TEXT NOT NULL, "
                    + stats + " INT NOT NULL, "
                    + tipe + " TEXT NOT NULL "
                    + ");";



    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    public DBadapter(Context ctx) {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // Membuka koneksi ke database.
    public DBadapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Menutup koneksi ke database
    public void close() {
        myDBHelper.close();
    }

    // Insert ke database.
    public long insertRow(String name, int statz, String tipez) {
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_Name, name);
        newValues.put(stats, statz);
        newValues.put(tipe, tipez);
        //initialValues.put(KEY_DATE, date);

        // Menambah data ke database
        return db.insert(DATABASE_TABLE, null, newValues);
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null) != 0;
    }

    // Mengambil semua data.
    public Cursor getAllRows() {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, where, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    // Change an existing row to be equal to new data.
    public boolean updateRow(long rowId, String name, int statz ,String tipez) {
        String where = KEY_ROWID + "=" + rowId;
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_Name, name);
        newValues.put(stats, statz);
        newValues.put(tipe, tipez);

        // Insert it into the database.
        return db.update(DATABASE_TABLE, newValues, where, null) != 0;
    }

    public Agent getAgent(String id){
        Cursor cur = null;
        Agent T = new Agent();
        String[] cols = new String[]{KEY_ROWID,KEY_Name,  stats,tipe};

        String[] param = {id};
        cur = db.query("Agent",cols,"_id=?",param,null,null,null);

        if(cur.getCount()>0){
            cur.moveToFirst();
            T.id = cur.getInt(0);
            T.nama = cur.getString(1);
            T.stats = cur.getInt(2);
            T.tipe = cur.getString(3);
        }

        return T;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
            _db.execSQL("INSERT INTO " + DATABASE_TABLE + " VALUES (1, 'siapa', 0,'tipe')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version " + oldVersion
                    + " to " + newVersion + ", which will destroy all old data!");

            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

            // Recreate new database:
            onCreate(_db);
        }
    }

}