package com.example.jigsolveclient;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class HttpClient {
    static HttpResponse sendRequest(String method, String serverAddr, String route) {
        HttpResponse response = new HttpResponse();

        try {
            URL url = new URL("http://" + serverAddr + route);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);

            // Tutaj wysyła zapytanie
            response.code = conn.getResponseCode();

            // Tutaj sprawdzamy czy sukces
            if (response.code == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                // Tutaj odczyt odpowiedzi
                response.data = in.readLine();
                Log.d("ODEBRANE",response.data);
            }
        } catch (Exception e) {
            Log.d("httpClient_sendRequest", e.toString());
            e.printStackTrace();
        }
        return response;
    }

    static HttpResponse sendRequest(String method, String serverAddr, String route, JSONObject content) {
        HttpResponse response = new HttpResponse();

        try {
            URL url = new URL("http://" + serverAddr + route);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8;");
            OutputStream os = conn.getOutputStream();
            os.write(content.toString().getBytes("UTF-8"));
            os.close();

            // Tutaj wysyła zapytanie
            response.code = conn.getResponseCode();

            // Tutaj sprawdzamy czy sukces
            if (response.code == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                // Tutaj odczyt odpowiedzi
                response.data = in.readLine();
            }
        } catch (Exception e) {
            Log.d("httpClient_sendRequest", e.toString());
            e.printStackTrace();
        }
        return response;
    }

    public static HttpResponse sendPicture(Bitmap bitmap, String url_string){
        try {
            URL url = new URL(url_string);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setDoInput(true);
            c.setRequestMethod("POST");
            c.setDoOutput(true);
            c.connect();
            OutputStream output = c.getOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, output);
            output.close();
            Scanner result = new Scanner(c.getInputStream());
            String response = result.nextLine();
            Log.e("ImageUploader", "Error uploading image: " + response);
            result.close();
        } catch (IOException e) {
            Log.e("ImageUploader", "Error uploading image", e);
        }
    }
}

