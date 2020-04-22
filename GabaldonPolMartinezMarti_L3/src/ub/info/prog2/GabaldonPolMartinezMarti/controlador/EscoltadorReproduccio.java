/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ub.info.prog2.GabaldonPolMartinezMarti.controlador;

import ub.info.prog2.GabaldonPolMartinezMarti.model.FitxerMultimedia;
import ub.info.prog2.GabaldonPolMartinezMarti.model.LlistaFitxers;
import ub.info.prog2.utils.EscoltadorReproduccioBasic;
import ub.info.prog2.utils.ReproException;


/**
 *
 * @author marti
 */
public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {
    private LlistaFitxers llistaReproduint;
    private boolean reproduccioCiclica;
    private boolean reproduccioReverse;
    private int reproduint;
    private int nombreFitxers;
    
    public EscoltadorReproduccio(){
        reproduccioCiclica = false;
        reproduccioReverse = false;
    }
            


    public void iniciarReproduccio(LlistaFitxers llistaReproduint, boolean reproduccioCiclica, boolean reproduccioReverse) throws ReproException{
        this.llistaReproduint = llistaReproduint;
        this.nombreFitxers = llistaReproduint.getSize();
        this.reproduccioCiclica = reproduccioCiclica;
        this.reproduccioReverse = reproduccioReverse;
        if(!reproduccioReverse){
            reproduint = 0;
            ((FitxerMultimedia)llistaReproduint.getAt(reproduint)).reproduir();           
            
        }
        
        else{
            reproduint = llistaReproduint.getSize() - 1;
            ((FitxerMultimedia) llistaReproduint.getAt(reproduint)).reproduir();
            
        }
            
    }
    
    public void skip(){
        onEndFile();
    }

    @Override
    protected void onEndFile() {
        if(hasNext()){
            next();
        }
        else{
            
        }
    }

    @Override
    protected void next() { 
        if(reproduccioCiclica){
            if(reproduccioReverse){
                reproduint = (((reproduint - 1) % nombreFitxers) + nombreFitxers) % nombreFitxers;
                try{
                    ((FitxerMultimedia) llistaReproduint.getAt(reproduint)).reproduir();
                }
                catch(ReproException e){
                    
                }
            }
            else{
                reproduint = (reproduint + 1) % nombreFitxers;
                try{
                    ((FitxerMultimedia) llistaReproduint.getAt(reproduint)).reproduir();
                }
                catch(ReproException e){
                    
                }
            }
        }
        else{
            if(reproduccioReverse){
                reproduint = reproduint - 1;
                try{
                    ((FitxerMultimedia) llistaReproduint.getAt(reproduint)).reproduir();
                }
                catch(ReproException e){
                    
                }                        
            }
            else{
                reproduint = reproduint + 1;
                try{
                    ((FitxerMultimedia) llistaReproduint.getAt(reproduint)).reproduir();
                }
                catch(ReproException e){
                    
                }                  
            }
            
        }
    }
    @Override
    protected boolean hasNext() {
        if(reproduccioCiclica){
            return true; 
        }
        else{
            if(reproduccioReverse){
                if(reproduint-1>0){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if(reproduint+1<nombreFitxers){
                    return true;
                }
                else{
                    return false;
                }

            }
        }
    }
    

    
}
