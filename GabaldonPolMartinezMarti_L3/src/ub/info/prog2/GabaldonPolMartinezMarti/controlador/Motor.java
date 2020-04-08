/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ub.info.prog2.GabaldonPolMartinezMarti.controlador;
import java.io.File;
import ub.info.prog2.utils.MotorBasic;
import java.io.Serializable;
import ub.info.prog2.GabaldonPolMartinezMarti.model.Audio;
import ub.info.prog2.GabaldonPolMartinezMarti.model.Imatge;
import ub.info.prog2.utils.EscoltadorReproduccioBasic;
import ub.info.prog2.utils.ReproException;

/**
 * De moment classe sense mètodes
 * @author GabaldonPolMartinezMarti
 */
public class Motor extends MotorBasic implements Serializable {
    public Motor(EscoltadorReproduccio hola){
        super("C:\\Program Files\\VideoLAN\\VLC", hola);
    }

    public void reprodueix(Imatge im) throws ReproException{     
        super.show(im);
    }
    
    public void reprodueix(Audio audio, File fitxerImatge) throws ReproException{
        super.play(audio,fitxerImatge);
    }
    public void mostraImatge(File fitxerImatge, int i) throws ReproException{
        super.show(fitxerImatge, i);
    }

   
}
