package com.example.gotrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addition, substitute, multiply, division;
    MainTrainer myTrainer;

    public void addButton(View view) {
        Intent openTrainer = new Intent(getApplicationContext(), MainTrainer.class);
        startActivity(openTrainer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addition = findViewById(R.id.addition);
        substitute = findViewById(R.id.substitute);
        multiply = findViewById(R.id.multiply);
        division = findViewById(R.id.division);

    }
}