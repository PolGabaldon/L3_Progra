/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ub.info.prog2.GabaldonPolMartinezMarti.controlador;
import java.io.File;
import ub.info.prog2.utils.MotorBasic;
import ub.info.prog2.GabaldonPolMartinezMarti.model.Audio;
import ub.info.prog2.GabaldonPolMartinezMarti.model.Imatge;
import ub.info.prog2.utils.ReproException;

/**
 * De moment classe sense mètodes
 * @author GabaldonPolMartinezMarti
 */
public class Motor extends MotorBasic {
    public Motor(EscoltadorReproduccio esc){
        super("C:\\Program Files\\VideoLAN\\VLC", esc);
    }
    /**
     * Reprodueix una imatge
     * @param im Imatge que es vol reproduir
     * @throws ReproException Throwea error si no es pot reproduir la imatge
     */
    public void reprodueix(Imatge im) throws ReproException{     
        super.show(im);
    }
    
    /**
     * Reprodueix un àudio
     * @param audio Àudio a reproduir
     * @param fitxerImatge Imatge que es posarà de fondo
     * @throws ReproException Throwea error si no es pot reproduir
     */
    public void reprodueix(Audio audio, File fitxerImatge) throws ReproException{
        super.play(audio,fitxerImatge);
    }
    
    /**
     * Reprodueix una imatge un nombre determinat de segons
     * @param fitxerImatge Imatge a reproduir
     * @param i Segons que es vol reproduir
     * @throws ReproException Tira l'excepció en cas d'error a la reproducció
     */
    public void mostraImatge(File fitxerImatge, int i) throws ReproException{
        super.show(fitxerImatge, i);
    }

   
}
