package br.com.estacionamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estacionamento.model.Veiculo;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

     @Query("select u from Veiculo u where u.placa like %?1%")
     Veiculo findByPlaca(String placa);

     @Query("SELECT v FROM Veiculo v WHERE v.placa = :placa")
     Optional<Veiculo> buscarVeiculoPorPlaca(String placa);


}
