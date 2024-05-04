package br.com.estacionamento.dtos.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoResponseDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private Integer quantidadeVagasMotos;

    private Integer quantidadeVagasCarros;

}
