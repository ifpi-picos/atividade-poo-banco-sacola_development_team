package DTO;

public class ContaPoupanca extends ContaDTO {
    private double taxaJuros;

    public double rendimento(double taxaJuros) {
        return this.getSaldo_Conta() * taxaJuros;
    }

    @Override
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.setSaldo_Conta(this.getSaldo_Conta() + valor);
            return true;
        }
        return false;
    }
}
