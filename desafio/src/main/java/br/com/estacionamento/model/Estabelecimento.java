package br.com.estacionamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estabelecimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Este campo é de preenchimento obrigatório")
    private String nome;
    @NotNull(message = "Este campo é de preenchimento obrigatório")
    private String cnpj;
    @NotNull(message = "Este campo é de preenchimento obrigatório")
    private String endereco;
    @NotNull(message = "Este campo é de preenchimento obrigatório")
    private String telefone;
    @NotNull(message = "Este campo é de preenchimento obrigatório")
    private Integer quantidadeVagasMotos;
    @NotNull(message = "Este campo é de preenchimento obrigatório")
    private Integer quantidadeVagasCarros;
}
