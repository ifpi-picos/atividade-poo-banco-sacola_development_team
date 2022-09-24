package Modulos;


import java.util.Random;


public class Account {
    private int numAgencia;
    private int numConta;
    private double saldo;
    static Random randomizator = new Random();

    public Account(int numAgencia, int numConta, double saldo, Client contaClient) {
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.saldo = saldo;
    }
    public int getNumAgencia() {
        return numAgencia;
    }

    public int getNumConta() {
        return numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public static void abrirConta(Client contaClient) {
        int numAgencia = randomizator.nextInt(99999) + 1;
        int numConta = randomizator.nextInt(9999) + 1;
        double saldo = randomizator.nextDouble(999);

        Account account = new Account(numAgencia, numConta, saldo, contaClient);
        Client.adicionarConta(account);
    }
    public static void exibirConta() {
        Client.statusConta();
    }
}
