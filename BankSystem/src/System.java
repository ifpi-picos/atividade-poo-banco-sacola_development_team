import Modulos.Account;
import Modulos.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class System {
    private static int exibeMenu(List<Integer> opcoes) {
        Object[] optionsArray = opcoes.toArray();
        int optionSelection = JOptionPane.showOptionDialog(null,
                "1. Iniciar Conta \n2. Exibir Contas \n3.  \n4. \n5.  \n6. Encerrar",
                "Selecione",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                optionsArray, null);
        return optionSelection;
    }
    public static void main(String[] args) {

        List<Integer> opcoes = new ArrayList<>();
        opcoes.add(1);
        opcoes.add(2);
        opcoes.add(3);
        opcoes.add(4);
        opcoes.add(5);
        opcoes.add(6);

        int optionSelection = 1;
        while (opcoes.get(optionSelection) != 6) {
            optionSelection = exibeMenu(opcoes);
            if (opcoes.get(optionSelection) == 1) {
                iniciarConta();
            } else if (opcoes.get(optionSelection) == 2) {
                Account.exibirConta();
            } else if (opcoes.get(optionSelection) == 3) {

            } else if (opcoes.get(optionSelection) == 4) {

            }
        }
    }

    private static void iniciarConta() {
        String nomeCliente = JOptionPane.showInputDialog("Informe seu nome: ");
        String dataDeNasc = JOptionPane.showInputDialog("Informe Sua data de nascimento:");
        String cpfCliente = JOptionPane.showInputDialog("Informe seu cpf: ");
        String enderecoCliente = JOptionPane.showInputDialog("Informe seu endereço: ");

        Client novoClient = new Client(nomeCliente, dataDeNasc, cpfCliente, enderecoCliente);
        Account.abrirConta(novoClient);

    }
}
