package com.example.alfatih.project_01.Database;


import java.util.Date;

/**
 * Created by ACER V5 on 3/13/2017.
 */

public class Data extends Koneksi {
    String URL = "http://titans-revolution.com/server.php";
    String url = "";
    String response = "";

    public String login(String username, String password) {
        try {
            url = URL + "?operasi=login&username=" + username + "&password=" + password;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getAgentById(int id) {
        try {
            url = URL + "?operasi=get_agent_by_id&id=" + id;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }


    public String insertRekrut(int id_agent,String nama_agen, String nama, int telp, String Kelamin, String Tanggal,String Status,int jumlahanak,String Sumbernama,String Pekerjaan, String alamat, String Keterangan, String hari,String tgl) {
        try {
            url = URL + "?operasi=insertRekrut&id_agent=" + id_agent + "&nama_agen=" + nama_agen+ "&nama=" + nama+ "&telp=" + telp+ "&kelamin=" + Kelamin+ "&tanggal=" + Tanggal+ "&status=" + Status+ "&jumlahanak=" + jumlahanak+ "&sumbernama=" + Sumbernama+ "&pekerjaan=" + Pekerjaan + "&alamat=" + alamat+ "&keterangan=" + Keterangan+ "&hari=" + hari+ "&tgl=" + tgl;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertProspek(int id_agent,String nama_agen, String nama, int telp, String Kelamin, String Tanggal,String Status,int jumlahanak,String Sumbernama,String Pekerjaan, String alamat, String Keterangan, String hari,String tgl) {
        try {
            url = URL + "?operasi=insertProspek&id_agent=" + id_agent + "&nama_agen=" + nama_agen+ "&nama=" + nama+ "&telp=" + telp+ "&kelamin=" + Kelamin+ "&tanggal=" + Tanggal+ "&status=" + Status+ "&jumlahanak=" + jumlahanak+ "&sumbernama=" + Sumbernama+ "&pekerjaan=" + Pekerjaan + "&alamat=" + alamat+ "&keterangan=" + Keterangan+ "&hari=" + hari+ "&tgl=" + tgl;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getAllDataProspek(int id) {
        try {
            url = URL + "?operasi=getAllProspek&id_agent=" + id;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getAllDataRekrut(int id) {
        try {
            url = URL + "?operasi=getRekrut&id_agent=" + id;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertJFK(int id_agent,String nama_agent, String calon_nasabah, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertJFK&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&calon_nasabah=" + calon_nasabah+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertFollowUp(int id_agent,String nama_agent, String calon_nasabah, String keterangan,String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertFollowUp&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&calon_nasabah=" + calon_nasabah+ "&tanggal=" + tanggal+ "&keterangan=" + keterangan+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertServis(int id_agent,String nama_agent, String calon_nasabah, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertServis&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&calon_nasabah=" + calon_nasabah+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertClosing(int id_agent,String nama_agent, String calon_nasabah, String premi, String api, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertClosing&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&calon_nasabah=" + calon_nasabah+ "&premi=" + premi+ "&api=" + api+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertAAJI(int id_agent,String nama_agent, String calon_nasabah, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertAAJI&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&calon_nasabah=" + calon_nasabah+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertTraining(int id_agent,String nama_agent, String jenis_kegiatan, String jumlah_hadir, String tempat, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertTraining&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&jenis_kegiatan=" + jenis_kegiatan+ "&jumlah_hadir=" + jumlah_hadir+ "&tempat=" + tempat+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertMeeting(int id_agent,String nama_agent, String jenis_kegiatan, String tempat, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertMeeting&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&jenis_kegiatan=" + jenis_kegiatan+ "&tempat=" + tempat+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertMonitoring(int id_agent,String nama_agent, String monitoring, String jumlah_orang, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertMonitoring&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&monitoring=" + monitoring+ "&jumlah_orang=" + jumlah_orang+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertApproaching(int id_agent,String nama_agent, String calon_nasabah,String metode, String tanggal, String hari) {
        try {
            url = URL + "?operasi=insertApproaching&id_agent=" + id_agent+ "&nama_agent=" + nama_agent+ "&calon_nasabah=" + calon_nasabah+ "&metode=" + metode+ "&tanggal=" + tanggal+ "&hari=" + hari;
            System.out.println("URL Insert Biodata : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }
}
