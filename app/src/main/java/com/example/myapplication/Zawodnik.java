package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Zawodnicy")
public class Zawodnik {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_zawodnika")
    private int id;
    private String imie;
    private String nazwisko;
    private int wzrost;
    private int wiek;
    private String pozycja;

    @Ignore
    public Zawodnik(String imie, String nazwisko, String wzrost, String wiek, String pozycja){

    }

    public Zawodnik(String imie, String nazwisko, int wzrost, int wiek, String pozycja) {
        this.id = 0;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wzrost = wzrost;
        this.wiek = wiek;
        this.pozycja = pozycja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getWzrost() {
        return wzrost;
    }

    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public String getPozycja() {
        return pozycja;
    }

    public void setPozycja(String pozycja) {
        this.pozycja = pozycja;
    }

    @Override
    public String toString() {
        return "Zawodnik"+
                id +
                ", imie=" + imie +
                ", nazwisko=" + nazwisko +
                ", wzrost=" + wzrost +
                ", wiek=" + wiek +
                ", pozycja=" + pozycja;
    }
}
