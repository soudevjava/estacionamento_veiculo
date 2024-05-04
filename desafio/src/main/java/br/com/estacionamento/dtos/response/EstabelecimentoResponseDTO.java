package br.com.estacionamento.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
