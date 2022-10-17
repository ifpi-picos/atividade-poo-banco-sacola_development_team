package DTO;

public class UsuarioDTO {
    private int id_Usuario;
    private String nome_Usuario, senha_Usuario;



    // Getters e Setters
    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNome_Usuario() {
        return nome_Usuario;
    }

    public void setNome_Usuario(String nome_Usuario) {
        this.nome_Usuario = nome_Usuario;
    }

    public String getSenha_Usuario() {
        return senha_Usuario;
    }

    public void setSenha_Usuario(String senha_Usuario) {
        this.senha_Usuario = senha_Usuario;
    }
}
