package com.example.asset.movimientos;

import com.example.asset.cuenta.Cuenta;
import com.example.asset.cuenta.CuentaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

	HashMap<String, Object> datos;
	@Autowired
    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;

    }

    public List<Movimiento> getMovimientos(){
        return this.movimientoRepository.findAll();
    }

    public ResponseEntity<Object> newMovimiento(Movimiento movimiento){

        datos = new HashMap<>();

        // Verifica si la cuenta con el CBU existe
        Optional<Cuenta> cuentaOptional = cuentaRepository.getCuentaByCBU(movimiento.getCuentaCBU());

        if (cuentaOptional.isEmpty()) {
            datos.put("error", true);
            datos.put("message", "No existe una cuenta con ese CBU");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.NOT_FOUND
            );
        }
        // Asigna la cuenta al movimiento
        //Cuenta cuenta = cuentaOptional.get();
        //movimiento.setCuenta(cuenta);
        Optional<Movimiento> res = this.movimientoRepository.getMovById(movimiento.getId());
        if (res.isPresent() && movimiento.getId() == null) {
            datos.put("error", true);
            datos.put("message", "ya existe un movimiento con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        movimientoRepository.save(movimiento);
        datos.put("data",movimiento);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }


    public ResponseEntity<Object> deleteMovimiento(Long id) {
        datos = new HashMap<>();
        boolean existe = this.movimientoRepository.existsById(id);
		if (!existe) {
            datos.put("error", true);
            datos.put("message", "no existe un movimiento con ese Id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Movimiento eliminada");
        movimientoRepository.deleteById(id);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );

    }

}
