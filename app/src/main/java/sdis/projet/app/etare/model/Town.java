package sdis.projet.app.etare.model;

/**
 * Déclaration de la classe Town
 */
public class Town {

    /** Variable pour l'id de la ville*/
    private int id_town;
    /** Variable pour le nom de la ville*/
    private String name_town;
    /** Variable pour le groupement de la ville*/
    private String group_town;
    /** Variable pour le code insee de la ville*/
    private String insee_town;


    /**
     * Déclaration d'un constructeur vide
     */
    public Town(){}

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : id de la ville, nom de la ville, groupement de la ville et numéro insee de la ville
     * @param id_town id de la ville
     * @param name_town nom de la commune
     * @param group_town groupement de la commune
     * @param insee_town numéro insee de la commune
     */
    public Town(int id_town, String name_town, String group_town,String insee_town) {
        this.id_town = id_town;
        this.name_town = name_town;
        this.group_town = group_town;
        this.insee_town = insee_town;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : nom de la ville, groupement de la ville et numéro insee de la ville
     * @param name_town nom de la commune
     * @param group_town groupement de la commune
     * @param insee_town numéro de la commune
     */
    public Town(String name_town, String group_town,String insee_town) {
        this.name_town = name_town;
        this.group_town = group_town;
        this.insee_town = insee_town;

    }

    /**
     * Récupération de l'id de la commune
     * @return Un entier correspondant à l'id de la commune
     */
    public int getId_town() {
        return id_town;
    }

    /**
     * Définie l'identifiant de la commune
     * @param id_town identifiant que l'on souhaite donner à la commune
     */
    public void setId_town(int id_town) {
        this.id_town = id_town;
    }

    /**
     * Récupération du nom de la commune
     * @return Une chaine de caractère correspondant au nom de la commune
     */
    public String getName_town() {
        return name_town;
    }

    /**
     * Définie le nom de la commune
     * @param name_town nom que l'on souhaite donner a la commune
     */
    public void setName_town(String name_town) {
        this.name_town = name_town;
    }

    /**
     * Récupération du groupement de la commune
     * @return Une chaine de caractère correspondant au groupement de la commune
     */
    public String getGroup_town() {
        return group_town;
    }

    /**
     * Définie le groupement de la commune
     * @param group_town groupement que l'on souhaite donner a la commune
     */
    public void setGroup_town(String group_town) {
        this.group_town = group_town;
    }

    /**
     * Récupération du numéro insee de la commune
     * @return Une chaine de caractère correspondant au numéro insee de la commune
     */
    public String getInsee_town() {
        return insee_town;
    }

    /**
     * Définie l'e numéro insee de la commune
     * @param insee_town numéro insee que l'on souhaite donner a la commune
     */
    public void setInsee_town(String insee_town) {
        this.insee_town = insee_town;
    }
}
