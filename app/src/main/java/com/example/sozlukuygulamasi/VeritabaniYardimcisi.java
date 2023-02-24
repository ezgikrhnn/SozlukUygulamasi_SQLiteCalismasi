package com.example.sozlukuygulamasi;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//veri tabani için bu sınıfı oluşturmam gerekiyor.
//sqldan extends etmem gerekiyor.
public class VeritabaniYardimcisi  extends SQLiteOpenHelper {


    //CONSTURCTOR OLUŞTURMAK ZORUNDAYIM
    public VeritabaniYardimcisi(@Nullable Context context) {
        super(context, "sozluk", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE kelimeler(kelime_id INTEGER PRIMARY KEY AUTOINCREMENT, ingilizce TEXT, turkce TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE if EXISTS kelimeler");  //varsa bunu sil neden? guncelleme yaparsak var olan veri tabanını silip üzerine yeniden yazmak isteyebiliriz.
        onCreate(db); //onCreate ile de yukarıya bunu aktarıyoruz.
    }
}
