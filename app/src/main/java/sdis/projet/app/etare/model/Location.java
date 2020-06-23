package sdis.projet.app.etare.model;

/**
 * Déclaration de la classe Location
 */
public class Location {

    /** id location, généré automatiquement par la base de données **/
    private int id_location;

    /** nom de l'ETARE, renseigné par l'utilisateur lors de la création de l'ETARE **/
    private String name_location;

    /** adresse de l'ETARE, renseigné par l'utilisateur lors de la création de l'ETARE **/
    private String address_location;

    /** activitée de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE **/
    private String activity_location;

    /** infrastructure de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE **/
    private String infrastructure_location;

    /** capacité d'accueil de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE **/
    private String capacity_location;

    /** motif de classement de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE **/
    private String classification_reason_location;

    /** consignes opérateur, renseigné par l'utilisateur lors de la modification de l'ETARE **/
    private String consignes_ope_location;

    /** consignes centre de secours de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE **/
    private String consignes_cs_location;

    /** analyse des risques de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE **/
    private String risk_analysis_location;

    /**
     * Définition d'un constructeur vide
     */
    public Location(){}

    /**
     * Définition d'un constructeur avec des paramètres
     * @param name_location nom de l'ETARE
     * @param address_location adresse de l'ETARE
     * @param activity_location activitée de l'ETARE
     * @param infrastructure_location infrastructure de l'ETARE
     * @param capacity_location capacitée d'accueil de l'ETARE
     * @param classification_reason_location motif de classement de l'ETARE
     */
    public Location(String name_location, String address_location, String activity_location, String infrastructure_location, String capacity_location, String classification_reason_location) {
        this.name_location = name_location;
        this.address_location = address_location;
        this.activity_location = activity_location;
        this.infrastructure_location = infrastructure_location;
        this.capacity_location = capacity_location;
        this.classification_reason_location = classification_reason_location;
    }

    /**
     * Recupération de l'id location
     * @return entier: id_location
     */
    public int getId_location() {
        return id_location;
    }

    /**
     * Initialise l'id location
     * @param id_location id location, généré automatiquement par la base de données
     */
    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    /**
     * Recupération de nom de l'ETARE
     * @return champ texte: name_location
     */
    public String getName_location() {
        return name_location;
    }

    /**
     * Initialise le nom de l'ETARE
     * @param name_location nom de l'ETARE, renseigné par l'utilisateur lors de la création de l'ETARE
     */
    public void setName_location(String name_location) {
        this.name_location = name_location;
    }

    /**
     * Recupération de l'adresse de l'ETARE
     * @return champ texte: address_location
     */
    public String getAddress_location() {
        return address_location;
    }

    /**
     * Initialise l'adresse de l'ETARE
     * @param address_location adresse de l'ETARE, renseigné par l'utilisateur lors de la création de l'ETARE
     */
    public void setAddress_location(String address_location) {
        this.address_location = address_location;
    }

    /**
     * Recupération de l'activitée de l'ETARE
     * @return champ texte: activity_location
     */
    public String getActivity_location() {
        return activity_location;
    }

    /**
     * Initialise le champ des activitées de l'ETARE
     * @param activity_location activitée de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE
     */
    public void setActivity_location(String activity_location) {
        this.activity_location = activity_location;
    }

    /**
     * Recupération de linfrastructure de l'ETARE
     * @return champ texte: infrastructure_location
     */
    public String getInfrastructure_location() {
        return infrastructure_location;
    }

    /**
     * Initialise le champ des infrastructure de l'ETARE
     * @param infrastructure_location infrastructure de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE
     */
    public void setInfrastructure_location(String infrastructure_location) {
        this.infrastructure_location = infrastructure_location;
    }

    /**
     * Recupération de la capacitée d'accueil de l'ETARE
     * @return champ texte: capacity_location
     */
    public String getCapacity_location() {
        return capacity_location;
    }

    /**
     * Initialise le champ des capacitées d'accueil de l'ETARE
     * @param capacity_location capacité d'accueil de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE
     */
    public void setCapacity_location(String capacity_location) {
        this.capacity_location = capacity_location;
    }

    /**
     * Recupération du motif de classement de l'ETARE
     * @return champ texte: classification_reason_location
     */
    public String getClassification_reason_location() {
        return classification_reason_location;
    }

    /**
     * Initialise le champ des raisons de classement de l'ETARE
     * @param classification_reason_location motif de classement de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE
     */
    public void setClassification_reason_location(String classification_reason_location) {
        this.classification_reason_location = classification_reason_location;
    }

    /**
     * Recupération des consignes opérateurs de l'ETARE
     * @return champ texte: consignes_ope_location
     */
    public String getConsignes_ope_location() {
        return consignes_ope_location;
    }

    /**
     * Initialise le champ des consignes opérateur de l'ETARE
     * @param consignes_ope_location consignes opérateur, renseigné par l'utilisateur lors de la modification de l'ETARE
     */
    public void setConsignes_ope_location(String consignes_ope_location) {
        this.consignes_ope_location = consignes_ope_location;
    }

    /**
     * Recupération des consignes centre de secours de l'ETARE
     * @return champ texte: consignes_cs_location
     */
    public String getConsignes_cs_location() {
        return consignes_cs_location;
    }

    /**
     * Initialise le champ des consignes centre de secours de l'ETARE
     * @param consignes_cs_location consignes centre de secours de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE
     */
    public void setConsignes_cs_location(String consignes_cs_location) {
        this.consignes_cs_location = consignes_cs_location;
    }

    /**
     * Recupération de l'analyse des risque de l'ETARE
     * @return champ texte: risk_analysis_location
     */
    public String getRisk_analysis_location() {
        return risk_analysis_location;
    }

    /**
     * Initialise le champ de l'analyse des risques de l'ETARE
     * @param risk_analysis_location analyse des risques de l'ETARE, renseigné par l'utilisateur lors de la modification de l'ETARE
     */
    public void setRisk_analysis_location(String risk_analysis_location) {
        this.risk_analysis_location = risk_analysis_location;
    }
}
