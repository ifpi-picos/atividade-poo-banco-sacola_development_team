package DAO;

import DTO.ClienteDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    int id_Cliente_ID;
    ArrayList<ClienteDTO> lista = new ArrayList<>();

    public void cadastrarCliente(ClienteDTO clienteDTO) {
        // Conexão com o banco de dados
        String sql = "INSERT INTO clientes (nome_Cliente, cpf_Cliente, data_Nascimento) values (?, ?, ?)";
        String sql2 = "INSERT INTO usuarios (nome_Usuario, senha_Usuario, cod_Cliente) values (?, ?, ?)";
        String sql3 = "INSERT INTO endereco (logradouro, numero, bairro, cidade, uf, cod_Cliente) values (?, ?, ?, ?, ?, ?)";
        String sql4 = "select id_Clientes from clientes where cpf_Cliente = ?";
        String sql5 = "INSERT INTO contas (numConta, agenciaConta, saldoConta, tipo_da_Conta, cod_Cliente) values (?, ?, ?, ?, ?)";
        conn = new ConexaoDAO().conectarBD();

        try {
            // Inserindo dados na tabela clientes
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, clienteDTO.getNome_Client());
            pstmt.setString(2, clienteDTO.getCpf_Cliente());
            pstmt.setString(3, clienteDTO.getDataNascimento_Cliente());

            pstmt.execute();
            pstmt.close();

            // Pegando o id do cliente cadastrado
            pstmt = conn.prepareStatement(sql4);
            pstmt.setString(1, clienteDTO.getCpf_Cliente());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id_Cliente_ID = rs.getInt("id_Clientes");
            }

            // Inserindo dados na tabela usuários
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, clienteDTO.getNome_Usuario());
            pstmt.setString(2, clienteDTO.getSenha_Usuario());
            pstmt.setInt(3, id_Cliente_ID);


            pstmt.execute();
            pstmt.close();

            // Inserindo dados na tabela endereços
            pstmt = conn.prepareStatement(sql3);
            pstmt.setString(1, clienteDTO.getEndereco_Cliente().getLogradouro());
            pstmt.setInt(2, clienteDTO.getEndereco_Cliente().getNumero());
            pstmt.setString(3, clienteDTO.getEndereco_Cliente().getBairro());
            pstmt.setString(4, clienteDTO.getEndereco_Cliente().getCidade());
            pstmt.setString(5, clienteDTO.getEndereco_Cliente().getUf());
            pstmt.setInt(6, id_Cliente_ID);

            pstmt.execute();
            pstmt.close();

            // Inserindo dados na tabela contas


            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + erro.getMessage());
        }
    }
}
