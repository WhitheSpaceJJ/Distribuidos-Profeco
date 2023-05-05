package principal;

import java.io.PrintStream;

public class Trabajo {

    int tamIn, timeIn;
    String posc, time, size, avance, marcador;

    public Trabajo(String a, int b, int c, String d, String e) {
        this.posc = a;
        this.timeIn = b;
        this.tamIn = c;
        this.avance = d;
        this.marcador = e;
        this.time = Integer.toString(b);
        this.size = Integer.toString(c);
    }

    public void setPosc(String x) {
        this.posc = x;
    }

    public String getJob() {
        return this.posc;
    }

    public void setTimeIn(int x) {
        this.timeIn = x;
    }

    public String getSize() {
        return this.size;
    }

    public void setSizeIn(int x) {
        this.tamIn = x;
    }

    public int getSizeIn() {
        return this.tamIn;
    }

    public void setStat(String x) {
        this.avance = x;
    }

    public String getStat() {
        return this.avance;
    }

    public void setMarcador(String x) {
        this.marcador = x;
    }

    public String getMarcador() {
        return this.marcador;
    }

    public String getPosc() {
        return posc;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public String getTime() {
        return time;
    }

    public PrintStream printDatos() {
        return System.out.printf("%10s %10d %10d %10s %10s\n", this.posc, this.timeIn, this.tamIn, this.avance, this.marcador, "\n");
    }

}
