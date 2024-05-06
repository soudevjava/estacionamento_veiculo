package br.com.estacionamento.dtos.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoResponseDTO {

    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private String tipo;

    private Long idEstabelecimento;

}
