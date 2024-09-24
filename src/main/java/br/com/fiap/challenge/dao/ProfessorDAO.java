package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    private static final String INSERT_PROFESSOR = "INSERT INTO professor (nome, email) VALUES (?, ?)";
    private static final String SELECT_PROFESSOR_BY_ID = "SELECT * FROM professor WHERE id = ?";
    private static final String SELECT_ALL_PROFESSORS = "SELECT * FROM professor";
    private static final String UPDATE_PROFESSOR = "UPDATE professor SET nome = ?, email = ? WHERE id = ?";
    private static final String DELETE_PROFESSOR = "DELETE FROM professor WHERE id = ?";

    // Método para criar um novo professor no banco de dados
    public void criarProfessor(Professor professor) {
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PROFESSOR)) {

            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.executeUpdate();

            System.out.println("Professor cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar professor: " + e.getMessage());
        }
    }

    // Método para buscar um professor pelo ID
    public Professor buscarProfessorPorId(int id) {
        Professor professor = null;
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_PROFESSOR_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                professor = new Professor(nome, email);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }
        return professor;
    }

    // Método para listar todos os professores
    public List<Professor> listarProfessores() {
        List<Professor> professores = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_PROFESSORS);

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                Professor professor = new Professor(nome, email);
                professores.add(professor);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }
        return professores;
    }

    // Método para atualizar os dados de um professor
    public void atualizarProfessor(Professor professor, int id) {
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PROFESSOR)) {

            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEmail());
            ps.setInt(3, id);
            ps.executeUpdate();

            System.out.println("Professor atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    // Método para deletar um professor pelo ID
    public void deletarProfessor(int id) {
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_PROFESSOR)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Professor deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
        }
    }
}

