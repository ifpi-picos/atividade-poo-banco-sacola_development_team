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
    PreparedStatement pstmt, pstmt2;
    ResultSet rs;
    ArrayList<ClienteDTO> lista = new ArrayList<>();

    public void cadastrarCliente(ClienteDTO clienteDTO) {
        // Conexão com o banco de dados
        String sql = "INSERT INTO clientes (nome_Cliente, cpf_Cliente, data_Nascimento, endereco_Cliente) values (?, ?, ?, ?)";
        String sql2 = "INSERT INTO usuarios (nome_Usuario, senha_Usuario) values (?, ?)";
        conn = new ConexaoDAO().conectarBD();

        try {
            // Inserindo dados na tabela clientes
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, clienteDTO.getNome_Client());
            pstmt.setString(2, clienteDTO.getCpf_Cliente());
            pstmt.setString(3, clienteDTO.getDataNascimento_Cliente());
            pstmt.setString(4, clienteDTO.getEndereco_Cliente());

            pstmt.execute();
            pstmt.close();

            // Inserindo dados na tabela usuarios
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, clienteDTO.getNome_Usuario());
            pstmt2.setString(2, clienteDTO.getSenha_Usuario());

            pstmt2.execute();
            pstmt2.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + erro.getMessage());
        }
    }
    public ArrayList<ClienteDTO> pesquisarClientes() {
        String sql = "SELECT * FROM clientes";
        conn = new ConexaoDAO().conectarBD();

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ClienteDTO objClienteDTO = new ClienteDTO();
                objClienteDTO.setId_Cliente(rs.getInt("id_Cliente"));
                objClienteDTO.setNome_Client(rs.getString("nome_Cliente"));
                objClienteDTO.setCpf_Cliente(rs.getString("cpf_Cliente"));
                objClienteDTO.setDataNascimento_Cliente(rs.getString("data_Nascimento"));
                objClienteDTO.setEndereco_Cliente(rs.getString("endereco_Cliente"));

                lista.add(objClienteDTO);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar clientes: " + erro.getMessage());
        }
        return lista;
    }
}
