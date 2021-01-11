package br.com.zup.store.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.store.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    boolean existsByCpf(Long cpf);
    
    Optional<Client> findByCpf(Long cpf);
}
