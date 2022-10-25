package Entidades;

import DAO.ContaDAO;

import javax.swing.*;

public class ContaPoupanca extends Conta {
    private double taxaJuros;

    public double rendimento(double taxaJuros) {
        return this.getSaldoConta() * taxaJuros;
    }

    @Override
    public void transferir(double valor, int numeroConta, int numConta) {
        if (valor > this.getSaldoConta()) {
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.puxarConta(numConta);
            contaDAO.transferenciaContaPoupanca(valor, numeroConta);

            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }
}
