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
    
    /**
     * Constructor de controlador. Instancia objectes de tipus Dades, EscoltadorReproduccio i Motor-
     */
    public Controlador(){
        dades = new Dades();
        escoltador = new EscoltadorReproduccio();
        motor = new Motor(escoltador);
    }
    
    /**
     * Crida el mètode corresponent a dades que afegeix un àudio a la llista de fitxers
     * @param camiFitxerAudio Àudio que es vol afegir
     * @param camiFitxerImatge Imatge associada a l'àudio
     * @param autor Autor de l'àudio
     * @param codec Codec de l'àudio
     * @param kbps Kbps de l'àudio
     * @throws ReproException Tira l'excepció en cas d'error en afegir
     */
    @Override
    public void addAudio(String camiFitxerAudio, String camiFitxerImatge, String autor, String codec, int kbps) throws ReproException{
        dades.addAudio(camiFitxerAudio,camiFitxerImatge,autor,codec,kbps, motor);
    }
    
    /**
     * Crida el mètode corresponent a dades que afegeix una imatge a la llista de fitxers
     * @param cami Imatge que es vol afegir
     * @param autor Autor de l'imatge
     * @param codec Codec de l'imatge
     * @param pixelsAlcada Píxels d'alçada de l'imatge
     * @param pixelsAmplada Píxels d'amplada de l'imatge
     * @throws ReproException Tira excepció en cas d'error
     */
    @Override
    public void addImatge(String cami, String autor, String codec, int pixelsAlcada, int pixelsAmplada) throws ReproException{
        dades.addImatge(cami, autor, codec, pixelsAlcada, pixelsAmplada, motor);
    }
    
    /**
     * Crida el mètode corresponent a dades que mostra els fitxers del repositori
     * @return Fitxers del repositori
     */
    @Override
    public List<String> showRepositori(){
        return dades.showRepositori();
    }
    
    /**
     * Crida el mètode corresponent a dades que elimina un fitxer del repositori a partir de la seva posició
     * @param i Posició del fitxer que es vol borrar
     * @throws ReproException En cas d'error tira excapció
     */
    @Override
    public void removeFitxer(int i) throws ReproException{
        dades.removeFitxer(i);
    }
    
    /**
     * Crida el mètode corresponent a dades que guarda les dades i els fitxers
     * @param cami Adreça del fitxer.dat on es vol guardar tot
     * @throws ReproException En cas d'error tira excepció
     */
    @Override
    public void saveDades(String cami) throws ReproException{
        dades.saveDades(cami);
    }

    /**
     * Crida el mètode corresponent a dades que recupera els fitxers guardats a un arxiu .dat
     * @param cami Adreça del fitxer.dat
     * @throws ReproException Tira excepció en cas d'error
     */
    @Override
    public void loadDades(String cami) throws ReproException{
        dades = Dades.loadDades(cami);
        setMotor();
    }

    /**
     * Crida el mètode de dades que afegeix un nou portafoli
     * @param titol Nom del portafoli
     * @throws ReproException La tira si no s'ha pogut afegir
     */
    @Override
    public void addPortafoli(String titol) throws ReproException{
        dades.addPortafoli(titol);
    }

    /**
     * Crida el mètode de dades que mostra la llista de tots els portafolis
     * @return Torna la llista amb tots ells
     */
    @Override
    public List<String> showPortafolis(){
        return dades.showPortafolis();
    }

    /**
     * 
     * @param titol
     * @return
     * @throws ReproException 
     */
    @Override
    public List<String> showPortafoli(String titol) throws ReproException{
        return dades.showPortafoli(titol);        
    }

    /**
     * 
     * @param titol
     * @throws ReproException 
     */
    @Override
    public void removePortafoli(String titol) throws ReproException{
        dades.removePortafoli(titol);
    }

    /**
     * 
     * @param titol
     * @return 
     */
    @Override
    public boolean existPortafoli(String titol){
        return dades.existPortafoli(titol);
    }

    /**
     * 
     * @param titol
     * @param i
     * @throws ReproException 
     */
    @Override
    public void addFitxer(String titol, int i) throws ReproException{
        dades.addFitxer(titol, i);
    }

    /**
     * 
     * @param titol
     * @param i
     * @throws ReproException 
     */
    @Override
    public void removeFitxer(String titol, int i) throws ReproException{
        dades.removeFitxer(titol, i);
    }          

    /**
     * 
     * @param i
     * @throws ReproException 
     */
    @Override
    public void playFitxer(int i) throws ReproException {
        openFinestraReproductor();
        File fitxer = dades.getFitxer(i);
        LlistaFitxers llista = new LlistaFitxers(1);
        llista.addFitxer(fitxer);
        escoltador.iniciarReproduccio(llista, dades.getCiclica(), dades.getReverse());
    }

    /**
     * 
     */
    @Override
    public void openFinestraReproductor() {
        openFinestraMotor();
    }

    /**
     * 
     * @throws ReproException 
     */
    @Override
    public void closeFinestraReproductor() throws ReproException {
        closeFinestraMotor();
    }

    /**
     * 
     * @throws ReproException 
     */
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

    /**
     * 
     * @param titol
     * @throws ReproException 
     */
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

    /**
     * 
     * @throws ReproException 
     */
    @Override
    public void resumeReproduccio() throws ReproException {
        motor.resume();
    }

    /**
     * 
     * @throws ReproException 
     */
    @Override
    public void pauseReproduccio() throws ReproException {
        motor.pause();
    }

    /**
     * 
     * @throws ReproException 
     */
    @Override
    public void stopReproduccio() throws ReproException {
        motor.stop();
    }

    /**
     * 
     * @throws ReproException 
     */
    @Override
    public void jumpReproduccio() throws ReproException {
        escoltador.skip();
    }
  
    /**
     * 
     */
    public void openFinestraMotor(){
        this.motor.open();
    }
    
    /**
     * 
     * @throws ReproException 
     */
    public void closeFinestraMotor() throws ReproException{
        this.motor.close();
    }
    
    /**
     * 
     */
    public void changeCiclica() {
        dades.changeCiclica();
    }
    
    /**
     * 
     */
    public void changeReverse() {
        dades.changeReverse();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getCiclica() {
        return dades.getCiclica();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getReverse() {
        return dades.getReverse();
    }
    
    /**
     * 
     */
    public void setMotor(){
        dades.setMotor(motor);
    }
}
