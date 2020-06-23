package sdis.projet.app.etare.model;

/**
 * Déclaration de la classe Level
 */
public class Level {
    /** Variable pour l'id du niveau ETARE*/
    private int id_level;
    /** Variable pour la description du niveau etare*/
    private String desc_level;

    /**
     * Déclaration d'un constructeur vide
     */
    public Level(){}

    /**
     * Déclaration d'un constructeur avec les paramètres suivants : id du niveau et description du niveau
     * @param id_level id du niveau etare
     * @param desc_level description du niveau etare
     */
    public Level(int id_level, String desc_level) {
        this.id_level = id_level;
        this.desc_level = desc_level;
    }

    /**
     * Récupération du l'id level (ou niveau etare)
     * @return Un entier correspondant au niveau etare
     */
    public int getId_level() {
        return id_level;
    }

    /**
     * Définition de l'id level (ou niveau etare)
     * @param id_level id correspondant au niveau etare
     */
    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    /**
     * Récupération de la description du niveau etare
     * @return Une chaine de caractère correspondant à la description du niveau etare
     */
    public String getDesc_level() {
        return desc_level;
    }

    /**
     * Définition de la description du niveau etare
     * @param desc_level description du niveau etare
     */
    public void setDesc_level(String desc_level) {
        this.desc_level = desc_level;
    }
}
