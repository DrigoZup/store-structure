package br.com.zup.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.com.zup.pojo.Cliente;

public class Main {

    private static final String MENU = "-------------------------------\n"
            + "      CADASTRO DE CLIENTES     \n" + "-------------------------------\n\n"
            + "1 - Cadastrar Cliente\n" + "2 - Buscar Cliente pelo CPF\n"
            + "3 - Listar todos os Clientes\n" + "4 - Atualizar dados do Cliente\n"
            + "5 - Deletar Cadastro\n" + "0 - Encerrar Programa\n";

    public static void cadastraCliente(Scanner input, List<Cliente> clientes) {

        System.out.println("Nome: ");
        String nome = input.next();
        System.out.println("CPF: ");
        Long cpf = input.nextLong();
        System.out.println("Idade: ");
        Byte idade = input.nextByte();
        System.out.println("Emai: ");
        String email = input.next();
        System.out.println("Telefone para contato: ");
        Long telefone = input.nextLong();
        System.out.println("Endereço: ");
        String endereco = input.next();

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setIdade(idade);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        
        clientes.add(cliente);

        System.out.println("O cliente " + cliente.getNome() + " cadastrado com sucesso!\n");
    }

    public static void listaClientePeloCpf(Scanner input, List<Cliente> clientes) {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();

        for(Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println(cliente.toString());
            }
        }
    }

    public static void listaClientes(List<Cliente> clientes) {

        System.out.println("-----------------------------------------\n"
                + "           LISTA DE CLIENTES             \n"
                + "-----------------------------------------\n");

        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    public static void atualizaDadosDoCliente(Scanner input, List<Cliente> clientes) {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();
        
        for(Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                System.out.println(
                        "Seguem os dados q podem ser atualizados, se não desejar atualizar,\n"
                        + "apenas preencha com o valor que ja existe!\n");
                System.out.println("Nome: ");
                String nome = input.next();
                System.out.println("Idade: ");
                Byte idade = input.nextByte();
                System.out.println("Emai: ");
                String email = input.next();
                System.out.println("Telefone para contato: ");
                Long telefone = input.nextLong();
                System.out.println("Endereço: ");
                String endereco = input.next();

                cliente.setNome(nome);
                cliente.setIdade(idade);
                cliente.setEmail(email);
                cliente.setTelefone(telefone);
                cliente.setEndereco(endereco);
            }
        }
        
        System.out.println("O cliente foi atualizado com sucesso");
    }

    public static void deletaCliente(Scanner input, List<Cliente> clientes) {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();

        
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                clientes.remove(i);
            }
        }

        System.out.println("O cliente foi removido com sucesso");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        List<Cliente> clientesSalvos = new ArrayList<Cliente>();
        
        String escolha = null;
        do {
            
        System.out.println(MENU);
        escolha = input.next();

        switch (escolha) {
            case "1":
                cadastraCliente(input, clientesSalvos);
                break;

            case "2":
                listaClientePeloCpf(input, clientesSalvos);
                break;

            case "3":
                listaClientes(clientesSalvos);
                break;

            case "4":
                atualizaDadosDoCliente(input, clientesSalvos);
                break;

            case "5":
                deletaCliente(input, clientesSalvos);
                break;

            case "0":
                System.out.println("O programa foi encerrado");
                break;

            default:
                System.out.println("Opção Inválida, tente novamente...");
                break;
        }
        } while (!escolha.equals("0"));

        input.close();
    }

}
