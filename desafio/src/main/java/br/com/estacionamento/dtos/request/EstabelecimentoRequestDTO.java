package br.com.estacionamento.dtos.request;

import br.com.estacionamento.model.Endereco;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstabelecimentoRequestDTO {

    @NotNull(message = "O Nome do Estabelecimento é de preenchimento obrigatório")
    @Size(min = 3, max = 50, message = "O Nome do Estabelecimento deve ter entre 3 e 50 caracteres")
    private String nome;

    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$")
    @Size(min = 14, max = 18, message = "O CNPJ do Estabelecimento deve ter 14 caracteres")
    @NotNull(message = "O CNPJ do Estabelecimento é de preenchimento obrigatório")
    private String cnpj;

    @NotNull(message = "O Endereço do Estabelecimento é de preenchimento obrigatório")
    private Endereco endereco;

    @Pattern(regexp= "^\\(\\d{2}\\)\\s\\d{4}-\\d{4}$")
    @NotNull(message = "O Telefone do Estabelecimento é de preenchimento obrigatório")
    @Size(min = 10, max = 14, message = "O Telefone do Estabelecimento deve ter entre 10 e 11 caracteres")
    private String telefone;

    @NotNull(message = "A Quantidade de Vagas para Motos do Estabelecimento é de preenchimento obrigatório")
    private Integer quantidadeVagasMotos;

    @NotNull(message = "A Quantidade de Vagas para Carros do Estabelecimento é de preenchimento obrigatório")
    private Integer quantidadeVagasCarros;

}
