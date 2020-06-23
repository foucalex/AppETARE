package sdis.projet.app.etare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    /**Database Version**/
    private static final int DATABASE_VERSION = 1;

    /**Database Name**/
    private static final String DATABASE_NAME = "etareManager";

    // Nom des Tables
    /**Chaine de caratère pour définir le nom de la table ETARE*/
    public static final String TABLE_ETARE = "ETARE";
    /**Chaine de caratère pour définir le nom de la table User*/
    public static final String TABLE_USER = "User";
    /**Chaine de caratère pour définir le nom de la table Access Spot*/
    public static final String TABLE_ACCESS_SPOT = "Access_Spot";
    /**Chaine de caratère pour définir le nom de la table Contact*/
    public static final String TABLE_CONTACT = "Contact";
    /**Chaine de caratère pour définir le nom de la table Ressource Doc*/
    public static final String TABLE_RESSOURCE_DOC = "Ressource_Doc";
    /**Chaine de caratère pour définir le nom de la table Level*/
    public static final String TABLE_LEVEL = "Level";
    /**Chaine de caratère pour définir le nom de la table Location*/
    public static final String TABLE_LOCATION = "Location";
    /**Chaine de caratère pour définir le nom de la table Town*/
    public static final String TABLE_TOWN = "Town";
    /**Chaine de caratère pour définir le nom de la table Media*/
    public static final String TABLE_MEDIA = "Media";
    /**Chaine de caratère pour définir le nom de la table Category*/
    public static final String TABLE_CATEGORY = "Category";
    /**Chaine de caratère pour définir le nom de la table Map*/
    public static final String TABLE_MAP = "Map";
    /**Chaine de caratère pour définir le nom de la table Pictogram*/
    public static final String TABLE_PICTOGRAM = "Pictogram";

    //Nom de Colonne - Table ETARE
    /**Chaine de caratère pour définir le nom de la colonne id etare*/
    public static final String KEY_ETARE_ID = "id_etare";
    /**Chaine de caratère pour définir le nom de la colonne date de création*/
    public static final String KEY_ETARE_CREATION_DATE = "creation_date";
    /**Chaine de caratère pour définir le nom de la colonne date de mise à jour*/
    public static final String KEY_ETARE_UPDATE_DATE = "update_date";
    /**Chaine de caratère pour définir le nom de la colonne statut*/
    public static final String KEY_ETARE_STATUS = "status";
    /**Chaine de caratère pour définir le nom de la colonne id niveau etare*/
    public static final String KEY_ETARE_LEVEL = "id_level";
    /**Chaine de caratère pour définir le nom de la colonne id de la ville*/
    public static final String KEY_ETARE_TOWN = "id_town";
    /**Chaine de caratère pour définir le nom de la colonne id location*/
    public static final String KEY_ETARE_LOCATION = "id_location";

    //Nom de Colonne - Table User
    /**Chaine de caratère pour définir le nom de la colonne id utilisateur*/
    public static final String KEY_USER_ID = "id_user";
    /**Chaine de caratère pour définir le nom de la colonne identifiant utilisateur*/
    public static final String KEY_USER_LOGIN = "login_user";
    /**Chaine de caratère pour définir le nom de la colonne mot de passe utilisateur*/
    public static final String KEY_USER_PASSWORD = "pwd_user";
    /**Chaine de caratère pour définir le nom de la colonne des droits administrateur*/
    public static final String KEY_USER_ADMIN = "admin";

    //Nom de Colonne - Table Contact
    /**Chaine de caratère pour définir le nom de la colonne id contact*/
    public static final String KEY_CONTACT_ID="id_contact";
    /**Chaine de caratère pour définir le nom de la colonne nom du contact*/
    public static final String KEY_CONTACT_NAME="name_contact";
    /**Chaine de caratère pour définir le nom de la colonne fonction du contact*/
    public static final String KEY_CONTACT_JOB="job_contact";
    /**Chaine de caratère pour définir le nom de la colonne téléphone du contact*/
    public static final String KEY_CONTACT_PHONE="phone_contact";
    /**Chaine de caratère pour définir le nom de la colonne téléphone portable du contact*/
    public static final String KEY_CONTACT_MOBILEPHONE="mobilephone_contact";
    /**Chaine de caratère pour définir le nom de la colonne email du contact*/
    public static final String KEY_CONTACT_EMAIL="email_contact";
    /**Chaine de caratère pour définir le nom de la colonne id etare*/
    public  static final String KEY_CONTACT_ETARE="id_etare";
    /**Chaine de caratère pour définir le nom de la colonne contacter en cas d'urgence*/
    public static final String KEY_CONTACT_URGENCE = "urgence";


    //Nom de Colonne - Table Ressource Doc
    /**Chaine de caratère pour définir le nom de la colonne id ressource*/
    public static final String KEY_RESSOURCE_DOC_ID="id_ressource";
    /**Chaine de caratère pour définir le nom de la colonne description ressource*/
    public static final String KEY_RESSOURCE_DOC_DESC= "desc_ressource";
    /**Chaine de caratère pour définir le nom de la colonne url de la ressource*/
    public static final String KEY_RESSOURCE_DOC_URL="url_ressource";
    /**Chaine de caratère pour définir le nom de la colonne id etare*/
    public  static final String KEY_RESSOURCE_DOC_ETARE="id_etare";


    //Nom de Colonne - Table Level
    /**Chaine de caratère pour définir le nom de la colonne id niveau etare*/
    public static final String KEY_LEVEL_ID = "id_level";
    /**Chaine de caratère pour définir le nom de la colonne description du niveau etare*/
    public static final String KEY_LEVEL_DESC = "desc_level";


    //Nom de Colonne - Table Town
    /**Chaine de caratère pour définir le nom de la colonne id de la ville*/
    public static final String KEY_TOWN_ID="id_town";
    /**Chaine de caratère pour définir le nom de la colonne nom de la ville*/
    public static final String KEY_TOWN_NAME="name_town";
    /**Chaine de caratère pour définir le nom de la colonne groupement de la ville*/
    public static final String KEY_TOWN_GROUP="group_town";
    /**Chaine de caratère pour définir le nom de la colonne code insee de la ville*/
    public static final String KEY_TOWN_INSEE="insee_town";


    //Nom de Colonne - Table Location
    /**Chaine de caratère pour définir le nom de la colonne id location*/
    public static final String KEY_LOCATION_ID="id_location";
    /**Chaine de caratère pour définir le nom de la colonne nom du lieu*/
    public static final String KEY_LOCATION_NAME="name_location";
    /**Chaine de caratère pour définir le nom de la colonne adresse du lieu*/
    public static final String KEY_LOCATION_ADDRESS="address_location";
    /**Chaine de caratère pour définir le nom de la colonne activitée du lieu*/
    public static final String KEY_LOCATION_ACTIVITY="activity_location";
    /**Chaine de caratère pour définir le nom de la colonne infrastructure du lieu*/
    public static final String KEY_LOCATION_INFRASTRUCTURE="infrastructure_location";
    /**Chaine de caratère pour définir le nom de la colonne capacitée du lieu*/
    public static final String KEY_LOCATION_CAPACITY="capacity_location";
    /**Chaine de caratère pour définir le nom de la colonneraison de classement du lieu*/
    public static final String KEY_LOCATION_CLASSIFICATION_REASON="classification_reason_location";
    /**Chaine de caratère pour définir le nom de la colonne consignes opérateur concernant le lieu*/
    public static final String KEY_LOCATION_CONSIGNES_OPE="consignes_ope_location";
    /**Chaine de caratère pour définir le nom de la colonne consignes centre de secours concernant le lieu*/
    public static final String KEY_LOCATION_CONSIGNES_CS="consignes_cs_location";
    /**Chaine de caratère pour définir le nom de la colonne analyse des risque concernant le lieu*/
    public static final String KEY_LOCATION_RISK_ANALYSIS="risk_analysis_location";


    //Nom de Colonne - Table Media
    /**Chaine de caratère pour définir le nom de la colonne id media*/
    public static final String KEY_MEDIA_ID = "id_media";
    /**Chaine de caratère pour définir le nom de la colonne nom du media*/
    public static final String KEY_MEDIA_NAME= "name_media";
    /**Chaine de caratère pour définir le nom de la colonne description du media*/
    public static final String KEY_MEDIA_DESC = "desc_media";
    /**Chaine de caratère pour définir le nom de la colonne image du media*/
    public static final String KEY_MEDIA_IMG = "img_media";
    /**Chaine de caratère pour définir le nom de la colonne id etare*/
    public static final String KEY_MEDIA_ETARE="id_etare";
    /**Chaine de caratère pour définir le nom de la colonne id category*/
    public static final String KEY_MEDIA_CATEGORY ="id_category";


    //Nom de Colonne - Table Map
    /**Chaine de caratère pour définir le nom de la colonne id du plan*/
    public static final String KEY_MAP_ID = "id_map";
    /**Chaine de caratère pour définir le nom de la colonne nom du plan*/
    public static final String KEY_MAP_NAME = "name_map";
    /**Chaine de caratère pour définir le nom de la colonne image du plan*/
    public static final String KEY_MAP_FILE = "file_map";
    /**Chaine de caratère pour définir le nom de la colonne type de plan*/
    public static final String KEY_MAP_TYPE = "type_map";
    /**Chaine de caratère pour définir le nom de la colonne echelle du plan*/
    public static final String KEY_MAP_ECHELLE="echelle_map";
    /**Chaine de caratère pour définir le nom de la colonne id etare*/
    public static final String KEY_MAP_ETARE="id_etare";

    //Nom de Colonne - Table Category
    /**Chaine de caratère pour définir le nom de la colonne id categorie*/
    public static final String KEY_CATEGORY_ID = "id_category";
    /**Chaine de caratère pour définir le nom de la colonne nom de la categorie*/
    public static final String KEY_CATEGORY_NAME = "name_category";
    /**Chaine de caratère pour définir le nom de la colonne commentaire de la categorie*/
    public static final String KEY_CATEGORY_COMMENTARY = "commentary_category";
    /**Chaine de caratère pour définir le nom de la colonne id etare*/
    public static final String KEY_CATEGORY_ETARE="id_etare";


    //Nom de Colonne - Table Acces Spot
    /**Chaine de caratère pour définir le nom de la colonne id de l'acces*/
    public static final String KEY_ACCESS_SPOT_ID="id_access";
    /**Chaine de caratère pour définir le nom de la colonne commentaire concernant l'acces*/
    public static final String KEY_ACCESS_SPOT_COMMENTARY="commentary_access";
    /**Chaine de caratère pour définir le nom de la colonne localisation de l'acces*/
    public static final String KEY_ACCESS_SPOT_LOCATION="location_access";
    /**Chaine de caratère pour définir le nom de la colonne id etare*/
    public static final String KEY_ACCESS_SPOT_ETARE="id_etare";


    //Nom de Colonne - Table Pictogram
    /**Chaine de caratère pour définir le nom de la colonne id pictogramme*/
    public static final String KEY_PICTOGRAM_ID="id_picto";
    /**Chaine de caratère pour définir le nom de la colonne nom du pictogramme*/
    public static final String KEY_PICTOGRAM_NAME="name_picto";
    /**Chaine de caratère pour définir le nom de la colonne image du pictogramme*/
    public static final String KEY_PICTOGRAM_IMG="img_picto";
    /**Chaine de caratère pour définir le nom de la colonne taille du pictogramme*/
    public static final String KEY_PICTOGRAM_SIZE="size_picto";
    /**Chaine de caratère pour définir le nom de la colonne coordonnée x du pictogramme*/
    public static final String KEY_PICTOGRAM_COORD_X="coord_x_picto";
    /**Chaine de caratère pour définir le nom de la colonne coordonnée y du pictogramme*/
    public static final String KEY_PICTOGRAM_COORD_Y="coord_y_picto";
    /**Chaine de caratère pour définir le nom de la colonne id media*/
    public static final String KEY_PICTOGRAM_MEDIA="id_media";
    /**Chaine de caratère pour définir le nom de la colonne id du plan*/
    public static final String KEY_PICTOGRAM_MAP="id_map";


    /**Création Table ETARE**/
    private static final String CREATE_TABLE_ETARE = "CREATE TABLE "
            + TABLE_ETARE
            + " ("
            + KEY_ETARE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ETARE_CREATION_DATE +" TEXT NOT NULL,"
            + KEY_ETARE_UPDATE_DATE +" TEXT NOT NULL,"
            + KEY_ETARE_STATUS +" INTEGER NOT NULL,"
            + KEY_ETARE_LEVEL +" INTEGER NOT NULL,"
            + KEY_ETARE_TOWN +" INTEGER NOT NULL,"
            + KEY_ETARE_LOCATION +" INTEGER NOT NULL,"
            + "FOREIGN KEY (" + KEY_ETARE_LEVEL +") REFERENCES "+TABLE_LEVEL+"(" + KEY_LEVEL_ID +"),"
            + "FOREIGN KEY (" + KEY_ETARE_TOWN +") REFERENCES "+TABLE_TOWN+"(" + KEY_TOWN_ID +"),"
            + "FOREIGN KEY (" + KEY_ETARE_LOCATION +") REFERENCES "+TABLE_LOCATION+"(" + KEY_LOCATION_ID +")"
            + ");";

    /**Création Table USER**/
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER
            + " ("
            + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USER_LOGIN +" TEXT NOT NULL UNIQUE,"
            + KEY_USER_PASSWORD +" TEXT NOT NULL,"
            + KEY_USER_ADMIN +" INTEGER NOT NULL"
            + ");";

    /**Création Table CONTACT**/
    private static final String CREATE_TABLE_CONTACT = "CREATE TABLE "
            + TABLE_CONTACT
            + " ("
            + KEY_CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CONTACT_NAME +" TEXT NULL,"
            + KEY_CONTACT_JOB +" TEXT NOT NULL,"
            + KEY_CONTACT_PHONE +" TEXT NULL,"
            + KEY_CONTACT_MOBILEPHONE +" TEXT NULL,"
            + KEY_CONTACT_EMAIL +" TEXT NULL,"
            + KEY_CONTACT_ETARE +" INTEGER NOT NULL,"
            + KEY_CONTACT_URGENCE +" INTEGER NOT NULL,"
            + "FOREIGN KEY (" + KEY_CONTACT_ETARE +") REFERENCES "+TABLE_ETARE+"(" + KEY_ETARE_ID +")"
            + ");";

    /**Création Table ACCESS SPOT**/
    private static final String CREATE_TABLE_ACCESS_SPOT = "CREATE TABLE "
            + TABLE_ACCESS_SPOT
            + " ("
            + KEY_ACCESS_SPOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ACCESS_SPOT_COMMENTARY +" TEXT NOT NULL,"
            + KEY_ACCESS_SPOT_LOCATION +" TEXT NOT NULL,"
            + KEY_ACCESS_SPOT_ETARE +" INTEGER NOT NULL,"
            + "FOREIGN KEY (" + KEY_ACCESS_SPOT_ETARE +") REFERENCES "+TABLE_ETARE+"(" + KEY_ETARE_ID +")"
            + ");";

    /**Création Table RESSOURCE DOC**/
    private static final String CREATE_TABLE_RESSOURCE_DOC = "CREATE TABLE "
            + TABLE_RESSOURCE_DOC
            + " ("
            + KEY_RESSOURCE_DOC_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_RESSOURCE_DOC_DESC + " TEXT NOT NULL,"
            + KEY_RESSOURCE_DOC_URL +" TEXT NOT NULL,"
            + KEY_RESSOURCE_DOC_ETARE +" INTEGER NOT NULL,"
            + "FOREIGN KEY (" + KEY_RESSOURCE_DOC_ETARE +") REFERENCES "+TABLE_ETARE+"(" + KEY_ETARE_ID +")"
            + ");";

    /**Création Table LEVEL**/
    private static final String CREATE_TABLE_LEVEL = "CREATE TABLE "
            + TABLE_LEVEL
            + " ("
            + KEY_LEVEL_ID + " INTEGER PRIMARY KEY NOT NULL,"
            + KEY_LEVEL_DESC +" TEXT NOT NULL"
            + ");";

    /**Création Table TOWN**/
    private static final String CREATE_TABLE_TOWN = "CREATE TABLE "
            + TABLE_TOWN
            + " ("
            + KEY_TOWN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TOWN_NAME +" TEXT NOT NULL UNIQUE,"
            + KEY_TOWN_GROUP +" TEXT NOT NULL,"
            + KEY_TOWN_INSEE +" TEXT NOT NULL"
            + ");";

    /**Création Table LOCATION**/
    private static final String CREATE_TABLE_LOCATION = "CREATE TABLE "
            + TABLE_LOCATION
            + " ("
            + KEY_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_LOCATION_NAME +" TEXT NOT NULL,"
            + KEY_LOCATION_ADDRESS +" TEXT NOT NULL,"
            + KEY_LOCATION_ACTIVITY +" TEXT NOT NULL,"
            + KEY_LOCATION_INFRASTRUCTURE +" TEXT NOT NULL,"
            + KEY_LOCATION_CAPACITY +" TEXT NOT NULL,"
            + KEY_LOCATION_CLASSIFICATION_REASON+ " TEXT NOT NULL,"
            + KEY_LOCATION_CONSIGNES_OPE +" TEXT NOT NULL,"
            + KEY_LOCATION_CONSIGNES_CS +" TEXT NOT NULL,"
            + KEY_LOCATION_RISK_ANALYSIS + " TEXT NOT NULL"
            + ");";

    /**Création Table MEDIA**/
    private static final String CREATE_TABLE_MEDIA = "CREATE TABLE "
            + TABLE_MEDIA
            + "("
            + KEY_MEDIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_MEDIA_NAME +" TEXT NOT NULL,"
            + KEY_MEDIA_DESC +" TEXT NULL,"
            + KEY_MEDIA_IMG +" BLOB NOT NULL,"
            + KEY_MEDIA_ETARE +" INTEGER NOT NULL,"
            + KEY_MEDIA_CATEGORY +" INTEGER NULL,"
            + "FOREIGN KEY (" + KEY_MEDIA_ETARE +") REFERENCES "+TABLE_ETARE+"(" + KEY_ETARE_ID +"),"
            + "FOREIGN KEY (" + KEY_MEDIA_CATEGORY +") REFERENCES "+TABLE_CATEGORY+"(" + KEY_CATEGORY_ID +")"
            + ");";

    /**Création Table CATEGORY**/
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE "
            + TABLE_CATEGORY
            + "("
            + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CATEGORY_NAME +" TEXT NOT NULL,"
            + KEY_CATEGORY_COMMENTARY +" TEXT NULL,"
            + KEY_CATEGORY_ETARE +" INTEGER NOT NULL,"
            + "FOREIGN KEY (" + KEY_CATEGORY_ETARE +") REFERENCES "+TABLE_ETARE+"(" + KEY_ETARE_ID +")"
            + ");";

    /**Création Table MAP**/
    private static final String CREATE_TABLE_MAP = "CREATE TABLE "
            + TABLE_MAP
            + "("
            + KEY_MAP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_MAP_NAME +" TEXT NOT NULL,"
            + KEY_MAP_FILE +" BLOB NOT NULL,"
            + KEY_MAP_TYPE +" INTEGER NOT NULL,"
            + KEY_MAP_ECHELLE +" TEXT NULL,"
            + KEY_MAP_ETARE +" INTEGER NOT NULL,"
            + "FOREIGN KEY (" + KEY_MAP_ETARE +") REFERENCES "+TABLE_ETARE+"(" + KEY_ETARE_ID +")"
            + ");";

    /**Creation Table PICTOGRAM**/
    private static final String CREATE_TABLE_PICTOGRAM = "CREATE TABLE "
            + TABLE_PICTOGRAM
            + "("
            + KEY_PICTOGRAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_PICTOGRAM_NAME +" TEXT NOT NULL,"
            + KEY_PICTOGRAM_IMG +" BLOB NOT NULL,"
            + KEY_PICTOGRAM_SIZE +" TEXT NOT NULL,"
            + KEY_PICTOGRAM_COORD_X +" TEXT NOT NULL,"
            + KEY_PICTOGRAM_COORD_Y +" TEXT NOT NULL,"
            + KEY_PICTOGRAM_MEDIA +" INTEGER NULL,"
            + KEY_PICTOGRAM_MAP + " INTEGER NOT NULL,"
            + "FOREIGN KEY (" + KEY_PICTOGRAM_MEDIA +") REFERENCES "+TABLE_MEDIA+"(" + KEY_MEDIA_ID +"),"
            + "FOREIGN KEY (" + KEY_PICTOGRAM_MAP +") REFERENCES "+TABLE_MAP+"(" + KEY_MAP_ID +")"
            + ");";

    /**Ajout de données dans la table Level**/
    private static final String INSERT_DATA_LEVEL = "INSERT INTO "+TABLE_LEVEL+" ("+KEY_LEVEL_ID+","+KEY_LEVEL_DESC+") VALUES ('1', 'Niveau 1'),('2', 'Niveau 2'),('3', 'Niveau 3'),('4', 'Niveau 4'),('5', 'Niveau 5')";

    /**Ajout de données dans la table User**/
    private static final String INSERT_ADMIN_USER = "INSERT INTO "+TABLE_USER+" ("+KEY_USER_LOGIN+","+KEY_USER_PASSWORD+","+KEY_USER_ADMIN+") VALUES ('admin','admin','1'),('afoucaud','marans','0')";

    /**
     * Constructeur de DatabaseHandler
     * @param context contexte de l'application
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Fonction de création de la base de données
     * @param db Base de données cible
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_key = ON");
        db.execSQL(CREATE_TABLE_ETARE);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CONTACT);
        db.execSQL(CREATE_TABLE_ACCESS_SPOT);
        db.execSQL(CREATE_TABLE_RESSOURCE_DOC);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_LEVEL);
        db.execSQL(CREATE_TABLE_LOCATION);
        db.execSQL(CREATE_TABLE_MAP);
        db.execSQL(CREATE_TABLE_MEDIA);
        db.execSQL(CREATE_TABLE_PICTOGRAM);
        db.execSQL(CREATE_TABLE_TOWN);
        db.execSQL(INSERT_DATA_LEVEL);
        db.execSQL(INSERT_ADMIN_USER);
    }


    /**
     * Fonction de mise à jour de la base de données
     * @param db Base de données cible
     * @param oldVersion Ancienne version de la base de données
     * @param newVersion Nouvelle version de la base de données
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String DROP = "DROP TABLE IF EXISTS ";

        db.execSQL(DROP+CREATE_TABLE_ETARE);
        db.execSQL(DROP+CREATE_TABLE_USER);
        db.execSQL(DROP+CREATE_TABLE_CONTACT);
        db.execSQL(DROP+CREATE_TABLE_ACCESS_SPOT);
        db.execSQL(DROP+CREATE_TABLE_RESSOURCE_DOC);
        db.execSQL(DROP+CREATE_TABLE_CATEGORY);
        db.execSQL(DROP+CREATE_TABLE_LEVEL);
        db.execSQL(DROP+CREATE_TABLE_LOCATION);
        db.execSQL(DROP+CREATE_TABLE_MAP);
        db.execSQL(DROP+CREATE_TABLE_MEDIA);
        db.execSQL(DROP+CREATE_TABLE_PICTOGRAM);
        db.execSQL(DROP+CREATE_TABLE_TOWN);

        onCreate(db);
    }
}
