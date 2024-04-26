package br.com.estacionamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false, unique = true)
    private String telefone;

    @Column(nullable = false)
    private Integer quantidadeVagasMotos;

    @Column(nullable = false)
    private Integer quantidadeVagasCarros;

    @OneToMany(mappedBy="estabelecimento",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Veiculo> veiculos = new ArrayList<>();
    
    //@OneToMany(mappedBy="registro_estabelecimento",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //private List<RegistroEstacionamento>registro = new ArrayList<>();
    
}

