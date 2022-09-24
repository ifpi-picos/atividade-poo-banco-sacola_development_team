package Modulos;

public class Client {

    String nomeCliente;
    String dataNascimento;
    String cpf;
    String endereco;

    public Client(String nomeCliente, String dataNascimento, String cpf, String endereco) {
        this.nomeCliente = nomeCliente;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }
}
