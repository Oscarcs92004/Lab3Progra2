/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */
public class Jugador {
    private String nombre;
    private int puntos;
    
    public Jugador(String nombre){
        this.nombre = nombre;
        puntos = 0 ;
    }

    public String getNombre(){
        return nombre;
    }

    
}
