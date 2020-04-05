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
public class ReproductorUB2 {
    // Declarem les opcions per a referir-se a les opcions dels menus
    static private enum OpcionsMenuPrincipal {GESTIO_FITXERS, GUARDAR_DADES, RECUPERAR_DADES, SORTIR};
    static private enum OpcionsSubmenu1 {CREAR_PORTAFOLI, MOSTRAR_PORTAFOLIS, ELIMINAR_PORTAFOLI, AFEGIR_FITXER, MOSTRAR_FITXERS, ELIMINAR_FITXER, MENU_ANTERIOR};
    static private enum OpcionsSubmenu2 {AFEGIR_AUDIO, AFEGIR_IMATGE, MENU_ANTERIOR};
    
    // Declarem descripcions personalitzades per a les opcions del menu principal
    static private String[] descMenuPrincipal={"Menú gestió dels fitxers",
                                               "Guardar dades",
                                               "Recuperar dades",
                                               "Sortir"};

    // Declarem descripcions personalitzades per a les opcions del menu secundari
    static private String[] descMenu2={"Crear portafoli",
                                                "Mostrar portafolis",
                                                "Eliminar portafolis", "Agefir fitxer", "Mostrar fitxers", "Eliminar fitxer", "Tornar al menú anterior"};
    
    // Declarem descripcions personalitzades pera  les opcions del darrer menu
    static private String[] descMenu3={"Afegir fitxer d'àudio","Afegir fitxer d'imatge","Tornar al menú anterior"};

    /**
     * Fitxer principal. Crea el menú principal i uns menús secundaris
     * @param args the command line arguments
     */
    private Menu<ReproductorUB2.OpcionsMenuPrincipal> menu;
    private Menu<ReproductorUB2.OpcionsSubmenu1> subMenu1;
    private Menu<ReproductorUB2.OpcionsSubmenu2> subMenu2;
    private Controlador controlador;
    
   
    public ReproductorUB2(){
        menu = new Menu<>("Menu Principal",ReproductorUB2.OpcionsMenuPrincipal.values());
        subMenu1 = new Menu<>("Gestió Fitxers",ReproductorUB2.OpcionsSubmenu1.values());
        subMenu2 = new Menu<>("Afegir Fitxer",ReproductorUB2.OpcionsSubmenu2.values());
        controlador = new Controlador();

    }
    
    /**
     * Menú primari
     * @param sc Objecte de tipus Scanner que permet fer les entrades
     */
    public void gestioReproductorUB(Scanner sc){
        
        String cami;
        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);
        
       
        int i;
        ReproductorUB2.OpcionsMenuPrincipal opcio;
        do {
            // Mostrem les opcions del menÃº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessàries
            switch(opcio) {
                case GESTIO_FITXERS:
                    gestioMenu2(sc);
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
                   
                    System.out.println("Chao bambino!");
                    break;
            }

        } while(opcio!=ReproductorUB2.OpcionsMenuPrincipal.SORTIR);
    }
        

    /**
     * Menu secundari
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */
    public void gestioMenu2(Scanner sc){
        String nom;
        int opcio1, fitxer;

        // Assignem la descripciÃ³ de les opcions
        subMenu1.setDescripcions(descMenu2);

        ReproductorUB2.OpcionsSubmenu1 opcio;
        do {
            // Mostrem les opcions del menÃº
            subMenu1.mostrarMenu();

            // Demanem una opcio
            opcio=subMenu1.getOpcio(sc);

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
                        gestioMenu3(sc);
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
                    
                case MENU_ANTERIOR:
                   
                    break;
            }

        } while(opcio!=ReproductorUB2.OpcionsSubmenu1.MENU_ANTERIOR);
    }
    /**
     * Menú terciari
     * @param sc Objecte de tipus Scanner per fer l'entrada de dades
     */
    public void gestioMenu3(Scanner sc){
        String cami1, cami2, nom, codec;
        int i,j;
        
        // Assignem la descripciÃ³ de les opcions
        subMenu2.setDescripcions(descMenu3);

        ReproductorUB2.OpcionsSubmenu2 opcio;
        do {
            // Mostrem les opcions del menÃº
            subMenu2.mostrarMenu();

            // Demanem una opcio
            opcio=subMenu2.getOpcio(sc);

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

                case MENU_ANTERIOR:
                   
                    break;
            }

        } while(opcio!=ReproductorUB2.OpcionsSubmenu2.MENU_ANTERIOR);
    }
}
