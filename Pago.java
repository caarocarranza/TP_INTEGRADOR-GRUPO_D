public class Pago {
    private double monto;
    private String tipo;
    private MedioPago medio;
    private String estado;

    public Pago(double monto, String tipo, MedioPago medio) {
        this.monto = monto;
        this.tipo = tipo;
        this.medio = medio;
        estado = "PAGADO";
    }

    public void reembolsar() {
        estado = "REEMBOLSADO";
    }

    public void retener() {
        estado = "RETENIDO";
    }

    @Override
    public String toString() {
        return tipo + ": $" + monto + " - " + medio + " - " + estado;
    }
}

enum MedioPago {
    EFECTIVO, TRANSFERENCIA, QR
}
