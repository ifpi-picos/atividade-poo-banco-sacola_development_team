package DAO;

import DTO.UsuarioDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    Connection conn;

    public ResultSet autenticarUsuario(UsuarioDTO objUsuarioDTO) {
        conn = new ConexaoDAO().conectarBD();

        try {

            String sql = "Select * from usuarios where nomeusuario = ? and senhausuario = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, objUsuarioDTO.getNomeUsuario());
            pstm.setString(2, objUsuarioDTO.getSenhaUsuario());

            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO - Erro ao autenticar usuário: "
                    + e.getMessage());
            return null;
        }
    }
}
