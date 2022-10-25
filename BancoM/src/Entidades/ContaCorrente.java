package Entidades;

import DAO.ContaDAO;

import javax.swing.*;

public class ContaCorrente extends Conta {
    private double chequeEspecial;

    @Override
    public void depositar(double valor, int numConta) {
        if (valor > 0) {
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.puxarConta(numConta);
            contaDAO.depositoConta(valor);
            JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
        }
    }
    @Override
    public void sacar(double valor, int numConta) {
        if (valor > this.getSaldoConta()) {
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.puxarConta(numConta);
            contaDAO.saqueConta(valor);

            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }
    @Override
    public void transferir(double valor, int numeroConta, int numConta) {
        if (valor > this.getSaldoConta()) {
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.puxarConta(numConta);
            contaDAO.transferenciaContaCorrente(valor, numeroConta);

        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }
}
