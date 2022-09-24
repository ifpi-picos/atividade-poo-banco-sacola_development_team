package Modulos;

public class Account {
    private int numAgencia;
    private int numConta;
    private double saldo;
    private Client contaClient;

    public Account(int numAgencia, int numConta, double saldo, Client contaClient) {
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.saldo = saldo;
        this.contaClient = contaClient;
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
}
