package com.example.geradorTimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geradorTimes.helper.TimeHelper;
import com.example.geradorTimes.model.Jogador;
import com.example.geradorTimes.model.Time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Jogador> jogadores = new ArrayList<Jogador>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProximaTela();
        checarBtnOk();
        recuperarListaJogadoresSelecionados();
    }

    private void btnProximaTela(){
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
            sortearTimes(th.recuperarCampoQtdJogadores().getQtdJogadorTime());
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

    private void recuperarListaJogadoresSelecionados() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        jogadores = (List<Jogador>) extras.getSerializable("jogadoresSelecionados");
        ListView listJogadoresSelecionados = findViewById(R.id.main_list_jogadores_selecionados);
        ArrayAdapter<Jogador> adapterJogador = new ArrayAdapter<Jogador>(this, android.R.layout.simple_list_item_1, jogadores);
        listJogadoresSelecionados.setAdapter(adapterJogador);
    }

    private void sortearTimes(String quantidadeJogadores){
        Collections.shuffle(jogadores);
        Time time;
        List<Time> times = new ArrayList<Time>();
        Integer qtdJogadoresTime = Integer.parseInt(quantidadeJogadores);
        int increment = 0;
        for (int i = 0; i < Math.ceil(Double.valueOf(jogadores.size()) / Double.valueOf(qtdJogadoresTime)); i++) {
            List<Jogador> listaJogadores = new ArrayList<Jogador>();
            time = new Time();
            time.setQtdJogadorTime(qtdJogadoresTime.toString());
            for(int j = 0; j < qtdJogadoresTime; j++){
                if((increment+1) > jogadores.size())
                    break;
                listaJogadores.add(jogadores.get(increment));
                increment++;
            }
            time.setJogador(listaJogadores);
            time.setNomeTime(String.valueOf(i+1));
            times.add(time);
        }
        if(times.size() < 2){
            Toast.makeText(this, "Quantidade de jogadores insuficiente  para formar times", Toast.LENGTH_LONG).show();
        }else{
            Intent intentTimes = new Intent(this, TimesActivity.class);
            intentTimes.putExtra("listaTimes", (Serializable) times);
            startActivity(intentTimes);
            finish();
        }
    }

}
