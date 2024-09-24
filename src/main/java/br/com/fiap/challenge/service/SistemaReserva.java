package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dao.AlunoDAO;
import br.com.fiap.challenge.dao.ProfessorDAO;
import br.com.fiap.challenge.model.Aluno;
import br.com.fiap.challenge.model.Aula;
import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.model.Reserva;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class SistemaReserva {
    private AlunoDAO alunoDAO;
    private ProfessorDAO professorDAO;

    public SistemaReserva() {
        this.alunoDAO = new AlunoDAO();
        this.professorDAO = new ProfessorDAO();
    }

    // Adicionar aluno utilizando o DAO
    public void adicionarAluno(Aluno aluno) {
        alunoDAO.criarAluno(aluno);
    }

    // Adicionar professor utilizando o DAO
    public void adicionarProfessor(Professor professor) {
        professorDAO.criarProfessor(professor);
    }

    // Método para marcar uma reserva (será ajustado para uma futura camada de reserva DAO)
    public void fazerReserva(Aluno aluno, Aula aula, LocalDateTime dataHora) {
        Reserva reserva = new Reserva(aluno, aula, dataHora);
        System.out.println("Reserva realizada com sucesso para " + aluno.getNome() + " na aula " + aula.getTitulo() + " em " + dataHora);
    }

    // Buscar todos os alunos (listagem)
    public List<Aluno> listarAlunos() {
        return alunoDAO.listarAlunos();
    }

    // Buscar todos os professores (listagem)
    public List<Professor> listarProfessores() {
        return professorDAO.listarProfessores();
    }
}
