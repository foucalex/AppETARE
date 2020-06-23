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
import sdis.projet.app.etare.model.ETARE;

import static java.lang.String.valueOf;

public class EtareDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_ETARE_ID,
            DatabaseHandler.KEY_ETARE_CREATION_DATE,
            DatabaseHandler.KEY_ETARE_UPDATE_DATE,
            DatabaseHandler.KEY_ETARE_STATUS,
            DatabaseHandler.KEY_ETARE_LEVEL,
            DatabaseHandler.KEY_ETARE_TOWN,
            DatabaseHandler.KEY_ETARE_LOCATION,
    };

    public EtareDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public ETARE createEtare(String creation_date, String update_date, String status, String id_level, String id_town, String id_location){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_ETARE_CREATION_DATE,creation_date);
        values.put(DatabaseHandler.KEY_ETARE_UPDATE_DATE,update_date);
        values.put(DatabaseHandler.KEY_ETARE_STATUS,status);
        values.put(DatabaseHandler.KEY_ETARE_LEVEL,id_level);
        values.put(DatabaseHandler.KEY_ETARE_TOWN,id_town);
        values.put(DatabaseHandler.KEY_ETARE_LOCATION,id_location);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_ETARE, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_ETARE,
                allColumns, DatabaseHandler.KEY_ETARE_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        ETARE newEtare = cursorToEtare(cursor);
        cursor.close();
        return newEtare;

    }
    public int updateEtare(ETARE etare){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_ETARE_CREATION_DATE,etare.getCreation_date());
        values.put(DatabaseHandler.KEY_ETARE_UPDATE_DATE,etare.getUpdate_date());
        values.put(DatabaseHandler.KEY_ETARE_STATUS,etare.getStatus());
        values.put(DatabaseHandler.KEY_ETARE_LEVEL,etare.getId_level());
        values.put(DatabaseHandler.KEY_ETARE_TOWN,etare.getId_town());
        values.put(DatabaseHandler.KEY_ETARE_LOCATION,etare.getId_location());
        db.update(DatabaseHandler.TABLE_ETARE,values, DatabaseHandler.KEY_ETARE_ID +" = "+etare.getId_etare(),null);

        db.close();
        close();
        return 0;
    }


    public void deleteEtare(ETARE etare) {
        open();
        int id = etare.getId_etare();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DatabaseHandler.TABLE_ETARE, DatabaseHandler.KEY_ETARE_ID
                + " = " + id, null);
        close();
    }

    public List<ETARE> getAllEtare() {
        List<ETARE> etares = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_ETARE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ETARE etare = cursorToEtare(cursor);
            etares.add(etare);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return etares;
    }

    public ETARE getEtareDataById(int id) {
        ETARE etare = new ETARE();
        String[] param = {valueOf(id)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_ETARE,
                allColumns, DatabaseHandler.KEY_ETARE_ID + "=?", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            etare = cursorToEtare(cursor);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return etare;
    }

    public int getIdEtareByIdLocation(int id) {
        int result = -1;
        String[] param = {String.valueOf(id)};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_ETARE,
                    null, DatabaseHandler.KEY_ETARE_LOCATION + "= ?", param, null, null, null);

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

    public int getIdLocationByIdEtare(int id) {
        int result = -1;
        String[] param = {valueOf(id)};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_ETARE,
                    null, DatabaseHandler.KEY_ETARE_ID + "= ?", param, null, null, null);

            if(cursor.moveToFirst()){
                result = cursor.getInt(6);
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("DB ERROR",e.toString() );
            e.printStackTrace();
        }
        return result;
    }

    public int getIdTownByEtareId(int id) {
        int result = -1;
        String[] param = {valueOf(id)};
        Cursor cursor = db.query(DatabaseHandler.TABLE_ETARE,
                    null, DatabaseHandler.KEY_ETARE_TOWN + "= ?", param, null, null, null);

        if(cursor.moveToFirst()){
                result = cursor.getInt(5);
        }
        cursor.close();
        return result;
    }

    private ETARE cursorToEtare(Cursor cursor) {
        ETARE etare = new ETARE();
        etare.setId_etare(cursor.getInt(0));
        etare.setCreation_date(cursor.getString(1));
        etare.setUpdate_date(cursor.getString(2));
        etare.setStatus(cursor.getString(3));
        etare.setId_level(cursor.getInt(4));
        etare.setId_town(cursor.getInt(5));
        etare.setId_location(cursor.getInt(6));
        return etare;
    }


}
