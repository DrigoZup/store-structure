package br.com.zup.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import br.com.zup.pojo.Cliente;

public class ClienteDAO {
    
    private EntityManager manager;
    
    public ClienteDAO() {
        this.manager = Persistence.createEntityManagerFactory("clientes").createEntityManager();
    }
    
    public boolean cadastraCliente(Cliente cliente) {
        
        try {
            
            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();
            
        } catch (EntityNotFoundException e) {
            System.err.println("Não foi possível cadastrar esse cliente");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public Cliente listaClientePeloCpf(Long cpf) {
        
        Cliente clienteEncontrado = new Cliente();
        
        try {

            clienteEncontrado = manager.find(Cliente.class, cpf);
            
        } catch (EntityNotFoundException e) {
            System.err.println("Cliente não encontrado");
            System.err.println(e.getMessage());
        }
        return clienteEncontrado;
    }
    
    @SuppressWarnings("unchecked")
    public List<Cliente> listaClientesNoBanco() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try {
            
            Query consulta = manager.createQuery("select c from Cliente as c");
            clientes = consulta.getResultList();
            
        } catch (EntityNotFoundException e) {
            System.err.println("Não foi possível listar os clientes");
            System.err.println(e.getMessage());
        }
        return clientes;
    }
    
    public boolean atualizaDadosCliente(Cliente clienteAtualizado, Long cpf) {
        Cliente cliente = new Cliente();
        
        try {
            cliente = manager.find(Cliente.class, cpf);
            
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setIdade(clienteAtualizado.getIdade());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            
            manager.getTransaction().begin();
            manager.merge(cliente);
            manager.getTransaction().commit();
            
        } catch (EntityNotFoundException e) {
            System.err.println("Não foi possível atualizar os dados do cliente");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public boolean deletaCliente(Long cpf) {
        
        try {
            Cliente clienteRemovido = manager.find(Cliente.class, cpf);
            manager.getTransaction().begin();
            manager.remove(clienteRemovido);
            manager.getTransaction().commit();
            
        } catch (EntityNotFoundException e) {
            System.err.println("Não foi possível deletar o cliente");
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }
}
