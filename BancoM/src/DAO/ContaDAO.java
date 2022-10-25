package DAO;


import Entidades.Conta;
import Entidades.ContaCorrente;
import Entidades.ContaPoupanca;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class ContaDAO extends Conta {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    Random random = new Random();
    public int tipoContaDAO;
    ContaCorrente contaCorrente = new ContaCorrente();
    ContaPoupanca contaPoupanca = new ContaPoupanca();
    ArrayList<Integer> contas = new ArrayList<>();


    //Metodos para criar conta
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
    public void abrirNovaConta(String usuarioLogin) {
        String sql1 = "SELECT cod_Cliente FROM usuarios WHERE nome_usuario = ?";
        conn = new ConexaoDAO().conectarBD();
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, usuarioLogin);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int codCliente = rs.getInt("cod_Cliente");
                cadastrarConta(codCliente);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir nova conta: " + e.getMessage());
        }
    }

    public void puxarConta(int numConta) {
//        String sql1 = "SELECT cod_Cliente FROM contas WHERE numConta = ?";
        String sql2 = "SELECT * FROM contas WHERE numConta = ?";
        conn = new ConexaoDAO().conectarBD();
        try {
//            pstmt = conn.prepareStatement(sql1);
//            pstmt.setInt(1, numConta);
//            rs = pstmt.executeQuery();
//            rs.next();
//            int codCliente = rs.getInt("cod_Cliente");
//            pstmt.close();
//            rs.close();

            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, numConta);
            rs = pstmt.executeQuery();
            rs.next();
            numConta = rs.getInt("numConta");
            String agenciaConta = rs.getString("agenciaConta");
            double saldoConta = rs.getDouble("saldoDaConta");
            int tipoConta = rs.getInt("tipo_da_Conta");
            tipoContaDAO = tipoConta;
            pstmt.close();
            rs.close();

            if (tipoConta == 1) {
                contaCorrente.setNumConta(numConta);
                contaCorrente.setAgenciaConta(agenciaConta);
                contaCorrente.setSaldoConta(saldoConta);
                contaCorrente.setTipoConta(tipoConta);
            } else {
                contaPoupanca.setNumConta(numConta);
                contaPoupanca.setAgenciaConta(agenciaConta);
                contaPoupanca.setSaldoConta(saldoConta);
                contaPoupanca.setTipoConta(tipoConta);
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao puxar conta: " + e.getMessage());
        }
    }
    public int selecionarConta(String nomeUsuario) {
        String sql1 = "SELECT cod_Cliente FROM usuarios WHERE nome_usuario = ?";
        String sql2 = "SELECT numConta FROM contas WHERE cod_Cliente = ?";
        String sql3 = "SELECT tipo_da_Conta FROM contas WHERE numConta = ?";
        conn = new ConexaoDAO().conectarBD();

        int contaSelecionada = 0;
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, nomeUsuario);
            rs = pstmt.executeQuery();
            rs.next();
            int codCliente = rs.getInt("cod_Cliente");
            pstmt.close();
            rs.close();

            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, codCliente);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int numConta = rs.getInt("numConta");
                contas.add(numConta);
            }

            pstmt.close();
            rs.close();

            contaSelecionada = (Integer) JOptionPane.showInputDialog(null, "Selecione a conta", "Selecionar Conta", JOptionPane.QUESTION_MESSAGE, null, contas.toArray(), contas.get(0));

            pstmt = conn.prepareStatement(sql3);
            pstmt.setInt(1, contaSelecionada);
            rs = pstmt.executeQuery();
            rs.next();
            tipoContaDAO = rs.getInt("tipo_da_Conta");
            pstmt.close();
            rs.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao puxar conta (s): " + e.getMessage());
        }
        return contaSelecionada;
    }

    // Metodos de Conta Corrente
    public void saqueConta(double valor) {
        String sql = "UPDATE contas SET saldoDaConta = ? WHERE numConta = ?";
        conn = new ConexaoDAO().conectarBD();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, contaCorrente.getSaldoConta() - valor);
            pstmt.setInt(2, contaCorrente.getNumConta());
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao sacar: " + e.getMessage());
        }
    }
    public void depositoConta(double valor) {
        String sql = "UPDATE contas SET saldoDaConta = ? WHERE numConta = ?";
        conn = new ConexaoDAO().conectarBD();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, contaCorrente.getSaldoConta() + valor);
            pstmt.setInt(2, contaCorrente.getNumConta());
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao depositar: " + e.getMessage());
        }
    }
    public void transferenciaContaCorrente(double valor, int numContaDestino) {
        String sql1 = "UPDATE contas SET saldoDaConta = ? WHERE numConta = ?";
        String sql2 = "SELECT saldoDaConta FROM contas WHERE numConta = ?";
        String sql3 = "UPDATE contas SET saldoDaConta = ? WHERE numConta = ?";
        conn = new ConexaoDAO().conectarBD();
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setDouble(1, contaCorrente.getSaldoConta() - valor);
            pstmt.setInt(2, contaCorrente.getNumConta());
            pstmt.execute();
            pstmt.close();

            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, numContaDestino);
            rs = pstmt.executeQuery();
            rs.next();
            double saldoContaDestino = rs.getDouble("saldoDaConta");


            pstmt = conn.prepareStatement(sql3);
            pstmt.setDouble(1, saldoContaDestino + valor);
            pstmt.setInt(2, numContaDestino);
            pstmt.execute();
            pstmt.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao transferir: " + e.getMessage());
        }
    }

    // Metodos de Conta Poupanca
    public void transferenciaContaPoupanca(double valor, int numContaDestino) {
        String sql1 = "UPDATE contas SET saldoDaConta = ? WHERE numConta = ?";
        String sql2 = "SELECT saldoDaConta FROM contas WHERE numConta = ?";
        String sql3 = "UPDATE contas SET saldoDaConta = ? WHERE numConta = ?";

        conn = new ConexaoDAO().conectarBD();
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setDouble(1, contaPoupanca.getSaldoConta() - valor);
            pstmt.setInt(2, contaPoupanca.getNumConta());
            pstmt.execute();
            pstmt.close();

            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, numContaDestino);
            rs = pstmt.executeQuery();
            rs.next();
            double saldoContaDestino = rs.getDouble("saldoDaConta");


            pstmt = conn.prepareStatement(sql3);
            pstmt.setDouble(1, saldoContaDestino + valor);
            pstmt.setInt(2, numContaDestino);
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao transferir: " + e.getMessage());
        }
    }
}
