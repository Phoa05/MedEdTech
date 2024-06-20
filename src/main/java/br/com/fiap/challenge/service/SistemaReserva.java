package br.com.fiap.challenge.service;

import br.com.fiap.challenge.model.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SistemaReserva {
    private List<Aluno> alunos;
    private List<Professor> professores;
    private List<Aula> aulas;
    private List<Reserva> reservas;
    private List<Sala> salas;

    public SistemaReserva() {
        this.alunos = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.aulas = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.salas = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void adicionarAula(Aula aula) {
        aulas.add(aula);
    }

    public void adicionarSala(Sala sala) {
        salas.add(sala);
    }

    public void fazerReserva(Aluno aluno, Aula aula, LocalDateTime dataHora) {
        Reserva reserva = new Reserva(aluno, aula, dataHora);
        reservas.add(reserva);
        aula.adicionarReserva(reserva);
        System.out.println("Reserva realizada com sucesso para " + aluno.getNome() + " na aula " + aula.getTitulo() + " em " + dataHora);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Sala> getSalas() {
        return salas;
    }
}
