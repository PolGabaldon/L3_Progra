/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ub.info.prog2.GabaldonPolMartinezMarti.controlador;

import ub.info.prog2.GabaldonPolMartinezMarti.model.LlistaFitxers;
import ub.info.prog2.utils.EscoltadorReproduccioBasic;

/**
 *
 * @author marti
 */
public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {
    private LlistaFitxers llistaReproduint;
    private boolean reproduccioCiclica;
    private boolean reproduccioReverse;
    
    public EscoltadorReproduccio(){
        reproduccioCiclica = false;
        reproduccioReverse = false;
    }
            


    public void iniciarReproduccio(LlistaFitxers llistaReproduint, boolean reproduccioCiclica, boolean reproduccioRverse){
        int k;
        this.llistaReproduint = llistaReproduint;
        if(reproduccioReverse){
            llistaReproduint.getAt(0).reproduir();           
        }
        
        else{
            k = llistaReproduint.getSize() - 1;
            llistaReproduint.getAt(k).reproduir();
            
        }
            
    }

    @Override
    protected void onEndFile() {
        if(hasNext()){
            
        }
    }

    @Override
    protected void next() {
    }

    @Override
    protected boolean hasNext() {
    }
    

    
}
