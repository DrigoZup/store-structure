package br.com.zup.main;

import java.util.List;
import java.util.Scanner;
import br.com.zup.DAO.ClienteDAO;
import br.com.zup.pojo.Cliente;

public class Main {

    private static final String MENU = "-------------------------------\n"
            + "      CADASTRO DE CLIENTES     \n" + "-------------------------------\n\n"
            + "1 - Cadastrar Cliente\n" + "2 - Buscar Cliente pelo CPF\n"
            + "3 - Listar todos os Clientes\n" + "4 - Atualizar dados do Cliente\n"
            + "5 - Deletar Cadastro\n" + "0 - Encerrar Programa\n";

    public static void cadastraCliente(Scanner input) {

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

        ClienteDAO cadastro = new ClienteDAO();
        cadastro.cadastraCliente(cliente);

        System.out.println("O cliente " + cliente.getNome() + " cadastrado com sucesso!\n");
    }

    public static void listaClientePeloCpf(Scanner input) {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();

        ClienteDAO clienteDB = new ClienteDAO();
        Cliente clienteEncontrado = clienteDB.listaClientePeloCpf(cpf);

        System.out.println(clienteEncontrado.toString());
    }

    public static void listaClientes() {
        ClienteDAO clientesDB = new ClienteDAO();
        List<Cliente> listagem = clientesDB.listaClientesNoBanco();

        System.out.println("-----------------------------------------\n"
                + "           LISTA DE CLIENTES             \n"
                + "-----------------------------------------\n");

        for (Cliente cliente : listagem) {
            System.out.println(cliente.toString());
        }
    }

    public static void atualizaDadosDoCliente(Scanner input) {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();
        
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

        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setIdade(idade);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        
        ClienteDAO metodo = new ClienteDAO();
        metodo.atualizaDadosCliente(cliente, cpf);
        
        System.out.println("O cliente foi atualizado com sucesso");
    }

    public static void deletaCliente(Scanner input) {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();

        ClienteDAO clienteDB = new ClienteDAO();
        clienteDB.deletaCliente(cpf);

        System.out.println("O cliente foi removido com sucesso");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String escolha = null;
        do {
            
        System.out.println(MENU);
        escolha = input.next();

        switch (escolha) {
            case "1":
                cadastraCliente(input);
                break;

            case "2":
                listaClientePeloCpf(input);
                break;

            case "3":
                listaClientes();
                break;

            case "4":
                atualizaDadosDoCliente(input);
                break;

            case "5":
                deletaCliente(input);
                break;

            case "0":
                break;

            default:
                System.out.println("Opção Inválida, tente novamente...");
                break;
        }
        } while (!escolha.equals("0"));

        input.close();
    }
}


