package com.example.asset.cuenta;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cuentas")
@CrossOrigin(origins = "http://localhost:3000")
public class CuentaController {

    private final CuentaService cuentaService;


    @Autowired
    public CuentaController(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> getCuentas(){
        return cuentaService.getCuentasConMovimientos();
    }

    @PostMapping
    public ResponseEntity<Object> registrarCuenta(@RequestBody Cuenta cuenta){
        return this.cuentaService.newCuenta(cuenta);
    }

    @PutMapping
    public ResponseEntity<Object> modificarCuenta(@RequestBody Cuenta cuenta){
        return this.cuentaService.newCuenta(cuenta);
    }


    @DeleteMapping(path = "{cuenta_id}")
    public ResponseEntity<Object> eliminarCuenta(@PathVariable("cuenta_id")  Long id){
        return cuentaService.deleteCuenta(id);
    }
}
