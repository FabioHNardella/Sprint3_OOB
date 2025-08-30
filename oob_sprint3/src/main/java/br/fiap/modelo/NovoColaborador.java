package br.fiap.modelo;

public class NovoColaborador {
    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private Buddy buddy;

    public NovoColaborador(long id, String nome, String sobrenome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Buddy getBuddy() {
        return buddy;
    }

    public void setBuddy(Buddy buddy) {
        this.buddy = buddy;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
