package br.com.estacionamento.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstabelecimentoResponseDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private Integer quantidadeVagasMotos;

    private Integer quantidadeVagasCarros;

}
