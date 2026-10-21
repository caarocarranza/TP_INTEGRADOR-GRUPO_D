import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorReserva {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<Cancha> canchas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private int numeroReserva = 1;

    public Cliente registrarCliente(String nombre, String apellido, String telefono, int dni) {
        Cliente cliente = new Cliente(nombre, apellido, telefono, dni);
        clientes.add(cliente);
        return cliente;
    }

    public Empleado registrarEmpleado(String nombre, String apellido, String telefono,
                                      int dni, RolEmpleado rol) {
        Empleado empleado = new Empleado(nombre, apellido, telefono, dni, rol);
        empleados.add(empleado);
        return empleado;
    }

    public Cancha registrarCancha(int numero, TipoCancha tipo,boolean disponibilidad) {
        Cancha cancha = new Cancha(numero, tipo, disponibilidad);
        canchas.add(cancha);
        return cancha;
    }

    public ArrayList<Cancha> consultarDisponibilidad(LocalDate fecha, LocalTime hora) {
        ArrayList<Cancha> disponibles = new ArrayList<>();

        for (Cancha cancha : canchas) {
            boolean libre = true;
            for (Reserva reserva : reservas) {
                if (reserva.getCancha() == cancha && reserva.seSuperpone(fecha, hora)) {
                    libre = false;
                }
            }
            if (libre) {
                disponibles.add(cancha);
            }
        }
        return disponibles;
    }

    public Reserva registrarReserva(Cliente cliente, Cancha cancha, LocalDate fecha,
                                    LocalTime hora, MedioPago medio) {
        if (!consultarDisponibilidad(fecha, hora).contains(cancha)) {
            System.out.println("La cancha no está disponible en ese horario.");
            return null;
        }

        Reserva reserva = new Reserva(numeroReserva, cliente, cancha, fecha, hora, medio);
        numeroReserva++;
        reservas.add(reserva);
        return reserva;
    }

    public void cancelarReserva(Reserva reserva, LocalDateTime ahora) {
        reserva.cancelar(ahora);
    }

    public void registrarAsistencia(Reserva reserva, boolean asistio, MedioPago medio) {
        reserva.registrarAsistencia(asistio, medio);
    }

    public void consultarReservas(Cliente cliente) {
        for (Reserva reserva : reservas) {
            if (reserva.getCliente() == cliente) {
                System.out.println(reserva);
            }
        }
    }

    public void modificarPrecio(Empleado empleado, TipoCancha tipo, double precio) {
        if (empleado.getRol() == RolEmpleado.ENCARGADO) {
            tipo.setPrecio(precio);
        } else {
            System.out.println("Solo el encargado puede modificar los precios.");
        }
    }
}
