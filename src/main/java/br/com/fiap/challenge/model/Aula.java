package br.com.fiap.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Aula {
    private String titulo;
    private String descricao;
    private Professor professor;
    private List<Reserva> reservas;

    public Aula(String titulo, String descricao, Professor professor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.professor = professor;
        this.reservas = new ArrayList<>();
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}

