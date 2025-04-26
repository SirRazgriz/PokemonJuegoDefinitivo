package Movimientos;

import Juego.Pokemon;
import Tipos.Tipo;

public class Placaje implements Movimientos {

    @Override
    public String getNombre() { return "PLACAJE"; }
    @Override
    public int getPoder() { return 35; }
    @Override
    public Tipo getTipo() { return Tipo.NORMAL; }
    @Override
    public int getPP() { return 35; }

    @Override
    public String usar(Pokemon atacar, Pokemon defender) {
        String Registro = atacar.getNombre() + " usa " + getNombre() + " contra " + defender.getNombre() + "!";

        // Llama al método simplificado del enum Tipo
        double efectividad = Tipo.efectividad(this.getTipo(), defender.getTipo());

        // Cálculo de daño (simple)
        int danoBase = (atacar.getAtaque() / 5) + getPoder() - (defender.getDefensa() / 6);
        int danoFinal = (int) (Math.max(1, danoBase) * efectividad); // Math.max se asegura de que almenos se realize daño y no haya daño negativo

        int danoInfligido = defender.recibirDano(danoFinal);

        // Mensajes de efectividad
        if (efectividad == 0.0) Registro += "\n¡No tuvo efecto en " + defender.getNombre() + "!";
        else if (efectividad > 1.0) Registro += "\n¡Es súper efectivo!";
        else if (efectividad < 1.0) Registro += "\nNo es muy efectivo...";

        if (efectividad > 0.0) {
            Registro+= "\n" + defender.getNombre() + " recibió " + danoInfligido + " puntos de daño.";
        }
        return Registro;
    }
}