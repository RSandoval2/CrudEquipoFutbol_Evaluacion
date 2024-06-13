package entidadesdenegocio;

public class Equipo {

    private int id;
    private String nombrejugador;
    private String apellidojugador;
    private int numerocamiseta;
    private String posicion;

    public Equipo(){}

    public Equipo(int id, String nombrejugador, String apellidojugador, int numerocamiseta, String posicion) {
        this.id = id;
        this.nombrejugador = nombrejugador;
        this.apellidojugador = apellidojugador;
        this.numerocamiseta = numerocamiseta;
        this.posicion = posicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrejugador() {
        return nombrejugador;
    }

    public void setNombrejugador(String nombrejugador) {
        this.nombrejugador = nombrejugador;
    }

    public String getApellidojugador() {
        return apellidojugador;
    }

    public void setApellidojugador(String apellidojugador) {
        this.apellidojugador = apellidojugador;
    }

    public int getNumerocamiseta() {
        return numerocamiseta;
    }

    public void setNumerocamiseta(int numerocamiseta) {
        this.numerocamiseta = numerocamiseta;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}

