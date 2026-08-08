/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;


/**
 *
 * @author oscar
 */
public class GUI extends JFrame{
    private JTextField txtJug1;
    private JTextField txtJug2;
    private JButton iniciar;
    private JButton salir;
    private JPanel panelInicio;
    private JPanel panelJuego;
    private JPanel panelTablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private JLabel mensaje;
    private Controlador controlador;
    private JPanel panelContenedor;
    
    private void inicializarPaneles(){
        
        panelContenedor = new JPanel(new BorderLayout());
        panelContenedor.setOpaque(false);

        mensaje = new JLabel(" ");

        mensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);

        panelContenedor.add(panelInicio, BorderLayout.CENTER);
        panelContenedor.add(mensaje, BorderLayout.SOUTH);

        add(panelContenedor, BorderLayout.CENTER);
    }
    
    private void crearPanelJuego(){
        panelJuego = new JPanel();
        panelJuego.setLayout(new BorderLayout(10,10));
        
        JPanel panelJugadores = new JPanel(new GridLayout(1,2));
        
        JLabel jug1 = new JLabel(controlador.getJugador1().getNombre() + " - Puntos: 0", SwingConstants.CENTER);
        JLabel jug2 = new JLabel(controlador.getJugador1().getNombre() + " - Puntos: 0", SwingConstants.CENTER);
    
        panelJugadores.add(jug1);
        panelJugadores.add(jug2);
        
        panelJuego.add(panelJugadores, BorderLayout.NORTH);
        
        inicializartablero(controlador.getTablero(),900,650);
        
        panelJuego.add(panelTablero,BorderLayout.CENTER);
        
        panelContenedor.removeAll();
        panelContenedor.add(panelJuego,BorderLayout.CENTER);
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }
    private void inicializartablero(Carta[][] tablero,  int anchoPanel,int altoPanel){
        
        int filas = tablero.length;
        int columnas = tablero[0].length;

        panelTablero = new JPanel(  new GridLayout(filas, columnas, 10, 10));

        panelTablero.setPreferredSize(new Dimension(anchoPanel, altoPanel) );

        panelTablero.setOpaque(false);

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                Carta carta = tablero[fila][columna];

                carta.setFocusPainted(false);
                carta.setMargin(new Insets(0, 0, 0, 0));

                panelTablero.add(carta);
            }
        }

       //add o return de ese jpanel

    }
    
    private void iniciarPartida(){
        String nombre1 = txtJug1.getText().trim();
        String nombre2 = txtJug2.getText().trim();
    
        if(nombre1.isEmpty() || nombre2.isEmpty()){
            mensaje.setText("Error: Debe ingresar el nombre de ambos jugadores.");
            return;
        }
        controlador = new Controlador(nombre1,nombre2);
        controlador.iniciarJuego();
        crearPanelJuego();
    }
    
    private void inicializarComponentes(){
        panelInicio = new JPanel();
        panelInicio.setLayout(new BorderLayout(10,10));
        JLabel titulo = new JLabel("Pokemon Memory Game", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new GridLayout(4,1,10,10));
        
        JLabel jugador1 = new JLabel("Nombre del Jugador 1: ", SwingConstants.CENTER);
        txtJug1 = new JTextField();
        txtJug1.setHorizontalAlignment(JTextField.CENTER);
        
        JLabel jugador2 = new JLabel("Nombre del jugador 2: ", SwingConstants.CENTER);
        txtJug2 = new JTextField();
        txtJug2.setHorizontalAlignment(JTextField.CENTER);
        iniciar = new JButton("Iniciar Partida");
        salir = new JButton("Salir");
        JPanel panelBotones = new JPanel();
        
        panelJugadores.add(jugador1);
        panelJugadores.add(txtJug1);
        panelJugadores.add(jugador2);
        panelJugadores.add(txtJug2);
        panelBotones.add(iniciar);
        panelBotones.add(salir);
        panelInicio.add(titulo, BorderLayout.NORTH);
        panelInicio.add(panelJugadores,BorderLayout.CENTER);
        panelInicio.add(panelBotones, BorderLayout.SOUTH);
        
        
        salir.addActionListener(e-> { System.exit(0);});
        iniciar.addActionListener(e-> iniciarPartida());
        
    }
    
    public GUI(){
        setTitle("Pokemon Memory Game");
        setSize(1200,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        inicializarComponentes();
        inicializarPaneles();
        setVisible(true);
    }
}
