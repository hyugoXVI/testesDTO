package usuarios.testeConhecimentos.repositories;

import usuarios.testeConhecimentos.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByOrderByIdAsc();
    List<Endereco> findAllByUsuarioId(long usuarioId);
    Optional<Endereco> findByIdAndUsuarioId(long enderecoId, long usuarioId);
}
