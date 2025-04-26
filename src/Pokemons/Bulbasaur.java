package Pokemons;

import Juego.Pokemon;
import Tipos.Tipo;

public class Bulbasaur extends Pokemon {

    public Bulbasaur() {
        super(
                " Bulbasaur ",
                200,
                49,
                49,
                Tipo.PLANTA,
                Bulbasaur.class.getResource("/sprites/bulbasaur.png").toExternalForm(),
                null
        );
    }
}
