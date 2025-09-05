package usuarios.testeConhecimentos.services;

import jakarta.transaction.Transactional;
import usuarios.testeConhecimentos.dtos.EnderecoDTO;
import usuarios.testeConhecimentos.dtos.EnderecoUsuarioNomeDTO;
import usuarios.testeConhecimentos.models.Endereco;
import usuarios.testeConhecimentos.models.Usuario;
import org.springframework.stereotype.Service;
import usuarios.testeConhecimentos.repositories.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;


    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<EnderecoUsuarioNomeDTO> mostrarEnderecos(){
        List<Endereco> endereco = enderecoRepository.findByOrderByIdAsc();

        return endereco.stream()
                .map(x -> new EnderecoUsuarioNomeDTO(x.getCidade(), x.getEstado(), x.getUsuario().getNome()))
                .toList();
    }

    public void cadastrarEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }
    public List<Endereco> mostrarEnderecosPorUsuarioId(long id){
        return enderecoRepository.findAllByUsuarioId(id);
    }

    @Transactional
    public Endereco adicionarEnderecoAoUsuario(Usuario usuario, Endereco endereco) {
        Endereco enderecoCriado = new Endereco(endereco.getEstado(), endereco.getCidade(), usuario);
        return enderecoRepository.save(enderecoCriado);
    }

    @Transactional
    public void removerEnderecoPorId(long enderecoId, long usuarioId ) {
        Optional<Endereco> result = enderecoRepository.findByIdAndUsuarioId(enderecoId, usuarioId);

        if (result.isPresent()) {
            enderecoRepository.deleteById(enderecoId);
        }
    }
}


