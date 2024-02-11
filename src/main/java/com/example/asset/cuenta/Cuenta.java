package com.example.asset.cuenta;

import com.example.asset.movimientos.Movimiento;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int CBU ;

    private String type;
    private String bank;
    private float credit_amt;
    private float debit_amt;
    private String currency;

	@OneToMany(mappedBy = "cuentaCBU", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos = new ArrayList<>();


    //Constructors


    public Cuenta(Long id, int CBU, String type, String bank, float credit_amt, float debit_amt, String currency, List<Movimiento> movimientos) {
        this.id = id;
        this.CBU = CBU;
        this.type = type;
        this.bank = bank;
        this.credit_amt = credit_amt;
        this.debit_amt = debit_amt;
        this.currency = currency;
        this.movimientos = movimientos;
    }

    public Cuenta() {
    }

    public Cuenta(int CBU, String type, String bank, float credit_amt, float debit_amt, String currency, List<Movimiento> movimientos) {
        this.CBU = CBU;
        this.type = type;
        this.bank = bank;
        this.credit_amt = credit_amt;
        this.debit_amt = debit_amt;
        this.currency = currency;
        this.movimientos = movimientos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCBU() {
        return CBU;
    }

    public void setCBU(int CBU) {
        this.CBU = CBU;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public float getCredit_amt() {
        return credit_amt;
    }

    public void setCredit_amt(float credit_amt) {
        this.credit_amt = credit_amt;
    }

    public float getDebit_amt() {
        return debit_amt;
    }

    public void setDebit_amt(float debit_amt) {
        this.debit_amt = debit_amt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos=movimientos;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
}
