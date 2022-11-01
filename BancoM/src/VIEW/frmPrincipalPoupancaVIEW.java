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

    public frmPrincipalPoupancaVIEW() {
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450, 474));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


        btnTransferencia.addActionListener(e -> transferir());
        btnEncerrar.addActionListener(e -> dispose());
        btnMostrarDadosConta.addActionListener(e -> mostrarDados());
    }

    private void mostrarDados() {
        ContaDAO contaDAO = new ContaDAO();
        contaDAO.exibirInformacoesDaConta(frmLoginVIEW.numConta);
    }

    private void transferir() {
        double valorTransferencia = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferência: "));
        int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        contaPoupanca.transferir(valorTransferencia, numeroConta, frmLoginVIEW.numConta);
    }
}
