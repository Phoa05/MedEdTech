package br.com.fiap.challenge.dao;

import br.com.fiap.challenge.model.Aula;
import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.model.Sala;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AulaDAO {
    private final Connection connection;

    public AulaDAO() {
        this.connection = ConexaoDB.getConnection();
    }

    // Método para adicionar uma nova aula
    public void adicionarAula(Aula aula) {
        String sql = "INSERT INTO aulas (titulo, descricao, professor_id, sala_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aula.getTitulo());
            stmt.setString(2, aula.getDescricao());
            stmt.setInt(3, aula.getProfessor().getId());
            stmt.setInt(4, aula.getSala().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as aulas
    public List<Aula> listarAulas() {
        List<Aula> aulas = new ArrayList<>();
        String sql = "SELECT * FROM aulas";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                Professor professor = new ProfessorDAO().buscarProfessorPorId(rs.getInt("professor_id"));
                Sala sala = new SalaDAO().buscarSalaPorId(rs.getInt("sala_id"));

                Aula aula = new Aula(titulo, descricao, data, professor, sala);
                aula.setId(id); // Aqui definimos o ID da aula
                aulas.add(aula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aulas;
    }

    // Método para atualizar uma aula
    public void atualizarAula(Aula aula) {
        String sql = "UPDATE aulas SET titulo = ?, descricao = ?, professor_id = ?, sala_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, aula.getTitulo());
            stmt.setString(2, aula.getDescricao());
            stmt.setInt(3, aula.getProfessor().getId());
            stmt.setInt(4, aula.getSala().getId());
            stmt.setInt(5, aula.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar uma aula
    public void deletarAula(int id) {
        String sql = "DELETE FROM aulas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

