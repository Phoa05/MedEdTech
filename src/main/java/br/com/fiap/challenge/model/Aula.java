package br.com.fiap.challenge.model;

import java.time.LocalDate;

public class Aula {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private Professor professor;
    private Sala sala;

    // Construtor
    public Aula(String titulo, String descricao, LocalDate data, Professor professor, Sala sala) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.professor = professor;
        this.sala = sala;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
