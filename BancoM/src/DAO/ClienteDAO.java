package DAO;

import Entidades.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO extends Cliente {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    ArrayList<Cliente> lista = new ArrayList<>();

    public void cadastrarCliente(Cliente cliente) {
        // Conexão com o banco de dados
        String sql = "INSERT INTO clientes (nomeCliente, cpfCliente, dataNascimento) values (?, ?, ?)";
        String sql2 = "INSERT INTO usuarios (nomeUsuario, senhaUsuario, codCliente) values (?, ?, ?)";
        String sql3 = "INSERT INTO endereco (logradouro, numero, bairro, cidade, uf, codCliente) values (?, ?, ?, ?, ?, ?)";
        String sql4 = "select idClientes from clientes where cpfCliente = ?";
        String sql5 = "INSERT INTO contas (numConta, agenciaConta, saldoConta, tipodaConta, codCliente) values (?, ?, ?, ?, ?)";
        conn = new ConexaoDAO().conectarBD();

        try {
            // Inserindo dados na tabela clientes
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cliente.getNomeClient());
            pstmt.setString(2, cliente.getCpfCliente());
            pstmt.setString(3, cliente.getDataNascimentoCliente());

            pstmt.execute();
            pstmt.close();

            // Pegando o id do cliente cadastrado
            pstmt = conn.prepareStatement(sql4);
            pstmt.setString(1, cliente.getCpfCliente());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                setIdCliente(rs.getInt("idClientes"));
            }

            // Inserindo dados na tabela usuários
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, cliente.getUsuarioCliente().getNomeUsuario());
            pstmt.setString(2, cliente.getUsuarioCliente().getSenhaUsuario());
            pstmt.setInt(3, getIdCliente());


            pstmt.execute();
            pstmt.close();

            // Inserindo dados na tabela endereços
            pstmt = conn.prepareStatement(sql3);
            pstmt.setString(1, cliente.getEnderecoCliente().getLogradouro());
            pstmt.setInt(2, cliente.getEnderecoCliente().getNumero());
            pstmt.setString(3, cliente.getEnderecoCliente().getBairro());
            pstmt.setString(4, cliente.getEnderecoCliente().getCidade());
            pstmt.setString(5, cliente.getEnderecoCliente().getUf());
            pstmt.setInt(6, getIdCliente());

            pstmt.execute();
            pstmt.close();

            // Inserindo dados na tabela contas
            ContaDAO contaDAO = new ContaDAO();
            contaDAO.cadastrarConta(getIdCliente());


            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + erro.getMessage());
        }
    }
}
