package br.com.estacionamento.dtos.response;

import br.com.estacionamento.model.Endereco;
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

    private Endereco endereco;

    private String telefone;

    private Integer quantidadeVagasMotos;

    private Integer quantidadeVagasCarros;

}
