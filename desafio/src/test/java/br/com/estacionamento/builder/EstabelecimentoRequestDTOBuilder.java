package br.com.estacionamento.builder;

import br.com.estacionamento.dtos.request.EstabelecimentoRequestDTO;
import br.com.estacionamento.model.Endereco;
import lombok.Builder;

@Builder
public class EstabelecimentoRequestDTOBuilder {

    @Builder.Default
    private String nome = "Estabelecimento bom demais";

    @Builder.Default
    private String cnpj = "15.744.968/0001-08";

    @Builder.Default
    private Endereco endereco = new Endereco("12345-678", "Rua das Flores", "Centro", "123", "Sala 101", "Cidade Nova", "Estado XZ");

    @Builder.Default
    private String telefone = "(81) 2264-7262";

    @Builder.Default
    private Integer quantidadeVagasMotos = 40;

    @Builder.Default
    private Integer quantidadeVagasCarros = 20;

    public EstabelecimentoRequestDTO toEstabelecimentoRequestDTO() {
        return new EstabelecimentoRequestDTO(nome,
                cnpj,
                endereco,
                telefone,
                quantidadeVagasMotos,
                quantidadeVagasCarros
        );
    }
}
