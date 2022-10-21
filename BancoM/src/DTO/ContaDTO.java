package DTO;

public class ContaDTO {
    private int idConta;
    private int numConta;
    private String agenciaConta;
    private double saldoConta;


    // Metodos
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.setSaldoConta(this.getSaldoConta() + valor);
            return true;
        }
        return false;
    }

    public double sacar(double valor) {
        return this.saldoConta -= valor;
    }

    public void transferir(double valor) {
        this.saldoConta -= valor;
    }

    // Getters e Setters
    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public String getAgenciaConta() {
        return agenciaConta;
    }

    public void setAgenciaConta(String agenciaConta) {
        this.agenciaConta = agenciaConta;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }
}
