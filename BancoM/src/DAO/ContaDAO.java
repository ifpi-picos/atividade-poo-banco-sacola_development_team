package DAO;



import Entidades.Conta;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ContaDAO extends Conta {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    Random random = new Random();

    private static Integer lerTipoConta() {
        String menu = "1 - Conta Corrente\n2 - Conta Poupança";
        String tipoConta = JOptionPane.showInputDialog(null, menu, "Tipo de Conta", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(tipoConta);
    }

    public void cadastrarConta(int codCliente) {
        String sql = "INSERT INTO contas (numConta, agenciaConta, saldoDaConta, tipo_da_Conta, cod_Cliente) values (?, ?, ?, ?, ?)";
        conn = new ConexaoDAO().conectarBD();

        // Gerando dados da conta
        int numConta = random.nextInt(999999);
        String agenciaConta = "0001-1";
        double saldoConta = 0;
        int tipoConta = lerTipoConta();



        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, numConta);
            pstmt.setString(2, agenciaConta);
            pstmt.setDouble(3, saldoConta);
            pstmt.setInt(4, tipoConta);
            pstmt.setInt(5, codCliente);


            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar conta: " + e.getMessage());
        }
    }
}
