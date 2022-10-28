package VIEW;

import DAO.ContaDAO;
import Entidades.ContaCorrente;
import Entidades.ContaPoupanca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPrincipalCorrenteVIEW extends JFrame {
    private JPanel painelPrincipal;
    private JButton BtnSacar;
    private JButton btnDepositar;
    private JButton btnTransferir;
    private JButton MOSTRARDADOSDACONTAButton;
    private JButton btnEncerrar;

    ContaDAO contaDAO = new ContaDAO();

    public frmPrincipalCorrenteVIEW() {
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(450, 474));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        dispose();
        BtnSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacar();
            }
        });
        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositar();
            }
        });
        btnTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferir();
            }
        });

        MOSTRARDADOSDACONTAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDados();
            }
        });
        btnEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
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
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.transferir(valorTransferencia, numeroConta, frmLoginVIEW.numConta);
    }

    private void depositar() {
        double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do depósito: "));
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.depositar(valorDeposito, frmLoginVIEW.numConta);
    }

    private void sacar() {
        double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque: "));
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.sacar(valorSaque, frmLoginVIEW.numConta);
    }
}


