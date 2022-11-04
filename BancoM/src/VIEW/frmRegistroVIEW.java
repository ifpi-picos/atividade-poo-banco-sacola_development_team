package VIEW;

import DAO.ClienteDAO;
import Entidades.Cliente;
import Entidades.Endereco;
import Entidades.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class frmRegistroVIEW extends JDialog {

    private JTextField txtNome;
    private JFormattedTextField formattedTxtDataNascimento;
    private JFormattedTextField formattedTxtCpf;
    private JPasswordField txtSenha;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private JPanel registerPanel;
    private JTextField txtNovoUsuario;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtUF;
    private JTextField txtLogradouro;
    private JFormattedTextField formattedTxtCep;
    private JPasswordField txtSenhaConfirma;
    private JTextField txtNumero;


    public frmRegistroVIEW(JFrame parent) {
        super(parent);

        dialogInit(); // Inicializa o diálogo
        formatarCampos(); // Formata os campos

        setTitle("Criar conta");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(480, 768));
        setModal(true);
        setLocationRelativeTo(parent);


        btnRegistrar.addActionListener(e -> registerUser());
        btnCancelar.addActionListener(e -> dispose());



    }
    // formata os campos de data, cpf e número
    private void formatarCampos()  {
        try {
            MaskFormatter maskDataNascimento = new MaskFormatter("##/##/####");
            maskDataNascimento.install(formattedTxtDataNascimento);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.install(formattedTxtCpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            MaskFormatter cep = new MaskFormatter("#####-###");
            cep.install(formattedTxtCep);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    // Registra o usuário
    private void registerUser() {
        // Pegando dados gerais do cliente.
        String name = txtNome.getText();
        String cpf = formattedTxtCpf.getText();
        String birthDate = formattedTxtDataNascimento.getText();



        // Pegando dados do endereço
        String street = txtLogradouro.getText();
        String number = txtNumero.getText();
        String cep = formattedTxtCep.getText();
        String neighborhood = txtBairro.getText();
        String city = txtCidade.getText();
        String uf = txtUF.getText();

        // Pegando dados do usuário
        String username = txtNovoUsuario.getText();
        String password = String.valueOf(txtSenha.getPassword());
        String confirmPassword = String.valueOf(txtSenhaConfirma.getPassword());

        // Abrindo conta padrão para o cliente.

        if (name.isEmpty() || cpf.isEmpty() || birthDate.isEmpty() || street.isEmpty() || number.isEmpty() || cep.isEmpty() || neighborhood.isEmpty() || city.isEmpty() || uf.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "As senhas não conferem!");
            return;
        }
        int numberInt = Integer.parseInt(number);
        Endereco endereco = new Endereco(numberInt, cep, street, neighborhood, city, uf);
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(username);
        usuario.setSenhaUsuario(password);


        Cliente cliente = new Cliente();
        cliente.setNomeClient(name);
        cliente.setCpfCliente(cpf);
        cliente.setDataNascimentoCliente(birthDate);
        cliente.setEnderecoCliente(endereco);
        cliente.setUsuarioCliente(usuario);



        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.cadastrarCliente(cliente);

        // Fecha o diálogo

        // Faz o dispose() caso o cliente seja cadastrado com sucesso.
        if (clienteDAO.checagemDeSucesso == 1) {
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente!");
        }
    }
}
