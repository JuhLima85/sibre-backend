package br.com.devsibre.Service.Inteface;

import br.com.devsibre.Domain.Entity.Pessoa;

import java.util.List;

public interface PessoaService {
    Pessoa salvar(Pessoa pessoa);
    List<Pessoa> listarTodas();
    Pessoa buscarPorId(Long id);
    Pessoa criarVinculo(Long id1, Long id2, String tipo);
    void deletar(Long id);
    Pessoa atualizar(Long id, Pessoa pessoaAtualizada);
    List<Pessoa> buscarPorNome(String nome);
}
