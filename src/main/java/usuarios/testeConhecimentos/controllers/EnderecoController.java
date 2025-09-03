package usuarios.testeConhecimentos.controllers;

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

    @GetMapping("/usuarios/{usuarioId}/enderecos")
    public List<Endereco> mostrarEnderecosDeUmUsuario(@PathVariable long usuarioId) {
        return enderecoService.mostrarEnderecosPorId(usuarioId);
    }

    @PostMapping("/usuarios/{usuarioId}/enderecos")
    public ResponseEntity<Endereco> adiconarEnderecoPorId(@PathVariable long usuarioId, @RequestBody Endereco endereco) {
        Optional<Usuario> usuarioOptional = usuarioService.encontrarUsuarioPorId(usuarioId);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Endereco enderecoAdicionado = enderecoService.adicionarEnderecoAoUsuario(usuario, endereco);

            return ResponseEntity.status(HttpStatus.CREATED).body(enderecoAdicionado);
        }
        return ResponseEntity.notFound().build();
    }
}

// HUGO,
