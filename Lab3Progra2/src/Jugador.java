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
    private int cantAciertos;
    
    public Jugador(String nombre){
        this.nombre = nombre;
        puntos = 0 ;
        cantAciertos = 0;
    }

    public String getNombre(){
        return nombre;
    }

    public int getPuntos(){
        return puntos;
    }
    
    public void sumarPunto(Carta carta){
        //dependiendo de la carta 
        puntos += carta.getPuntaje();
        cantAciertos++;
    }
    
    public int getCantAciertis(){
        return cantAciertos;
    }
    
    public void reiniciarPuntos(){
        puntos = 0;
    }
    
}
