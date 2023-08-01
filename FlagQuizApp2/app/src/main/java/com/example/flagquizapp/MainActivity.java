package com.example.flagquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button btnBasla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasla = findViewById(R.id.btnBasla);

        try {
            veriTabaniKopyala();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btnBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gecisQuiz = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(gecisQuiz);
            }
        });
    }

    public void veriTabaniKopyala() throws IOException {

        DatabaseCopyHelper helper = new DatabaseCopyHelper(this);
        try {
            helper.createDataBase();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        helper.openDataBase();

    }
}