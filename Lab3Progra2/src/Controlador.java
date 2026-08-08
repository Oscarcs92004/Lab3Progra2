public class Controlador implements LogicaJuego, GestionTurnos {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Carta[][] tablero;
    private int parejasEncontradas;

    public Controlador(String nombre1, String nombre2) {
        jugador1 = new Jugador(nombre1);
        jugador2 = new Jugador(nombre2);
        jugadorActual = jugador1;
        tablero = new Carta[6][6];
        parejasEncontradas = 0;
    }

    @Override
    public void iniciarJuego() {
        jugador1.reiniciarPuntos();
        jugador2.reiniciarPuntos();
        jugadorActual = jugador1;
        parejasEncontradas = 0;
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
    public boolean verificarPareja(Carta carta1, Carta carta2) {
        if (carta1.esParejaCon(carta2)) {
            return true;
        }

        return false;
    }

    public void registrarPareja(Carta carta1, Carta carta2) {
        if (verificarPareja(carta1, carta2)) {
            jugadorActual.sumarPunto(carta1);
            parejasEncontradas++;
        }
    }

    @Override
    public boolean juegoTerminado() {
        if (parejasEncontradas == 18) {
            return true;
        }

        return false;
    }

    @Override
    public String finalizarJuego() {
        if (jugador1.getPuntos() > jugador2.getPuntos()) {
            return "Ganador: " + jugador1.getNombre() + " con " + jugador1.getPuntos() + " puntos.";
        } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
            return "Ganador: " + jugador2.getNombre() + " con " + jugador2.getPuntos() + " puntos.";
        } else {
            return "Empate con " + jugador1.getPuntos() + " puntos.";
        }
    }

    public Carta getCarta(int fila, int columna) {
        return tablero[fila][columna];
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public int getParejasEncontradas() {
        return parejasEncontradas;
    }
}