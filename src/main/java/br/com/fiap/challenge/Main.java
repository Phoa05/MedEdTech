package br.com.fiap.challenge;

import br.com.fiap.challenge.dao.AlunoDAO;
import br.com.fiap.challenge.dao.AulaDAO;
import br.com.fiap.challenge.dao.ProfessorDAO;
import br.com.fiap.challenge.model.Aluno;
import br.com.fiap.challenge.model.Aula;
import br.com.fiap.challenge.model.Professor;
import br.com.fiap.challenge.service.Gamificacao;
import br.com.fiap.challenge.service.SistemaReserva;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaReserva sistema = new SistemaReserva();
        Scanner scanner = new Scanner(System.in);

        // DAOs
        ProfessorDAO professorDAO = new ProfessorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        AulaDAO aulaDAO = new AulaDAO();

        // Criar e adicionar professores no sistema
        Professor prof1 = new Professor("Dr. Silva", "dr.silva@fmusp.br");
        professorDAO.criarProfessor(prof1);

        // Criar e adicionar alunos no sistema
        Aluno aluno1 = new Aluno("Maria", "maria@fmusp.br");
        Aluno aluno2 = new Aluno("João", "joao@fmusp.br");
        alunoDAO.criarAluno(aluno1);
        alunoDAO.criarAluno(aluno2);

        boolean executando = true;
        while (executando) {
            System.out.println("Menu:");
            System.out.println("1. Professor: Marcar Aula");
            System.out.println("2. Aluno: Registrar Aula");
            System.out.println("3. Aluno: Marcar Aula");
            System.out.println("4. Ver Pontuação e Nível");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.println("Professor: Marcar Aula");
                    System.out.print("Título da Aula: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição da Aula: ");
                    String descricao = scanner.nextLine();
                    LocalDate dataAula = LocalDate.now(); // Exemplo de data
                    Aula novaAula = new Aula(titulo, descricao, dataAula, prof1, null); // Sala é nula por simplificação
                    aulaDAO.adicionarAula(novaAula);
                    System.out.println("Aula marcada com sucesso!");
                    break;

                case 2:
                    System.out.println("Aluno: Registrar Aula");
                    System.out.println("Escolha um aluno:");
                    List<Aluno> alunos = alunoDAO.listarAlunos();
                    for (int i = 0; i < alunos.size(); i++) {
                        System.out.println((i + 1) + ". " + alunos.get(i).getNome());
                    }
                    int escolhaAluno = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    Aluno alunoSelecionado = alunos.get(escolhaAluno);
                    System.out.println("Registrando participação na aula para " + alunoSelecionado.getNome());
                    Gamificacao.registrarParticipacao(alunoSelecionado);
                    alunoDAO.atualizarAluno(alunoSelecionado.getId(), alunoSelecionado);
                    System.out.println("Participação registrada!");
                    break;

                case 3:
                    System.out.println("Aluno: Marcar Aula");
                    System.out.println("Escolha um aluno:");
                    alunos = alunoDAO.listarAlunos();
                    for (int i = 0; i < alunos.size(); i++) {
                        System.out.println((i + 1) + ". " + alunos.get(i).getNome());
                    }
                    escolhaAluno = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    alunoSelecionado = alunos.get(escolhaAluno);
                    System.out.println("Escolha uma aula:");
                    List<Aula> aulas = aulaDAO.listarAulas();
                    for (int i = 0; i < aulas.size(); i++) {
                        System.out.println((i + 1) + ". " + aulas.get(i).getTitulo());
                    }
                    int escolhaAula = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    Aula aulaSelecionada = aulas.get(escolhaAula);
                    System.out.println("Aula '" + aulaSelecionada.getTitulo() + "' marcada para o aluno " + alunoSelecionado.getNome());
                    break;

                case 4:
                    System.out.println("Ver Pontuação e Nível");
                    System.out.println("Escolha um aluno:");
                    alunos = alunoDAO.listarAlunos();
                    for (int i = 0; i < alunos.size(); i++) {
                        System.out.println((i + 1) + ". " + alunos.get(i).getNome());
                    }
                    escolhaAluno = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    alunoSelecionado = alunos.get(escolhaAluno);
                    System.out.println("Aluno: " + alunoSelecionado.getNome() + ", Pontos: " + alunoSelecionado.getPontos() + ", Nível: " + alunoSelecionado.getNivel());
                    break;

                case 5:
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }
}
