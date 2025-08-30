package br.fiap.modelo;

import br.fiap.conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NovoColaboradorDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql;

    public void inserir(NovoColaborador novoColaborador) {
        sql = "insert into novo_colaborador values (?, ?, ?, ?, ?)";
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, novoColaborador.getId());
            ps.setString(2, novoColaborador.getNome());
            ps.setString(3, novoColaborador.getSobrenome());
            ps.setString(4, novoColaborador.getEmail());
            ps.setString(5, novoColaborador.getTelefone());
            ps.setString(6, novoColaborador.getBuddy());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("erro ao inserir despesa\n" + e);
        }
    }

    public List<NovoColaborador> listar() {
        List<NovoColaborador> lista = new ArrayList<>();
        sql = "select * from novo_colaborador";
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new NovoColaborador(
                        rs.getLong("id_novo_colaborador"),
                        rs.getString("nome_novo_colaborador"),
                        rs.getString("sobrenome_novo_colaborador"),
                        rs.getString("email_novo_colaborador"),
                        rs.getString("telefone_novo_colaborador")));
            }
        } catch (SQLException e) {
            System.out.println("erro ao listar categorias\n" + e);
        }
        return lista;
    }

    public void atualizar(Long id, NovoColaborador novoColaboradorAtualizado) {
        List<NovoColaborador> lista = listar();
        for (NovoColaborador nc : lista) {
            if (nc.getId() == id) {
                String nome = "nome_novo_colaborador = " + novoColaboradorAtualizado.getNome();
                String sobrenome = ",sobrenome_novo_colaborador = " + novoColaboradorAtualizado.getSobrenome();
                String email = ",email_novo_colaborador = " + novoColaboradorAtualizado.getEmail();
                String telefone = ",telefone_novo_colaborador = " + novoColaboradorAtualizado.getTelefone();

                sql = "UPDATE novo_colaborador" + nome + sobrenome + email + telefone;
                try (Connection connection = Conexao.conectar()) {
                    ps = connection.prepareStatement(sql);
                    ps.execute();
                } catch (SQLException e) {
                    System.out.println("erro ao listar categorias\n" + e);
                }
            } else {
                System.out.println("NovoColaborador não encontrado, tente novamente!");
            }
        }
    }

    public void deletar(Long id) {
        sql = "DELETE FROM novo_colaborador WHERE id_novo_colaborador = " + id;
        try (Connection connection = Conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("erro ao listar categorias\n" + e);
        }

    }

}
