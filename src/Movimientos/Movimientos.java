package Movimientos;

import Juego.Pokemon;
import Tipos.Tipo;

/*
Utilizamos la interface para poder desarrollar nuestros metodos y utilizarlos en otra clase,
 */
public interface Movimientos {
    String getNombre();
    int getPoder();
    Tipo getTipo();
    int getPP(); //Los PP son como el mana en Pokemon cada habilidad tiene un PP especificado
/*
Atacar es quien realiza el primer ataque
Defender es quien recibe el ataque

 */
    String usar(Pokemon atacar, Pokemon defender);
}
