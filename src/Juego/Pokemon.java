package Juego;

import Movimientos.Movimientos;
import Tipos.Tipo;

import java.util.ArrayList;
import java.util.List;

/*
 La clase abstracta es una plantilla para poder crear nuestros objetos,
 por que una clase abstracta no puede crear un objeto
 */
public abstract class Pokemon {
    protected final String nombre;
    protected final int hpMax;
    protected int hpActual;
    protected final int ataque;
    protected final int defensa;
    protected final Tipo tipo;
    protected final String imagenPokemon;
    protected final List<Movimientos> movimientos; // Lista que funciona para almacenar habilidades

    /*
    Nuestro constructor esta protegido con protected para que nadie mas pueda acceder a los valores
    editarlos o modificarlos, es el encapsulamiento de nuestro codigo
     */
    protected Pokemon(String nombre, int hp, int ataque, int defensa, Tipo tipo, String imagenPokemon, List<Movimientos> movimientosIniciales) {
        if (nombre == null) {
            throw new NullPointerException("El nombre no puede ser Nulo");
        }
        this.nombre = nombre;

        if (hp <= 0) {
            throw new IllegalArgumentException("HP debe ser positivo");
        }
        this.hpMax = hp;
        this.hpActual = hp;

        this.ataque = Math.max(0, ataque); // Asegura que no sea negativo
        this.defensa = Math.max(0, defensa);

        if (tipo == null) {
            throw new NullPointerException("El tipo no puede ser null");
        }
        this.tipo = tipo;

        this.imagenPokemon = imagenPokemon != null ? imagenPokemon : ""; // Asigna "" si es null

/*
El array list sirve para poder almacenar las habilidades
en este caso son movimientos que los pokemones pueden tener
 */
        this.movimientos = new ArrayList<>();
        if (movimientosIniciales != null) {
            for (Movimientos movimiento1 : movimientosIniciales) {
                // Añade hasta 4 movimientos
                if (this.movimientos.size() < 4 && movimiento1 != null) {
                    this.movimientos.add(movimiento1);
                }
            }
        }
    }

    /*
    Los metodos Get funcionan para poder consultar el valor que tienen asignados
    puesto que estan protegidos y los get son publicos para poder consultarlos
     */
    public String getNombre() {
        return nombre; }
    public int getHpActual() {
        return hpActual; }
    public int getHpMax() {
        return hpMax; }
    public int getAtaque() {
        return ataque; }
    public int getDefensa() {
        return defensa; }
    public Tipo getTipo() {
        return tipo; }
    public String getImagenPokemon() {
        return imagenPokemon; }
    public List<Movimientos> getMovimientos() {
        return movimientos; }

   //Es el metodo para que un Pokemon pueda aprender un movimiento
    public String aprenderMovimiento(Movimientos movimientos) {
        if (movimientos != null && this.movimientos.size() < 4) {
            //  "this.movimientos.add()" añade el movimiento a la lista
            this.movimientos.add(movimientos);
            return this.nombre + " aprendió " + movimientos.getNombre() + "!";
        } else if (movimientos == null) {
            return "No se puede aprender un movimiento nulo.";
        } else {
            // "this.movimiento.size" muestra cuantos movimientos tienes aprendidos
            return this.nombre + " ya conoce " + this.movimientos.size() + " movimientos.";
        }
    }

   //Es el metodos para que el pokemon reciba daño los hps en este caso deben disminuir
    public int recibirDano(int dano) {
        int danoReal = Math.max(0, dano); // Math.max nos ayuda a que no reciba daño negativo
        this.hpActual -= danoReal;
        if (this.hpActual < 0) { // el hp no puede bajar de
            this.hpActual = 0;
        }
        return danoReal; // Devuelve cuanto daño se realizo
    }

    // Este metodo que sirver para comprobar si nuestro pokemon sigue vivo
    public boolean vivo() {
        return this.hpActual > 0; //Si la vida es mayor a  el 0 siguie vivo
    }
}