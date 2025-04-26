package Tipos;

/*
Enumeracion es para poder contabilizar las constantes en este caso
son los tipos pokemon que vamos a utilizar
 */
public enum Tipo {
    NORMAL("Normal"),
    FUEGO("Fuego"),
    AGUA("Agua"),
    PLANTA("Planta"),
    ELECTRICO("Eléctrico"),
    TIERRA("Tierra"),
    VOLADOR("Volador"),
    BICHO("Bicho"),
    ROCA("Roca"),
    FANTASMA("Fantasma"),
    ACERO("Acero"),
    LUCHA("Lucha"),
    PSIQUICO("Psíquico"),
    DRAGON("Dragón"),
    SINIESTRO("Siniestro"),
    VENENO("Veneno"),
    HADA("Hada");


    private final String nombreTipo;

    // El Constructor de la enumeracion para poder utilizarlos
    Tipo(String nombreTipo) {
        this.nombreTipo = nombreTipo ;
    }

    // Método para poder mostrar el tipo
    public String getTipoPokemon() {
        return nombreTipo;
    }

    /*
    Son las efectividades de los tipos pokemon
    por ejemplo 2.0 es muy efectivo y 0.5 es no muy efectivo
     */
    public static double efectividad(Tipo atacante, Tipo defensor) {
        if (atacante == null || defensor == null) {
            return 1.0;
        }

        // El atacante pokemon
        switch (atacante) {
            case FUEGO:
                switch (defensor) {
                    case PLANTA: case BICHO: case ACERO: return 2.0; // Súper efectivo
                    case FUEGO: case AGUA: case ROCA: case DRAGON: return 0.5; // No muy efectivo
                    default: return 1.0;
                }
            case AGUA:
                switch (defensor) {
                    case FUEGO: case TIERRA: case ROCA: return 2.0;
                    case AGUA: case PLANTA: case DRAGON: return 0.5;
                    default: return 1.0;
                }
            case PLANTA:
                switch (defensor) {
                    case AGUA: case TIERRA: case ROCA: return 2.0;
                    case FUEGO: case PLANTA: case VENENO: case VOLADOR:
                    case BICHO: case DRAGON: case ACERO: return 0.5;
                    default: return 1.0;
                }
            case ELECTRICO:
                switch (defensor) {
                    case AGUA: case VOLADOR: return 2.0;
                    case ELECTRICO: case PLANTA: case DRAGON: return 0.5;
                    case TIERRA: return 0.0; // Inmune
                    default: return 1.0;
                }
            case NORMAL:
                switch (defensor) {
                    case ROCA: case ACERO: return 0.5;
                    case FANTASMA: return 0.0; // Inmune
                    default: return 1.0;
                }
            case TIERRA:
                switch (defensor) {
                    case FUEGO: case ELECTRICO: case VENENO: case ROCA: case ACERO: return 2.0;
                    case PLANTA: case BICHO: return 0.5;
                    case VOLADOR: return 0.0; // Inmune
                    default: return 1.0;
                }
            case VOLADOR:
                switch (defensor) {
                    case PLANTA: case LUCHA: case BICHO: return 2.0;
                    case ELECTRICO: case ROCA: case ACERO: return 0.5;
                    default: return 1.0;
                }
            case FANTASMA:
                switch (defensor) {
                    case PSIQUICO: case FANTASMA: return 2.0;
                    case SINIESTRO: return 0.5;
                    case NORMAL: return 0.0; // Inmune
                    default: return 1.0;
                }


            default:
                // Si el tipo no esta entonces su daño sera neutro.
                return 1.0;
        }
    }

    // Para que el nombre se muestre en el JavaFx En el pane
    @Override
    public String toString () {
        return nombreTipo;
    }
}