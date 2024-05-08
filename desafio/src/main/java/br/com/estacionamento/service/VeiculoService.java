package br.com.estacionamento.service;

import br.com.estacionamento.dtos.request.VeiculoRequestDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.repository.VeiculoRepository;
import br.com.estacionamento.service.exception.RegraNegocioException;
import br.com.estacionamento.service.exception.VeiculoNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    private ModelMapper mapper = new ModelMapper();



    public VeiculoResponseDTO cadastrar(VeiculoRequestDTO dto) {


        Veiculo veiculo = mapper.map(dto, Veiculo.class);

        verificarExistenciaVeiculoPorPlaca(veiculo);

        veiculoRepository.save(veiculo);

        return mapper.map(veiculo, VeiculoResponseDTO.class);
    }

    public VeiculoResponseDTO atualizar(Long id, VeiculoRequestDTO dto) {
        Veiculo veiculo = mapper.map(dto, Veiculo.class);
        veiculo.setId(id);
        veiculo = veiculoRepository.save(veiculo);

        return mapper.map(veiculo, VeiculoResponseDTO.class);
    }

    public Page<VeiculoResponseDTO> listar(Pageable paginacao) {

        if (paginacao == null || paginacao.getPageNumber() < 0 || paginacao.getPageSize() <= 0) {
            throw new IllegalArgumentException("Paginação inválida!");
        }

        return veiculoRepository.
                findAll(paginacao).map(v -> mapper.map(v, VeiculoResponseDTO.class));

    }

    public VeiculoResponseDTO listarPorId(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new VeiculoNotFoundException("Veiculo não encontrado"));

        return mapper.map(veiculo, VeiculoResponseDTO.class);
    }

    public void excluir(Long id) {
        veiculoRepository.deleteById(id);
    }

    public VeiculoResponseDTO buscarPorPlaca(String placa) {
        Veiculo veiculo = veiculoRepository.buscarVeiculoPorPlaca(placa)
                .orElseThrow(() -> new VeiculoNotFoundException("Essa placa nao existe"));

        return mapper.map(veiculo, VeiculoResponseDTO.class);
    }

    public void verificarExistenciaVeiculoPorPlaca(Veiculo veiculo)  {
        String placa = veiculo.getPlaca();

        veiculoRepository.buscarVeiculoPorPlaca(placa)
                .ifPresent(veiculoExistente -> {
                    if (!veiculoExistente.equals(veiculo)) {
                        throw new RegraNegocioException(String.format("Já existe um Veiculo cadastrado com a placa %s", placa));
                    }
                });
    }

}
