package br.com.estacionamento.model;

import br.com.estacionamento.model.enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "veiculo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String marca;

  @Column(nullable = false)
  private String modelo;

  @Column(nullable = false)
  private String cor;

  @Column(nullable = false, unique = true)
  private String placa;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Tipo tipo;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Estabelecimento estabelecimento;

    // @ManyToOne
    // @JoinColumn(name = "estabelecimento_id")
    // private Estabelecimento estabelecimento;

}
