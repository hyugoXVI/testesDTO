package usuarios.testeConhecimentos.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import usuarios.testeConhecimentos.dtos.ProdutoDTO;
import usuarios.testeConhecimentos.dtos.ProdutoUsuarioNomeDTO;
import usuarios.testeConhecimentos.models.Produto;
import usuarios.testeConhecimentos.models.Usuario;
import usuarios.testeConhecimentos.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoUsuarioNomeDTO> mostrarProdutos(){
        List<Produto> produto = produtoRepository.findAll();

        return produto.stream()
                .map(x -> new ProdutoUsuarioNomeDTO(x.getNome(), x.getPreco(), x.getUsuario().getNome()))
                .toList();
    }


    public List<ProdutoDTO> mostrarProdutoPorUsuarioId(long id){
        List<Produto> produtosUsuario = produtoRepository.findAllByUsuarioId(id);
        return produtosUsuario.stream()
                .map(x -> new ProdutoDTO(x.getNome(), x.getPreco()))
                .toList();
    }

    @Transactional
    public Produto adicionarProdutoAoUsuario(Usuario usuario, Produto produto){
        Produto produtoCriado = new Produto(produto.getNome(), produto.getPreco(), usuario);
        return produtoRepository.save(produtoCriado);
    }

    @Transactional
    public void removerProdutoComId(long produtoId, long usuarioId){
        Optional<Produto> produto = produtoRepository.findByIdAndUsuarioId(produtoId, usuarioId);

        if (produto.isPresent()){
            produtoRepository.deleteById(produtoId);
        }
    }
}
