package VIEW;

import DAO.ContaDAO;
import Entidades.ContaPoupanca;

import javax.swing.*;
import java.awt.*;


public class frmPrincipalPoupancaVIEW extends JDialog{
    private JButton btnTransferencia;
    private JButton btnEncerrar;
    private JPanel painelPrincipal;
    private JButton btnMostrarDadosConta;
    private JLabel txtNomeUsuario;
    private JLabel txtSaldo;

    ContaDAO contaDAO = new ContaDAO();

    public frmPrincipalPoupancaVIEW(JFrame parent) {
        super(parent);
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450, 474));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);

        txtNomeUsuario.setText(frmLoginVIEW.usuario);
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
        btnTransferencia.addActionListener(e -> transferir());
        btnEncerrar.addActionListener(e -> dispose());
        btnMostrarDadosConta.addActionListener(e -> mostrarDados());
    }

    private void mostrarDados() {
        ContaDAO contaDAO = new ContaDAO();
        contaDAO.exibirInformacoesDaConta(frmLoginVIEW.numConta);
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
    }

    private void transferir() {
        double valorTransferencia = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferência: "));
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.transferir(valorTransferencia, numeroConta, frmLoginVIEW.numConta);
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
    }
}
