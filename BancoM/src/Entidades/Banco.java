package Entidades;

import VIEW.frmLoginVIEW;
import VIEW.frmRegistroVIEW;

import javax.swing.*;

public class Banco {

    public void menu() {
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Login","Cadastro", "Sair"}, "Login");
        switch (opcao) {
            case 0:
                frmLoginVIEW login = new frmLoginVIEW(null);
                break;
            case 1:
                frmRegistroVIEW registro = new frmRegistroVIEW(null);
                menu();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
                menu();
                break;
        }
    }
    public void mainMenu() {
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Login","Cadastro", "Sair"}, "Login");
        switch (opcao) {
            case 0:

            case 1:

            case 2:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida");
                mainMenu();
                break;
        }
    }



    }

