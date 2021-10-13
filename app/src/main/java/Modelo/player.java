package Modelo;

public class player {
    String user;
    int puntos;

    public player(String user, int puntos) {
        this.user = user;
        this.puntos = puntos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
