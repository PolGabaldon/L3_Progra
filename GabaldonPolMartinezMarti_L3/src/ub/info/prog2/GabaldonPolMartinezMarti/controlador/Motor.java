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

/**
 * De moment classe sense mètodes
 * @author GabaldonPolMartinezMarti
 */
public class Motor extends MotorBasic implements Serializable {
    public Motor(){
        super("C:\\Program Files\\VideoLAN\\VLC", controlador);
    }

    void reprodueix(Imatge im, Imatge thumbnail, int durada){
        play(im,thumbnail,durada);
    }
    void reprodueix(Audio audio, File fitxerImatge){
        play(audio, fitxerImatge);
    }
}
