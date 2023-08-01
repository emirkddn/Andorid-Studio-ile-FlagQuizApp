package com.example.flagquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewSonuc, textViewSonucYuzde;
    private Button btnTekrar;
    private int dogruSayac;
    private int yanlisSayac;
    private int yuzdeSonuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewSonucYuzde = findViewById(R.id.textViewSonucYuzde);
        btnTekrar = findViewById(R.id.btnTekrar);

        dogruSayac = getIntent().getIntExtra("dogruSayac",0);
        yanlisSayac = 5 - dogruSayac;
        textViewSonuc.setText(dogruSayac+" DOĞRU "+yanlisSayac+" YANLIŞ ");
        yuzdeSonuc = 20 * dogruSayac;

        textViewSonucYuzde.setText(String.valueOf("%"+yuzdeSonuc+" BAŞARI"));

        btnTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gecisQuiz = new Intent(getApplicationContext(),QuizActivity.class);
                startActivity(gecisQuiz);
                finish();
            }
        });
    }
}