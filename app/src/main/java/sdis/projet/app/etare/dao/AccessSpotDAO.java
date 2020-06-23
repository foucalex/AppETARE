package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import sdis.projet.app.etare.database.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.model.AccessSpot;

import static java.lang.String.valueOf;

public class AccessSpotDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_ACCESS_SPOT_ID,
            DatabaseHandler.KEY_ACCESS_SPOT_COMMENTARY,
            DatabaseHandler.KEY_ACCESS_SPOT_LOCATION,
            DatabaseHandler.KEY_ACCESS_SPOT_ETARE };

    public AccessSpotDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public AccessSpot createAccessSpot(String commentary_access,String location_access,int id_etare){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_ACCESS_SPOT_COMMENTARY,commentary_access);
        values.put(DatabaseHandler.KEY_ACCESS_SPOT_LOCATION,location_access);
        values.put(DatabaseHandler.KEY_ACCESS_SPOT_ETARE,id_etare);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_ACCESS_SPOT, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_ACCESS_SPOT,
                allColumns, DatabaseHandler.KEY_ACCESS_SPOT_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        AccessSpot newAccessSpot = cursorToAccessSpot(cursor);
        cursor.close();
        return newAccessSpot;

    }
    public int updateAccessSpot(AccessSpot accessSpot){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_ACCESS_SPOT_COMMENTARY,accessSpot.getCommentary_access());
        values.put(DatabaseHandler.KEY_ACCESS_SPOT_LOCATION,accessSpot.getLocation_access());
        db.update(DatabaseHandler.TABLE_ACCESS_SPOT,values, DatabaseHandler.KEY_ACCESS_SPOT_ID +" = "+accessSpot.getId_access(),null);

        db.close();
        close();
        return 0;
    }


    public void deleteAccessSpot(AccessSpot accessSpot) {
        open();
        int id = accessSpot.getId_access();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DatabaseHandler.TABLE_ACCESS_SPOT, DatabaseHandler.KEY_ACCESS_SPOT_ID
                + " = " + id, null);
        close();
    }

    public void deleteAccessSpotByEtareId(int id) {
        open();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DatabaseHandler.TABLE_ACCESS_SPOT, DatabaseHandler.KEY_ACCESS_SPOT_ETARE
                + " = " + id, null);
        close();
    }

    public List<AccessSpot> getAllAccessSpots() {
        List<AccessSpot> accessSpots = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_ACCESS_SPOT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AccessSpot accessSpot = cursorToAccessSpot(cursor);
            accessSpots.add(accessSpot);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return accessSpots;
    }

    public List<AccessSpot> getAccessSpotByEtareId(int id) {
        List<AccessSpot> accessSpots = new ArrayList<>();
        String[] param = {valueOf(id)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_ACCESS_SPOT,
                allColumns, DatabaseHandler.KEY_ACCESS_SPOT_ETARE + "=?", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AccessSpot accessSpot = cursorToAccessSpot(cursor);
            accessSpots.add(accessSpot);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return accessSpots;
    }

    private AccessSpot cursorToAccessSpot(Cursor cursor) {
        AccessSpot accessSpot = new AccessSpot();
        accessSpot.setId_access(cursor.getInt(0));
        accessSpot.setCommentary_access(cursor.getString(1));
        accessSpot.setLocation_access(cursor.getString(2));
        accessSpot.setId_etare(cursor.getInt(3));
        return accessSpot;
    }


}
