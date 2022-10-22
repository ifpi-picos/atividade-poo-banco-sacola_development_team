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
                mainMenu();
            }
            case 1 -> {
                frmRegistroVIEW registro = new frmRegistroVIEW(null);
                menu();
            }
            case 2 -> System.exit(0);
            default -> {
                JOptionPane.showMessageDialog(null, "Opção inválida");
                menu();
            }
        }
    }
    public void mainMenu() {
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Banco", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Login","Cadastro", "Sair"}, "Login");
        switch (opcao) {
            case 0:
                Sacar();
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

    private void Sacar() {
        tipoConta();
    }

    private void tipoConta() {

    }


}

