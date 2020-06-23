package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.database.DatabaseHandler;
import sdis.projet.app.etare.model.Location;

import static java.lang.String.valueOf;

public class LocationDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_LOCATION_ID,
            DatabaseHandler.KEY_LOCATION_NAME,
            DatabaseHandler.KEY_LOCATION_ADDRESS,
            DatabaseHandler.KEY_LOCATION_ACTIVITY,
            DatabaseHandler.KEY_LOCATION_INFRASTRUCTURE,
            DatabaseHandler.KEY_LOCATION_CAPACITY,
            DatabaseHandler.KEY_LOCATION_CLASSIFICATION_REASON,
            DatabaseHandler.KEY_LOCATION_CONSIGNES_OPE,
            DatabaseHandler.KEY_LOCATION_CONSIGNES_CS,
            DatabaseHandler.KEY_LOCATION_RISK_ANALYSIS
    };

    public LocationDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    /**
     * Ouvre la connexion avec la base de données pour permettre l'écriture
     * @throws SQLException
     */
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Ferme la connexion avec la base de données
     */
    public void close(){
        dbHelper.close();
    }

    /**
     * Ajout d'une nouvelle Location en base de données
     * @param name_location nom de l'ETARE
     * @param address_location adresse de l'ETARE
     * @param activity_location activitée de l'ETARE
     * @param infrastructure_location infrastructure de l'ETARE
     * @param capacity_location capacitée d'accueil de l'ETARE
     * @param classification_reason_location motif de classement de l'ETARE
     * @param consignes_ope_location consignes opérateur concernant l'ETARE
     * @param consignes_cs_location consignes centre de secours concernant l'ETARE
     * @param risk_analysis_location analyse des risque de l'ETARE
     * @return un objet Location avec les données actuelle
     */
    public Location createLocation(String name_location, String address_location, String activity_location, String infrastructure_location, String capacity_location, String classification_reason_location, String consignes_ope_location,String consignes_cs_location,String risk_analysis_location){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_LOCATION_NAME,name_location);
        values.put(DatabaseHandler.KEY_LOCATION_ADDRESS,address_location);
        values.put(DatabaseHandler.KEY_LOCATION_ACTIVITY,activity_location);
        values.put(DatabaseHandler.KEY_LOCATION_INFRASTRUCTURE,infrastructure_location);
        values.put(DatabaseHandler.KEY_LOCATION_CAPACITY,capacity_location);
        values.put(DatabaseHandler.KEY_LOCATION_CLASSIFICATION_REASON,classification_reason_location);
        values.put(DatabaseHandler.KEY_LOCATION_CONSIGNES_OPE,consignes_ope_location);
        values.put(DatabaseHandler.KEY_LOCATION_CONSIGNES_CS,consignes_cs_location);
        values.put(DatabaseHandler.KEY_LOCATION_RISK_ANALYSIS,risk_analysis_location);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_LOCATION, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_LOCATION,
                allColumns, DatabaseHandler.KEY_LOCATION_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Location newLocation = cursorToLocation(cursor);
        cursor.close();
        return newLocation;

    }

    /**
     * Actualisation des données d'une Location
     * @param location objet Location avec le données actualisées
     */
    public void updateLocation(Location location){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_LOCATION_NAME,location.getName_location());
        values.put(DatabaseHandler.KEY_LOCATION_ADDRESS,location.getAddress_location());
        values.put(DatabaseHandler.KEY_LOCATION_ACTIVITY,location.getActivity_location());
        values.put(DatabaseHandler.KEY_LOCATION_INFRASTRUCTURE,location.getInfrastructure_location());
        values.put(DatabaseHandler.KEY_LOCATION_CAPACITY,location.getCapacity_location());
        values.put(DatabaseHandler.KEY_LOCATION_CLASSIFICATION_REASON,location.getClassification_reason_location());
        values.put(DatabaseHandler.KEY_LOCATION_CONSIGNES_OPE,location.getConsignes_ope_location());
        values.put(DatabaseHandler.KEY_LOCATION_CONSIGNES_CS,location.getConsignes_cs_location());
        values.put(DatabaseHandler.KEY_LOCATION_RISK_ANALYSIS,location.getRisk_analysis_location());
        db.update(DatabaseHandler.TABLE_LOCATION,values, DatabaseHandler.KEY_LOCATION_ID +" = "+location.getId_location(),null);

        db.close();
        close();
    }


    /**
     * Suppression en base d'une Location
     * @param location objet location que l'on souhaite supprimer en base
     */
    public void deleteLocation(Location location) {
        open();
        int id = location.getId_location();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DatabaseHandler.TABLE_LOCATION, DatabaseHandler.KEY_LOCATION_ID
                + " = " + id, null);
        close();
    }

    /**
     * Récupération de la liste de tout les Locations
     * @return List<Location> contenant tout les Locations de la table
     */
    public List<Location> getAllLocation() {
        List<Location> locations = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_LOCATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Location location = cursorToLocation(cursor);
            locations.add(location);
            cursor.moveToNext();
        }
        cursor.close();
        return locations;
    }

    /**
     * Récupération de location grâce à l'id_location
     * @param id id location dont on veut les informations
     * @return un objet Location avec les données associées
     */
    public Location getLocationById(int id){
        Location location = new Location();
        String[] param = {valueOf(id)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_LOCATION,
                allColumns, DatabaseHandler.KEY_LOCATION_ID + "= ?", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            location = cursorToLocation(cursor);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return location;
    }

    /**
     * Récupération de l'Id location grâce au nom de l'ETARE
     * @param name nom de l'ETARE
     * @return id_etare
     */
    public int getIdLocationByName(String name) {
        int result = -1;
        String[] param = {name};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_LOCATION,
                    null, DatabaseHandler.KEY_LOCATION_NAME + "= ?", param, null, null, null);

            if(cursor.moveToFirst()){
                result = cursor.getInt(0);
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("DB ERROR",e.toString() );
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Permet de parcourir les information de la base de données
     * @param cursor curseur de navigation de la table Location
     * @return un objet Location avec les données associées
     */
    private Location cursorToLocation(Cursor cursor) {
        Location location = new Location();
        location.setId_location(cursor.getInt(0));
        location.setName_location(cursor.getString(1));
        location.setAddress_location(cursor.getString(2));
        location.setActivity_location(cursor.getString(3));
        location.setInfrastructure_location(cursor.getString(4));
        location.setCapacity_location(cursor.getString(5));
        location.setClassification_reason_location(cursor.getString(6));
        location.setConsignes_ope_location(cursor.getString(7));
        location.setConsignes_cs_location(cursor.getString(8));
        location.setRisk_analysis_location(cursor.getString(9));
        return location;
    }
}
