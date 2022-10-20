package DTO;

public class ContaDTO {
    private int id_Conta;
    private int num_Conta;
    private String agencia_Conta;
    private double saldo_Conta;


    // Metodos
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.setSaldo_Conta(this.getSaldo_Conta() + valor);
            return true;
        }
        return false;
    }

    public double sacar(double valor) {
        return this.saldo_Conta -= valor;
    }

    public void transferir(double valor) {
        this.saldo_Conta -= valor;
    }

    // Getters e Setters
    public int getId_Conta() {
        return id_Conta;
    }

    public void setId_Conta(int id_Conta) {
        this.id_Conta = id_Conta;
    }

    public int getNum_Conta() {
        return num_Conta;
    }

    public void setNum_Conta(int num_Conta) {
        this.num_Conta = num_Conta;
    }

    public String getAgencia_Conta() {
        return agencia_Conta;
    }

    public void setAgencia_Conta(String agencia_Conta) {
        this.agencia_Conta = agencia_Conta;
    }

    public double getSaldo_Conta() {
        return saldo_Conta;
    }

    public void setSaldo_Conta(double saldo_Conta) {
        this.saldo_Conta = saldo_Conta;
    }
}
