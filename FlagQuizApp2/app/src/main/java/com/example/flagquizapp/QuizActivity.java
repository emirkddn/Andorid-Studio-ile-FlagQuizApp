package com.example.flagquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private Button buttonA, buttonB, buttonC, buttonD;
    private TextView textViewDogru, textViewYanlis, textViewSoruNumarasi;
    private ImageView imageViewBayrak;

    private ArrayList<Bayraklar> sorularListe;
    private ArrayList<Bayraklar> yanlisSecenekler;
    private Bayraklar dogruSoru;
    private Veritabani vt;

    private int soruSayac = 0;
    private int dogruSayac = 0;
    private int yanlisSayac = 0;

    private HashSet<Bayraklar> secenekleriKaristirmaListe = new HashSet<>();
    private ArrayList<Bayraklar> seceneklerListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        textViewDogru = findViewById(R.id.textViewDoğru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruNumarasi = findViewById(R.id.textViewSoruNumarasi);

        imageViewBayrak = findViewById(R.id.imageViewBayrak);

        vt = new Veritabani(this);

        sorularListe = new BayraklarDAO().rastgele5Getir(vt);

        soruYukle();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonA);
                sayacKontrol();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonB );
                sayacKontrol();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonC);
                sayacKontrol();
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dogruKontrol(buttonD);
                sayacKontrol();
            }
        });
    }

    public void soruYukle(){

        textViewSoruNumarasi.setText((soruSayac+1)+". SORU");
        textViewDogru.setText("DOĞRU: "+dogruSayac);
        textViewYanlis.setText("YANLIŞ: "+yanlisSayac);

        dogruSoru = sorularListe.get(soruSayac);

        yanlisSecenekler = new BayraklarDAO().rastgele3yanlisSecenekGetir(vt,dogruSoru.getBayrak_id());

        imageViewBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(),"drawable",getPackageName()));

        secenekleriKaristirmaListe.clear();
        secenekleriKaristirmaListe.add(dogruSoru);
        secenekleriKaristirmaListe.add(yanlisSecenekler.get(0));
        secenekleriKaristirmaListe.add(yanlisSecenekler.get(1));
        secenekleriKaristirmaListe.add(yanlisSecenekler.get(2));

        seceneklerListe.clear();

        for (Bayraklar b: secenekleriKaristirmaListe){
            seceneklerListe.add(b);
        }

        buttonA.setText(seceneklerListe.get(0).getBayrak_ad());
        buttonB.setText(seceneklerListe.get(1).getBayrak_ad());
        buttonC.setText(seceneklerListe.get(2).getBayrak_ad());
        buttonD.setText(seceneklerListe.get(3).getBayrak_ad());
    }

    public void dogruKontrol(Button button){

        String buttonYazi = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_ad();

        if (buttonYazi.equals(dogruCevap)){
            dogruSayac++;
        }else {
            yanlisSayac++;
        }
    }

    public void sayacKontrol(){
        soruSayac++;
        if (soruSayac != 5){
            soruYukle();
        }else {
            Intent gecisResult = new Intent(getApplicationContext(),ResultActivity.class);
            gecisResult.putExtra("dogruSayac",dogruSayac);
            startActivity(gecisResult);
            finish();
        }
    }
}