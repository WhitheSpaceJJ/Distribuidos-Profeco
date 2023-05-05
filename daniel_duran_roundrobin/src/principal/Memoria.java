package principal;

import java.io.PrintStream;

public class Memoria {

    int dynSize, timeIn, posc, procIn;
    String size, proc, time, marcador, avance;

    public Memoria(int a, String b, String c, String d, String e, String r) {
        this.posc = a;
        this.size = b;
        this.proc = c;
        this.procIn = Integer.parseInt(c);
        this.time = d;
        this.timeIn = Integer.parseInt(d);
        this.marcador = r;
        this.avance = e;
        this.dynSize = 50000;

    }

    public void setPosc(int x) {
        this.posc = x;
    }

    public int getPosc() {
        return this.posc;
    }

    public void setSize(String x) {
        this.size = x;
    }

    public String getSize() {
        return this.size;
    }

    public void setProc(String x) {
        this.proc = x;
    }

    public String getProceso() {
        return this.proc;
    }

    public void setTime(String x) {
        this.time = x;
        if (x.equals("")) {
            this.timeIn = 0;
        } else {
            this.timeIn = Integer.parseInt(x);
        }

    }

    public int getTimeIn() {
        return this.timeIn;
    }

    public void setMarcador(String x) {
        this.marcador = x;
    }

    public String getMarcador() {
        return this.marcador;
    }

    public void setDynSize(int x) {
        this.dynSize = x;
    }

    public int getDynSize() {
        return dynSize;
    }

    public String getStat() {
        return avance;
    }

    public void setStat(String x) {
        this.avance = x;
    }

    public int getProcIn() {
        return procIn;
    }

    public String getTime() {
        return this.time;
    }

    public PrintStream memDat() {
        return System.out.printf("%100s %6s %10s %10s %10s %2s %1s \n", "|", this.posc, this.size, this.proc, this.time, "|", this.marcador);

    }

}
