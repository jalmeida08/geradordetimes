package com.example.geradorTimes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Time implements Serializable{

    private Integer id;
    private List<Jogador> jogador;
    private String qtdJogadorTime;
    private String nomeTime;

    public List<Jogador> getJogador() { return jogador; }
    public void setJogador(List<Jogador> jogador) { this.jogador = jogador; }

    public String getQtdJogadorTime() { return qtdJogadorTime; }
    public void setQtdJogadorTime(String qtdJogadorTime) { this.qtdJogadorTime = qtdJogadorTime; }

    public String getNomeTime() { return nomeTime; }
    public void setNomeTime(String nomeTime) { this.nomeTime = nomeTime; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

}
