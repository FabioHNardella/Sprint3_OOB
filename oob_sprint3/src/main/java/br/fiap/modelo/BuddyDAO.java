package br.fiap.modelo;

import br.fiap.conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuddyDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public void inserir(Buddy buddy) {
        sql = "insert into buddy values (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, buddy.getId());
            ps.setString(2, buddy.getNome());
            ps.setString(3, buddy.getSobrenome());
            ps.setString(4, buddy.getEmail());
            ps.setString(5, buddy.getTelefone());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("erro ao inserir despesa\n" + e);
        }
    }

    public List<Buddy> listar() {
        List<Buddy> lista = new ArrayList<>();
        sql = "select * from buddy";
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Buddy(
                        rs.getLong("id_buddy"),
                        rs.getString("nome_buddy"),
                        rs.getString("sobrenome_buddy"),
                        rs.getString("email_buddy"),
                        rs.getString("telefone_buddy")));
            }
        } catch (SQLException e) {
            System.out.println("erro ao listar categorias\n" + e);
        }
        return lista;
    }

    public void atualizar(Long id, Buddy buddyAtualizado) {
        List<Buddy> lista = listar();
        for (Buddy g : lista) {
            if (g.getId() == id) {
                String nome = "nome_buddy = " + buddyAtualizado.getNome();
                String sobrenome = ",sobrenome_buddy = " + buddyAtualizado.getSobrenome();
                String email = ",email_buddy = " + buddyAtualizado.getEmail();
                String telefone = ",telefone_buddy = " + buddyAtualizado.getTelefone();

                sql = "UPDATE buddy" + nome + sobrenome + email + telefone;
                try (Connection connection = Conexao.conectar()) {
                    ps = connection.prepareStatement(sql);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("erro ao listar categorias\n" + e);
                }
            } else {
                System.out.println("Buddy não encontrado, tente novamente!");
            }
        }
    }

    public void deletar(Long id) {
        sql = "DELETE FROM buddy WHERE id_buddy = " + id;
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("erro ao listar categorias\n" + e);
        }

    }

}