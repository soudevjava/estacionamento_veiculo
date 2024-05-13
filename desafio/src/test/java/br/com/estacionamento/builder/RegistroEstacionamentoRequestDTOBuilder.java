package br.com.estacionamento.builder;

import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.request.RegistroEstacionamentoRequestDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
public class RegistroEstacionamentoRequestDTOBuilder {

    @Builder.Default
    private Long idEstabelecimento = 1L ;

    @Builder.Default
    private Long idVeiculo = 1L;


    public RegistroEstacionamentoRequestDTO toRegistroEstacionamentoRequestDTO() {
        return new RegistroEstacionamentoRequestDTO(idEstabelecimento
                  ,idVeiculo);

    }
}
