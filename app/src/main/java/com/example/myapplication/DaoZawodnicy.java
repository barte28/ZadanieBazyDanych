package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoZawodnicy {

    @Insert
    public void dodajZawodnika(Zawodnik zawodnik);


    @Delete
    public void usunZawodnika(Zawodnik zawodnik);

    @Update
    public void zaktualizujDaneZawodnikow(Zawodnik zawodnik);

    @Query("Select * from zawodnicy")
    public List<Zawodnik> wyswietlWszystkichZawodnikow();

    @Query("Select * from zawodnicy where pozycja = 'Środkowy'")
    public List<Zawodnik> wyswietlWszystkichSrodkowychZawodnikow();

}
