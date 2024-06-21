package br.com.fiap.challenge;

import br.com.fiap.challenge.model.*;
import br.com.fiap.challenge.service.Gamificacao;
import br.com.fiap.challenge.service.SistemaReserva;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            mostrarMenu();
            int opcao = lerOpcao(scanner);

            switch (opcao) {
                case 1:
                    marcarAula(scanner, sistema, prof1);
                    break;

                case 2:
                    registrarParticipacao(scanner, sistema);
                    break;

                case 3:
                    marcarAulaAluno(scanner, sistema);
                    break;

                case 4:
                    verPontuacaoNivel(scanner, sistema);
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

    private static void mostrarMenu() {
        System.out.println("Menu:");
        System.out.println("1. Professor: Marcar Aula");
        System.out.println("2. Aluno: Registrar Aula");
        System.out.println("3. Aluno: Marcar Aula");
        System.out.println("4. Ver Pontuação e Nível");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcao(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Opção inválida. Digite um número.");
            scanner.next(); // Limpa a entrada inválida
        }
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        return opcao;
    }

    private static void marcarAula(Scanner scanner, SistemaReserva sistema, Professor prof1) {
        try {
            System.out.println("Professor: Marcar Aula");
            System.out.print("Título da Aula: ");
            String titulo = scanner.nextLine();
            System.out.print("Descrição da Aula: ");
            String descricao = scanner.nextLine();
            LocalDate dataInicio = lerData(scanner, "Digite a data de início (dd/MM/yyyy): ");
            LocalTime horaInicio = lerHora(scanner, "Digite a hora de início (HH:mm): ");
            LocalDate dataFim = lerData(scanner, "Digite a data de fim (dd/MM/yyyy): ");
            LocalTime horaFim = lerHora(scanner, "Digite a hora de fim (HH:mm): ");
            LocalDateTime inicio = LocalDateTime.of(dataInicio, horaInicio);
            LocalDateTime fim = LocalDateTime.of(dataFim, horaFim);
            Horario horario = new Horario(inicio, fim);
            Sala salaSelecionada = escolherSala(scanner, sistema.getSalas());
            if (salaSelecionada == null) return;
            Aula novaAula = new Aula(titulo, descricao, prof1, horario, salaSelecionada);
            sistema.adicionarAula(novaAula);
            System.out.println("Aula marcada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao marcar a aula: " + e.getMessage());
        }
    }

    private static void registrarParticipacao(Scanner scanner, SistemaReserva sistema) {
        try {
            System.out.println("Aluno: Registrar Aula");
            Aluno alunoSelecionado = escolherAluno(scanner, sistema.getAlunos());
            if (alunoSelecionado == null) return;
            System.out.println("Registrando participação na aula para " + alunoSelecionado.getNome());
            Gamificacao.registrarParticipacao(alunoSelecionado);
            System.out.println("Participação registrada!");
        } catch (Exception e) {
            System.out.println("Erro ao registrar participação: " + e.getMessage());
        }
    }

    private static void marcarAulaAluno(Scanner scanner, SistemaReserva sistema) {
        try {
            System.out.println("Aluno: Marcar Aula");
            Aluno alunoSelecionado = escolherAluno(scanner, sistema.getAlunos());
            if (alunoSelecionado == null) return;
            Aula aulaSelecionada = escolherAula(scanner, sistema.getAulas());
            if (aulaSelecionada == null) return;
            LocalDate dataReserva = lerData(scanner, "Digite a data da reserva (dd/MM/yyyy): ");
            LocalTime horaReserva = lerHora(scanner, "Digite a hora da reserva (HH:mm): ");
            LocalDateTime dataHora = LocalDateTime.of(dataReserva, horaReserva);
            sistema.fazerReserva(alunoSelecionado, aulaSelecionada, dataHora);
            System.out.println("Aula marcada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao marcar a aula: " + e.getMessage());
        }
    }

    private static void verPontuacaoNivel(Scanner scanner, SistemaReserva sistema) {
        try {
            System.out.println("Ver Pontuação e Nível");
            Aluno alunoSelecionado = escolherAluno(scanner, sistema.getAlunos());
            if (alunoSelecionado == null) return;
            System.out.println("Aluno: " + alunoSelecionado.getNome() + ", Pontos: " + alunoSelecionado.getPontos() + ", Nível: " + alunoSelecionado.getNivel());
        } catch (Exception e) {
            System.out.println("Erro ao ver pontuação e nível: " + e.getMessage());
        }
    }

    private static LocalDate lerData(Scanner scanner, String mensagem) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Por favor, tente novamente.");
            }
        }
    }

    private static LocalTime lerHora(Scanner scanner, String mensagem) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            try {
                return LocalTime.parse(input, timeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Hora inválida. Por favor, tente novamente.");
            }
        }
    }

    private static Sala escolherSala(Scanner scanner, List<Sala> salas) {
        System.out.println("Escolha uma sala:");
        for (int i = 0; i < salas.size(); i++) {
            System.out.println((i + 1) + ". " + salas.get(i).getNome() + " - " + salas.get(i).getLocalizacao());
        }
        int escolhaSala = lerOpcao(scanner) - 1;
        if (escolhaSala < 0 || escolhaSala >= salas.size()) {
            System.out.println("Escolha inválida. Operação cancelada.");
            return null;
        }
        return salas.get(escolhaSala);
    }

    private static Aluno escolherAluno(Scanner scanner, List<Aluno> alunos) {
        System.out.println("Escolha um aluno:");
        for (int i = 0; i < alunos.size(); i++) {
            System.out.println((i + 1) + ". " + alunos.get(i).getNome());
        }
        int escolhaAluno = lerOpcao(scanner) - 1;
        if (escolhaAluno < 0 || escolhaAluno >= alunos.size()) {
            System.out.println("Escolha inválida. Operação cancelada.");
            return null;
        }
        return alunos.get(escolhaAluno);
    }

    private static Aula escolherAula(Scanner scanner, List<Aula> aulas) {
        System.out.println("Escolha uma aula:");
        for (int i = 0; i < aulas.size(); i++) {
            System.out.println((i + 1) + ". " + aulas.get(i).getTitulo());
        }
        int escolhaAula = lerOpcao(scanner) - 1;
        if (escolhaAula < 0 || escolhaAula >= aulas.size()) {
            System.out.println("Escolha inválida. Operação cancelada.");
            return null;
        }
        return aulas.get(escolhaAula);
    }
}
