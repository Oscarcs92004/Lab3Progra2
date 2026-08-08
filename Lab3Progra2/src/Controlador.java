public class Controlador implements LogicaJuego, GestionTurnos {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private int parejasEncontradas;
    private int totalParejas;

    public Controlador(String nombre1, String nombre2) {
        jugador1 = new Jugador(nombre1);
        jugador2 = new Jugador(nombre2);
        jugadorActual = jugador1;
        parejasEncontradas = 0;
        totalParejas = 18;
    }

    @Override
    public Jugador obtenerJugadorActual() {
        return jugadorActual;
    }

    @Override
    public void cambiarTurno() {
        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else {
            jugadorActual = jugador1;
        }
    }

    @Override
    public void iniciarJuego() {

    }

    @Override
    public boolean verificarPareja(Carta carta1, Carta carta2) {
        return false;
    }

    @Override
    public boolean juegoTerminado() {
        return false;
    }

    @Override
    public String finalizarJuego() {
        return "";
    }
}