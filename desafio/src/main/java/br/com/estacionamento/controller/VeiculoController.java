package br.com.estacionamento.controller;

import br.com.estacionamento.dtos.request.VeiculoRequestDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import br.com.estacionamento.service.VeiculoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoResponseDTO cadastrar(@Valid @RequestBody VeiculoRequestDTO dto) {
        return veiculoService.cadastrar(dto);
    }


    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable @NotNull Long id, @RequestBody @Valid VeiculoRequestDTO dto) {
        try {
            VeiculoResponseDTO veiculoDTO = veiculoService.atualizar(id, dto);

            return ResponseEntity.status(HttpStatus.OK).body(veiculoDTO);
        } catch (RuntimeException erro) {
            return ResponseEntity.badRequest().body("");
        }
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoResponseDTO>> listar(@PageableDefault(size = 10) Pageable paginacao) {

        try {
            Page<VeiculoResponseDTO> veiculoDTO = veiculoService.listar(paginacao);

            return ResponseEntity.status(HttpStatus.OK).body(veiculoDTO);
        } catch (RuntimeException erro) {
            throw new RuntimeException("Erro ao listar o veiculo", erro);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> detalhar(@PathVariable Long id) {
        VeiculoResponseDTO dto = veiculoService.listarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<VeiculoResponseDTO> excluir(@PathVariable @NotNull Long id) {
        veiculoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{placa}")
    public ResponseEntity<VeiculoResponseDTO> findPlaca(@PathVariable String placa) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(veiculoService.buscarPorPlaca(placa));
    }

}
