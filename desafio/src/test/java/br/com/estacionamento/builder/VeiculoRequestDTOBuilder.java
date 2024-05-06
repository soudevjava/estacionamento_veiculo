package br.com.estacionamento.builder;

import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.request.VeiculoRequestDTO;
import br.com.estacionamento.model.enums.Tipo;
import lombok.Builder;

@Builder
public class VeiculoRequestDTOBuilder {

    @Builder.Default
    private String marca = "Matra";

    @Builder.Default
    private String modelo = "Pick-Up CD 4x4 Curto/Longo 2.5 TDI Dies.";

    @Builder.Default
    private String cor = "Bege";

    @Builder.Default
    private String placa = "NEV-1916";

    @Builder.Default
    private String tipo = "CARRO";

    @Builder.Default
    private Long idEstabelecimento = 1L;


    public VeiculoRequestDTO toVeiculoRequestDTO() {
        return new VeiculoRequestDTO(marca,
                modelo,
                cor,
                placa,
                tipo,
                idEstabelecimento
        );
    }
}
