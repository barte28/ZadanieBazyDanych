package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private DataBaseZawodnicy dataBaseZawodnicy;

    private EditText editTextImie;
    private EditText editTextNazwisko;
    private EditText editTextwzrost;
    private EditText editTextwiek;
    private Spinner spinnerpozycja;
    private Button buttondodaj;
    private List<Zawodnik> zawodnicy;
    private ListView listView;
    private ArrayAdapter<Zawodnik> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextImie = findViewById(R.id.editTextImie);
        editTextNazwisko = findViewById(R.id.editTextNazwisko);
        editTextwzrost = findViewById(R.id.editTextWzrost);
        editTextwiek = findViewById(R.id.editTextWiek);
        spinnerpozycja = findViewById(R.id.spinnerPozycja);
        buttondodaj = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        RoomDatabase.Callback mojCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        dataBaseZawodnicy = Room.databaseBuilder(
                getApplicationContext(),
                DataBaseZawodnicy.class,
                "ZawodnicyDB").
                addCallback(mojCallBack).
                allowMainThreadQueries().
                build();

        buttondodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String imie = editTextImie.getText().toString();
                        String nazwisko = editTextNazwisko.getText().toString();
                        int wzrost = Integer.parseInt(editTextwzrost.getText().toString());
                        int wiek = Integer.parseInt(editTextwiek.getText().toString());
                        String pozycja = spinnerpozycja.getSelectedItem().toString();
                        Zawodnik zawodnik = new Zawodnik(
                                imie,
                                nazwisko,
                                wzrost,
                                wiek,
                                pozycja);


                        dodaDanedoBazy(zawodnik);
                    }
                }
        );
    }

    private void wypiszZawodnikow(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        zawodnicy = dataBaseZawodnicy.getDaoZawodnicy().wyswietlWszystkichZawodnikow();

                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, zawodnicy);
                                        listView.setAdapter(arrayAdapter);
                                    }
                                }
                        );
                    }
                }
        );
    }

    private void dodaDanedoBazy(Zawodnik zawodnik){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                       dataBaseZawodnicy.getDaoZawodnicy().dodajZawodnika(zawodnik);

                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        zawodnicy.add(zawodnik);
                                        Toast.makeText(MainActivity.this, "Dodano do bazy", Toast.LENGTH_SHORT).show();

                                    }
                                }
                        );
                    }
                }
        );
    }
}