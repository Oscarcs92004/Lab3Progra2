import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

public class Controlador implements LogicaJuego, GestionTurnos {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    private Carta[][] tablero;
    private ArrayList<Carta> listaCartas;
    private int parejasEncontradas;
    private Carta primeraCarta;
    private Carta segundaCarta;
    private Timer tiempo;
    private GUI gui;

    public Controlador(String nombre1, String nombre2, GUI gui) {
        jugador1 = new Jugador(nombre1);
        jugador2 = new Jugador(nombre2);
        jugadorActual = jugador1;
        tablero = new Carta[6][6];
        listaCartas = new ArrayList<>();
        parejasEncontradas = 0;
        this.gui = gui;
    }

    @Override
    public void iniciarJuego() {
        jugador1.reiniciarPuntos();
        jugador2.reiniciarPuntos();
        jugadorActual = jugador1;
        parejasEncontradas = 0;
        primeraCarta = null;
        segundaCarta = null;
        crearCartas();
        Collections.shuffle(listaCartas);
        llenarTablero();
    }

    public void crearCartas() {

        listaCartas.clear();

        String[] normales = {"blastoise", "bulbasaur", "charmander", "charmeleon", "clefable", "dwebble", "eevee", "haunter", "lickitung", "pajaro1", "pajaro2", "pajaro3", "rata1", "rata2", "serpiente1", "serpiente2"};

        String[] especiales = {"pikachu", "raichu"};

        String rutaOculta = "/Imagenes/pantalla.png";

        for (int i = 0; i < normales.length; i++) {

            String rutaFrente = "/Imagenes/" + normales[i] + ".png";

            listaCartas.add(new CasillaNormal(normales[i], rutaFrente, rutaOculta, 100, 100, this));
            listaCartas.add(new CasillaNormal(normales[i], rutaFrente, rutaOculta, 100, 100, this));
        }

        for (int i = 0; i < especiales.length; i++) {

            String rutaFrente = "/Imagenes/" + especiales[i] + ".png";

            listaCartas.add(new CasillaEspecial(especiales[i], rutaFrente, rutaOculta, 100, 100, this));
            listaCartas.add(new CasillaEspecial(especiales[i], rutaFrente, rutaOculta, 100, 100, this));
        }
    }

    public void llenarTablero() {

        int posicion = 0;

        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 6; columna++) {

                tablero[fila][columna] = listaCartas.get(posicion);
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

            
            primeraCarta = null;
            segundaCarta = null;
        }
    }

    public void parejaIncorrecta(Carta carta1, Carta carta2) {
        
        tiempo = new Timer(1500, ev -> {
            
            carta1.ocultar();
        carta2.ocultar();
        primeraCarta = null;
        segundaCarta = null;

        cambiarTurno();
            
            
        });
        tiempo.setRepeats(false);
        tiempo.start();

        
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
        } catch (Exception e) {
            return null;
        }
    }

    public Carta[][] getTablero() {
        return tablero;
    }

    public ArrayList<Carta> getListaCartas() {
        return listaCartas;
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
    
    public void procesarClick(Carta carta) {
        if (carta == null) {
            return;
        }

        if (carta.IsDescubierta()) {
            return;
        }

        
        
        

        if (primeraCarta == null) {
            primeraCarta = carta;
            carta.Mostrar();
            return;
        }

        if (segundaCarta == null) {
            segundaCarta = carta;
            carta.Mostrar();
            compararCartas();
            if(gui != null){
                gui.actualizarPuntaje();
            }
        }
    }
    private void compararCartas() {
        if (verificarPareja(primeraCarta, segundaCarta)) {
            registrarPareja(primeraCarta, segundaCarta);
        } else {
            parejaIncorrecta(primeraCarta, segundaCarta);
        }

        
    }
    
}