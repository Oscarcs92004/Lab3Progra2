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

/**
 *
 * @author oscar
 */
public class GUI extends JFrame{
    private JTextField txtJug1;
    private JTextField txtJug2;
    private JButton iniciar;
    private JButton salir;
    
    private void inicializarComponentes(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10,10));
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
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(panelJugadores,BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel);
    }
    
    public GUI(){
        setTitle("Pokemon Memory Game");
        setSize(1200,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        inicializarComponentes();
        setVisible(true);
    }
}
