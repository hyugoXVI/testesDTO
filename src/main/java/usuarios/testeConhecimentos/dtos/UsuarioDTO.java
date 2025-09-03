package usuarios.testeConhecimentos.dtos;

import java.util.List;

public record UsuarioDTO(long id, String nome, String email, List<EnderecoDTO> enderecos) {
}
