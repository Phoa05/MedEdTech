package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private static final String INSERT_ALUNO = "INSERT INTO aluno (nome, email, pontos, nivel) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALUNO_BY_ID = "SELECT * FROM aluno WHERE id = ?";
    private static final String SELECT_ALL_ALUNOS = "SELECT * FROM aluno";
    private static final String UPDATE_ALUNO = "UPDATE aluno SET nome = ?, email = ?, pontos = ?, nivel = ? WHERE id = ?";
    private static final String DELETE_ALUNO = "DELETE FROM aluno WHERE id = ?";

    // Método para criar um novo aluno no banco de dados
    public void criarAluno(Aluno aluno) {
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_ALUNO)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setInt(3, aluno.getPontos());
            ps.setInt(4, aluno.getNivel());
            ps.executeUpdate();

            System.out.println("Aluno cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar aluno: " + e.getMessage());
        }
    }

    // Método para buscar um aluno pelo ID
    public Aluno buscarAlunoPorId(int id) {
        Aluno aluno = null;
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALUNO_BY_ID)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int pontos = rs.getInt("pontos");
                int nivel = rs.getInt("nivel");
                aluno = new Aluno(nome, email);
                aluno.setPontos(pontos);
                aluno.setNivel(nivel);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return aluno;
    }

    // Método para listar todos os alunos
    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SELECT_ALL_ALUNOS);

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                int pontos = rs.getInt("pontos");
                int nivel = rs.getInt("nivel");
                Aluno aluno = new Aluno(nome, email);
                aluno.setPontos(pontos);
                aluno.setNivel(nivel);
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }

    // Método para atualizar os dados de um aluno
    public void atualizarAluno(Aluno aluno, int id) {
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_ALUNO)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.setInt(3, aluno.getPontos());
            ps.setInt(4, aluno.getNivel());
            ps.setInt(5, id);
            ps.executeUpdate();

            System.out.println("Aluno atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    // Método para deletar um aluno pelo ID
    public void deletarAluno(int id) {
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALUNO)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Aluno deletado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
        }
    }
}

