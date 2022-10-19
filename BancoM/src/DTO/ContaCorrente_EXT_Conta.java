package DTO;

import javax.swing.*;

public class ContaCorrente_EXT_Conta extends ContaDTO {
    private double chequeEspecial;

    public ContaCorrente_EXT_Conta(int num_Conta, int agencia_Conta, double saldo_Conta) {
        super(num_Conta, agencia_Conta, saldo_Conta);
    }
    @Override
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.setSaldo_Conta(this.getSaldo_Conta() + valor);
            return true;
        }
        return false;
    }
    @Override
    public double sacar(double valor) {
        if (valor > this.getSaldo_Conta()) {
            this.setSaldo_Conta(this.getSaldo_Conta() - valor);
            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
        return this.getSaldo_Conta();
    }
    @Override
    public void transferir(double valor) {
        if (valor > this.getSaldo_Conta()) {
            this.setSaldo_Conta(this.getSaldo_Conta() - valor);
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }
}
