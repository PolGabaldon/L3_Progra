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
        if(escoltador.reproduccioIniciada() && dades.getRepositori().equals(escoltador.getLlistaReproduint())){
            escoltador.incrementNombreFitxers;
        }
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
     * Crida el mètode de dades que mostra els elements d'un portafoli
     * @param titol Nom del portafoli
     * @return Portafoli en concret
     * @throws ReproException Torna excepció si no s'ha trobat el portafoli
     */
    @Override
    public List<String> showPortafoli(String titol) throws ReproException{
        return dades.showPortafoli(titol);        
    }

    /**
     * Crida el mètode da dades que borra el portafoli amb el títol que se li entra per paràmetre
     * @param titol Títol del portafoli que es vol borrar
     * @throws ReproException 
     */
    @Override
    public void removePortafoli(String titol) throws ReproException{
        dades.removePortafoli(titol);
    }

    /**
     * Crida el mètode de dades que retorna si existeix el protafoli amb el títol entrat per paràmetre
     * @param titol Títol del portafoli que es vol saber si existeix
     * @return Si existeix o no
     */
    @Override
    public boolean existPortafoli(String titol){
        return dades.existPortafoli(titol);
    }

    /**
     * Crida el mètode de dades que afegeix un fitxer del repositori a un portafoli
     * @param titol Nom del portafoli
     * @param i Posició del fitxer dins el repositori
     * @throws ReproException Tira excepció si no s'ha pogut afegir
     */
    @Override
    public void addFitxer(String titol, int i) throws ReproException{
        dades.addFitxer(titol, i);
    }

    /**
     * Crida el mètode de dades que borra un fitxer d'un portafoli
     * @param titol Títol del portafoli
     * @param i Fitxer del portafoli a borrar
     * @throws ReproException Tira excepció si no s'ha pogut borrar
     */
    @Override
    public void removeFitxer(String titol, int i) throws ReproException{
        dades.removeFitxer(titol, i);
    }          

    /**
     * Inicia la reproducció del fitxer en la posició i
     * @param i Posició del fitxer
     * @throws ReproException Tira excepció si no s'ha pogut reproduir
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
     * Obri la finestra de reproducció
     */
    @Override
    public void openFinestraReproductor() {
        this.motor.open();   
    }

    /**
     * Tanca la finestra de reproducció
     * @throws ReproException 
     */
    @Override
    public void closeFinestraReproductor() throws ReproException {
        this.motor.close();
    }

    /**
     * Comença a reproduir el repositori
     * @throws ReproException Tira excepció si no s'ha pogut reproduir
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
     * Comença a reproduir un portafoli
     * @param titol Títol del portafoli
     * @throws ReproException Tira excepció si hi ha hagut error al començar la reproducció
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
     * Continua la reproducció
     * @throws ReproException Tira excepció si no s'ha pogut continuar
     */
    @Override
    public void resumeReproduccio() throws ReproException {
        motor.resume();
    }

    /**
     * Pausa la reproducció en curs
     * @throws ReproException Tira excepció si no s'ha pogut pausar
     */
    @Override
    public void pauseReproduccio() throws ReproException {
        motor.pause();
    }

    /**
     * Atura la reproducció en curs
     * @throws ReproException Tira excepció si no s'ha pogut aturar
     */
    @Override
    public void stopReproduccio() throws ReproException {
        motor.stop();
    }

    /**
     * Salta al següent fitxer
     * @throws ReproException Tira excepció si no s'ha pogut saltar
     */
    @Override
    public void jumpReproduccio() throws ReproException {
        escoltador.skip();
    }
    
    /**
     * Crida el mètode de dades que canvia el tipus de reproducció de cíclia a no cíclia o a l'inversa
     */
    public void changeCiclica() {
        dades.changeCiclica();
    }
    
    /**
     * Crida el mètode de dades que canvia l'estat de la reproducció reversa 
     */
    public void changeReverse() {
        dades.changeReverse();
    }
    
    /**
     * Crida el mètode de dades que retorna l'estat de la reproducció cíclica
     * @return Booleà reproducció cílcia
     */
    public boolean getCiclica() {
        return dades.getCiclica();
    }
    
    /**
     * Crida el mètode de dades que retorna l'estat de la reproducció reversa
     * @return Booleà reproducció reversa
     */
    public boolean getReverse() {
        return dades.getReverse();
    }
    
    /**
     * Assigna un motor a tots els fitxers
     */
    public void setMotor(){
        dades.setMotor(motor);
    }
   
    
}
