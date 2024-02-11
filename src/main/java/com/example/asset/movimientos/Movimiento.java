package com.example.asset.movimientos;
import com.example.asset.cuenta.Cuenta;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String concepto;
    private BigDecimal importe;

    @Column(name = "cbu")
    private int cuentaCBU;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public int getCuentaCBU() {
        return cuentaCBU;
    }

    public void setCuentaCBU(int cuentaCBU) {
        this.cuentaCBU = cuentaCBU;
    }

    public Movimiento(Long id, LocalDate fecha, String concepto, BigDecimal importe, int cuentaCBU) {
        this.id = id;
        this.fecha = fecha;
        this.concepto = concepto;
        this.importe = importe;
        this.cuentaCBU = cuentaCBU;
    }

    public Movimiento(LocalDate fecha, String concepto, BigDecimal importe, int cuentaCBU) {
        this.fecha = fecha;
        this.concepto = concepto;
        this.importe = importe;
        this.cuentaCBU = cuentaCBU;
    }

    public Movimiento() {


    }
}
