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
    private transient final Motor motor;
    private final EscoltadorReproduccio escoltador;
    
    public Controlador(){
        dades = new Dades();
        escoltador = new EscoltadorReproduccio();
        motor = new Motor(escoltador);
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
        setMotor();

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
        openFinestraReproductor();
        File fitxer = dades.getFitxer(i);
        LlistaFitxers llista = new LlistaFitxers(1);
        llista.addFitxer(fitxer);
        escoltador.iniciarReproduccio(llista, dades.getCiclica(), dades.getReverse());
    }

    @Override
    public void openFinestraReproductor() {
        openFinestraMotor();
    }

    @Override
    public void closeFinestraReproductor() throws ReproException {
        closeFinestraMotor();
    }

    @Override
    public void playLlista() throws ReproException {
        if(dades.getRepositori().getSize()==0){
            throw new ReproException("El repositori està buit.");
        }
        else{
            openFinestraReproductor();
            escoltador.iniciarReproduccio(dades.getRepositori(), dades.getCiclica(), dades.getReverse());
        }
    }

    @Override
    public void playLlista(String titol) throws ReproException {
        if(dades.getPortafoli(titol).getSize()==0){
            throw new ReproException("El portafoli està buit.");
        }
        else{
        openFinestraReproductor();
        escoltador.iniciarReproduccio(dades.getPortafoli(titol), dades.getCiclica(), dades.getReverse());
        }
    }

    @Override
    public void resumeReproduccio() throws ReproException {
        motor.resume();
    }

    @Override
    public void pauseReproduccio() throws ReproException {
        motor.pause();
    }

    @Override
    public void stopReproduccio() throws ReproException {
        motor.stop();
    }

    @Override
    public void jumpReproduccio() throws ReproException {
        escoltador.skip();
    }
    
    public void openFinestraMotor(){
        this.motor.open();
    }
    
    public void closeFinestraMotor() throws ReproException{
        this.motor.close();
    }
    
    public void changeCiclica() {
        dades.changeCiclica();
    }
    
    public void changeReverse() {
        dades.changeReverse();
    }
    
    public boolean getCiclica() {
        return dades.getCiclica();
    }
    
    public boolean getReverse() {
        return dades.getReverse();
    }
    
    public void setMotor(){
        dades.setMotor(motor);
    }
}
