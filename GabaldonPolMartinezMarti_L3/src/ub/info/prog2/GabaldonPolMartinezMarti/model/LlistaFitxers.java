package ub.info.prog2.GabaldonPolMartinezMarti.model;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import ub.info.prog2.utils.InFileList;
import ub.info.prog2.utils.ReproException;

/**
 * Classe que gestiona un ArrayList de fitxers amb una mida màxima.
 * @author GabaldonPolMartinezMarti
 */
public class LlistaFitxers implements InFileList, Serializable{
    protected ArrayList<File> llistaFitxers;
    protected int midaMax;
    
    /**
     * Constructor que inicialitza l'ArrayList i la mida màxima a 100 per defecte.
     */
    public LlistaFitxers(){
        llistaFitxers = new ArrayList<>();
        midaMax = 100;
    }
    
    /**
     * Constructor que inicialitza l'ArrayList i la mida màxima al valor passat
     * per paràmetre.
     * @param midaMax Nombre màxim de fitxers que podràn ser guardats.
     */
    public LlistaFitxers(int midaMax){
        llistaFitxers = new ArrayList<>();
        this.midaMax = midaMax;
    }
    
    /**
     * 
     * @return Mida actual de l'ArrayList.
     */
    @Override
    public int getSize() {
        return llistaFitxers.size();
    }

    /**
     * Si el fitxer passat per paràmetre existeix, l'afegeix a l'ArrayList i si 
     * no llença un error.
     * @param file Fitxer que es desitja afegir a l'ArrayList.
     * @throws ReproException Error llançat si el fitxer a afegir no existeix.
     */
    @Override
    public void addFitxer(File file) throws ReproException {
        if(file.exists()){
            if(!isFull()){
                llistaFitxers.add(file);               
            }
            else{
                throw new ReproException("La llista està plena");
            }
        }
        else{
            throw new ReproException("El fitxer no existeix.");
        }
    }
    
  
      
    

    /**
     * Elimina la primera instància del fitxer passat per paràmetre que troba.
     * @param file Fitxer que es desitja eliminar.
     */
    @Override
    public void removeFitxer(File file){
        int id = llistaFitxers.indexOf(file);
        llistaFitxers.remove(id);
    }
    
    /**
     * Elimina un fitxer per l'índex
     * @param i Índex del fitxerque es vol eliminar
     * @throws ReproException Tira excepció si l'índex esta fora de l'array
     */
    public void removeFitxer(int i) throws ReproException{
        if(0 <= i && i < llistaFitxers.size()){
            llistaFitxers.remove(i);
        }
        else {
            throw new ReproException("Index fora de límits");
        }
            
    }

    /**
     * Mètode per obtenir el fitxer de l'ArrayList que es troba a la posició i.
     * @param i Index del fitxer que es vol obtenir.
     * @return Fitxer a la posició i.
     */
    @Override
    public File getAt(int i) {
        return llistaFitxers.get(i);
    }

    /**
     * Buida l'ArrayList
     */
    @Override
    public void clear() {
        llistaFitxers.clear();
    }

    /**
     * Retorna un booleà segons si l'ArrayList és ple o no.
     * @return Booleà que és cert si l'ArrayList és ple i fals si no.
     */
    @Override
    public boolean isFull() {
        return llistaFitxers.size() >=  midaMax;
    }
    
    /**
     * Permet la representació de l'ArrayList de fitxers mitjançact un String.
     * @return String en el qual es representa l'ArrayList tractat.
     */
    @Override
    public String toString(){
        String s = "Carpeta Fitxers:\\n==============\n";
        for(int i = 0; i < llistaFitxers.size(); i++){
            s += "[" + (i + 1) + "] " + llistaFitxers.get(i).toString() + "\n";
        }
        return s;
    }
}