package br.com.fiap.challenge.model;

public class Aluno extends Usuario {
    int id;
    private int pontos;
    private int nivel;

    public Aluno(String nome, String email) {
        super(nome, email);
        this.pontos = 0;
        this.nivel = 1;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
        atualizarNivel();
    }

    private void atualizarNivel() {
        this.nivel = this.pontos / 100 + 1;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
        atualizarNivel();
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }
}

