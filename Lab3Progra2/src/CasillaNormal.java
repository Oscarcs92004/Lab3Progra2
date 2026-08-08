/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

import javax.swing. *;
import java.awt. *;
public class CasillaNormal extends Carta{


    public CasillaNormal(String id, String rutaFrente, String rutaOculta, int alto, int ancho) {
        super(id, rutaFrente, rutaOculta, alto, ancho);
    }

    public int getPuntaje() {
        return 1;
    }
    
    
    
}
