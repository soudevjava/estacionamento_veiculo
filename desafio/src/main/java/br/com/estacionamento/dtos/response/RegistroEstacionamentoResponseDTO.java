package br.com.estacionamento.dtos.response;

import br.com.estacionamento.dtos.EstabelecimentoDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegistroEstacionamentoResponseDTO {

    private Long id;
    private EstabelecimentoDTO estabelecimento;
    private VeiculoResponseDTO veiculo;
    private LocalDateTime data;
    private String tipoRegistro;

}
