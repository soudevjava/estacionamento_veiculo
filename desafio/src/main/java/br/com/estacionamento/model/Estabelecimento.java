package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estabelecimento")
@Data
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false)
    private Integer quantidadeVagasMotos;

    @Column(nullable = false)
    private Integer quantidadeVagasCarros;

    @OneToMany(mappedBy="estabelecimento",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Veiculo> veiculos = new ArrayList<>();
    
}

