package br.com.estacionamento.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEstacionamentoRequestDTO {

    @NotNull(message = "O id do estabelecimento é obrigatório")
    private Long idEstabelecimento;

    @NotNull(message = "O id do veiculo é obrigatório")
    private Long idVeiculo;


}
