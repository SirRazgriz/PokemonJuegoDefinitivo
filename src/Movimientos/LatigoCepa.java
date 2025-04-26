package Movimientos;

import Movimientos.*;

import Juego.Pokemon;
import Tipos.Tipo;

public class LatigoCepa implements Movimientos {

    @Override
    public String getNombre() { return "Latigo Cepa"; }
    @Override
    public int getPoder() { return 45; }
    @Override
    public Tipo getTipo() { return Tipo.PLANTA; }
    @Override
    public int getPP() { return 30; }

    @Override
    public String usar(Pokemon ataca, Pokemon defiende) {
        String Registro = ataca.getNombre() + " usa " + getNombre() + " contra " + defiende.getNombre() + "!";

        // Llama al método simplificado del enum Tipo
        double efectividad = Tipo.efectividad(this.getTipo(), defiende.getTipo());

        // Cálculo de daño (simple)
        int danoBase = (ataca.getAtaque() / 5) + getPoder() - (defiende.getDefensa() / 6);
        int danoFinal = (int) (Math.max(1, danoBase) * efectividad); // Math.max se asegura de que almenos se realize daño y no haya daño negativo

        int danoInfligido = defiende.recibirDano(danoFinal);

        // Mensajes de efectividad
        if (efectividad == 0.0) Registro += "\n¡No tuvo efecto en " + defiende.getNombre() + "!";
        else if (efectividad > 1.0) Registro += "\n¡Es súper efectivo!";
        else if (efectividad < 1.0) Registro += "\nNo es muy efectivo...";

        if (efectividad > 0.0) {
            Registro += "\n" + defiende.getNombre() + " recibió " + danoInfligido + " puntos de daño.";
        }
        return Registro;
    }
}