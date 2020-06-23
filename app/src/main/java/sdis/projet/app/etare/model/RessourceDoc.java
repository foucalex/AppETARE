package sdis.projet.app.etare.model;

/**
 * Déclaration de la classe RessourceDoc
 */
public class RessourceDoc {

    /** Variable pour l'id du document*/
    private int id_ressource;
    /** Variable pour la description de la ressource*/
    private String desc_ressource;
    /** Variable pour l'url de la ressource'*/
    private String url_ressource;
    /** Variable pour l'id de l'etare*/
    private int id_etare;

    /**
     * Déclaration d'un constructeur vide
     */
    public RessourceDoc(){}

    /**
     * Déclaration d'un constructeur avec les paramètres suivant: id, description, lien de la ressource et id de l'etare
     * @param id_ressource id de la ressource
     * @param desc_ressource description de la ressource
     * @param url_ressource lien de la ressource
     * @param id_etare id de l'etare auquel on souhaite lier la ressource
     */
    public RessourceDoc(int id_ressource, String desc_ressource, String url_ressource, int id_etare) {
        this.id_ressource = id_ressource;
        this.desc_ressource = desc_ressource;
        this.url_ressource = url_ressource;
        this.id_etare = id_etare;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant: id, description, lien de la ressource
     * @param id_ressource id de la ressource
     * @param desc_ressource description de la ressource
     * @param url_ressource lien de la ressource
     */
    public RessourceDoc(int id_ressource,String desc_ressource ,String url_ressource) {
        this.id_ressource = id_ressource;
        this.desc_ressource = desc_ressource;
        this.url_ressource = url_ressource;
    }

    /**
     * Récupération de l'id de la ressource
     * @return Un entier correspondant à l'id de la ressource
     */
    public int getId_ressource() {
        return id_ressource;
    }

    /**
     * Définition de l'id de la ressource
     * @param id_ressource id que l'on souhaite donner a la ressource
     */
    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    /**
     * Récupération du lien de la ressource
     * @return Une chaine de caractère correspondant au lien de la ressource
     */
    public String getUrl_ressource() {
        return url_ressource;
    }

    /**
     * Définition du lien de la ressource
     * @param url_ressource lien de la ressource que l'on souhaite
     */
    public void setUrl_ressource(String url_ressource) {
        this.url_ressource = url_ressource;
    }

    /**
     * Récupération de l'id de l'etare lié à la ressource
     * @return Un entier correspondant à l'id de la ressource
     */
    public int getId_etare() {
        return id_etare;
    }

    /**
     * Définition de l'id de l'etare auquel on souhaite lier la ressource
     * @param id_etare id de l'etare auquel on souhaite lier la ressource
     */
    public void setId_etare(int id_etare) {
        this.id_etare = id_etare;
    }

    /**
     * Récupération de la description de la ressource
     * @return Une chaine de caractère correspondant à la description de la ressource
     */
    public String getDesc_ressource() {
        return desc_ressource;
    }

    /**
     * Définition de la description de la ressource
     * @param desc_ressource description que l'on souhaites donner à la ressource
     */
    public void setDesc_ressource(String desc_ressource) {
        this.desc_ressource = desc_ressource;
    }
}
