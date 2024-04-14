package br.com.estacionamento.model;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
  @NotNull(message = "Este campo é de preenchimento obrigatório")
  private String marca;
  @NotNull(message = "Este campo é de preenchimento obrigatório")
  private String modelo;
  @NotNull(message = "Este campo é de preenchimento obrigatório")
  private String cor;
  @NotNull(message = "Este campo é de preenchimento obrigatório")
  private String placa;
  @NotNull(message = "Este campo é de preenchimento obrigatório")
  @Enumerated(EnumType.STRING)
  private Tipo tipo;

  @ManyToOne
  private Estabelecimento estabelecimento;

    // @ManyToOne
    // @JoinColumn(name = "estabelecimento_id")
    // private Estabelecimento estabelecimento;

}
