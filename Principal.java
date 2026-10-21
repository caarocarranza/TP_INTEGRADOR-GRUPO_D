import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Principal {
    public static void main(String[] args) {
        GestorReserva sistema = new GestorReserva();

        Empleado recepcion = sistema.registrarEmpleado(
                "Juan", "Perez", "3534000002", 30222333, RolEmpleado.RECEPCIONISTA);

        Cliente reni = sistema.registrarCliente(
                "Renata", "Lopez", "3534111111", 40111222);
        Cliente angie = sistema.registrarCliente(
                "Angy", "Lopez", "3534111111", 40111222);
        Cliente carola = sistema.registrarCliente(
                "Carolina", "Lopez", "3534111111", 40111222);
        Cliente delfi = sistema.registrarCliente(
                "Delfina", "Lopez", "3534111111", 40111222);

        Cancha cancha1 = sistema.registrarCancha(1, TipoCancha.PARED,true);
        Cancha cancha2 = sistema.registrarCancha(2, TipoCancha.PARED,true);
        Cancha cancha3 = sistema.registrarCancha(3, TipoCancha.BLINDEX, true);
        Cancha cancha4 = sistema.registrarCancha(4, TipoCancha.BLINDEX, true);
        LocalDate fecha = LocalDate.of(2026, 11, 20);
        LocalTime hora = LocalTime.of(20, 0);

        System.out.println("Canchas disponibles:");
        System.out.println(sistema.consultarDisponibilidad(fecha, hora));

        Reserva reserva = sistema.registrarReserva(
                reni, cancha2, fecha, hora, MedioPago.EFECTIVO);
        
         Reserva reserva2 = sistema.registrarReserva(
                angie, cancha1, fecha, hora, MedioPago.EFECTIVO);
         Reserva reserva3 = sistema.registrarReserva(
                carola, cancha3, fecha, hora, MedioPago.EFECTIVO);
         Reserva reserva4 = sistema.registrarReserva(
                delfi, cancha4, fecha, hora, MedioPago.EFECTIVO);

        System.out.println("\nReserva realizada:");
        System.out.println(reserva);
        System.out.println(reserva2);
        System.out.println(reserva3);
        System.out.println(reserva4);


        System.out.println("\nCancelar reserva:");
        sistema.cancelarReserva(reserva, LocalDateTime.of(2026, 11, 10, 12, 0));
        System.out.println(reserva);
        

        System.out.println("\nReservas:");
        sistema.consultarReservas(reni);
        sistema.consultarReservas(delfi);
    }
}
