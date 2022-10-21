package VIEW;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import DTO.ContaDTO;
import DTO.EnderecoDTO;
import DTO.UsuarioDTO;

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
        setMinimumSize(new Dimension(768, 480));
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
        // Pegando dados gerais do cliente
        String name = txtNome.getText();
        String cpf = txtCPF.getText();
        String birthDate = txtDataNascimento.getText();
        String street = txtLogradouro.getText();
        String number = txtNumero.getText();

        // Pegando dados do endereço
        String neighborhood = txtBairro.getText();
        String city = txtCidade.getText();
        String uf = txtUF.getText();
        String username = txtNovoUsuario.getText();
        String password = txtSenha.getText();
        String confirmPassword = txtConfirmarSenha.getText();

        // Abrindo conta padrão para o cliente

        if (name.isEmpty() || cpf.isEmpty() || birthDate.isEmpty() || street.isEmpty() || number.isEmpty() || neighborhood.isEmpty() || city.isEmpty() || uf.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem!");
            return;
        }
        int numberInt = Integer.parseInt(number);
        EnderecoDTO endereco = new EnderecoDTO(numberInt, street, neighborhood, city, uf);
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNomeUsuario(username);
        usuario.setSenhaUsuario(password);


        ClienteDTO cliente = new ClienteDTO();
        cliente.setNomeClient(name);
        cliente.setCpfCliente(cpf);
        cliente.setDataNascimentoCliente(birthDate);
        cliente.setEnderecoCliente(endereco);
        cliente.setUsuarioCliente(usuario);



        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.cadastrarCliente(cliente);

        dispose();
    }

    public static void main(String[] args) {
        frmRegistroVIEW registry = new frmRegistroVIEW(null);
    }
}
