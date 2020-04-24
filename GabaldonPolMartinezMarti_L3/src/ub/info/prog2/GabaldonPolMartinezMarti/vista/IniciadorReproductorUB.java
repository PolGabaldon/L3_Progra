package ub.info.prog2.GabaldonPolMartinezMarti.vista;

import java.util.Scanner;

/**
 *
 * @author GabaldonPolMartinezMarti
 */
public class IniciadorReproductorUB {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ReproductorUB3 repro = new ReproductorUB3();
        repro.gestionar();
        repro.gestioReproductorUB(sc);
        
        
    }
}
