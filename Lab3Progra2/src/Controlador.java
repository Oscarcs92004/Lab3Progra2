import java.util.Random;

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

        Carta[] cartas = crearCartas();

        mezclarCartas(cartas);

        cargarTablero(cartas);
    }

    private Carta[] crearCartas() {

        Carta[] cartas = new Carta[36];

        String[] normales = {"bulbasaur", "charmander", "dwebble", "eevee", "lickitung", "pajaro1", "pajaro2", "pajaro3", "rata1", "rata2", "serpiente1", "serpiente2"};

        String[] especiales = {"blastoise", "charmeleon", "clefable", "haunter", "pikachu", "raichu"};

        String rutaOculta = "/Imagenes/pantalla.png";

        int posicion = 0;

        for (int i = 0; i < normales.length; i++) {

            String rutaFrente = "/Imagenes/" + normales[i] + ".png";

            cartas[posicion] = new CasillaNormal(normales[i], rutaFrente, rutaOculta, 100, 100);
            posicion++;

            cartas[posicion] = new CasillaNormal(normales[i], rutaFrente, rutaOculta, 100, 100);
            posicion++;
        }

        for (int i = 0; i < especiales.length; i++) {

            String rutaFrente = "/Imagenes/" + especiales[i] + ".png";

            cartas[posicion] = new CasillaEspecial(especiales[i], rutaFrente, rutaOculta, 100, 100);
            posicion++;

            cartas[posicion] = new CasillaEspecial(especiales[i], rutaFrente, rutaOculta, 100, 100);
            posicion++;
        }

        return cartas;
    }

    private void mezclarCartas(Carta[] cartas) {

        Random random = new Random();

        for (int i = 0; i < cartas.length; i++) {

            int posicion = random.nextInt(cartas.length);

            Carta temporal = cartas[i];
            cartas[i] = cartas[posicion];
            cartas[posicion] = temporal;
        }
    }

    private void cargarTablero(Carta[] cartas) {

        int posicion = 0;

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                tablero[fila][columna] = cartas[posicion];

                tablero[fila][columna].ocultar();

                posicion++;
            }
        }
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

            carta1.setEnabled(false);
            carta2.setEnabled(false);
        }
    }

    public void parejaIncorrecta(Carta carta1, Carta carta2) {

        carta1.ocultar();
        carta2.ocultar();

        cambiarTurno();
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

        try {

            return tablero[fila][columna];

        } catch (ArrayIndexOutOfBoundsException e) {

            return null;
        }
    }

    public Carta[][] getTablero() {
        return tablero;
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