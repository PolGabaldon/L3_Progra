package ub.info.prog2.GabaldonPolMartinezMarti.model;
import ub.info.prog2.GabaldonPolMartinezMarti.controlador.Motor;
import ub.info.prog2.utils.ReproException;

/**
 * Classe pels arxius de tipus imatge, hereda de FitxerMultimedia
 * @author GabaldonPolMartinezMarti
 */
public class Imatge extends FitxerMultimedia {
    
    private final int alcada, amplada, resolucio;
    
    /**
     * Constructor que inicialitza tots els atributs, els primers 4 amb el constructor de la classe mare
     * @param cami Ruta del fitxer
     * @param autor Nom de l'autor
     * @param codec Codec del fitxer
     * @param alcada Pixels d'alçada
     * @param amplada Pixels d'amplada
     * @param motor  Motor
     */
    public Imatge(String cami, String autor, String codec, int alcada, int amplada, Motor motor){
        super(cami, codec, motor, autor);
        this.alcada = alcada;
        this.amplada = amplada;
        resolucio = alcada * amplada;
    }
    
    /**
     * Reprodueix la imatge, encara no està implementat
     * @throws ReproException 
     */
    @Override
    public void reproduir() throws ReproException {
        motor.reprodueix(this);
    }
    
    /**
     * @return Retorna la informació de la imatge
     */
    @Override
    public String toString(){
        String s;
        s = "Tipus = Imatge, Autor = " + getAutor() + ", Data = " + getUltimaModificacio() + ",\n    Cami complet = " + getCamiAbsolut()
                + ",\n    Resolució = " + resolucio + "\n";
        return s;
    }
    
}