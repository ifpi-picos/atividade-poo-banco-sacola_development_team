package Entidades;

public class ContaPoupanca extends Conta {
    private double taxaJuros;

    public double rendimento(double taxaJuros) {
        return this.getSaldoConta() * taxaJuros;
    }

    @Override
    public boolean depositar(double valor) {
        if (valor > 0) {
            this.setSaldoConta(this.getSaldoConta() + valor);
            return true;
        }
        return false;
    }
}
