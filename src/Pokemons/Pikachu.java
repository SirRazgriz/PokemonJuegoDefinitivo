package Pokemons;


import Juego.Pokemon;
import Tipos.Tipo;

/*
Pikachu en este caso hereda todo los metodos que obtiene de Pokemon para poder crear
a nuestro objeto que en este caso es Pikachu
 */
public class Pikachu extends Pokemon {

    /*
    Llamamos a el constructor
    para poder crear a Pikachu
     */
    public Pikachu() {
        super( // Llamamos al constructor desde Pokemon
                "Pikachu ",            // nombre
                180,                  // hp
                55,                   // ataque
                40,                   // defensa
                Tipo.ELECTRICO,       // tipo
                Bulbasaur.class.getResource("/sprites/pikachu.png").toExternalForm(),
                null                  // movimientos
        );
    }
}
