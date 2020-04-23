/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ub.info.prog2.GabaldonPolMartinezMarti.model;

import java.io.File;
import ub.info.prog2.utils.ReproException;

/**
 * Classe que servirà per guardar els fitxers, hereda de LlistaFitxers
 * @author GabaldonPolMartinezMarti
 */
public class RepositoriFitxersMultimedia extends LlistaFitxers {
    
    /**
     * Constructor que inicialitza l'ArrayList i la mida màxima a 100 per defecte.
     */
    public RepositoriFitxersMultimedia(){
        super();
    }
    
 

    /**
     * Si el fitxer passat per paràmetre existeix, l'afegeix a l'ArrayList i si 
     * no llença un error.
     * @param file Fitxer que es desitja afegir a l'ArrayList.
     * @throws ReproException Error llançat si el fitxer a afegir no existeix.
     */
    @Override
    public void addFitxer(File file) throws ReproException {
        if(isCorrect(file)){
            super.addFitxer(file);
        }
        else{
            throw new ReproException("El fitxer està repetit");
        }
           
    }
    
    /**
     * Mira si un fitxer no estigui repetit
     * @param file FItxer que es mira
     * @return Retorna si és vàlid o si no ho és
     */
    private boolean isCorrect(File file) {
        int i = 0;
        boolean repetit = false;
        while(i < getSize() && !repetit){
            if(getAt(i).equals(file))
                repetit = true;
            else
                i++;               
        }
        return !repetit;
        
    }
    
    /**
     * Mètode sobreescrit per mostrar fitxer per fitxer del repositori
     * @return Llista de tots els fitxers
     */
    @Override
    public String toString(){
        String s = "Repositori:\n==============\n";
        for(int i = 0; i < llistaFitxers.size(); i++){
            s += "[" + (i + 1) + "] " + llistaFitxers.get(i).toString() + "\n";
        }
        return s;
    }
    
    /**
     * Sobreescribiendo el método de Llista Fitxers para que siempra devuelva
     * false, ya que el repositorio no debe tener medida maxima
     * @return Boolean
     */
    @Override
    public boolean isFull() {
        return false;
    }
}

    

