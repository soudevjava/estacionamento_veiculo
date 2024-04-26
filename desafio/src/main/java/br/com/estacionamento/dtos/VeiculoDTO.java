package br.com.estacionamento.dtos;

import br.com.estacionamento.model.Tipo;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    private Long id;

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
    @Size(min = 7, max = 7, message = "A Placa do Veiculo deve ter 7 caracteres")
    private String placa;

    @NotNull(message = "O Tipo do Veiculo é de preenchimento obrigatório")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotNull(message = "O Id do Estabelecimento do Veiculo é de preenchimento obrigatório")
    private Long idEstabelecimento;

    public VeiculoDTO(String message) {
        //TODO Auto-generated constructor stub
    }
}

