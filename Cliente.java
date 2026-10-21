public class Cliente extends Persona {
    private int inasistencias;

    public Cliente(String nombre, String apellido, String telefono, int dni) {
        super(nombre, apellido, telefono, dni);
        inasistencias = 0;
    }

    public void registrarInasistencia() {
        inasistencias++;
    }

    public int getInasistencias() {
        return inasistencias;
    }
}
