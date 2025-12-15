package br.com.devsibre.Service;

import br.com.devsibre.Domain.Entity.Parentescos;
import br.com.devsibre.Domain.Entity.Pessoa;
import br.com.devsibre.Domain.Repository.PessoaRepository;
import br.com.devsibre.Service.Inteface.PessoaService;
import br.com.devsibre.error.BusinessException;
import br.com.devsibre.util.Mensagens;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    @Override
    public List<Pessoa> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return pessoaRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        }
        return pessoaRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome);
    }

    @Override
    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new BusinessException(Mensagens.pessoa.NAO_EXISTE));
    }

    @Override
    public Pessoa criarVinculo(Long id1, Long id2, String tipo) {
        Pessoa p1 = buscarPorId(id1);
        Pessoa p2 = buscarPorId(id2);

        boolean jaExiste = p1.getParentescos().stream()
                .anyMatch(par -> par.getPessoaRelacionada().getId().equals(p2.getId()));

        if (jaExiste) {
            throw new BusinessException(Mensagens.pessoa.RELACIONAMENTO_DUPLICADO);
        }

        Parentescos parentesco = new Parentescos(tipo, p1, p2);
        p1.getParentescos().add(parentesco);

        return pessoaRepository.save(p1);
    }

    @Override
    public void deletar(Long id) {
        Pessoa pessoa = buscarPorId(id);
        pessoaRepository.delete(pessoa);
    }

    @Override
    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = buscarPorId(id);

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setFone(pessoaAtualizada.getFone());
        pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setCep(pessoaAtualizada.getCep());
        pessoaExistente.setLogradouro(pessoaAtualizada.getLogradouro());
        pessoaExistente.setLocalidade(pessoaAtualizada.getLocalidade());
        pessoaExistente.setMembro(pessoaAtualizada.isMembro());
        pessoaExistente.setDataInicioMembresia(pessoaAtualizada.getDataInicioMembresia());

        return pessoaRepository.save(pessoaExistente);
    }
}
