package br.com.fiap.challenge.test;

import br.com.fiap.challenge.dao.AlunoDAO;
import br.com.fiap.challenge.dao.ProfessorDAO;
import br.com.fiap.challenge.model.Aluno;
import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.service.SistemaReserva;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaReservaTest {
    private SistemaReserva sistemaReserva;
    private AlunoDAO alunoDAO;
    private ProfessorDAO professorDAO;

    @BeforeEach
    public void setUp() {
        sistemaReserva = new SistemaReserva();
        alunoDAO = new AlunoDAO();
        professorDAO = new ProfessorDAO();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAdicionarAluno() {
        Aluno aluno = new Aluno("Maria", "maria@fmusp.br");
        sistemaReserva.adicionarAluno(aluno);

        // Testar se o aluno foi adicionado corretamente
        Aluno alunoSalvo = alunoDAO.listarAlunos().stream()
                .filter(a -> a.getNome().equals("Maria"))
                .findFirst()
                .orElse(null);
        assertNotNull(alunoSalvo);
        assertEquals("Maria", alunoSalvo.getNome());
    }

    @Test
    public void testAdicionarProfessor() {
        Professor professor = new Professor("Dr. Silva", "dr.silva@fmusp.br");
        sistemaReserva.adicionarProfessor(professor);

        // Testar se o professor foi adicionado corretamente
        Professor professorSalvo = professorDAO.listarProfessores().stream()
                .filter(p -> p.getNome().equals("Dr. Silva"))
                .findFirst()
                .orElse(null);
        assertNotNull(professorSalvo);
        assertEquals("Dr. Silva", professorSalvo.getNome());
    }

    @Test
    public void testListarAlunos() {
        Aluno aluno1 = new Aluno("Ana", "ana@fmusp.br");
        Aluno aluno2 = new Aluno("Carlos", "carlos@fmusp.br");
        sistemaReserva.adicionarAluno(aluno1);
        sistemaReserva.adicionarAluno(aluno2);

        assertEquals(2, sistemaReserva.listarAlunos().size());
    }

    @Test
    public void testListarProfessores() {
        Professor professor1 = new Professor("Dr. Souza", "dr.souza@fmusp.br");
        Professor professor2 = new Professor("Dra. Mendes", "dra.mendes@fmusp.br");
        sistemaReserva.adicionarProfessor(professor1);
        sistemaReserva.adicionarProfessor(professor2);

        assertEquals(2, sistemaReserva.listarProfessores().size());
    }
}
