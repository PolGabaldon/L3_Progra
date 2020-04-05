package ub.info.prog2.GabaldonPolMartinezMarti.vista;

import java.util.Scanner;

/**
 *
 * @author GabaldonPolMartinezMarti
 */
public class IniciadorReproductorUB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ReproductorUB2 repro = new ReproductorUB2();
        repro.gestioReproductorUB(sc);
    }
}
