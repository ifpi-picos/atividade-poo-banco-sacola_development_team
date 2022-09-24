package Modulos;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private String nomeCliente;
    private String dataNascimento;
    private String cpf;
    private String endereco;
    private static List<Account> accounts;


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


    public static void adicionarConta(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
    }
    public static void statusConta() {
        StringBuilder show = new StringBuilder();
        for (Account account : accounts) {
            show.append("Número da conta: ").append(account.getNumConta()).append("\nNúmero da agencia: ").append(account.getNumAgencia()).append("\nSaldo Atual: ").append(account.getSaldo());
        }
        JOptionPane.showMessageDialog(null, show.toString(), "Conta (s)" ,JOptionPane.PLAIN_MESSAGE);
    }

}
