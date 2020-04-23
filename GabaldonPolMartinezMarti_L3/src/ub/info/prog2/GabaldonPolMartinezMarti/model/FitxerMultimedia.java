package ub.info.prog2.GabaldonPolMartinezMarti.model;
import java.util.Date;
import ub.info.prog2.utils.InFile;
import java.io.File;
import ub.info.prog2.utils.ReproException;
import ub.info.prog2.GabaldonPolMartinezMarti.controlador.Motor;


/**
 * Classe que hereda de la classe File.
 * Té un atribut anomenat autor i representa un fitxer multimèdia.
 * @author GabaldonPolMartinezMarti
 */
public abstract class FitxerMultimedia extends File implements InFile {
    private String autor;
    private String codec;
    protected transient Motor motor;
    
    /**
     * Constructor que inicialitza l'autor a Desconegut
     * @param cami Camí del fitxer
     * @param codec Codec del fitxer
     * @param motor Motor utilitzat en la reproducció del fitxer
     * @param autor Autor del fitxer
     */
    protected FitxerMultimedia(String cami, String codec, Motor motor){
        super(cami);
        this.codec = codec;
        this.motor = motor;
        autor = "Desconegut";
    }
    
    /**
     * Constructor
     * @param cami Camí del fitxer
     * @param codec Codec del fitxer
     * @param motor Motor utilitzat en la reproducció del fitxer
     */
    protected FitxerMultimedia(String cami, String codec, Motor motor, String autor){
        super(cami);
        this.codec = codec;
        this.motor = motor;
        this.autor = autor;
    }

    /**
     * Obté la data de la última modificació del fitxer.
     * @return Data de la última modificació.
     */
    @Override
    public Date getUltimaModificacio() {
        Date d;
        d = new Date(lastModified());
        return d;
    }

    /**
     * 
     * @return Camí absolut al fitxer
     */
    @Override
    public String getCamiAbsolut() {
        return getAbsolutePath();
    }

    /**
     * 
     * @return Nom del fitxer.
     */
    @Override
    public String getNomFitxer() {
        return getName();
    }

    /**
     * 
     * @return Autor del fitxer.
     */
    @Override
    public String getAutor() {
        return autor;
    }
    /**
     * 
     * @return Codec
     */
    public String getCodec(){
        return codec;
    }
    
    /**
     * Assigna l'autor del fitxer al autor passat per paràmetre.
     * @param string Autor del fitxer.
     */
    @Override
    public void setAutor(String string) {
        autor = string;
    }
    
    /**
     * Mira si dos fitxers són iguals
     * @param file Fitxer a comprovar
     * @return Si són iguals
     */
    @Override
    public boolean equals(Object file){
        FitxerMultimedia fitxerMultimedia = (FitxerMultimedia)file;
        boolean equal = false;
        if (getCamiAbsolut().equals(fitxerMultimedia.getCamiAbsolut()) && getName().equals(fitxerMultimedia.getName())
                && getAutor().equals(fitxerMultimedia.getAutor()) && getCodec().equals(fitxerMultimedia.getCodec())){
            equal = true;
        }
        return equal;
    }
    
    /**
     * Sobreescriu el mètode toString de File i retorna un String amb els atributs
     * rellevants en un format fàcilment llegible.
     * @return Un String amb els atributs rellevants del fitxer multimèdia.
     */
    @Override
    public String toString(){
        String s;
        s = "Autor = " + getAutor() + ", Data = " + getUltimaModificacio() + ",\nCami complet = " + getCamiAbsolut();
        return s;
    }
    
    /**
     * Mètode abstracte per reproduir
     * @throws ReproException La tira en cas d'error
     */
    public abstract void reproduir() throws ReproException;
    
    /**
     * Setter de l'atribut motor
     * @param motor Motor que se li entra per paràmetre
     */
    public void setMotor(Motor motor){
        this.motor = motor;
    }
}