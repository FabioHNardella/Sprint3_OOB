package br.fiap.modelo;

import br.fiap.conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public void inserir(Gestor gestor) {
        sql = "insert into gestor values (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, gestor.getId());
            ps.setString(2, gestor.getNome());
            ps.setString(3, gestor.getSobrenome());
            ps.setString(4, gestor.getEmail());
            ps.setString(5, gestor.getTelefone());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("erro ao inserir despesa\n" + e);
        }
    }

    public List<Gestor> listar() {
        List<Gestor> lista = new ArrayList<>();
        sql = "select * from gestor";
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Gestor(
                        rs.getLong("id_gestor"),
                        rs.getString("nome_gestor"),
                        rs.getString("sobrenome_gestor"),
                        rs.getString("email_gestor"),
                        rs.getString("telefone_gestor")));
            }
        } catch (SQLException e) {
            System.out.println("erro ao listar categorias\n" + e);
        }
        return lista;
    }

    public void atualizar(Long id, Gestor gestorAtualizado) {
        List<Gestor> lista = listar();
        for (Gestor g : lista) {
            if (g.getId() == id) {
                String nome = "nome_gestor = " + gestorAtualizado.getNome();
                String sobrenome = ",sobrenome_gestor = " + gestorAtualizado.getSobrenome();
                String email = ",email_gestor = " + gestorAtualizado.getEmail();
                String telefone = ",telefone_gestor = " + gestorAtualizado.getTelefone();

                sql = "UPDATE gestor" + nome + sobrenome + email + telefone;
                try (Connection connection = Conexao.conectar()) {
                    ps = connection.prepareStatement(sql);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("erro ao listar categorias\n" + e);
                }
            } else {
                System.out.println("Gestor não encontrado, tente novamente!");
            }
        }
    }

    public void deletar(Long id) {
        sql = "DELETE FROM gestor WHERE id_gestor = " + id;
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("erro ao listar categorias\n" + e);
        }

    }

}
