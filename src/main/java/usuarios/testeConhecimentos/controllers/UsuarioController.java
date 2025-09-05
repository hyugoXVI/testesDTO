package usuarios.testeConhecimentos.controllers;

import usuarios.testeConhecimentos.dtos.UsuarioCadastrarDTO;
import usuarios.testeConhecimentos.dtos.UsuarioDTO;
import usuarios.testeConhecimentos.dtos.UsuarioLoginDTO;
import usuarios.testeConhecimentos.models.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarios.testeConhecimentos.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> mostrarUsuarios(){
        return usuarioService.mostrarUsuarios();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody UsuarioCadastrarDTO usuarioCadastrarDTO) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuarioCadastrarDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginDTO> loginUsuario(@RequestBody UsuarioLoginDTO dto) {
        Optional<UsuarioLoginDTO> result = usuarioService.loginUsuario(dto.getEmail(), dto.getSenha());
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/encontrarUsuario/{usuarioId}")
    public Optional<Usuario> encontrarUsuarioPorId(@PathVariable long usuarioId){
        return usuarioService.encontrarUsuarioPorId(usuarioId);
    }


    @GetMapping("/encontrarUsuarioDTO/{usuarioId}")
    public Optional<UsuarioDTO> encontrarUsuarioDTOPorId(@PathVariable long usuarioId){
        return usuarioService.encontrarUsuarioDTOPorId(usuarioId);
    }

}
