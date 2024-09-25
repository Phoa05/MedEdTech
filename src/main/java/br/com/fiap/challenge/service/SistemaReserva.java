package br.com.fiap.challenge.service;

import br.com.fiap.challenge.dao.AlunoDAO;
import br.com.fiap.challenge.dao.ProfessorDAO;
import br.com.fiap.challenge.dao.AulaDAO;
import br.com.fiap.challenge.dao.SalaDAO;
import br.com.fiap.challenge.model.Aluno;
import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.model.Aula;
import br.com.fiap.challenge.model.Sala;

import java.util.List;

public class SistemaReserva {
    private AlunoDAO alunoDAO;
    private ProfessorDAO professorDAO;
    private AulaDAO aulaDAO;
    private SalaDAO salaDAO;

    public SistemaReserva() {
        alunoDAO = new AlunoDAO();
        professorDAO = new ProfessorDAO();
        aulaDAO = new AulaDAO();
        salaDAO = new SalaDAO();
    }

    public void adicionarProfessor(Professor professor) {
        professorDAO.criarProfessor(professor);
    }

    public void adicionarAluno(Aluno aluno) {
        alunoDAO.criarAluno(aluno);
    }

    public void adicionarAula(Aula aula) {
        aulaDAO.adicionarAula(aula);
    }

    public List<Professor> listarProfessores() {
        return professorDAO.listarProfessores();
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.listarAlunos();
    }

    public List<Aula> listarAulas() {
        return aulaDAO.listarAulas();
    }

    public List<Sala> listarSalas() {
        return salaDAO.listarSalas();
    }
}
