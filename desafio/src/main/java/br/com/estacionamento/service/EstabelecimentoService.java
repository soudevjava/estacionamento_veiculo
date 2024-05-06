package br.com.estacionamento.service;

import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.model.Estabelecimento;
import br.com.estacionamento.repository.EstabelecimentoRepository;
import br.com.estacionamento.service.exception.EstabelecimentoNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;


    public EstabelecimentoResponseDTO cadastrar(EstabelecimentoRequestDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Estabelecimento estabelecimento = mapper.map(dto, Estabelecimento.class);
        estabelecimentoRepository.save(estabelecimento);

        return mapper.map(estabelecimento, EstabelecimentoResponseDTO.class);
    }


    public EstabelecimentoResponseDTO atualizar(Long id, EstabelecimentoRequestDTO dto){

        ModelMapper mapper = new ModelMapper();
        
        Estabelecimento estabelecimento = mapper.map(dto, Estabelecimento.class);
           
        estabelecimento.setId(id);

        estabelecimento = estabelecimentoRepository.save(estabelecimento);
 
        return mapper.map(estabelecimento, EstabelecimentoResponseDTO.class);
    }

    public Page<EstabelecimentoResponseDTO> listar(Pageable paginacao) {

        ModelMapper mapper = new ModelMapper();

        if (paginacao == null || paginacao.getPageNumber() < 0 || paginacao.getPageSize() <= 0) {
            throw new IllegalArgumentException("Paginação inválida!");
        }

        return estabelecimentoRepository.
                findAll(paginacao).map(e -> mapper.map(e, EstabelecimentoResponseDTO.class));

    }

    public EstabelecimentoResponseDTO listarPorId(Long id) {

        ModelMapper mapper = new ModelMapper();

        Estabelecimento estabelecimento = estabelecimentoRepository.
                findById(id).orElseThrow(() -> new EstabelecimentoNotFoundException("Estabelecimento não encontrado"));

        return mapper.map(estabelecimento, EstabelecimentoResponseDTO.class);
    }

    public void excluir(Long id) {
        estabelecimentoRepository.deleteById(id);
    }

}
