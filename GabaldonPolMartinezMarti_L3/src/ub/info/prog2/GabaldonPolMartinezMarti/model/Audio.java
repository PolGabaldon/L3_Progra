package ub.info.prog2.GabaldonPolMartinezMarti.model;
import java.io.File;
import ub.info.prog2.GabaldonPolMartinezMarti.controlador.Motor;
import ub.info.prog2.utils.ReproException;

/**
 * Classe per representar els arxius de tipus àudio, hereda de FItxerMultimedia
 * @author GabaldonPolMartinezMarti
 */
public class Audio extends FitxerMultimedia {
    
    private final int kbps;
    private final File fitxerImatge;
    
    /**
     * Constructor, crida al constructor de FItxerMultimedia i a part, inicialitza kbps i motor
     * @param cami Ruta del fitxer
     * @param fitxerImatge Ruta del fitxer imatge
     * @param autor Nom de l'autor
     * @param codec Codec assignat
     * @param kbps Kbps de l'àudio
     * @param motor Objecte de tipus motor
     */
    public Audio(String cami, File fitxerImatge, String autor, String codec, int kbps, Motor motor){
        super(cami, codec, motor, autor);
        this.kbps = kbps;
        this.fitxerImatge = fitxerImatge;
    }
    /**
     * Mètode per reproduir àudio
     * @throws ReproException Excepció que es tira en cas d'error
     */
    @Override
    public void reproduir() throws ReproException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Mètode sobreescrit de FitxerMultimèdia per msotrar els atributs del fitxer d'àudio
     * @return Atributs del fitxer d'àudio
     */
    @Override
    public String toString(){
        String s;
        s = "Tipus = Audio, Autor = " + getAutor() + ", Data = " + getUltimaModificacio() + ",\n    Cami complet = " + getCamiAbsolut()
                + ",\n    Kbps = " + kbps + ", Imatge Associada = " + fitxerImatge.getAbsolutePath() + "\n";
        return s;
    }
    
 
}
