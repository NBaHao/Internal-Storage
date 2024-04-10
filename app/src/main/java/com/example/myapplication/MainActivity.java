package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Lưu dữ liệu vào Internal Storage
        try {
            FileOutputStream fos = this.openFileOutput("myFile.txt", Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write("Hello, Internal Storage!");
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Đọc dữ liệu từ Internal Storage
        StringBuilder data = new StringBuilder();
        try {
            FileInputStream fis = this.openFileInput("myFile.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Hiển thị dữ liệu đã đọc
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}