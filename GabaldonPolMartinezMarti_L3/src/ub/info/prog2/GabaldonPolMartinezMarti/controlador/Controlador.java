/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ub.info.prog2.GabaldonPolMartinezMarti.controlador;
import java.util.List;
import java.io.File;
import ub.info.prog2.utils.InControlador;
import ub.info.prog2.utils.ReproException;
import ub.info.prog2.GabaldonPolMartinezMarti.model.Dades;
import ub.info.prog2.GabaldonPolMartinezMarti.model.LlistaFitxers;
/**
 * Classe encarregada de gestionar la interacció entre els paquets vist i model, als mètodes d'aquesta classe simplement es criden als mètodes corresponents a la classe Dades
 * @author GabaldonPolMartinezMarti
 */
public class Controlador implements InControlador {
    private Dades dades;
    private final Motor motor;
    private EscoltadorReproduccio escoltador;
    private boolean ciclica;
    private boolean reverse;
    
    public Controlador(){
        dades = new Dades();
        escoltador = new EscoltadorReproduccio();
        motor = new Motor(escoltador);
        ciclica = false;
        reverse = false;
    }
    
    @Override
    public void addAudio(String camiFitxerAudio, String camiFitxerImatge, String autor, String codec, int kbps) throws ReproException{
        dades.addAudio(camiFitxerAudio,camiFitxerImatge,autor,codec,kbps, motor);
    }

    @Override
    public void addImatge(String cami, String autor, String codec, int pixelsAlcada, int pixelsAmplada) throws ReproException{
        dades.addImatge(cami, autor, codec, pixelsAlcada, pixelsAmplada, motor);
    }

    @Override
    public List<String> showRepositori(){
        return dades.showRepositori();
    }

    @Override
    public void removeFitxer(int i) throws ReproException{
        dades.removeFitxer(i);
    }

    @Override
    public void saveDades(String cami) throws ReproException{
        dades.saveDades(cami);
    }

    @Override
    public void loadDades(String cami) throws ReproException{
        dades = Dades.loadDades(cami);
    }

    @Override
    public void addPortafoli(String titol) throws ReproException{
        dades.addPortafoli(titol);
    }

    @Override
    public List<String> showPortafolis(){
        return dades.showPortafolis();
    }
    
    @Override
    public List<String> showPortafoli(String titol) throws ReproException{
        return dades.showPortafoli(titol);
        
    }

    @Override
    public void removePortafoli(String titol) throws ReproException{
        dades.removePortafoli(titol);
    }

    @Override
    public boolean existPortafoli(String titol){
        return dades.existPortafoli(titol);
    }

    @Override
    public void addFitxer(String titol, int i) throws ReproException{
        dades.addFitxer(titol, i);
    }

    @Override
    public void removeFitxer(String titol, int i) throws ReproException{
        dades.removeFitxer(titol, i);
    }          

    @Override
    public void playFitxer(int i) throws ReproException {
        File fitxer = dades.getFitxer(i);
        LlistaFitxers llista = new LlistaFitxers(1);
        llista.addFitxer(fitxer);
        escoltador.iniciarReproduccio(llista, ciclica, reverse);
    }

    @Override
    public void openFinestraReproductor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeFinestraReproductor() throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playLlista() throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playLlista(String string) throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resumeReproduccio() throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pauseReproduccio() throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopReproduccio() throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void jumpReproduccio() throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void openFinestraMotor(){
        this.motor.open();
    }
    public void closeFinestraMotor() throws ReproException{
        this.motor.close();
    }
}
