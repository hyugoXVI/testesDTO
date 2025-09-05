package usuarios.testeConhecimentos.controllers;

import usuarios.testeConhecimentos.dtos.EnderecoDTO;
import usuarios.testeConhecimentos.dtos.EnderecoUsuarioNomeDTO;
import usuarios.testeConhecimentos.models.Endereco;
import usuarios.testeConhecimentos.models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarios.testeConhecimentos.services.EnderecoService;
import usuarios.testeConhecimentos.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final UsuarioService usuarioService;

    public EnderecoController(EnderecoService enderecoService, UsuarioService usuarioService) {
        this.enderecoService = enderecoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/enderecos")
    public List<EnderecoUsuarioNomeDTO> mostrarEnderecos(){
        return enderecoService.mostrarEnderecos();
    }

    @GetMapping("/usuarios/{usuarioId}/enderecos")
    public List<Endereco> mostrarEnderecosDeUmUsuario(@PathVariable long usuarioId) {
        return enderecoService.mostrarEnderecosPorUsuarioId(usuarioId);
    }

    @PostMapping("/usuarios/{usuarioId}/enderecos")
    public ResponseEntity<Endereco> adiconarEnderecoAoUsuario(@PathVariable long usuarioId, @RequestBody Endereco endereco) {
        Optional<Usuario> usuarioOptional = usuarioService.encontrarUsuarioPorId(usuarioId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Endereco enderecoAdicionado = enderecoService.adicionarEnderecoAoUsuario(usuario, endereco);

            return ResponseEntity.status(HttpStatus.CREATED).body(enderecoAdicionado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{usuarioId}/enderecos/{enderecoId}")
    public ResponseEntity<Void> deletarEnderecoPorId(@PathVariable long usuarioId, @PathVariable long enderecoId){
        enderecoService.removerEnderecoPorId(enderecoId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}


