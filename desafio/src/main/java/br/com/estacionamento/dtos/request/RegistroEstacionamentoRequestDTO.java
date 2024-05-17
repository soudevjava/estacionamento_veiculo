package br.com.estacionamento.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEstacionamentoRequestDTO {

    @NotNull(message = "O id do estabelecimento é obrigatório")
    private Long estabelecimentoId;

    @NotNull(message = "O id do veiculo é obrigatório")
    private Long idVeiculo;



}
