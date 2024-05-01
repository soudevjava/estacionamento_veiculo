package br.com.estacionamento.controller;

import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estacionamento.service.EstabelecimentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    public EstabelecimentoService estabelecimentoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstabelecimentoResponseDTO cadastrar(@Valid @RequestBody EstabelecimentoRequestDTO dto, UriComponentsBuilder componentsBuilder) {
           return estabelecimentoService.cadastrar(dto);

    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable @NotNull Long id ,@RequestBody @Valid EstabelecimentoRequestDTO dto  ){
        try {
            EstabelecimentoResponseDTO estabelecimentoDTO = estabelecimentoService.atualizar(id, dto);

            return ResponseEntity.status(HttpStatus.OK).body(estabelecimentoDTO);
        }
        catch (RuntimeException erro) {
            return ResponseEntity.badRequest().body("");
        }
    }


    @GetMapping
    public ResponseEntity<Page<EstabelecimentoResponseDTO>> listar(@PageableDefault(size = 10) Pageable paginacao) {

        try {
           Page< EstabelecimentoResponseDTO>  estabelecimentoDTO = estabelecimentoService.listar(paginacao);

           return ResponseEntity.status(HttpStatus.OK).body(estabelecimentoDTO);
        } catch (RuntimeException erro) {
            throw new RuntimeException("Erro ao listar o estabelecimento", erro);
        }


    }

     @GetMapping("/{id}")
     public ResponseEntity<EstabelecimentoResponseDTO> detalhar(@PathVariable Long id) {
        EstabelecimentoResponseDTO dto = estabelecimentoService.listarPorId(id);
        return ResponseEntity.ok(dto);
     }


     @DeleteMapping("/{id}")
     public ResponseEntity<EstabelecimentoResponseDTO> excluir(@PathVariable @NotNull Long id){
            
          estabelecimentoService.excluir(id);

          return ResponseEntity.noContent().build();
     }

     

}