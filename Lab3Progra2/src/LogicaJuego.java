public interface LogicaJuego {

    public abstract void iniciarJuego();

    public abstract boolean verificarPareja(Carta carta1, Carta carta2);

    public abstract boolean juegoTerminado();

    public abstract String finalizarJuego();
}