package com.example.asset.cuenta;

import com.example.asset.movimientos.Movimiento;
import com.example.asset.movimientos.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    HashMap<String, Object> datos;
    @Autowired
    public CuentaService(CuentaRepository cuentaRepository, MovimientoRepository movimientoRepository) {
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository= movimientoRepository;
    }

    public Optional<Cuenta> getCuentaByCBU(int cbu) {
        return cuentaRepository.getCuentaByCBU(cbu);
    }

    public List<Cuenta> getCuentasConMovimientos() {
        List<Cuenta> cuentas = this.cuentaRepository.findAll();

        for(Cuenta cuenta: cuentas){
            List<Movimiento> movimientos = this.movimientoRepository.findByCuentaCBU(cuenta.getCBU());
            cuenta.setMovimientos(movimientos);
        }

        return cuentas;
    }

    public ResponseEntity<Object> newCuenta(Cuenta cuenta) {
        Optional<Cuenta> res = this.cuentaRepository.getCuentaByCBU(cuenta.getCBU());
		datos= new HashMap<>();

        if (res.isPresent() && cuenta.getId()==null) {
            datos.put("error", true);
            datos.put("message", "ya existe una cuenta con ese cbu");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message", "Se guardó la cuenta con éxito");
        if (cuenta.getId()!=null){
            datos.put("message", "Se actualizó la cuenta con éxito");
        }
        cuentaRepository.save(cuenta);
        datos.put("data", cuenta);
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }


    public ResponseEntity<Object> deleteCuenta(Long id){
        datos= new HashMap<>();
        boolean existe = this.cuentaRepository.existsById(id);
        if (!existe) {
            datos.put("error", true);
            datos.put("message", "No existe una cuenta con ese id");
        	return new ResponseEntity<>(
                    datos,
            		HttpStatus.CONFLICT
            );
        }
        datos.put("message","cuenta eliminado");
        cuentaRepository.deleteById(id);
        return new ResponseEntity<>(
                	datos,
                	HttpStatus.ACCEPTED
        );
    }
}

