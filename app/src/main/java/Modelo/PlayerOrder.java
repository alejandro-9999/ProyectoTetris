package Modelo;

import java.util.Comparator;

public class PlayerOrder implements Comparator<player> {
    @Override
    public int compare(player player, player t1) {
        return Integer.compare(player.getPuntos(),t1.getPuntos());
    }
}
