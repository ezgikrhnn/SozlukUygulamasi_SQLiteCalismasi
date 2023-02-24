package com.example.sozlukuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //birkaç tane veri kaydı yapalım.
    private VeritabaniYardimcisi vt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vt = new VeritabaniYardimcisi(this);


        new Kelimelerdao().kelimeEkle(vt, "door", "kapı");
        new Kelimelerdao().kelimeEkle(vt, "black", "siyah");
        new Kelimelerdao().kelimeEkle(vt, "dress", "elbise");
        new Kelimelerdao().kelimeEkle(vt, "love", "sevgi");
        new Kelimelerdao().kelimeEkle(vt, "blue", "mavi");
        new Kelimelerdao().kelimeEkle(vt, "book", "kitap");

        //birini sileyim:
        new Kelimelerdao().kelimeSil(vt, 5);
        //birini güncelleyeyim:
        new Kelimelerdao().kelimeGuncelle(vt, 2, "blackxxxx", "siyahxxxxx");

        ArrayList<Kelimeler> gelenKelimelerListesi = new Kelimelerdao().tumKelimeler(vt);

        for (Kelimeler k : gelenKelimelerListesi){
            Log.e(String.valueOf(k.getKelime_id()), k.getIngilizce() +"-"+ k.getTurkce());
        }

    }
}