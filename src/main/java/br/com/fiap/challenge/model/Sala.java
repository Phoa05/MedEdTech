package br.com.fiap.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private String localizacao;
    private List<Equipamento> equipamentos;

    public Sala(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.equipamentos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void adicionarEquipamento(Equipamento equipamento) {
        this.equipamentos.add(equipamento);
    }
}
