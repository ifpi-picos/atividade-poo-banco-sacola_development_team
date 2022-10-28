package VIEW;

import DAO.ContaDAO;
import Entidades.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


        btnTransferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferir();
            }
        });
        btnEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnMostrarDadosConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDados();
            }
        });
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
