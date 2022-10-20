package VIEW;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class frmLoginVIEW extends JDialog {
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
            String senhausuario = txtSenha.getText();

            UsuarioDTO objUsuarioDTO = new UsuarioDTO();
            objUsuarioDTO.setNomeUsuario(nomeusuario);
            objUsuarioDTO.setSenhaUsuario(senhausuario);

            UsuarioDAO objUsuarioDAO = new UsuarioDAO();
            ResultSet rsUsuarioDAO = objUsuarioDAO.autenticarUsuario(objUsuarioDTO);

            if (rsUsuarioDAO.next()) {
                // Se o usuário existir, abre o formulário principal
                frmPrincipalVIEW objfrmPrincipalVIEW = new frmPrincipalVIEW();
                objfrmPrincipalVIEW.setVisible(true);

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
