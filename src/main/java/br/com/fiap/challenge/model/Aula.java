package br.com.fiap.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Aula {
    private String titulo;
    private String descricao;
    private Professor professor;
    private List<Reserva> reservas;
    private Horario horario;
    private Sala sala;

    public Aula(String titulo, String descricao, Professor professor, Horario horario, Sala sala) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.professor = professor;
        this.horario = horario;
        this.sala = sala;
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
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
