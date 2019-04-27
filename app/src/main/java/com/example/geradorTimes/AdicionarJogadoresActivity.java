package com.example.geradorTimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geradorTimes.adapter.JogadorAdapter;
import com.example.geradorTimes.dao.JogadorDAO;
import com.example.geradorTimes.helper.JogadorHelper;
import com.example.geradorTimes.model.Jogador;
import com.example.geradorTimes.model.Time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AdicionarJogadoresActivity extends AppCompatActivity {
    private List<Jogador> jogadores = new ArrayList<Jogador>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_jogadores);
        botaoAdicionar();
        botaoSortarTimes();
        botaoOk();


    }


    private void botaoAdicionar(){
        this.jogadores = new ArrayList<Jogador>();
        final Button btn = findViewById(R.id.add_jogador_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // JogadorHelper jh = new JogadorHelper(AdicionarJogadoresActivity.this);
                //acaoBotao(jh);
                Intent intent = new Intent(AdicionarJogadoresActivity.this, ListaJogadoresActivity.class);
                startActivity(intent);
            }
        });
    }

    private void acaoBotao(JogadorHelper jh) {
        if(verificarCampo(jh.getJogador(), jogadores)){
            JogadorDAO dao = new JogadorDAO(AdicionarJogadoresActivity.this);
            AdicionarJogadoresActivity.this.jogadores.add(jh.getJogador());
            dao.insertJogador(jh.getJogador());
            addJogadorList(AdicionarJogadoresActivity.this.jogadores);
            jh.limparCapo();
        }
    }

    private void botaoOk(){
        final JogadorHelper jh = new JogadorHelper(AdicionarJogadoresActivity.this);
        jh.getCampoNome().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                acaoBotao(jh);
                return true;
            }
        });
    }

    private void addJogadorList(List<Jogador> jogadores){
        ListView lst = findViewById(R.id.add_list_jogadores);
        Collections.reverse(jogadores);
//        ArrayAdapter<Jogador> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jogadores);
        JogadorAdapter listAdapter = new JogadorAdapter(this, R.layout.modelo_jogadores, jogadores);
        lst.setAdapter(listAdapter);
    }

    private Time recuperarTime(){
        Intent it = getIntent();
        Bundle extras = it.getExtras();
        Time time = (Time) extras.get("qtdJogadores");
        return time;
    }

    private boolean verificarCampo(Jogador jogador, List<Jogador> jogadores){
        if(jogador.getNome().length() >= 2){
              for(Jogador jog: jogadores){
                  if(jog.getNome().equals(jogador.getNome())){
                      Toast.makeText(AdicionarJogadoresActivity.this, "Nome duplicado", Toast.LENGTH_LONG).show();
                      return false;
                  }
            }
            return true;
        }
        Toast.makeText(AdicionarJogadoresActivity.this, "Nome obrigatório", Toast.LENGTH_LONG).show();
        return false;
    }

    private void botaoSortarTimes(){
        final Button btn = findViewById(R.id.add_btn_sortear_times);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortearTimes(AdicionarJogadoresActivity.this.jogadores);
                finishActivity(R.layout.activity_main);
            }
        });
    }

    private void sortearTimes(List<Jogador> jogadors){
        Collections.shuffle(jogadors);
        Time time = recuperarTime();
        List<Time> times = new ArrayList<Time>();
        Integer qtdJogadoresTime = Integer.parseInt(time.getQtdJogadorTime());
        int increment = 0;
        for (int i = 0; i < Math.ceil(Double.valueOf(jogadors.size()) / Double.valueOf(qtdJogadoresTime)); i++) {
            List<Jogador> listaJogadores = new ArrayList<Jogador>();
            time = new Time();
            time.setQtdJogadorTime(qtdJogadoresTime.toString());
            for(int j = 0; j < qtdJogadoresTime; j++){
                if((increment+1) > jogadors.size())
                    break;
                listaJogadores.add(jogadors.get(increment));
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
        }
    }
}
