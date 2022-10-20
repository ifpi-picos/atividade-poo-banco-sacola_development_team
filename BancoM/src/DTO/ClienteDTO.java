package DTO;

public class ClienteDTO {

    private int idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private String dataNascimentoCliente;
    private EnderecoDTO enderecoCliente;
    private ContaDTO contaCliente;
    private UsuarioDTO usuarioCliente;

// Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNomeClient() {
        return nomeCliente;
    }

    public void setNome_Client(String nomeClient) {
        this.nomeCliente = nomeClient;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }

    public void setDataNascimentoCliente(String dataNascimentoCliente) {
        this.dataNascimentoCliente = dataNascimentoCliente;
    }

    public EnderecoDTO getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(EnderecoDTO enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public ContaDTO getContaCliente() {
        return contaCliente;
    }

    public void setContaCliente(ContaDTO contaCliente) {
        this.contaCliente = contaCliente;
    }

    public UsuarioDTO getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(UsuarioDTO usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }
}

