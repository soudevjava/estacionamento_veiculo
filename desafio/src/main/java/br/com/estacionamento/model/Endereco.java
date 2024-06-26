package br.com.estacionamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Column(name = "endereco_cep")
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String cep;

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_bairro")
    private String bairro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_cidade")
    private String cidade;

    @Column(name = "endereco_estado")
    private String estado;

}
