package br.com.estacionamento.builder;

import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import br.com.estacionamento.dtos.response.RegistroEstacionamentoResponseDTO;
import br.com.estacionamento.dtos.response.VeiculoResponseDTO;
import br.com.estacionamento.model.Endereco;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class RegistroEstacionamentoResponseDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private EstabelecimentoResponseDTO estabelecimento = new EstabelecimentoResponseDTO(1L,"Estabelecimento bom demais","15.744.968/0001-08", new Endereco("12345-678", "Rua das Flores", "Centro", "123", "Sala 101", "Cidade Nova", "Estado XZ"),"(81) 2264-7262",60,40) ;

    @Builder.Default
    private VeiculoResponseDTO veiculo = new VeiculoResponseDTO(1L,"Matra","Pick-Up CD 4x4 Curto/Longo 2.5 TDI Dies.","Bege","NEV-1916","CARRO",1l);


    @Builder.Default
    private LocalDateTime data  = LocalDateTime.now();

    @Builder.Default
    private String tipoRegistro  = "ENTRADA";


    public RegistroEstacionamentoResponseDTO toRegistroEstacionamentoResponseDTO() {
        return new RegistroEstacionamentoResponseDTO(id,
                estabelecimento,
                veiculo,
                data,
                tipoRegistro
        );
    }



}
