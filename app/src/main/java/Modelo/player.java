package Modelo;

public class player {
    String user;
    int puntos;
    int power;


    public player(String user, int puntos, int power) {
        this.user = user;
        this.puntos = puntos;
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public player(String user, int puntos) {
        this.user = user;
        this.puntos = puntos;
        power = 0;
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
