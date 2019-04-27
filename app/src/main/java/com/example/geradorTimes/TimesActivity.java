package com.example.geradorTimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.geradorTimes.adapter.TimesAdapter;
import com.example.geradorTimes.model.Time;

import java.util.List;

public class TimesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        List<Time> times = (List<Time>) extras.get("listaTimes");
        ListView lstTimes = findViewById(R.id.lstTimes);

        TimesAdapter timesAdapter = new TimesAdapter(getBaseContext(), R.layout.modelo_time, times);
        lstTimes.setAdapter(timesAdapter);

    }
}
