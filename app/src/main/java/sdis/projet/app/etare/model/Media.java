package sdis.projet.app.etare.model;


/**
 * Déclaration de la classe Media
 */
public class Media {

    /** Variable pour l'id du media */
    private int id_media;
    /** Variable pour le nom du media */
    private String name_media;
    /** Variable pour la description du media */
    private String desc_media;
    /** Variable pour l'image du media */
    private byte[] img_media;
    /** Variable pour l'id de l'etare */
    private int id_etare;
    /** Variable pour l'id de la categorie */
    private int id_category;

    /**
     * Déclaration d'un constructeur vide
     */
    public Media(){}


    /**
     * Déclaration d'un constructeur avec les paramètres suivant : nom du media, description du media et image du media
     * @param name_media nom du media
     * @param desc_media description du media
     * @param img_media image du media
     */
    public Media(String name_media, String desc_media, byte[] img_media) {
        this.name_media = name_media;
        this.desc_media = desc_media;
        this.img_media = img_media;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : id du media, nom du media, description du media et image du media
     * @param id_media id du media
     * @param name_media nom du media
     * @param desc_media description du media
     * @param img_media image du media
     */
    public Media(int id_media, String name_media, String desc_media, byte[] img_media) {
        this.id_media = id_media;
        this.name_media = name_media;
        this.desc_media = desc_media;
        this.img_media = img_media;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : nom du media, description du media, image du media, id de l'etare et id de la categorie
     * @param name_media nom du media
     * @param desc_media description du media
     * @param img_media image du media
     * @param id_etare id de l'etare
     * @param id_category id de la categorie
     */
    public Media(String name_media, String desc_media, byte[] img_media, int id_etare, int id_category) {
        this.name_media = name_media;
        this.desc_media = desc_media;
        this.img_media = img_media;
        this.id_etare = id_etare;
        this.id_category = id_category;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivant : id du media, nom du media, description du media, image du media, id de l'etare et id de la categorie
     * @param id_media id du media
     * @param name_media nom du media
     * @param desc_media description du media
     * @param img_media image du media
     * @param id_etare id de l'etare
     * @param id_category id de la categorie
     */
    public Media(int id_media, String name_media, String desc_media, byte[] img_media, int id_etare,int id_category) {
        this.id_media = id_media;
        this.name_media = name_media;
        this.desc_media = desc_media;
        this.img_media = img_media;
        this.id_etare = id_etare;
        this.id_category = id_category;

    }

    /**
     * Récupération de l'id du media
     * @return Un entier correspondant à l'id du media
     */
    public int getId_media() {
        return id_media;
    }

    /**
     * Définition de l'id du media
     * @param id_media id que l'on souhaite donner au media
     */
    public void setId_media(int id_media) {
        this.id_media = id_media;
    }

    /**
     * Récupération du nom du media
     * @return Une chaine de caractère correspondant au nom du media
     */
    public String getName_media() {
        return name_media;
    }

    /**
     * Définition du nom du media
     * @param name_media nom que l'on souhaite donner au media
     */
    public void setName_media(String name_media) {
        this.name_media = name_media;
    }

    /**
     * Récupération de la description du media
     * @return Une chaine de caractère correspondant à la description du media
     */
    public String getDesc_media() {
        return desc_media;
    }

    /**
     * Définition de la description du media
     * @param desc_media description que l'on souhaite donner au media
     */
    public void setDesc_media(String desc_media) {
        this.desc_media = desc_media;
    }

    /**
     * Récupération du tableau de byte correspondant au media
     * @return Un tableau de byte correspondant à l'image du media
     */
    public byte[] getImg_media() {
        return img_media;
    }

    /**
     * Définition du tableau de byte du media
     * @param img_media image que l'on souhaite donner au media
     */
    public void setImg_media(byte[] img_media) {
        this.img_media = img_media;
    }

    /**
     * Récupération de l'id de l'etare
     * @return Un entier correspondant à l'id du media
     */
    public int getId_etare() {
        return id_etare;
    }

    /**
     * Définition de l'id de l'etare
     * @param id_etare id de l'etare auquel on souhaite lier le media
     */
    public void setId_etare(int id_etare) {
        this.id_etare = id_etare;
    }

    /**
     * Récupération de l'id de la categorie
     * @return Un entier correspondant à l'id de la categorie
     */
    public int getId_category() {
        return id_category;
    }

    /**
     * Définition de l'id de la categorie
     * @param id_category id de la categorie auquel on souhaite lier le media
     */
    public void setId_category(int id_category) {
        this.id_category = id_category;
    }
}
