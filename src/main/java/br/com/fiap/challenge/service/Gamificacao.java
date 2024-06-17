package br.com.fiap.challenge.service;


import br.com.fiap.challenge.model.Aluno;

public class Gamificacao {
    public static void registrarParticipacao(Aluno aluno) {
        aluno.adicionarPontos(10);
    }

    public static void concluirAula(Aluno aluno) {
        aluno.adicionarPontos(50);
    }
}

