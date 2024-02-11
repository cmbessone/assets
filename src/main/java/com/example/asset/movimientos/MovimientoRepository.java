package com.example.asset.movimientos;

import com.example.asset.cuenta.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {

	Optional<Movimiento> getMovById(Long id);

	List<Movimiento> findByCuentaCBU(int cuentaCBU);
}
