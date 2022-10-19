package DTO;

public class ContaPoupanca_EXT_Conta extends ContaDTO {
    private double taxaJuros;

    public ContaPoupanca_EXT_Conta(int num_Conta, int agencia_Conta, double saldo_Conta) {
        super(num_Conta, agencia_Conta, saldo_Conta);
    }

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
