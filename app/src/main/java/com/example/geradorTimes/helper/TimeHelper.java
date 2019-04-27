package com.example.geradorTimes.helper;

import android.widget.EditText;
import android.widget.Toast;
import com.example.geradorTimes.model.Time;

import com.example.geradorTimes.MainActivity;
import com.example.geradorTimes.R;

public class TimeHelper {

    private EditText campoQtdJogadores;

    public TimeHelper(MainActivity activity) { campoQtdJogadores = activity.findViewById(R.id.main_qtd_jg_time); }


    public Time recuperarCampoQtdJogadores() {
        Time time = new Time();
        time.setQtdJogadorTime(campoQtdJogadores.getText().toString());
        return time;
    }

    public void setCampoQtdJogadores(EditText campoQtdJogadores) { this.campoQtdJogadores = campoQtdJogadores; }
    public EditText getCampoQtdJogadores() { return campoQtdJogadores; }
}
