/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */

import javax.swing.*;
import java.awt.*;
    
public abstract class Carta extends JButton {
    protected String id;
    protected String rutaFrente;
    protected String rutaOculta;
    protected boolean descubierta;
    protected int alto;
    protected int ancho;
    protected Controlador controlador;

    public Carta(String id, String rutaFrente, String rutaOculta, int alto, int ancho, Controlador controlador) {
        this.id = id;
        this.rutaFrente = rutaFrente;
        this.rutaOculta = rutaOculta;
        this.descubierta = false;
        this.alto = alto;
        this.ancho= ancho;
        this.controlador= controlador;


        setFocusPainted(false);
        setBorderPainted(true);


        ocultar();
        agregarActionListener();
    }

    public boolean IsDescubierta() {
        return descubierta;
    }

    public String getId() {
        return id;
    }

    public void Mostrar() {

        setIcon(escalarImagen(rutaFrente));
        descubierta = true;
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        setText("");
    }

    public void ocultar() {
        setIcon(escalarImagen(rutaOculta));
        descubierta = false;
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));
        setText("");
    }

    public boolean esParejaCon(Carta otra) {
        boolean pareja;
        pareja = otra != null && id.equals(otra.getId());
        return pareja;
    }

    public abstract int getPuntaje();

    protected ImageIcon escalarImagen(String ruta) {
        ImageIcon origen = new ImageIcon(getClass().getResource(ruta));
        Image escala = origen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(escala);
    }
    
    
    private void agregarActionListener() {
        addActionListener(e -> {
            if (descubierta) {
                return;
            }

            if (!isEnabled()) {
                return;
            }

            controlador.procesarClick(this);
        });
    }

}

    

    

