/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ub.info.prog2.GabaldonPolMartinezMarti.model;

import java.io.File;
import ub.info.prog2.utils.ReproException;

/**
 * Classe que s'encarrega de guardar els fitxers en portafolis
 * Tendrà un nom i un autor (només podran haver-hi imatges i àudios d'un sol autor a cada portafoli)
 * @author GabaldonPolMartinezMarti
 */
public class PortafoliFitxersMultimedia extends LlistaFitxers {
    private String titol;
    private String autor;
    
    /**
     * Constructor que assigna un nom al portafoli, i crea un portafoli recurrint al constructor de la classe mare
     * @param titol Nom pel portafoli
     */
    public PortafoliFitxersMultimedia(String titol){
        super();
        this.titol = titol;
    }
    
    /**
     * @return Nom del portafoli
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Si el fitxer passat per paràmetre existeix, l'afegeix a l'ArrayList i si 
     * no llença un error. Mira també que l'autor sigui correcte, o assigna l'autor a l'hora d'afegir el primer fitxer.
     * @param file Fitxer que es desitja afegir a l'ArrayList.
     * @throws ReproException Error llançat si el fitxer a afegir no existeix.
     */
    @Override
    public void addFitxer(File file) throws ReproException {
        System.out.println(autor + " Titol: " +  titol);               
        
        if(getSize() == 0){
            FitxerMultimedia fitxer = (FitxerMultimedia) file;
            autor = fitxer.getAutor();
            super.addFitxer(file);
        
        }
        else{
            FitxerMultimedia fitxer = (FitxerMultimedia) file;
            if(autorCorrecte(fitxer)){
                super.addFitxer(file);
            }
            else{
                throw new ReproException("Autor incorrecte");
            }   
        }
    }
    
    /**
     * Comprova si el nou fitxer que es vol afegir té el mateix autor que la resta de fitxers del portafoli
     * @param file FItxer que es vol afegir
     * @return Si té un autor vàlid
     */
    private boolean autorCorrecte(FitxerMultimedia file){
        return file.getAutor().equals(autor);
    }
    
    /**
     * @return Retorna la informació de tots els fitxers del portafolis
     */
    @Override
    public String toString(){
        String s = titol + ":\n==============\n";
        for(int i = 0; i < llistaFitxers.size(); i++){
            s += "[" + (i + 1) + "] " + llistaFitxers.get(i).toString() + "\n";
        }
        return s;
    }
}
