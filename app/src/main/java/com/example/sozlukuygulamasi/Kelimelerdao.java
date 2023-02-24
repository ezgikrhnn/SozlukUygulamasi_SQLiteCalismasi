package com.example.sozlukuygulamasi;

//veri tabanıyla iletişime gececek sınıfımı oluşturuyorum.
// DAO veri tabaniyla iletişime geçen yapıya denir


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Kelimelerdao {  //bu sınıfımın içerisinde metotlar olacak

    public void kelimeEkle(VeritabaniYardimcisi vt, String ingilizce, String turkce){
        //kendi sozlugumu kendim oluşturup içine kelimeler ekleyeceğim.
        //ver buradan nesne kaydetmek istediğim için veritabaniYardımcısından vt; nesnesini en başta ekliyorum.

        //ilk once sql veri tabanında nesne oluşturuyorum:
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();  //bu bana veri tabanına yazacağım değerleri tutacak.

        degerler.put("ingilizce", ingilizce);
        degerler.put("turkce", turkce);

        dbx.insertOrThrow("kelimeler", null, degerler);
        dbx.close(); //en sonda bu şekilde veri tabanımla bağlantıyı kesiyorum.
    }

    //KELİME EKLEDİK BUNLARI GORMEK İSTIYORSAK ONCE ALMAMIZ LAZIM.
    public ArrayList<Kelimeler> tumKelimeler(VeritabaniYardimcisi vt){

        ArrayList<Kelimeler> kelimelerArrayList = new ArrayList<>(); //bu arrayliste ben dışarıdan yani veri tabanından aldığım kelimeleri oluşturacağım.
        SQLiteDatabase dbx = vt.getWritableDatabase();

        //aldığım verilerle sorgu yapacağım
        Cursor c = dbx.rawQuery("SELECT * FROM kelimeler",null);

        //c kelimeler tablosundan verileri aldıktan sonra bana sonuç dönecek ben de onları satır satır okuyacağım.
        while(c.moveToNext()){
            @SuppressLint("Range") Kelimeler kelime = new Kelimeler(c.getInt(c.getColumnIndex("kelime_id")),
                    c.getString(c.getColumnIndex("ingilizce")),
                    c.getString(c.getColumnIndex("turkce")));

            kelimelerArrayList.add(kelime);
        }
        return kelimelerArrayList;
    }

    //SİLME İŞLEMİNİ YAPIYORUM
    public void kelimeSil(VeritabaniYardimcisi vt, int kelime_id){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        dbx.delete("kelimeler","kelime_id = ?", new String[]{String.valueOf(kelime_id)}); //tablonun ismi, karşılaştıracağım şey,
        dbx.close();  //silme işlemi bu şekildedir.

    }

    //ŞİMDİ GÜNCELLEME İŞLEMİNİ YAPIYORUM, guncelleme kelime ekleme ile neredeyse aynıdır.
    public void kelimeGuncelle(VeritabaniYardimcisi vt, int kelime_id, String ingilizce, String turkce){

        //ilk once sql veri tabanında nesne oluşturuyorum:
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();  //bu bana veri tabanına yazacağım değerleri tutacak.

        degerler.put("ingilizce", ingilizce);
        degerler.put("turkce", turkce);

        dbx.update("kelimeler", degerler, "kelime_id=?", new String[]{String.valueOf(kelime_id)});
        dbx.close(); //en sonda bu şekilde veri tabanımla bağlantıyı kesiyorum.
    }


}
