package br.com.fiap.challenge;

import br.com.fiap.challenge.model.*;
import br.com.fiap.challenge.service.Gamificacao;
import br.com.fiap.challenge.service.SistemaReserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaReserva sistema = new SistemaReserva();
        Scanner scanner = new Scanner(System.in);

        // Criar professores, alunos e salas
        Professor prof1 = new Professor("Dr. Silva", "dr.silva@fmusp.br");
        sistema.adicionarProfessor(prof1);

        Aluno aluno1 = new Aluno("Maria", "maria@fmusp.br");
        Aluno aluno2 = new Aluno("João", "joao@fmusp.br");
        sistema.adicionarAluno(aluno1);
        sistema.adicionarAluno(aluno2);

        Sala sala1 = new Sala("Sala A", "Edifício 1, Andar 2");
        sistema.adicionarSala(sala1);

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
                    System.out.print("Data e Hora de Início (YYYY-MM-DDTHH:MM): ");
                    LocalDateTime inicio = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("Data e Hora de Fim (YYYY-MM-DDTHH:MM): ");
                    LocalDateTime fim = LocalDateTime.parse(scanner.nextLine());
                    Horario horario = new Horario(inicio, fim);
                    System.out.println("Escolha uma sala:");
                    List<Sala> salas = sistema.getSalas();
                    for (int i = 0; i < salas.size(); i++) {
                        System.out.println((i + 1) + ". " + salas.get(i).getNome() + " - " + salas.get(i).getLocalizacao());
                    }
                    int escolhaSala = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    Sala salaSelecionada = salas.get(escolhaSala);
                    Aula novaAula = new Aula(titulo, descricao, prof1, horario, salaSelecionada);
                    sistema.adicionarAula(novaAula);
                    System.out.println("Aula marcada com sucesso!");
                    break;

                case 2:
                    System.out.println("Aluno: Registrar Aula");
                    System.out.println("Escolha um aluno:");
                    List<Aluno> alunos = sistema.getAlunos();
                    for (int i = 0; i < alunos.size(); i++) {
                        System.out.println((i + 1) + ". " + alunos.get(i).getNome());
                    }
                    int escolhaAluno = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    Aluno alunoSelecionado = alunos.get(escolhaAluno);
                    System.out.println("Registrando participação na aula para " + alunoSelecionado.getNome());
                    Gamificacao.registrarParticipacao(alunoSelecionado);
                    System.out.println("Participação registrada!");
                    break;

                case 3:
                    System.out.println("Aluno: Marcar Aula");
                    System.out.println("Escolha um aluno:");
                    alunos = sistema.getAlunos();
                    for (int i = 0; i < alunos.size(); i++) {
                        System.out.println((i + 1) + ". " + alunos.get(i).getNome());
                    }
                    escolhaAluno = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    alunoSelecionado = alunos.get(escolhaAluno);
                    System.out.println("Escolha uma aula:");
                    List<Aula> aulas = sistema.getAulas();
                    for (int i = 0; i < aulas.size(); i++) {
                        System.out.println((i + 1) + ". " + aulas.get(i).getTitulo());
                    }
                    int escolhaAula = scanner.nextInt() - 1;
                    scanner.nextLine(); // Limpar o buffer
                    Aula aulaSelecionada = aulas.get(escolhaAula);
                    System.out.print("Data e Hora da Reserva (YYYY-MM-DDTHH:MM): ");
                    String dataHoraString = scanner.nextLine();
                    LocalDateTime dataHora = LocalDateTime.parse(dataHoraString);
                    sistema.fazerReserva(alunoSelecionado, aulaSelecionada, dataHora);
                    System.out.println("Aula marcada com sucesso!");
                    break;

                case 4:
                    System.out.println("Ver Pontuação e Nível");
                    System.out.println("Escolha um aluno:");
                    alunos = sistema.getAlunos();
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
