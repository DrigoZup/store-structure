package br.com.zup.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.zup.factory.ConnectionFactory;
import br.com.zup.pojo.Client;

public class ClientDAO {

    private Connection connection;

    public ClientDAO() {

        this.connection = new ConnectionFactory().getConnection();
    }

    private static void listConstructor(ResultSet rs, List<Client> listClients)
            throws SQLException {
        while (rs.next()) {
            Client client = new Client();

            client.setName(rs.getString("nome"));
            client.setEmail(rs.getString("email"));
            client.setAge(rs.getByte("idade"));
            client.setAddress(rs.getString("endereco"));
            client.setCpf(rs.getLong("cpf"));
            client.setPhoneNumber(rs.getLong("telefone"));
            listClients.add(client);
        }

    }

    public boolean signUpClient(Client client) throws SQLException {

        String querySQL = "insert into clientes" + "(nome, idade, endereco, cpf, telefone, email)"
                + "values (?, ?, ?, ?, ?, ?)";
        try {

            PreparedStatement comando = this.connection.prepareStatement(querySQL);

            comando.setString(1, client.getName());
            comando.setByte(2, client.getAge());
            comando.setString(3, client.getAddress());
            comando.setLong(4, client.getCpf());
            comando.setLong(5, client.getPhoneNumber());
            comando.setString(6, client.getEmail());

            comando.execute();
            comando.close();
        } catch (SQLException exception) {
            System.err.println("Problemas ao cadastrar a cliente...");
            System.err.println(exception.getMessage());
        }
        return true;
    }

    public List<Client> listAllClients() throws SQLException {

        List<Client> listClients = new ArrayList<>();

        String querySQL = "select * from clientes";

        try {
            PreparedStatement command = this.connection.prepareStatement(querySQL);

            ResultSet resultQuery = command.executeQuery();

            listConstructor(resultQuery, listClients);

            command.close();
        } catch (SQLException exception) {
            System.err.println("Erro na listagem...");
            System.err.println(exception.getMessage());

        }

        return listClients;
    }

    public Client listClientByCpf(Long cpf) throws SQLException {
        Client client = new Client();
        String querySQL = "select * from clientes where cpf = ?";

        try {

            PreparedStatement command = this.connection.prepareStatement(querySQL);
            command.setLong(1, cpf);

            ResultSet rs = command.executeQuery();

            while (rs.next()) {
                client.setName(rs.getString("nome"));
                client.setAge(rs.getByte("idade"));
                client.setCpf(rs.getLong("cpf"));
                client.setEmail(rs.getString("email"));
                client.setAddress(rs.getString("endereco"));
                client.setPhoneNumber(rs.getLong("telefone"));
            }
            command.close();

        } catch (SQLException exception) {
            System.err.println("Erro na listagem...");
            System.err.println(exception.getMessage());
        }

        return client;
    }

    public boolean updateClient(Client clientUpdated, Long cpf) throws SQLException {
        String querySQL = "update from clientes where cpf = ?";

        try {
            PreparedStatement command = this.connection.prepareStatement(querySQL);
            command.setLong(1, cpf);

            command.execute();
            command.close();

        } catch (SQLException exception) {
            System.err.println("erro ao encontrar cliente");
            System.err.println(exception.getMessage());
        }
        return true;
    }

    public boolean deleteClient(Long cpf) {
        String querySQL = "delete from clientes where cpf = ?";

        try {
            PreparedStatement command = this.connection.prepareStatement(querySQL);
            command.setLong(1, cpf);

            command.execute();
            command.close();

        } catch (SQLException exception) {
            System.err.println("erro ao encontrar cliente");
            System.err.println(exception.getMessage());
        }
        return true;
    }
}
