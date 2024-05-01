package br.com.estacionamento.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoRequestDTO {

    @NotBlank(message = "A Marca do Veiculo é de preenchimento obrigatório")
    @Size(min = 3, max = 20, message = "A Marca do Veiculo deve ter entre 3 e 20 caracteres")
    private String marca;

    @NotBlank(message = "O Modelo do Veiculo é de preenchimento obrigatório")
    @Size(min = 3, max = 30, message = "O Modelo do Veiculo deve ter entre 3 e 30 caracteres")
    private String modelo;

    @NotBlank(message = "A Cor do Veiculo é de preenchimento obrigatório")
    @Size(min = 3, max = 10, message = "A Cor do Veiculo deve ter entre 3 e 10 caracteres")
    private String cor;

    @Pattern(regexp = "^[A-Z]{3}-?[0-9]{4}$")
    @NotBlank(message = "A Placa do Veiculo é de preenchimento obrigatório")
    @Size(min = 8, max = 8, message = "A Placa do Veiculo deve ter 7 caracteres")
    private String placa;

    @NotBlank(message = "O Tipo do Veiculo é de preenchimento obrigatório")
    private String tipo;

    @NotNull(message = "O Id do Estabelecimento do Veiculo é de preenchimento obrigatório")
    private Long idEstabelecimento;

}
