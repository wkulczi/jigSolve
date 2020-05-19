package com.example.jigsolveclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jigsolveclient.camera.CameraActivity;


public class MainActivity extends AppCompatActivity {

    public static String server_address="localhost";
    public static Integer server_port=2137;

    Button btn_open_camera ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_open_camera = (Button) findViewById(R.id.btn_open_camera);
        btn_open_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
    }


}
