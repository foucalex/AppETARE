package sdis.projet.app.etare.model;

/**
 * Déclaration de la classe Pictogram
 */
public class Pictogram {

    /**
     * Variable pour l'id du pictogramme
     */
    private int id_picto;

    /**
     * Variable pour le nom du pictogramme
     */
    private String name_picto;

    /**
     * Variable pour l'image du pictogramme
     */
    private String img_picto;

    /**
     * Variable pour la taille du pictogramme
     */
    private String size_picto;

    /**
     * Variable pour la coordonnée x du pictogramme
     */
    private String coord_x_picto;

    /**
     * Variable pour la coordonnée y du pictogramme
     */
    private String coord_y_picto;

    /**
     * Variable pour l'id du media lié au pictogramme
     */
    private int id_media;

    /**
     * Variable pour l'id du plan auquel appartient le pictogramme
     */
    private int id_map;

    /**
     * Déclaration d'un constructeur avec les paramètres suivants : id du pictogramme, nom du pictogramme, image du pictogramme, taille,coordonnée x et y , id du media associé et id du plan sur lequel se trouve le pictogramme
     * @param id_picto id du pictogramme
     * @param name_picto nom du pictogramme
     * @param img_picto image du pictogramme
     * @param size_picto taille du pictogramme
     * @param coord_x_picto coordonnée x du pictogramme
     * @param coord_y_picto coordonnée y du pictogramme
     * @param id_media id du media associé
     * @param id_map id du plan sur lequel se trouve le pictogramme
     */
    public Pictogram(int id_picto, String name_picto, String img_picto, String size_picto, String coord_x_picto, String coord_y_picto,int id_media ,int id_map) {
        this.id_picto = id_picto;
        this.name_picto = name_picto;
        this.img_picto = img_picto;
        this.size_picto = size_picto;
        this.coord_x_picto = coord_x_picto;
        this.coord_y_picto = coord_y_picto;
        this.id_media = id_media;
        this.id_map=id_map;
    }

    /**
     * Déclaration d'un constructeur avec les paramètres suivants :  nom du pictogramme, image du pictogramme, taille,coordonnée x et y , id du media associé et id du plan sur lequel se trouve le pictogramme
     * @param name_picto nom du pictogramme
     * @param img_picto image du pictogramme
     * @param size_picto taille du pictogramme
     * @param coord_x_picto coordonnée x du pictogramme
     * @param coord_y_picto coordonnée y du pictogramme
     * @param id_media id du media associé
     * @param id_map id du plan sur lequel se trouve le pictogramme
     */
    public Pictogram(String name_picto, String img_picto, String size_picto, String coord_x_picto, String coord_y_picto,int id_media ,int id_map) {
        this.name_picto = name_picto;
        this.img_picto = img_picto;
        this.size_picto = size_picto;
        this.coord_x_picto = coord_x_picto;
        this.coord_y_picto = coord_y_picto;
        this.id_media = id_media;
        this.id_map=id_map;
    }

    /**
     *
     * @return
     */
    public int getId_picto() {
        return id_picto;
    }

    /**
     *
     * @param id_picto
     */
    public void setId_picto(int id_picto) {
        this.id_picto = id_picto;
    }

    /**
     *
     * @return
     */
    public String getName_picto() {
        return name_picto;
    }

    /**
     *
     * @param name_picto
     */
    public void setName_picto(String name_picto) {
        this.name_picto = name_picto;
    }

    /**
     *
     * @return
     */
    public String getImg_picto() {
        return img_picto;
    }

    /**
     *
     * @param img_picto
     */
    public void setImg_picto(String img_picto) {
        this.img_picto = img_picto;
    }

    /**
     *
     * @return
     */
    public String getSize_picto() {
        return size_picto;
    }

    /**
     *
     * @param size_picto
     */
    public void setSize_picto(String size_picto) {
        this.size_picto = size_picto;
    }

    /**
     *
     * @return
     */
    public String getCoord_x_picto() {
        return coord_x_picto;
    }

    /**
     *
     * @param coord_x_picto
     */
    public void setCoord_x_picto(String coord_x_picto) {
        this.coord_x_picto = coord_x_picto;
    }

    /**
     *
     * @return
     */
    public String getCoord_y_picto() {
        return coord_y_picto;
    }

    /**
     *
     * @param coord_y_picto
     */
    public void setCoord_y_picto(String coord_y_picto) {
        this.coord_y_picto = coord_y_picto;
    }

    /**
     *
     * @return
     */
    public int getId_media() {
        return id_media;
    }

    /**
     *
     * @param id_media
     */
    public void setId_media(int id_media) {
        this.id_media = id_media;
    }

    /**
     *
     * @return
     */
    public int getId_map() {
        return id_map;
    }

    /**
     *
     * @param id_map
     */
    public void setId_map(int id_map) {
        this.id_map = id_map;
    }
}
