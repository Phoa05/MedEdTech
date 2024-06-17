package br.com.fiap.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {
    private List<Aula> aulas;

    public Professor(String nome, String email) {
        super(nome, email);
        this.aulas = new ArrayList<>();
    }

    public void marcarAula(Aula aula) {
        aulas.add(aula);
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
}

