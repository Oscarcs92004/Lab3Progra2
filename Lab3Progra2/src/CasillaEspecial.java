/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
import javax.swing.*;
import java.awt.*;
public class CasillaEspecial extends Carta {
    

    public CasillaEspecial(String id, String rutaFrente, String rutaOculta, int alto, int ancho) {
        super(id, rutaFrente, rutaOculta, alto, ancho);
    }

    @Override
    public void Mostrar() {
        super.Mostrar();
        setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        
    }

    

    @Override
    public int getPuntaje() {
        return 2;
    }
}

