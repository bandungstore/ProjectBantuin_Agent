package com.example.alfatih.project_01;

/**
 * Created by ACER V5 on 3/13/2017.
 */

public class Data extends Koneksi {
    String URL = "http://fikry.96.lt/server.php";
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

}
