package VIEW;

import DAO.ContaDAO;
import Entidades.ContaCorrente;

import javax.swing.*;
import java.awt.*;


public class frmPrincipalCorrenteVIEW extends JDialog {
    private JPanel painelPrincipal;
    private JButton BtnSacar;
    private JButton btnDepositar;
    private JButton btnTransferir;
    private JButton MOSTRARDADOSDACONTAButton;
    private JButton btnEncerrar;
    private JLabel txtNomeUsuario;
    private JLabel txtSaldo;
    private JButton atualizarButton;

    ContaDAO contaDAO = new ContaDAO();

    public frmPrincipalCorrenteVIEW(JFrame parent) {
        super(parent);
        setContentPane(painelPrincipal);
        setMinimumSize(new Dimension(360, 474));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(parent);


        dispose();
        txtNomeUsuario.setText(frmLoginVIEW.usuario);
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
        BtnSacar.addActionListener(e -> sacar());
        btnDepositar.addActionListener(e -> depositar());
        btnTransferir.addActionListener(e -> transferir());
        MOSTRARDADOSDACONTAButton.addActionListener(e -> mostrarDados());
        btnEncerrar.addActionListener(e -> dispose());
        atualizarButton.addActionListener(e -> txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta))));
    }

    private void mostrarDados() {
        ContaDAO contaDAO = new ContaDAO();
        contaDAO.exibirInformacoesDaConta(frmLoginVIEW.numConta);
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
    }


    private void transferir() {
        if (contaDAO.getContadorTransferencia() > 2) {
            JOptionPane.showMessageDialog(null,
                    "Você atingiu o limite de 2 transferências por dia! A partir desse momento, " +
                            "será cobrado uma taxa de 3% por transferência.");
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja continuar?");
            switch (opcao) {
                case 0 -> {
                    double valorTransferencia = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferência: "));
                    int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));
                    ContaCorrente contaCorrente = new ContaCorrente();
                    contaCorrente.transferir(valorTransferencia, numeroConta, frmLoginVIEW.numConta);
                }
                case 1 ->
                        JOptionPane.showMessageDialog(null, "Volte amanhã para realizar mais transferências sem taxas!");
                case 2 -> JOptionPane.showMessageDialog(null, "Operação cancelada!");
            }
        } else {
            double valorTransferencia = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferência: "));
            int numeroConta = Integer.parseInt(JOptionPane.showInputDialog("Digite o número da conta: "));
            ContaCorrente contaCorrente = new ContaCorrente();
            contaCorrente.transferir(valorTransferencia, numeroConta, frmLoginVIEW.numConta);
        }
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
    }

    private void depositar() {
        double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do depósito: "));
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.depositar(valorDeposito, frmLoginVIEW.numConta);
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
    }

    private void sacar() {
        double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque: "));
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.sacar(valorSaque, frmLoginVIEW.numConta);
        txtSaldo.setText(String.valueOf(contaDAO.puxarSaldoConta(frmLoginVIEW.numConta)));
    }
}


