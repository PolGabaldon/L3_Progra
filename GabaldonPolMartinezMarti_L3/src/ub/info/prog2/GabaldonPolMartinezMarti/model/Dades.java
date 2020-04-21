package ub.info.prog2.GabaldonPolMartinezMarti.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ub.info.prog2.GabaldonPolMartinezMarti.controlador.Motor;
import ub.info.prog2.utils.ReproException;

/**
 * Gestiona totes les operacions del reproductor
 * @author GabaldonPolMartinezMarti
 */
public class Dades implements Serializable{
    private RepositoriFitxersMultimedia repositori;
    private ArrayList<PortafoliFitxersMultimedia> portafolis;
    
    /**
     * Crea un repositori per guardar fitxers i un ArrayList per guardar els portafolis de fitxers
     */
    public Dades(){
        repositori = new RepositoriFitxersMultimedia();
        portafolis = new ArrayList<>();
    }
    
    /**
     * Retorna el repositori
     * @return Repositori
     */
    public RepositoriFitxersMultimedia getRepositori(){
        return repositori;
    }
    
    /**
     * Retorna tots els portafolis
     * @return ArrayList amb tots els portafolis
     */
    public ArrayList<PortafoliFitxersMultimedia> getPortafolis(){
        return portafolis;
    }
    
    /**
     * Afegeix un àudio al repositori
     * @param camiFitxerAudio Ruta del fitxer
     * @param camiFitxerImatge Ruta d'una imatge
     * @param autor Nom de l'autor
     * @param codec Codec
     * @param kbps Kbps
     * @param motor Motor
     * @throws ReproException Excepció tirada en cas d'error
     */
    public void addAudio(String camiFitxerAudio, String camiFitxerImatge, String autor, String codec, int kbps, Motor motor) throws ReproException{
        File fitxerImatge = new File(camiFitxerImatge);
        Audio audio = new Audio(camiFitxerAudio, fitxerImatge, autor, codec, kbps, motor);
        
        String nomIm = fitxerImatge.getName(), nomAu = audio.getNomFitxer();
        String imExt = nomIm.substring(nomIm.lastIndexOf(".")), auExt = nomAu.substring(nomAu.lastIndexOf("."));
        if (isImatge(imExt) && isAudio(auExt)){
            repositori.addFitxer(audio);
        }
        
        else
            throw new ReproException("Error a l'afegir el fitxer. Els fitxers d'imatge acceptats són png, jpg, jpeg i els fitxers d'àudio acceptats són mp3 i wav.");
    }
    
    /**
     * Afegeix una imatge al repositori
     * @param cami Ruta de la imatge
     * @param autor Nom de l'autor
     * @param codec Codec
     * @param pixelsAlcada Pixels d'alçada de la imatge
     * @param pixelsAmplada Pixels d'amplada de la imatge
     * @param motor Motor
     * @throws ReproException Excepció tirada en cas d'error 
     */
    public void addImatge(String cami, String autor, String codec, int pixelsAlcada, int pixelsAmplada, Motor motor) throws ReproException{
        Imatge imatge = new Imatge(cami, autor, codec, pixelsAlcada, pixelsAmplada, motor);
        
        String nomIm = imatge.getNomFitxer();
        String imExt = nomIm.substring(nomIm.lastIndexOf("."));
        if (isImatge(imExt)){
            repositori.addFitxer(imatge);
        }
        else
            throw new ReproException("Error a l'afegir el fitxer.");
    }
    
    /**
     * Comprova si l'extensió del fitxer és d'àudio
     * @param ext Extensió del fitxer
     * @return Booleà
     */
    private boolean isAudio(String ext){
        return ext.equals(".mp3") || ext.equals(".wav");
    }
    
    /**
     * Comprova si l'extensió del fitxer és d'una imatge
     * @param ext Extensió del fitxxer
     * @return Booleà
     */
    private boolean isImatge(String ext){
        return ext.equals(".png") || ext.equals(".jpg") || ext.equals(".jpeg");
    }
    
    /**
     * Mostra els fitxers del repositori    
     * @return Fitxers del repositori
     */
    public List<String> showRepositori(){
        String s = repositori.toString();
        ArrayList<String> list = new ArrayList<>();
        list.add(s);
        return list;
    }
    
    /**
     * Elimina fitxer del repositori
     * @param i Índex del fitxer a eliminar
     * @throws ReproException Tira excepció en cas d'error
     */
    public void removeFitxer(int i) throws ReproException{
        File f = repositori.getAt(i);
        repositori.removeFitxer(i);
        Iterator<PortafoliFitxersMultimedia> iter = portafolis.iterator();
        while (iter.hasNext()) {
            
            LlistaFitxers portaf = iter.next();
            
            for (int j = 0; j < portaf.getSize(); j++) {
                if (portaf.getAt(i).equals(f)) {
                    portaf.removeFitxer(j);
                    j--;
                }
            }
        }
    }
    
    /**
     * Guarda les dades
     * @param cami Ruta on guardar-les (fitxer.dat)
     * @throws ReproException La tira en cas d'error
     */
    public void saveDades(String cami) throws ReproException {
        File fitxer = new File(cami);
        if (fitxer.exists()){
            try{
                FileOutputStream fout = new FileOutputStream(fitxer);
                ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(this);
                oos.close();
                fout.close();
            }
            catch (IOException e){
                throw new ReproException(e.toString());
            }
        }
        else{
            throw new ReproException("El fitxer on es volen guardar les dades no existeix.");
        }
        
    }
   
    /**
     * Carrega dades des d'un fitxer
     * @param cami RUta del fitxer.dat
     * @return Dades
     * @throws ReproException En cas d'error
     */
    public static Dades loadDades(String cami) throws ReproException {
        File fitxer = new File(cami);
        if (fitxer.exists()){
            try{
                Dades d;
                FileInputStream fin = new FileInputStream(fitxer);
                ObjectInputStream ois = new ObjectInputStream(fin);
                d = (Dades)ois.readObject();
                ois.close();
                fin.close();
                return d;
            }
            catch (IOException | ClassNotFoundException e){
                throw new ReproException(e.getMessage());
            }
        }
        else{
            throw new ReproException("El fitxer d'on es volen carregar les dades no existeix.");
        }
    }
    
    /**
     * Afegeix un portafoli
     * @param titol Nom del portafoli
     * @throws ReproException  En cas d'error
     */
    public void addPortafoli(String titol) throws ReproException{
        if (!existPortafoli(titol)) {
            PortafoliFitxersMultimedia portafoli = new PortafoliFitxersMultimedia(titol);
            portafolis.add(portafoli);
        }
        else{
            throw new ReproException("Ja existeix un portafoli amb aquest titol.");
        }
    }
    
    /**
     * Mostra llista de tots els portafolis
     * @return Portafolis
     */
    public List<String> showPortafolis() {
        String s = "";
        Iterator<PortafoliFitxersMultimedia> iter = portafolis.iterator();
        while (iter.hasNext()) {
            s += "-" + iter.next().getTitol() + "\n";
        }
        
        ArrayList<String> list = new ArrayList<>();
        list.add(s);
        return list;
    }
    
    /**
     * Mostra els fitxers d'un protafoli concret
     * @param titol Nom del portafoli
     * @return Fitxers del portafoli
     * @throws ReproException La tira en cas d'error
     */
    public List<String> showPortafoli(String titol) throws ReproException{
        
        if (existPortafoli(titol)) {
            PortafoliFitxersMultimedia portafoli = getPortafoli(titol);
            String s = portafoli.toString();
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            return list;
        }
        else{
            throw new ReproException("Aquest portafoli no existeix.");
        }
    }
    
    /**
     * Elimina un portafoli
     * @param titol Nom del portafoli
     * @throws ReproException La tira en cas d'error
     */
    public void removePortafoli(String titol) throws ReproException {
        if (existPortafoli(titol)) {
            Iterator<PortafoliFitxersMultimedia> iter = portafolis.iterator();
            int i = 0;
            boolean trobat = false;
            
            while (iter.hasNext() && !trobat) {
                if(iter.next().getTitol().equals(titol)) {
                    portafolis.remove(i);
                    trobat = true;
                }
                i++;
            }
        }
        else{
            throw new ReproException("Aquest portafoli no existeix.");
        }
    }
    
    /**
     * Comprova si existeix el portafoli a partir d'un nom
     * @param titol Nom del portafoli
     * @return Boolèa
     */
    public boolean existPortafoli(String titol){
        Iterator<PortafoliFitxersMultimedia> iter = portafolis.iterator();
        boolean exists = false;
        while(iter.hasNext() && !exists){
            if(iter.next().getTitol().equals(titol)) {
                exists = true;
            }
        }
        return exists;
    }
    
    /**
     * Afegeix fitxer a un portafoli
     * @param titol Nom del protafoli
     * @param i Índex del fitxer en el repositori
     * @throws ReproException La tira en cas d'error
     */
    public void addFitxer(String titol, int i) throws ReproException {
        if (existPortafoli(titol)) {
            if (i > 0 && i <= repositori.getSize()){
                getPortafoli(titol).addFitxer(repositori.getAt(i - 1));
            }
            else{
                throw new ReproException("L'index està fora de límits.");
            }
        }
        else{
            throw new ReproException("El portafoli no existex");
        }
    }
    
    /**
     * Elimina fitxer d'un portafoli
     * @param titol Nom del portafoli
     * @param i Índex del fitxer
     * @throws ReproException La tira en cas d'error
     */
    public void removeFitxer(String titol, int i) throws ReproException{
        if (existPortafoli(titol)) {
            PortafoliFitxersMultimedia portafoli = getPortafoli(titol);
            portafoli.removeFitxer(i);
        }
        else{
            throw new ReproException("El portafoli no existex");
        }
    }
    
    /**
     * Retorna un portafoli
     * @param titol Nom del portafoli
     * @return Portafoli
     */
    public PortafoliFitxersMultimedia getPortafoli(String titol) {
        Iterator<PortafoliFitxersMultimedia> iter = portafolis.iterator();
        PortafoliFitxersMultimedia portafoli = new PortafoliFitxersMultimedia("");
        boolean trobat = false;
        
        while (!trobat && iter.hasNext()) {
            portafoli = iter.next();
            if (portafoli.getTitol().equals(titol)) {
                trobat = true;
            }
        }
        
        return portafoli;
    }
    
    public File getFitxer(int i) throws ReproException{
        if (i > 0 && i <= repositori.getSize()){
            return repositori.getAt(i - 1);
        }
        else{
            throw new ReproException("L'index està fora de límits.");
        }
    }
    
    public void setMotor(Motor motor){
        for(int i = 0; i<repositori.getSize(); i++){
            ((FitxerMultimedia) repositori.getAt(i)).setMotor(motor);
        }

    
    }
}
