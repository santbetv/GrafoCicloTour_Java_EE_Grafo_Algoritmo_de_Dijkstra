/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafociclotour.modelo;

import java.io.Serializable;

/**
 * @author Carlos Loaiza
 * @author Santiago Betancur
 * @version V.8
 */
public class Municipio implements Serializable {

    /**
     * Atributos principales que toman el nombre, la posicion en posx y posy
     */
    private String nombre = "";
    private int posx = 0;
    private int posy = 0;

    public Municipio() {
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public Municipio(String nombre, int posx, int posy) {
        this.nombre = nombre;
        this.posx = posx;
        this.posy = posy;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

}
