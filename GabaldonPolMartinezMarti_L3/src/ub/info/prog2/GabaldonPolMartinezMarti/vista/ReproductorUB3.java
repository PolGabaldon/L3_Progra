package ub.info.prog2.GabaldonPolMartinezMarti.vista;

import java.util.Scanner;
import ub.info.prog2.utils.Menu;
import ub.info.prog2.GabaldonPolMartinezMarti.controlador.Controlador;
import ub.info.prog2.utils.ReproException;



/**
 * Classe que representa un reproductor amb el mètode gestioReproductorUB2 per a
 * gestionar la reproducció de fitxers.
 * @author GabaldonPolMartinezMarti
 */
public class ReproductorUB3 {
    // Declarem les opcions per a referir-se a les opcions dels menus
    static private enum OpcionsMenuPrincipal {GESTIO_FITXERS, CONTROL_REPRODUCCIO, GUARDAR_DADES, RECUPERAR_DADES, SORTIR};
    static private enum OpcionsGestioFitxers {CREAR_PORTAFOLI, MOSTRAR_PORTAFOLIS, ELIMINAR_PORTAFOLI, AFEGIR_FITXER, MOSTRAR_FITXERS, 
                                              ELIMINAR_FITXER, MENU_ANTERIOR};
    static private enum OpcionsAfegirFitxer {AFEGIR_AUDIO, AFEGIR_IMATGE, MENU_ANTERIOR};
    static private enum OpcionsControlReproduccio {REPRODUIR_FITXER_MULTIMEDIA, REPRODUIR_REPOSITORI, REPRODUIR_PORTAFOLI, 
                                                   ON_OFF_REPRODUCCIO_CICLICA,  ON_OFF_REPRODUCCIO_REVERSE, GESTIO_REPRODUCCIO, MENU_ANTERIOR};
    static private enum OpcionsGestioReproduccio {PLAY, PAUSA, ATURA, SALTA, MENU_ANTERIOR};
    
    // Declarem descripcions personalitzades per a les opcions del menu principal
    static private String[] descMenuPrincipal = {"Menú gestió dels fitxers", "Control Reproducció/Visió", "Guardar dades", "Recuperar dades", "Sortir"};

    // Declarem descripcions personalitzades per a les opcions de la gestio de fitxers
    static private String[] descGestioFitxers = {"Crear portafoli", "Mostrar portafolis", "Eliminar portafolis", "Agefir fitxer",
                                                 "Mostrar fitxers", "Eliminar fitxer", "Tornar al menú anterior"};
    
    // Declarem descripcions personalitzades per a les opcions d'afegir fitxers
    static private String[] descAfegirFitxer = {"Afegir fitxer d'àudio","Afegir fitxer d'imatge","Tornar al menú anterior"};
    
    // Declarem descripcions personalitzades per a les opcions de controlar la reproduccio
    static private String[] descControlReproduccio = {"Reproduir un fitxer multimèdia","Reproduir el repositori","Reproduir un portafoli",
                                                      "Activar/Desactivar la reproducció cíclica", "Activar/Desactivar la reproducció reverse",
                                                      "Gestionar la reproducció", "Tornar al menú anterior"};
    
    // Declarem descripcions personalitzades per a les opcions d'afegir fitxers
    static private String[] descGestioReproduccio = {"Play","Pausa","Atura", "Salta un fitxer", "Tornar al menú anterior"};

    /**
     * Fitxer principal. Crea el menú principal i uns menús secundaris
     * @param args the command line arguments
     */
    private Menu<ReproductorUB3.OpcionsMenuPrincipal> menu;
    private Menu<ReproductorUB3.OpcionsGestioFitxers> gestioFitxers;
    private Menu<ReproductorUB3.OpcionsAfegirFitxer> afegirFitxer;
    private Menu<ReproductorUB3.OpcionsControlReproduccio> controlReproduccio;
    private Menu<ReproductorUB3.OpcionsGestioReproduccio> gestioReproduccio;
    private Controlador controlador;
    
   
    public ReproductorUB3(){
        menu = new Menu<>("Menu Principal",ReproductorUB3.OpcionsMenuPrincipal.values());
        gestioFitxers = new Menu<>("Gestió Fitxers",ReproductorUB3.OpcionsGestioFitxers.values());
        afegirFitxer = new Menu<>("Afegir Fitxer",ReproductorUB3.OpcionsAfegirFitxer.values());
        controlReproduccio = new Menu<>("Control Reproduccio",ReproductorUB3.OpcionsControlReproduccio.values());
        gestioReproduccio = new Menu<>("Gestio Reproduccio",ReproductorUB3.OpcionsGestioReproduccio.values());
        
        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);
        gestioFitxers.setDescripcions(descGestioFitxers);
        afegirFitxer.setDescripcions(descAfegirFitxer);
        controlReproduccio.setDescripcions(descControlReproduccio);
        gestioReproduccio.setDescripcions(descGestioReproduccio);
        
        controlador = new Controlador();

    }
    
    /**
     * Menú primari
     * @param sc Objecte de tipus Scanner que permet fer les entrades
     */
    public void gestioReproductorUB(Scanner sc){
        String cami;
        int i;
        ReproductorUB3.OpcionsMenuPrincipal opcio;
        do {
            // Mostrem les opcions del menÃº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case GESTIO_FITXERS:
                    gestioGestioFitxers(sc);
                    break;
                
                case CONTROL_REPRODUCCIO:
                    gestioControlReproduccio(sc);
                    break;
                    
                case GUARDAR_DADES:
                    System.out.println("Introdueix el camí del fitxer de disc on guardar les dades: ");
                    cami = sc.nextLine();
                    try{
                        controlador.saveDades(cami);
                        System.out.println("Llista guardada exitosament.");
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());
                    }
                    break;
                    
                case RECUPERAR_DADES:
                    System.out.println("Introdueix el camí del fitxer de disc d'on recuperar les dades: ");
                    cami = sc.nextLine();
                    try{
                        controlador.loadDades(cami);
                        System.out.println("Llista recuperada exitosament.");
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());                        
                    }                    
                    break;
                
                case SORTIR:
                    try {
                        controlador.closeFinestraMotor();
                    }
                    catch (ReproException e) {
                        System.out.println(e.toString());
                    }
                    
                    System.out.println("Chao bambino!");
                    break;
            }

        } while(opcio != ReproductorUB3.OpcionsMenuPrincipal.SORTIR);
    }
        
    /**
     * Menu secundari
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    public void gestioGestioFitxers(Scanner sc){
        String nom;
        int opcio1, fitxer;

        ReproductorUB3.OpcionsGestioFitxers opcio;
        do {
            // Mostrem les opcions del menÃº
            gestioFitxers.mostrarMenu();

            // Demanem una opcio
            opcio = gestioFitxers.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case CREAR_PORTAFOLI:
                    System.out.println("Introdueix el nom del nou portafoli: ");
                    nom = sc.nextLine();
                    try{
                        controlador.addPortafoli(nom);
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());                        
                        
                    }
                    
                    break;
                   
                case MOSTRAR_PORTAFOLIS:
                    System.out.println(controlador.showPortafolis().get(0));
                    break;
                    
                case ELIMINAR_PORTAFOLI:
                    System.out.println("Introdueix el nom del portafolis que vols eliminar");
                    nom = sc.nextLine();
                    try{
                        controlador.removePortafoli(nom);
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());                        
                        
                    }
                    break;
                    
                case AFEGIR_FITXER:
                    opcio1 = 0;
                    while(opcio1 !=1 && opcio1 !=2 && opcio1 != 3){
                        System.out.println("        1.- Afegir un fitxer al repositori\n" 
                                + "        2.- Afegir un fitxer a un portafolis\n"
                                + "        3.- Sortir");
                        opcio1 = sc.nextInt();
                        sc.nextLine();
                                        
                    }
                    if(opcio1 == 1){
                        gestioAfegirFitxer(sc);
                    }
                    else if(opcio1 == 2){
                        System.out.println(controlador.showPortafolis().get(0));
                        System.out.println("A quin portafolis vols afegir el fitxer?");
                        nom = sc.nextLine();
                        System.out.println(controlador.showRepositori().get(0));
                        System.out.println("Quin dels fitxers del repositori vols afegir?");
                        fitxer = sc.nextInt();
                        try{
                            controlador.addFitxer(nom, fitxer);
                        }
                        catch(ReproException e){
                            System.out.println(e.toString());
                        }
                    }
                    
                    break;
                    
                case MOSTRAR_FITXERS:
                    opcio1 = 0;
                    while(opcio1 != 1 && opcio1 !=2 && opcio1 !=3){
                        System.out.println("        1.- Mostrar el repositori\n"
                                + "        2.- Mostrar un portafoli\n"
                                + "        3.- Sortir");
                        opcio1 = sc.nextInt();
                        sc.nextLine();
                    }
                    if(opcio1 == 1){
                        System.out.println(controlador.showRepositori().get(0));
                    }
                    else if(opcio1 ==2){
                        System.out.println(controlador.showPortafolis().get(0));
                        System.out.println("Quin portafoli vols mostrar?");
                        nom = sc.nextLine();
                        try{
                            System.out.println(controlador.showPortafoli(nom).get(0));
                        }
                        catch(ReproException e){
                            System.out.println(e.toString());                                                                                
                        }
                    }
                    break;
                    
                case ELIMINAR_FITXER:
                    opcio1 = 0;
                    while(opcio1 != 1 && opcio1 != 2 && opcio1 != 3){
                    System.out.println("        1.- Eliminar un fitxer del repositori\n"
                            + "        2.- Eliminar un fitxer d'un portafoli\n"
                            + "        3.- Sortir");
                    opcio1 = sc.nextInt();
                    sc.nextLine();
                    }
                    if(opcio1 == 1){
                        System.out.println(controlador.showRepositori().get(0));
                        System.out.print("Digues el número del fitxer que vols eliminar: ");
                        fitxer = sc.nextInt();
                        sc.nextLine();
                        try{
                            controlador.removeFitxer(fitxer - 1);
                        }
                        catch(ReproException e){
                            System.out.println(e.toString());
                        }
                    }
                    else if(opcio1 == 2){
                        System.out.println(controlador.showPortafolis().get(0));
                        System.out.println("De quin portafoli vols eliminar el fitxer?");
                        nom = sc.nextLine();
                        
                        try{
                            System.out.println(controlador.showPortafoli(nom).get(0));
                            System.out.println("Digues el número del fitxer que vols eliminar:");
                            fitxer = sc.nextInt();
                            sc.nextLine();
                            controlador.removeFitxer(nom, fitxer - 1);
                        }
                        catch(ReproException e){
                            System.out.println(e.toString());                                                                                
                        }
                    }
                    break;
            }

        } while(opcio!=ReproductorUB3.OpcionsGestioFitxers.MENU_ANTERIOR);
    }
    /**
     * Menú terciari
     * @param sc Objecte de tipus Scanner per fer l'entrada de dades
     */
    public void gestioAfegirFitxer(Scanner sc){
        String cami1, cami2, nom, codec;
        int i,j;

        ReproductorUB3.OpcionsAfegirFitxer opcio;
        do {
            // Mostrem les opcions del menÃº
            afegirFitxer.mostrarMenu();

            // Demanem una opcio
            opcio = afegirFitxer.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case AFEGIR_AUDIO:
                    System.out.println("Introdueix el camí del fitxer:");
                    cami1 = sc.nextLine();
                    System.out.println("Introdueix el camí del fitxer d'imatge:");
                    cami2 = sc.nextLine();
                    System.out.println("Introdueix el nom de l'autor:");
                    nom = sc.nextLine();
                    System.out.println("Introdueix el codec:");
                    codec = sc.nextLine();
                    System.out.println("Introdueix els kbps:");
                    i = sc.nextInt();
                    sc.nextLine();
                    
                    try{
                        controlador.addAudio(cami1, cami2, nom, codec, i);
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());                                                    
                        
                    }
                    break;
                   
                case AFEGIR_IMATGE:
                    System.out.println("Introdueix el camí del fitxer");
                    cami1 = sc.nextLine();
                    System.out.println("Introdueix el nom de l'autor:");
                    nom = sc.nextLine();
                    System.out.println("Introdueix el codec:");
                    codec = sc.nextLine();
                    System.out.println("Introdueix els píxels d'alçada");
                    i = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introdueix els píxels d'amplada");
                    j = sc.nextInt();
                    sc.nextLine();

                    try{
                        controlador.addImatge(cami1, nom, codec, i, j);
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());                                                    
                        
                    }
                            
                    break;
            }

        } while(opcio!=ReproductorUB3.OpcionsAfegirFitxer.MENU_ANTERIOR);
    }
    
    public void gestioControlReproduccio(Scanner sc) {
        int i;
        String opcio1;
        ReproductorUB3.OpcionsControlReproduccio opcio;
        do {
            // Mostrem les opcions del control de reproduccio
            controlReproduccio.mostrarMenu();

            // Demanem una opcio
            opcio = controlReproduccio.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case REPRODUIR_FITXER_MULTIMEDIA:
                    System.out.println(controlador.showRepositori().get(0));
                    System.out.println("Tria el fitxer a reproduir: ");
                    i = sc.nextInt();
                    try{
                        controlador.playFitxer(i);
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());                                                                            
                    }
                                
                break;
                
                case REPRODUIR_REPOSITORI:
                    try{
                        controlador.playLlista();
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());
                    }
                break;
                
                case REPRODUIR_PORTAFOLI:
                    System.out.println(controlador.showPortafolis().get(0));                    
                    System.out.println("Quin portafoli vols reproduir?");
                    opcio1 = sc.nextLine();
                    try{
                        controlador.playLlista(opcio1);
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());
                    }
                break;
                
                case ON_OFF_REPRODUCCIO_CICLICA:
                    controlador.changeCiclica();
                    System.out.println("S'ha canviat la reproducció a " + controlador.getCiclica());
                break;
                
                case ON_OFF_REPRODUCCIO_REVERSE:
                    controlador.changeReverse();
                    System.out.println("S'ha canviat la reproducció a " + controlador.getReverse());
                break;
                
                case GESTIO_REPRODUCCIO:
                    gestioGestioReproduccio(sc);
                break;
            }

        } while(opcio != ReproductorUB3.OpcionsControlReproduccio.MENU_ANTERIOR);
    }
    
    public void gestioGestioReproduccio(Scanner sc) {
        ReproductorUB3.OpcionsGestioReproduccio opcio;
        do {
            // Mostrem les opcions del control de reproduccio
            gestioReproduccio.mostrarMenu();

            // Demanem una opcio
            opcio = gestioReproduccio.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                    case PLAY:
                    try{
                        controlador.resumeReproduccio();
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());
                    }
                break;
                
                case PAUSA:
                    try{
                        controlador.pauseReproduccio();
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());
                    }
                break;
                
                case ATURA:
                    try{
                        controlador.stopReproduccio();
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());
                    }
                break;
                
                case SALTA:
                    try{
                        controlador.jumpReproduccio();
                    }
                    catch(ReproException e){
                        System.out.println(e.toString());
                    }
                break;
            }

        } while(opcio != ReproductorUB3.OpcionsGestioReproduccio.MENU_ANTERIOR);
    }
}
