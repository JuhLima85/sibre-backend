package br.com.devsibre.Controller;

import br.com.devsibre.Domain.Entity.Pessoa;
import br.com.devsibre.Service.Inteface.PessoaService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://sibre-frontend-production.up.railway.app",
        "https://sibre-adm.netlify.app"
})
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('admin') or hasRole('gestor')")
    public Pessoa salvar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @GetMapping
    public List<Pessoa> listarTodas() {
        return pessoaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id);
    }

    @PostMapping("/{id1}/vinculo/{id2}")
    @RolesAllowed({"admin", "gestor"})
    public Pessoa criarVinculo(@PathVariable Long id1,
                               @PathVariable Long id2,
                               @RequestParam String tipo) {
        return pessoaService.criarVinculo(id1, id2, tipo);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"admin", "gestor"})
    public void deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"admin", "gestor"})
    public Pessoa atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoa);
        return pessoaAtualizada;
    }

    @GetMapping("/buscar")
    public List<Pessoa> buscarPorNome(@RequestParam String nome) {
        return pessoaService.buscarPorNome(nome);
    }
}