package DTO;

public class ClienteDTO {

    private int id_Cliente;
    private String nome_Cliente;
    private String cpf_Cliente;
    private String dataNascimento_Cliente;
    private EnderecoDTO endereco_Cliente;
    private ContaDTO conta_Cliente;
    private UsuarioDTO usuarioCliente;

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

    public EnderecoDTO getEndereco_Cliente() {
        return endereco_Cliente;
    }

    public void setEndereco_Cliente(EnderecoDTO endereco_Cliente) {
        this.endereco_Cliente = endereco_Cliente;
    }

    public ContaDTO getConta_Cliente() {
        return conta_Cliente;
    }

    public void setConta_Cliente(ContaDTO conta_Cliente) {
        this.conta_Cliente = conta_Cliente;
    }

    public UsuarioDTO getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(UsuarioDTO usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }
}

