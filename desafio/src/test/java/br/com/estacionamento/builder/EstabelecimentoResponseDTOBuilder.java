package br.com.estacionamento.builder;

import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.dtos.response.EstabelecimentoResponseDTO;
import lombok.Builder;

@Builder
public class EstabelecimentoResponseDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Estabelecimento bom demais";

    @Builder.Default
    private String cnpj = "15.744.968/0001-08";

    @Builder.Default
    private String endereco = "Rua das freiras";

    @Builder.Default
    private String telefone = "(81) 2264-7262";

    @Builder.Default
    private Integer quantidadeVagasMotos = 60;

    @Builder.Default
    private Integer quantidadeVagasCarros = 40;

    public EstabelecimentoResponseDTO toEstabelecimentoResponseDTO() {
        return new EstabelecimentoResponseDTO(id,
                nome,
                cnpj,
                endereco,
                telefone,
                quantidadeVagasMotos,
                quantidadeVagasCarros
        );
    }
}
