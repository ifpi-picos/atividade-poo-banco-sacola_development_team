package DTO;

public class ClienteDTO extends UsuarioDTO {

    private int id_Cliente;
    private String nome_Cliente;
    private String cpf_Cliente;
    private String dataNascimento_Cliente;
    private String endereco_Cliente;

// Getters e Setters
    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
    public String getNome_Client() {
        return nome_Cliente;
    }

    public void setNome_Client(String nome_Client) {
        this.nome_Cliente = nome_Client;
    }

    public String getCpf_Cliente() {
        return cpf_Cliente;
    }

    public void setCpf_Cliente(String cpf_Cliente) {
        this.cpf_Cliente = cpf_Cliente;
    }

    public String getDataNascimento_Cliente() {
        return dataNascimento_Cliente;
    }

    public void setDataNascimento_Cliente(String dataNascimento_Cliente) {
        this.dataNascimento_Cliente = dataNascimento_Cliente;
    }

    public String getEndereco_Cliente() {
        return endereco_Cliente;
    }

    public void setEndereco_Cliente(String endereco_Cliente) {
        this.endereco_Cliente = endereco_Cliente;
    }
}

