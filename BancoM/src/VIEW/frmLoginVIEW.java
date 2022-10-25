package VIEW;

import DAO.ContaDAO;
import DAO.UsuarioDAO;
import Entidades.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class frmLoginVIEW extends JDialog {
    protected static String usuario;
    protected static int numConta;
    private JPanel loginPanel;
    private JTextField txtUsuario;
    private JTextField txtSenha;
    private JButton btnOk;
    private JButton btnCancel;


    public frmLoginVIEW(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logar();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        frmLoginVIEW login = new frmLoginVIEW(null);

    }


    private void logar() {
        try {

            String nomeusuario = txtUsuario.getText();
            usuario = nomeusuario;
            String senhausuario = txtSenha.getText();

            Usuario objUsuario = new Usuario();
            objUsuario.setNomeUsuario(nomeusuario);
            objUsuario.setSenhaUsuario(senhausuario);

            UsuarioDAO objUsuarioDAO = new UsuarioDAO();
            ResultSet rsUsuarioDAO = objUsuarioDAO.autenticarUsuario(objUsuario);



            if (rsUsuarioDAO.next()) {
                ContaDAO contaDAO = new ContaDAO();

                int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Selecionar conta","Abrir nova conta"}, "Login");
                if (opcao == 0) {
                    numConta = contaDAO.selecionarConta(nomeusuario);
                } else if (opcao == 1) {
                    contaDAO.abrirNovaConta(nomeusuario);
                    logar();
                }
                contaDAO.puxarConta(numConta);

                // Se o usuário existir, abre o formulário principal
                if (contaDAO.tipoContaDAO == 1) {
                    frmPrincipalCorrenteVIEW objfrmPrincipalCorrenteVIEW = new frmPrincipalCorrenteVIEW();
                    objfrmPrincipalCorrenteVIEW.setVisible(true);

                } else if (contaDAO.tipoContaDAO == 2) {
                    frmPrincipalPoupancaVIEW objfrmPrincipalPoupancaVIEW = new frmPrincipalPoupancaVIEW();
                    objfrmPrincipalPoupancaVIEW.setVisible(true);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao autenticar usuário: " + erro.getMessage());
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
