package com.example.asset.movimientos;


import com.example.asset.cuenta.Cuenta;
import com.example.asset.cuenta.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/mov")
@CrossOrigin(origins = "http://localhost:3000")
public class MovimientoController {

    private final MovimientoService movimientoService;
    private final CuentaService cuentaService;


    @Autowired
    public MovimientoController(MovimientoService movimientoService, CuentaService cuentaService) {
        this.movimientoService = movimientoService;
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Movimiento> getMovimientos() {
        return movimientoService.getMovimientos();
    }

    @PostMapping
    public ResponseEntity<Object> registrarMovimiento(@RequestBody Movimiento movimiento) {

            return this.movimientoService.newMovimiento(movimiento);
    }

    @PutMapping
    public ResponseEntity<Object> modificarMov(@RequestBody Movimiento movimiento){
        return this.movimientoService.newMovimiento(movimiento);
    }

    @DeleteMapping (path = "{id_mov}")
    public ResponseEntity<Object> eliminarMovimiento(@PathVariable("id_mov") Long id){
        return this.movimientoService.deleteMovimiento(id);

    }
}