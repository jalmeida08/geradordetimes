package com.example.geradorTimes.model;

import java.io.Serializable;

public class Jogador implements Serializable {

    private Long id;
    private String nome;
    private boolean isSelected;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isSelected() { return isSelected; }
    public void setSelected(boolean selected) { isSelected = selected; }

    @Override
    public String toString() {
        return getNome();

    }
}

