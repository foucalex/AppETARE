package sdis.projet.app.etare.model;

/**
 * Déclaration de la classe User
 */
public class User {

    /** Variable pour l'id de l'utilisateur*/
    private int id_user;
    /** Variable pour l'identifiant de l'utilisateur*/
    private String login_user;
    /** Variable pour le mot de passe de l'utilisateur*/
    private String pwd_user;
    /** Variable pour les droits de l'utilisateur*/
    private int admin;


    /**
     * Déclaration d'un constructeur vide
     */
    public User(){}


    /**
     * Déclaration d'un constructeur avec les paramètres suivant : identifiant, mot de passe et les droits
     * @param login_user identifiant de l'utilisateur
     * @param pwd_user mot de passe de l'utilisateur
     * @param admin droits de l'utilisateur
     */
    public User(String login_user, String pwd_user, int admin) {
        this.login_user = login_user;
        this.pwd_user = pwd_user;
        this.admin = admin;
    }

    /**
     * Constructeur avec les paramètres suivant : id, identifiant, mot de passe et les droits
     * @param id_user id de l'utilisateur
     * @param login_user identifiant de l'utilisateur
     * @param pwd_user mot de passe de l'utilisateur
     * @param admin droits de l'utilisateur
     */
    public User(int id_user, String login_user, String pwd_user, int admin) {
        this.id_user = id_user;
        this.login_user = login_user;
        this.pwd_user = pwd_user;
        this.admin = admin;
    }

    /**
     * Récupération de l'id de l'utilisateur
     * @return Un entier correspondant à l'id de l'utilisateur
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * Définie l'id de l'utilisateur
     * @param id_user id que l'on souhaite donner à l'utilisateur
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * Récupération de l'identifiant de l'utilisateur
     * @return Une chaine de caractère correspondant à l'identifiant de l'utilisateur
     */
    public String getLogin_user() {
        return login_user;
    }

    /**
     * Définie l'identifiant de l'utilisateur
     * @param login_user identifiant que l'on souhaite donner à l'utilisateur
     */
    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    /**
     * Récupération du mot de passe de l'utilisateur
     * @return Une chaine de caractère correspondant au mot de passe de l'utilisateur
     */
    public String getPwd_user() {
        return pwd_user;
    }

    /**
     * Définie le mot de passe de l'utilisateur
     * @param pwd_user mot de passe que l'on souhaite donner à l'utilisateur
     */
    public void setPwd_user(String pwd_user) {
        this.pwd_user = pwd_user;
    }

    /**
     * Récupération des droits de l'utilisateur
     * @return Un entier correspondant au droits de l'utilisateur
     */
    public int getAdmin() {
        return admin;
    }

    /**
     * Définie les droits de l'utilisateur
     * @param admin 0: utilisateur basique , 1: administrateur
     */
    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
