package DAO;

import DTO.ClienteDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO extends ClienteDTO {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    ArrayList<ClienteDTO> lista = new ArrayList<>();

    public void cadastrarCliente(ClienteDTO clienteDTO) {
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
            pstmt.setString(1, clienteDTO.getNomeClient());
            pstmt.setString(2, clienteDTO.getCpfCliente());
            pstmt.setString(3, clienteDTO.getDataNascimentoCliente());

            pstmt.execute();
            pstmt.close();

            // Pegando o id do cliente cadastrado
            pstmt = conn.prepareStatement(sql4);
            pstmt.setString(1, clienteDTO.getCpfCliente());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                setIdCliente(rs.getInt("idClientes"));
            }

            // Inserindo dados na tabela usuários
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, clienteDTO.getUsuarioCliente().getNomeUsuario());
            pstmt.setString(2, clienteDTO.getUsuarioCliente().getSenhaUsuario());
            pstmt.setInt(3, getIdCliente());


            pstmt.execute();
            pstmt.close();

            // Inserindo dados na tabela endereços
            pstmt = conn.prepareStatement(sql3);
            pstmt.setString(1, clienteDTO.getEnderecoCliente().getLogradouro());
            pstmt.setInt(2, clienteDTO.getEnderecoCliente().getNumero());
            pstmt.setString(3, clienteDTO.getEnderecoCliente().getBairro());
            pstmt.setString(4, clienteDTO.getEnderecoCliente().getCidade());
            pstmt.setString(5, clienteDTO.getEnderecoCliente().getUf());
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
