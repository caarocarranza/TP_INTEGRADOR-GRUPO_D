import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Duration;

public class Reserva {
    private int numero;
    private Cliente cliente;
    private Cancha cancha;
    private LocalDate fecha;
    private LocalTime hora;
    private double monto;
    private String estado;
    private Pago sena;

    public Reserva(int numero, Cliente cliente, Cancha cancha, LocalDate fecha,
                   LocalTime hora, MedioPago medio) {
        this.numero = numero;
        this.cliente = cliente;
        this.cancha = cancha;
        this.fecha = fecha;
        this.hora = hora;
        this.monto = cancha.getPrecio();
        this.estado = "CONFIRMADA";
        this.sena = new Pago(monto * 0.5, "SEÑA", medio);
    }

    public boolean seSuperpone(LocalDate otraFecha, LocalTime otraHora) {
        if (!fecha.equals(otraFecha) || estado.equals("CANCELADA") || estado.equals("AUSENTE") || estado.equals("COMPLETADA")) {
            return false;
        }

        int inicio = hora.toSecondOfDay() / 60;
        int otroInicio = otraHora.toSecondOfDay() / 60;
        return Math.abs(inicio - otroInicio) < 120;
    }

    public void cancelar(LocalDateTime ahora) {
        if (!estado.equals("CONFIRMADA")) {
            System.out.println("La reserva no se puede cancelar.");
            return;
        }

        LocalDateTime inicioTurno = LocalDateTime.of(fecha, hora);
        long horas = Duration.between(ahora, inicioTurno).toHours();

        if (horas >= 24) {
            sena.reembolsar();
        } else {
            sena.retener();
        }
        estado = "CANCELADA";
    }

    public void registrarAsistencia(boolean asistio, MedioPago medioSaldo) {
        if (!estado.equals("CONFIRMADA")) {
            System.out.println("La reserva ya fue cerrada.");
            return;
        }

        if (asistio) {
            new Pago(monto * 0.5, "SALDO", medioSaldo);
            estado = "COMPLETADA";
        } else {
            sena.retener();
            cliente.registrarInasistencia();
            estado = "AUSENTE";
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "Reserva " + numero + " | " + cancha + " | " + fecha + " " + hora
                + " | " + cliente.getNombreCompleto() + " | $" + monto + " | " + estado;
    }
}
