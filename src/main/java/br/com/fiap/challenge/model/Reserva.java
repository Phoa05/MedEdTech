package br.com.fiap.challenge.model;

import java.time.LocalDateTime;

public class Reserva {
    private Aluno aluno;
    private Aula aula;
    private LocalDateTime dataHora;

    public Reserva(Aluno aluno, Aula aula, LocalDateTime dataHora) {
        this.aluno = aluno;
        this.aula = aula;
        this.dataHora = dataHora;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

