package VIEW;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import DTO.ContaDTO;
import DTO.EnderecoDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmRegistroVIEW extends JDialog {
    private JTextField txtNome;
    private JTextField txtCPF;
    private JTextField txtDataNascimento;
    private JTextField txtSenha;
    private JTextField txtConfirmarSenha;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JPanel registerPanel;
    private JTextField txtNovoUsuario;
    private JTextField txtNumero;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtUF;
    private JTextField txtLogradouro;

    public frmRegistroVIEW(JFrame parent) {
        super(parent);
        setTitle("Criar nova conta");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);


        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    private void registerUser() {
        String name = txtNome.getText();
        String cpf = txtCPF.getText();
        String birthDate = txtDataNascimento.getText();
        String street = txtLogradouro.getText();
        String number = txtNumero.getText();

        String neighborhood = txtBairro.getText();
        String city = txtCidade.getText();
        String uf = txtUF.getText();
        String username = txtNovoUsuario.getText();
        String password = txtSenha.getText();
        String confirmPassword = txtConfirmarSenha.getText();

        if (name.isEmpty() || cpf.isEmpty() || birthDate.isEmpty() || street.isEmpty() || number.isEmpty() || neighborhood.isEmpty() || city.isEmpty() || uf.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem!");
            return;
        }
        int numberInt = Integer.parseInt(number);
        EnderecoDTO endereco = new EnderecoDTO(numberInt, street, neighborhood, city, uf);


        ClienteDTO cliente = new ClienteDTO();
        cliente.setNome_Client(name);
        cliente.setCpf_Cliente(cpf);
        cliente.setDataNascimento_Cliente(birthDate);
        cliente.setEndereco_Cliente(endereco);
        cliente.setNome_Usuario(username);
        cliente.setSenha_Usuario(password);


        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.cadastrarCliente(cliente);
    }

    public static void main(String[] args) {
        frmRegistroVIEW registry = new frmRegistroVIEW(null);
    }
}
