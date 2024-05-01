package br.com.estacionamento.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegistroEstacionamentoResponseDTO {

    private Long id;

    private EstabelecimentoResponseDTO estabelecimento;

    private VeiculoResponseDTO veiculo;

    private LocalDateTime data;

    private String tipoRegistro;

}
