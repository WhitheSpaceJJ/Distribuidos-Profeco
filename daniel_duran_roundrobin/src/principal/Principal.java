package principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    static Scanner teclado = new Scanner(System.in);
    static int contadorMEmoria = 0;
    static int contadorTrabajoFinalizado = 0;
    static int subt = 0;
    static int tamanoMemoria = 50000;
    static String trabajando;
    static String siguiente;
    static String guardando;
    static String cargando;

    static int quantum = 5;
    static Trabajo[] trabajos = new Trabajo[25];
    static List<Memoria> bloquesMemoria = new ArrayList<>();

    public static void main(String[] args) {
        asignarTrabajos();
        imprimirDatos();
        establecerInicio();
        comenzarTrabajo();
    }

    public static void asignarTrabajos() {
        List<Trabajo> jobsObjs = new ArrayList<Trabajo>();

        jobsObjs.add(new Trabajo("1", 2, 740, "", ""));
        jobsObjs.add(new Trabajo("2", 1, 420, "", ""));
        jobsObjs.add(new Trabajo("3", 2, 760, "", ""));
        jobsObjs.add(new Trabajo("4", 6, 5760, "", ""));
        jobsObjs.add(new Trabajo("5", 8, 6990, "", ""));
        jobsObjs.add(new Trabajo("6", 4, 2550, "", ""));
        jobsObjs.add(new Trabajo("7", 6, 3930, "", ""));
        jobsObjs.add(new Trabajo("8", 10, 9350, "", ""));
        jobsObjs.add(new Trabajo("9", 5, 3820, "", ""));
        jobsObjs.add(new Trabajo("10", 7, 5950, "", ""));
        jobsObjs.add(new Trabajo("11", 8, 8390, "", ""));
        jobsObjs.add(new Trabajo("12", 3, 2030, "", ""));
        jobsObjs.add(new Trabajo("13", 5, 3610, "", ""));
        jobsObjs.add(new Trabajo("14", 1, 220, "", ""));
        jobsObjs.add(new Trabajo("15", 10, 9140, "", ""));
        jobsObjs.add(new Trabajo("16", 10, 8940, "", ""));
        jobsObjs.add(new Trabajo("17", 4, 2710, "", ""));
        jobsObjs.add(new Trabajo("18", 8, 7540, "", ""));
        jobsObjs.add(new Trabajo("19", 6, 4190, "", ""));
        jobsObjs.add(new Trabajo("20", 8, 7540, "", ""));
        jobsObjs.add(new Trabajo("21", 7, 6890, "", ""));
        jobsObjs.add(new Trabajo("22", 7, 6580, "", ""));
        jobsObjs.add(new Trabajo("23", 3, 1380, "", ""));
        jobsObjs.add(new Trabajo("24", 4, 3210, "", ""));
        jobsObjs.add(new Trabajo("25", 5, 3290, "", ""));
        for (int i = 0; i < jobsObjs.size(); i++) {
            Trabajo get = jobsObjs.get(i);
            trabajos[i] = get;
        }
    }

    public static void hlp(int x, int proc, int procNext) {
        String auxProc = Integer.toString(proc);
        String auxProcNext = Integer.toString(procNext);
        if (x == 0) {
            Principal.trabajando = auxProc;
            Principal.siguiente = auxProcNext;
            procIn();

        } else if (x == 1) {
            Principal.guardando = auxProc;
            Principal.cargando = auxProcNext;
            Principal.siguiente = auxProcNext;
            procComplete();

        }
    }

    public static void establecerInicio() {
        if (Principal.contadorTrabajoFinalizado == 25) {
            System.out.println("EJECUCION TERMINADA\n");
            imprimirDatos();
            System.out.println("Memoria; "+Principal.tamanoMemoria);
        } else {
            for (int j = 0; j < trabajos.length; j++) {
                teclado.nextLine();

                trabajos[j].setMarcador("<<");
                if ((trabajos[j].getSizeIn() <= Principal.tamanoMemoria) && (trabajos[j].getStat().equals("en espera")
                        || trabajos[j].getStat().equals("")) && (Principal.contadorTrabajoFinalizado != 25)) {
                    trabajos[j].setStat("ejecutando");

                    contadorMEmoria++;
                    Principal.tamanoMemoria = Principal.tamanoMemoria - Principal.trabajos[j].getSizeIn();
                    bloquesMemoria.add(new Memoria(contadorMEmoria, trabajos[j].getSize(), trabajos[j].getPosc(), trabajos[j].getTime(), "ocupado", "<<"));

                    imprimirDatos();
                    System.out.println("");
                    System.out.printf("%50s,%4s", "Tamaño Dinamico", Principal.tamanoMemoria);

                    trabajos[j].setMarcador("");
                    bloquesMemoria.get(bloquesMemoria.size() - 1).setMarcador("");

                } else {
                    if (trabajos[j].getStat().equals("ejecutando") || trabajos[j].getStat().equals("terminado")) {

                    } else {
                        trabajos[j].setStat("en espera");
                    }

                    imprimirDatos();
                    System.out.println("");
                    System.out.printf("%50s,%4s", "Tamaño Dinamico", Principal.tamanoMemoria);

                }
                trabajos[j].setMarcador("");

            }
        }
    }

    public static void comenzarTrabajo() {
        for (int k = 0; k < bloquesMemoria.size(); k++) {
            teclado.nextLine();
            if (k == bloquesMemoria.size() - 1) {
                hlp(0, bloquesMemoria.get(k).getPosc(), bloquesMemoria.get(0).getPosc());
            } else {
                hlp(0, bloquesMemoria.get(k).getPosc(), bloquesMemoria.get(k + 1).getPosc());
            }

            if (bloquesMemoria.get(k).getTimeIn() <= Principal.quantum && bloquesMemoria.get(k).getTimeIn() != 0) {

                teclado.nextLine();
                bloquesMemoria.get(k).setMarcador("<<");
                trabajos[bloquesMemoria.get(k).getProcIn() - 1].setMarcador("<<");
                imprimirDatos();
                do {
                    subt = bloquesMemoria.get(k).getTimeIn() - 1;
                    String aux = Integer.toString(subt);
                    bloquesMemoria.get(k).setTime(aux);
                    teclado.nextLine();

                    printMemory();

                } while (bloquesMemoria.get(k).getTimeIn() > 0);

                trabajos[bloquesMemoria.get(k).getProcIn() - 1].setStat("terminado");
                bloquesMemoria.get(k).setStat("libre");
//                int temp = memys.get(k).getProcIn();
                bloquesMemoria.get(k).setProc("");
                bloquesMemoria.get(k).setTime("");
                bloquesMemoria.get(k).setSize("");

                teclado.nextLine();
                imprimirDatos();
                Principal.contadorTrabajoFinalizado++;

                trabajos[bloquesMemoria.get(k).getProcIn() - 1].setMarcador("");
                Principal.tamanoMemoria = Principal.tamanoMemoria + trabajos[bloquesMemoria.get(k).getProcIn() - 1].getSizeIn();
                bloquesMemoria.get(k).setMarcador("");
                bloquesMemoria.remove(k);
                k = k - 1;
                teclado.nextLine();
                printMemory();
                establecerInicio();

            } else {
                if (bloquesMemoria.get(k).getTimeIn() > Principal.quantum && bloquesMemoria.get(k).getTimeIn() != 0) {
                    bloquesMemoria.get(k).setMarcador("<<");
                    trabajos[bloquesMemoria.get(k).getProcIn() - 1].setMarcador("<<");
                    teclado.nextLine();
                    imprimirDatos();
                    byte substc = 0;
                    do {
                        teclado.nextLine();
                        substc++;
                        subt = (bloquesMemoria.get(k).getTimeIn() - 1);
                        String aux = Integer.toString(subt);
                        bloquesMemoria.get(k).setTime(aux);
                        printMemory();
                    } while (substc < Principal.quantum);
                    bloquesMemoria.get(k).setMarcador("");
                    trabajos[bloquesMemoria.get(k).getProcIn() - 1].setMarcador("");

                }
                if (k == bloquesMemoria.size() - 1) {
                    teclado.nextLine();
                    hlp(1, bloquesMemoria.get(k).getPosc(), bloquesMemoria.get(0).getPosc());
                } else {
                    teclado.nextLine();
                    hlp(1, bloquesMemoria.get(k).getPosc(), bloquesMemoria.get(k + 1).getPosc());
                }
            }
            if (k == bloquesMemoria.size() - 1) {
                k = -1;
            }
        }
    }

    public static void imprimirDatos() {
                    System.out.println("Quantum; "+Principal.quantum);
        System.out.printf("%141s\n", "");
        System.out.printf("%141s\n", "--------------------------------------------------------------------------------------------------------------");
        System.out.printf("%35s %33S %8s %39s %1s\n", "|", "               Tabla de Tareas                ", "|", "             Tabla de Memorias           ", "");
        System.out.printf("%141s\n", "--------------------------------------------------------------------------------------------------------------");
        System.out.printf("%35s %8S %10s %10s %8s %14s %13s %10s %10s %10s %3s\n", "|", "posicion", "tiempo", "tamanio", "estado", "|", "bloque", "tamanio", "proceso", "tiempo", "|");
        for (int i = 0; i < trabajos.length; i++) {
            try {
                System.out.printf("%35s %4s %10d %15d %15s %1s %5s %10s %10s %10s %10s %7s %1s\n", "|", trabajos[i].getPosc(), trabajos[i].getTimeIn(), trabajos[i].getSizeIn(), trabajos[i].getStat(), trabajos[i].getMarcador(), "|", bloquesMemoria.get(i).getPosc(), bloquesMemoria.get(i).getSize(), bloquesMemoria.get(i).getProceso(), bloquesMemoria.get(i).getTime(), "|", bloquesMemoria.get(i).getMarcador());
            } catch (Exception e) {
                System.out.printf("%35s %4s %10s %15d %15s %1s %5s %51s\n", "|", trabajos[i].getPosc(), trabajos[i].getTimeIn(), trabajos[i].getSizeIn(), trabajos[i].getStat(), trabajos[i].getMarcador(), "|", "|");
            }
        }
        System.out.println("");
    }

    public static void printJobs() {
                            System.out.println("Quantum; "+Principal.quantum);
        System.out.printf("%30S\n", "          Tabla de Tareas          ");
        System.out.printf("%10s %10s %10s %10s\n", "posicion", "tiempo", "tamanio", "estado");
        for (Trabajo jobsObj1 : trabajos) {
            jobsObj1.printDatos();
        }
        System.out.println("");
    }

    public static void printMemory() {
                            System.out.println("Quantum; "+Principal.quantum);
        System.out.printf("%142s\n", "------------------------------------------");
        System.out.printf("%133s\n", "             Tabla de Memoria            ");
        System.out.printf("%100s %6s %10s %10s %10s %1s\n", "|", "bloque", "tamanio", "proceso", "tiempo", "|");
        for (int j = 0; j < bloquesMemoria.size(); j++) {
            bloquesMemoria.get(j).memDat();
        }
        System.out.printf("%142s\n", "------------------------------------------");
        System.out.println("");
    }

    public static void procIn() {
                            System.out.println("Quantum; "+Principal.quantum);
        System.out.printf("%106s \n", "-------------------------------");
        System.out.printf("%99s\n", "             Procesador            ");
        System.out.printf("%87s %15s %2s %1s %85s %16s %2s %1s %94s %11s %93s %11s %1s ", "|Atendiendo:", Principal.trabajando, "|", "\n", "|Siguiente:", Principal.siguiente, "|", "\n", "|Guardando contexto:", "|\n", "|Cargando contexto:", "|", "\n");
        System.out.printf("%105s \n", "-------------------------------");
        System.out.println("");
    }

    public static void procComplete() {
                            System.out.println("Quantum; "+Principal.quantum);
        System.out.printf("%106s \n", "-------------------------------");
        System.out.printf("%99s\n", "            Procesador           ");
        System.out.printf("%87s %16s %1s %1s %85s %17s %1s %1s %94s %8s %1s %1s %93s %9s %1s %1s ", "|Atendinedo:", Principal.trabajando, "|", "\n",
                "|Siguiente:", Principal.siguiente, "|", "\n",
                "|Guardando Contexto:", Principal.guardando, "|", "\n",
                "|Cargando Contexto:", Principal.cargando, "|", "\n");
        System.out.printf("%105s \n", "-------------------------------");
        System.out.println("");
    }

}
