package br.com.estacionamento.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistroEstacionamentoResponseDTO {

    private Long id;

    private EstabelecimentoResponseDTO estabelecimento;

    private VeiculoResponseDTO veiculo;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    private LocalDateTime data;

    private String tipoRegistro;

}
