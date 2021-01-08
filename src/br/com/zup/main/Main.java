package br.com.zup.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import br.com.zup.DAO.ClientDAO;
import br.com.zup.pojo.Client;

public class Main {

    private static final String MENU = "-------------------------------\n"
            + "      CADASTRO DE CLIENTES     \n" + "-------------------------------\n\n"
            + "1 - Cadastrar Cliente\n" + "2 - Buscar Cliente pelo CPF\n"
            + "3 - Listar todos os Clientes\n" + "4 - Atualizar dados do Cliente\n"
            + "5 - Deletar Cadastro\n" + "0 - Encerrar Programa\n";

    public static void signUpClient(Scanner input) throws SQLException {

        System.out.println("Nome: ");
        String name = input.next();
        System.out.println("CPF: ");
        Long cpf = input.nextLong();
        System.out.println("Idade: ");
        Byte age = input.nextByte();
        System.out.println("Emai: ");
        String email = input.next();
        System.out.println("Telefone para contato: ");
        Long phoneNumber = input.nextLong();
        System.out.println("Endereço: ");
        String address = input.next();

        Client cliente = new Client();

        cliente.setName(name);
        cliente.setCpf(cpf);
        cliente.setAge(age);
        cliente.setEmail(email);
        cliente.setPhoneNumber(phoneNumber);
        cliente.setAddress(address);

        ClientDAO method = new ClientDAO();
        method.signUpClient(cliente);

        System.out.println("O cliente " + cliente.getName() + " cadastrado com sucesso!\n");
    }

    public static void listClientByCpf(Scanner input) throws SQLException {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();

        ClientDAO clientDB = new ClientDAO();
        Client clientReturned = clientDB.listClientByCpf(cpf);

        System.out.println(clientReturned.toString());
    }

    public static void listAllClients() throws SQLException {
        ClientDAO clientsDB = new ClientDAO();
        List<Client> resultList = clientsDB.listAllClients();

        System.out.println("-----------------------------------------\n"
                + "           LISTA DE CLIENTES             \n"
                + "-----------------------------------------\n");

        for (Client client : resultList) {
            System.out.println(client.toString());
        }
    }

    public static void updateClient(Scanner input) throws SQLException {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();
        
        System.out.println(
                "Seguem os dados q podem ser atualizados, se não desejar atualizar,\n"
                + "apenas preencha com o valor que ja existe!\n");
        System.out.println("Nome: ");
        String name = input.next();
        System.out.println("Idade: ");
        Byte age = input.nextByte();
        System.out.println("Emai: ");
        String email = input.next();
        System.out.println("Telefone para contato: ");
        Long phoneNumber = input.nextLong();
        System.out.println("Endereço: ");
        String address = input.next();

        Client cliente = new Client();

        cliente.setName(name);
        cliente.setAge(age);
        cliente.setEmail(email);
        cliente.setPhoneNumber(phoneNumber);
        cliente.setAddress(address);
        
        ClientDAO metodo = new ClientDAO();
        metodo.updateClient(cliente, cpf);
        
        System.out.println("O cliente foi atualizado com sucesso");
    }

    public static void deleteClient(Scanner input) {
        System.out.println("Digite o CPF do cliente: ");
        Long cpf = input.nextLong();

        ClientDAO clienteDB = new ClientDAO();
        clienteDB.deleteClient(cpf);

        System.out.println("O cliente foi removido com sucesso");
    }

    public static void main(String[] args) throws SQLException {

        Scanner input = new Scanner(System.in);
        String key = null;
        do {
            
        System.out.println(MENU);
        key = input.next();

        switch (key) {
            case "1":
                signUpClient(input);
                break;

            case "2":
                listClientByCpf(input);
                break;

            case "3":
                listAllClients();
                break;

            case "4":
                updateClient(input);
                break;

            case "5":
                deleteClient(input);
                break;

            case "0":
                break;

            default:
                System.out.println("Opção Inválida, tente novamente...");
                break;
        }
        } while (!key.equals("0"));

        input.close();
    }
}
