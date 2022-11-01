package Entidades;

import VIEW.frmLoginVIEW;
import VIEW.frmRegistroVIEW;

import javax.swing.*;

public class Banco {

    public void menu() {
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Login","Cadastro", "Sair"}, "Login");
        switch (opcao) {
            case 0 -> {
                frmLoginVIEW login = new frmLoginVIEW(null);
                login.setVisible(true);
            }
            case 1 -> {
                frmRegistroVIEW registro = new frmRegistroVIEW(null);
                registro.setVisible(true);
                menu();
            }
            case 2 -> System.exit(0);
            default -> {
                JOptionPane.showMessageDialog(null, "Opção inválida");
                menu();
            }
        }
    }
}

