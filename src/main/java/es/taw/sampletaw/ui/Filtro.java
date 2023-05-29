package es.taw.sampletaw.ui;

public class Filtro {
    String tipoComision;
    Double saldo;

    public Filtro() {
    }

    public Filtro(String tipoComision, Double saldo) {
        this.saldo = saldo;
        this.tipoComision = tipoComision;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }
}
