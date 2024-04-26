package br.com.estacionamento.model;

import java.time.LocalDateTime;

import br.com.estacionamento.model.enums.TipoRegistro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registro_estacionamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEstacionamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Veiculo veiculo;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoRegistro tipoRegistro;
}
