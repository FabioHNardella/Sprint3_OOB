package br.fiap.conexao;

import br.fiap.modelo.Gestor;
import br.fiap.modelo.GestorDAO;

public class Main {
    public static void main(String[] args) {
        GestorDAO gestDAO = new GestorDAO();

        gestDAO.inserir(new Gestor(1L, "Gustavo", "Burro", "mariana.almeida@empresa.com", "(11) 98877-1234"));
        gestDAO.inserir(new Gestor(2L, "Caio", "Patto", "caio.patto@empresa.com", "(21) 97766-4321"));
        gestDAO.inserir(new Gestor(3L, "Rafah", "Sargento", "rafah.sargento@empresa.com", "(31) 99655-6789"));
        gestDAO.inserir(new Gestor(4L, "Gustavo", "Pereira", "gustavo.pereira@empresa.com", "(41) 98444-1122"));
        gestDAO.inserir(new Gestor(5L, "Gabriel", "OpenToWork", "open.towork@empresa.com", "(51) 97233-5566"));

        gestDAO.atualizar(1L, new Gestor(1L, "Fabio", "Mortadella", "fabio.nardella@empresa.com", "(11) 92233-4455"));

        gestDAO.listar();

        gestDAO.deletar(1L);

    }
}
