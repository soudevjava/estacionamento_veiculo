package br.com.estacionamento.service;

import br.com.estacionamento.dtos.request.RegistroEstacionamentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.dtos.response.RegistroEstacionamentoResponseDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import br.com.estacionamento.model.Estabelecimento;
import br.com.estacionamento.model.RegistroEstacionamento;
import br.com.estacionamento.model.enums.TipoRegistro;
import br.com.estacionamento.model.Veiculo;
import br.com.estacionamento.repository.RegistroEstacionamentoRepository;
import br.com.estacionamento.service.exception.EstabelecimentoNotFoundException;
import br.com.estacionamento.service.exception.VeiculoNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistroEstacionamentoService {



   @Autowired
   private RegistroEstacionamentoRepository registroEstacionamentoRepository ;

   @Autowired
   private EstabelecimentoService estabelecimentoService;

   @Autowired
   private VeiculoService veiculoService;


   private ModelMapper mapper = new ModelMapper();


   public RegistroEstacionamentoResponseDTO registrarEntrada(RegistroEstacionamentoRequestDTO dto) {
      return registrar(dto, TipoRegistro.ENTRADA);
   }

   public RegistroEstacionamentoResponseDTO registrarSaida(RegistroEstacionamentoRequestDTO dto) {
      return registrar(dto, TipoRegistro.SAIDA);
   }

   public RegistroEstacionamentoResponseDTO registrar(@NotNull RegistroEstacionamentoRequestDTO dto, TipoRegistro tipoRegistro) {
      Estabelecimento estabelecimento = buscarEstabelecimento(dto.getEstabelecimentoId());
      Veiculo veiculo = buscarVeiculo(dto.getIdVeiculo());

      RegistroEstacionamento registroEstacionamento = mapper.map(dto, RegistroEstacionamento.class);
      registroEstacionamento.setEstabelecimento(estabelecimento);
      registroEstacionamento.setVeiculo(veiculo);
      registroEstacionamento.setData(LocalDateTime.now());
      registroEstacionamento.setTipoRegistro(tipoRegistro);

      registroEstacionamentoRepository.save(registroEstacionamento);

      return mapper.map(registroEstacionamento, RegistroEstacionamentoResponseDTO.class);
   }

   public Estabelecimento buscarEstabelecimento(Long idEstabelecimento) {
      EstabelecimentoResponseDTO estabelecimentoDTO = estabelecimentoService.listarPorId(idEstabelecimento);
      if (estabelecimentoDTO == null){
         throw new EstabelecimentoNotFoundException("Estabelecimento não encontrado");
      }
      return mapper.map(estabelecimentoDTO, Estabelecimento.class);
   }

   public Veiculo buscarVeiculo(Long idVeiculo) {
      VeiculoResponseDTO veiculoDTO = veiculoService.listarPorId(idVeiculo);
      if (veiculoDTO == null){
         throw new VeiculoNotFoundException("Veiculo não encontrado");
      }
      return mapper.map(veiculoDTO, Veiculo.class);
   }

}
