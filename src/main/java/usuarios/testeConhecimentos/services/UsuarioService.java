package usuarios.testeConhecimentos.services;

import usuarios.testeConhecimentos.dtos.EnderecoDTO;
import usuarios.testeConhecimentos.dtos.UsuarioCadastrarDTO;
import usuarios.testeConhecimentos.dtos.UsuarioDTO;
import usuarios.testeConhecimentos.dtos.UsuarioLoginDTO;
import jakarta.transaction.Transactional;
import usuarios.testeConhecimentos.models.Endereco;
import usuarios.testeConhecimentos.models.Usuario;
import org.springframework.stereotype.Service;
import usuarios.testeConhecimentos.repositories.UsuarioRepository;
import usuarios.testeConhecimentos.validator.UsuarioValidator;

import java.util.List;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoService enderecoService;
    public UsuarioService(UsuarioRepository usuarioRepository, EnderecoService enderecoService) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoService = enderecoService;
    }

    @Transactional
    public Usuario registrarUsuario(UsuarioCadastrarDTO dto){
        UsuarioValidator.validarEmail(dto.getEmail());
        UsuarioValidator.validarNome(dto.getNome());
        UsuarioValidator.validarSenha(dto.getSenha());

        Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), dto.getSenha());
        usuarioRepository.save(usuario);

        for (EnderecoDTO enderecoDTO : dto.getEnderecos()){
            Endereco endereco = new Endereco(enderecoDTO.estado(), enderecoDTO.cidade(), usuario);
            enderecoService.cadastrarEndereco(endereco);
        }

        return usuario;
    }


    // Só para ter uma ideia e consultar algumas coisas.
    public List<Usuario> mostrarUsuarios(){
        return usuarioRepository.findAllByOrderByIdAsc();
    }

    public Optional<UsuarioDTO> encontrarUsuarioDTOPorId(long id){
        Optional<Usuario> result = usuarioRepository.findById(id);

        if (result.isPresent()){
            Usuario usuario = result.get();
            List<EnderecoDTO>enderecoDTO = usuario.getEnderecos()
                    .stream()
                    .map(x -> new EnderecoDTO(x.getCidade(), x.getEstado()))
                    .toList();

            UsuarioDTO dto = new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(),enderecoDTO);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<Usuario> encontrarUsuarioPorId(long id){
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioLoginDTO> loginUsuario(String email, String senha){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();

            if (usuario.getSenha().equals(senha)){
                return Optional.of(new UsuarioLoginDTO(usuario.getEmail(), usuario.getSenha()));
            }
        }
        return Optional.empty();
    }

}
