package Entidades;

import DAO.ContaDAO;

import javax.swing.*;

public class ContaCorrente extends Conta {
    private double chequeEspecial;

    @Override
    public void depositar(double valor, int numConta) {
        ContaDAO contaDAO = new ContaDAO();
        if (valor > 0) {
            contaDAO.puxarConta(numConta);
            contaDAO.depositoConta(valor);
            JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
        }
    }
    @Override
    public void sacar(double valor, int numConta) {
        ContaDAO contaDAO = new ContaDAO();
        int opcao;
        JOptionPane.showMessageDialog(null, "Saldo: " + contaDAO.puxarSaldoConta(numConta));
        if (valor < contaDAO.puxarSaldoConta(numConta)) {
            contaDAO.puxarConta(numConta);
            contaDAO.saqueConta(valor);

            opcao = JOptionPane.showOptionDialog(null,
                    "Deseja receber o comprovante?", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new String[]{"Sim", "Não"}, "Comprovante");
            if (opcao == 0) {
                opcao = JOptionPane.showOptionDialog(null,
                        "Por qual via deseja receber?", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new String[]{"SMS", "EMAIL"}, "Comprovante");
                switch (opcao) {
                    case 0 -> {
                        Sms sms = new Sms();
                        sms.enviarNotificacao("Saque", valor);
                    }
                    case 1 -> {
                        Email email = new Email();
                        email.enviarNotificacao("Saque", valor);
                    }

                }
            }
        } else {
            opcao = JOptionPane.showOptionDialog(null,
                    "Saldo insuficiente! Deseja usar seu cheque especial?", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new String[]{"Sim", "Não"}, "Limite insuficiente");

            if (opcao == 0) {
                double operacao = contaDAO.puxarSaldoConta(numConta) - valor;
                contaDAO.puxarConta(numConta);

                if (contaDAO.puxarSaldoConta(numConta) <= 0) {
                    contaDAO.atualizarChequeEspecial(operacao);
                } else {
                    contaDAO.saqueConta(-(operacao));
                    contaDAO.atualizarChequeEspecial(operacao);
                }

                opcao = JOptionPane.showOptionDialog(null,
                        "Deseja receber o comprovante?", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new String[]{"Sim", "Não"}, "Comprovante");
                if (opcao == 0) {
                    opcao = JOptionPane.showOptionDialog(null,
                            "Por qual via deseja receber?", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, new String[]{"SMS", "EMAIL"}, "Comprovante");
                    switch (opcao) {
                        case 0 -> {
                            Sms sms = new Sms();
                            sms.enviarNotificacao("Saque", valor);
                        }
                        case 1 -> {
                            Email email = new Email();
                            email.enviarNotificacao("Saque", valor);
                        }

                    }
                }
            }

        }
    }
    @Override
    public void transferir(double valor, int numeroConta, int numConta) {
        ContaDAO contaDAO = new ContaDAO();
        if (valor < contaDAO.puxarSaldoConta(numConta)) {
            contaDAO.puxarConta(numConta);
            contaDAO.transferenciaContaCorrente(valor, numeroConta);

            int opcao = JOptionPane.showOptionDialog(null,
                    "Deseja receber o comprovante?", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new String[]{"Sim","Não"}, "Comprovante");
            if (opcao == 0){
                opcao = JOptionPane.showOptionDialog(null,
                        "Por qual via deseja receber?", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, new String[]{"SMS","EMAIL"}, "Comprovante");
                switch (opcao) {
                    case 0 -> {
                        Sms sms = new Sms();
                        sms.enviarNotificacao("Transferência", valor);
                    }
                    case 1 -> {
                        Email email = new Email();
                        email.enviarNotificacao("Transferência", valor);
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
        }
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }
    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }
}
