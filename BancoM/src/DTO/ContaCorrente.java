package DTO;

import javax.swing.*;

public class ContaCorrente extends ContaDTO {
    private double chequeEspecial;

    @Override
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.setSaldoConta(this.getSaldoConta() + valor);
            return true;
        }
        return false;
    }
    @Override
    public double sacar(double valor) {
        if (valor > this.getSaldoConta()) {
            this.setSaldoConta(this.getSaldoConta() - valor);
            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
        return this.getSaldoConta();
    }
    @Override
    public void transferir(double valor) {
        if (valor > this.getSaldoConta()) {
            this.setSaldoConta(this.getSaldoConta() - valor);
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }
}
