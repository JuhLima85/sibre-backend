package br.com.devsibre.Domain.Repository;

import br.com.devsibre.Domain.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

}
