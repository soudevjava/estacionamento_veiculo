package br.com.estacionamento.builder;

import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.request.VeiculoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import lombok.Builder;

@Builder
public class VeiculoResponseDTOBuilder {

    @Builder.Default
    private Long id = 1L;

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


    public VeiculoResponseDTO toVeiculoResponseDTO() {
        return new VeiculoResponseDTO(id,
                marca,
                modelo,
                cor,
                placa,
                tipo,
                idEstabelecimento
        );
    }
}
