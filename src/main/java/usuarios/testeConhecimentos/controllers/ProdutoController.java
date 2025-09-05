package usuarios.testeConhecimentos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarios.testeConhecimentos.dtos.ProdutoDTO;
import usuarios.testeConhecimentos.dtos.ProdutoUsuarioNomeDTO;
import usuarios.testeConhecimentos.models.Produto;
import usuarios.testeConhecimentos.models.Usuario;
import usuarios.testeConhecimentos.services.ProdutoService;
import usuarios.testeConhecimentos.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController

public class ProdutoController {
    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    public ProdutoController(ProdutoService produtoService, UsuarioService usuarioService) {
        this.produtoService = produtoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/produtos")
    public List<ProdutoUsuarioNomeDTO> mostrarProdutos(){
        return produtoService.mostrarProdutos();
    }

    @GetMapping("/usuarios/{usuarioId}/produtos")
    public List<ProdutoDTO> mostrarProdutosDeUmUsuario(@PathVariable long usuarioId){
        return produtoService.mostrarProdutoPorUsuarioId(usuarioId);
    }

    @PostMapping("/usuarios/{usuarioId}/produtos")
    public ResponseEntity<Produto> adicionarProdutoAoUsuario(@PathVariable long usuarioId, @RequestBody Produto produto){
        Optional<Usuario> usuarioOptional = usuarioService.encontrarUsuarioPorId(usuarioId);

        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.adicionarProdutoAoUsuario(usuario, produto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{usuarioId}/produtos/{produtoId}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable long usuarioId, @PathVariable long produtoId){
        produtoService.removerProdutoComId(produtoId, usuarioId);
        return ResponseEntity.noContent().build();
    }

}
