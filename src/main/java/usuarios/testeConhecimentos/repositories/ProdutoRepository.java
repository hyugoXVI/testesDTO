package usuarios.testeConhecimentos.repositories;

import usuarios.testeConhecimentos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByOrderByIdAsc();
    Optional<Produto> findByIdAndUsuarioId(long produtoId, long usuarioId);
    List<Produto> findAllByUsuarioId(long id);
}
