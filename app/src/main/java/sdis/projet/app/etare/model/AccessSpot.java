package sdis.projet.app.etare.model;

/**
 * Déclaration de la classe AccessSpot
 */
public class AccessSpot {

    /** Variable pour l'id de l'accès*/
    private int Id_access;
    /** Variable pour le commentaire de l'accès*/
    private String Commentary_access;
    /** Variable pour la localisation de l'accès*/
    private String Location_access;
    /** Variable pour l'id de l'etare*/
    private int Id_etare;

    /**
     * Déclaration d'un constructeur vide
     */
    public AccessSpot(){}

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : commentaire et localisation de l'accès
     * @param commentary_access
     * @param location_access
     */
    public AccessSpot(String commentary_access, String location_access) {
        this.Commentary_access = commentary_access;
        this.Location_access = location_access;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : id de l'accès, commentaire et localisation de l'accès
     * @param id_access
     * @param commentary_access
     * @param location_access
     */
    AccessSpot(int id_access, String commentary_access, String location_access) {
        this.Id_access = id_access;
        this.Commentary_access = commentary_access;
        this.Location_access = location_access;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : commentaire de l'accès, localisation de l'accès et id de l'etare
     * @param commentary_access
     * @param location_access
     * @param id_etare
     */
    AccessSpot(String commentary_access, String location_access, int id_etare) {
        this.Commentary_access = commentary_access;
        this.Location_access = location_access;
        this.Id_etare = id_etare;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : id de l'accès, commentaire de l'accès, localisation de l'accès et id de l'etare
     * @param id_access
     * @param commentary_access
     * @param location_access
     * @param id_etare
     */
    AccessSpot(int id_access, String commentary_access, String location_access, int id_etare) {
        this.Id_access = id_access;
        this.Commentary_access = commentary_access;
        this.Location_access = location_access;
        this.Id_etare = id_etare;
    }

    /**
     * Récupération de l'id de l'accès
     * @return Un entier correspondant à l'id de l'accès
     */
    public int getId_access() {
        return this.Id_access;
    }

    /**
     * Définition de l'id de l'accès
     * @param id_access id que l'on souhaite donner a l'accès
     */
    public void setId_access(int id_access) {
        this.Id_access = id_access;
    }

    /**
     * Récupération du commentaire de l'accès
     * @return Une chaine de caractère correspondant au commentaire de l'accès
     */
    public String getCommentary_access() {
        return this.Commentary_access;
    }

    /**
     * Définition du commentaire de l'accès
     * @param commentary_access commentaire que l'on souhaite donner à l'accès
     */
    public void setCommentary_access(String commentary_access) {
        this.Commentary_access = commentary_access;
    }

    /**
     * Récupération de la localisation de l'accès
     * @return Une chaine de caractères correspondant à la localisation de l'accès
     */
    public String getLocation_access() {
        return this.Location_access;
    }

    /**
     * Définition de la localisation de l'accès
     * @param location_access localisation que l'on souhaite donner à l'accès
     */
    public void setLocation_access(String location_access) {
        this.Location_access = location_access;
    }

    /**
     * Récupération de l'id de l'etare
     * @return Un entier correspondant à l'id de l'etare
     */
    public int getId_etare() {
        return this.Id_etare;
    }

    /**
     * Définition de l'id etare
     * @param id_etare id de l'etare auquel on souhaite lier l'accès
     */
    public void setId_etare(int id_etare) {
        this.Id_etare = id_etare;
    }
}
