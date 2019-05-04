package com.example.geradorTimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
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
        botaoVoltarTela();
        botaoOk();
    }


    private void botaoAdicionar(){
        this.jogadores = new ArrayList<Jogador>();
        final Button btn = findViewById(R.id.add_jogador_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JogadorHelper jh = new JogadorHelper(AdicionarJogadoresActivity.this);
                acaoBotao(jh);
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
        ArrayAdapter<Jogador> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jogadores);
//        JogadorAdapter listAdapter = new JogadorAdapter(this, R.layout.modelo_jogadores, jogadores);
        lst.setAdapter(listAdapter);
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

    private void botaoVoltarTela(){
        final Button btn = findViewById(R.id.add_voltar_tela);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdicionarJogadoresActivity.this, ListaJogadoresActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
