package com.example.geradorTimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geradorTimes.helper.TimeHelper;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recuperarQuantidadeJogadoresTime();
        checarBtnOk();
    }

    private void recuperarQuantidadeJogadoresTime(){
        final Button btn = findViewById(R.id.main_btn_next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acaoBotoes();
            }
        });
    }
    private void acaoBotoes(){
        TimeHelper th = new TimeHelper(MainActivity.this);
        if(verificarCampoQtdTime(th.recuperarCampoQtdJogadores().getQtdJogadorTime())){
            Intent adicionarJogadores = new Intent(MainActivity.this, AdicionarJogadoresActivity.class);
            adicionarJogadores.putExtra("qtdJogadores", th.recuperarCampoQtdJogadores());
            startActivity(adicionarJogadores);
            finish();
        }else{
            Toast.makeText(MainActivity.this, "Preenchimento obrigatorio.", Toast.LENGTH_LONG).show();
        }
    }
    private boolean verificarCampoQtdTime(String qtdJogadoresTime){
        if (qtdJogadoresTime.length() < 1 || Integer.parseInt(qtdJogadoresTime) < 1){
            return false;
        }
        return true;
    }

    public void checarBtnOk(){
        TimeHelper th = new TimeHelper(this);
        th.getCampoQtdJogadores().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                acaoBotoes();
                return true;
            }
        });
    }
}
