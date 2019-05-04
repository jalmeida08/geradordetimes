package com.example.geradorTimes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

public class ListaJogadoresActivity extends AppCompatActivity {

    private List<Jogador> jogadores = new ArrayList<Jogador>();
    private ListView lst;
    private JogadorDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_jogadores);

        listarJogadores();
        registerForContextMenu(lst);
        botaoSortear();
        botaoTelaAdicionarJogador();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarJogadores();
    }

    private void listarJogadores() {
        dao = new JogadorDAO(this);

        jogadores = dao.listarJogadores();
        dao.close();
        lst = findViewById(R.id.lista_jogadores_list);
        JogadorAdapter adapter = new JogadorAdapter(this, R.layout.modelo_jogadores, jogadores);
        lst.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario:
                JogadorDAO dao = new JogadorDAO(this);
                for (Jogador j : jogadores){
                    if(j.isSelected()){
                        dao.deletarJogador(j.getId());
                        dao.close();
                        System.out.println(j.toString());
                    }
                }
                listarJogadores();
                Toast.makeText(this, "Removido", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_selecionar_todos:
                for(Jogador j : jogadores){
                    j.setSelected(true);
                }
            break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Jogador jogador = (Jogador) lst.getItemAtPosition(info.position);
                dao = new JogadorDAO(ListaJogadoresActivity.this);
                dao.deletarJogador(jogador.getId());
                dao.close();
                listarJogadores();
                Toast.makeText(ListaJogadoresActivity.this, "Item excluído com sucesso.", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }

    public void botaoSortear(){
        Button btnSrtear = findViewById(R.id.list_btn_sortear_times);
        btnSrtear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Jogador> jogadoresSelecionados = new ArrayList<Jogador>();
                for(Jogador j: jogadores){
                    if(j.isSelected()){
                        jogadoresSelecionados.add(j);
                    }
                }
                //sortearTimes(jogadoresSelecionados);
                Intent intentQtdJogadorTime = new Intent(ListaJogadoresActivity.this, MainActivity.class);
                intentQtdJogadorTime.putExtra("jogadoresSelecionados", (Serializable)jogadoresSelecionados);
                startActivity(intentQtdJogadorTime);
            }
        });
    }

    private void botaoTelaAdicionarJogador(){
        Button btnAdicionarJogador = findViewById(R.id.list_btn_add_jogador);
        btnAdicionarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdicionarNovosJogadores = new Intent(ListaJogadoresActivity.this, AdicionarJogadoresActivity.class);
                startActivity(intentAdicionarNovosJogadores);
                finish();
            }
        });
    }
}
