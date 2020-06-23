package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.model.Town;
import sdis.projet.app.etare.database.DatabaseHandler;

import static java.lang.String.valueOf;

public class TownDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_TOWN_ID,
            DatabaseHandler.KEY_TOWN_NAME,
            DatabaseHandler.KEY_TOWN_GROUP,
            DatabaseHandler.KEY_TOWN_INSEE
    };

    public TownDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Town createTown(String name_town,String group_town, String insee_town){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_TOWN_NAME,name_town);
        values.put(DatabaseHandler.KEY_TOWN_GROUP,group_town);
        values.put(DatabaseHandler.KEY_TOWN_INSEE,insee_town);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_TOWN, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                allColumns, DatabaseHandler.KEY_TOWN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Town newTown = cursorToTown(cursor);
        cursor.close();
        return newTown;

    }

    public List<Town> getAllTown() {
        List<Town> towns = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Town town = cursorToTown(cursor);
            towns.add(town);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return towns;
    }

    public List<String> getAllNameTown() {
        List<String> towns = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String town = cursor.getString(1);
            towns.add(town);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return towns;
    }

    public List<Town> getTownDataByName(String name) {
        List<Town> towns = new ArrayList<>();
        String[] param = {name};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                    allColumns, DatabaseHandler.KEY_TOWN_NAME + "= ?", param, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Town town = cursorToTown(cursor);
                towns.add(town);
                cursor.moveToNext();
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("DB ERROR",e.toString() );
            e.printStackTrace();
        }
        return towns;
    }

    public Town getTownDataById(int id) {
        Town town = new Town();
        String[] param = {valueOf(id)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                    allColumns, DatabaseHandler.KEY_ETARE_TOWN + "= ?", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
                town = cursorToTown(cursor);
                cursor.moveToNext();
        }
        cursor.close();
        return town;
    }

    public int getIdTownByName(String name) {
        int result = -1;
        String[] param = {name};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                    null, DatabaseHandler.KEY_TOWN_NAME + "= ?", param, null, null, null);

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

    public String getNameTownById(int id) {
        String result ="";
        String[] param = {valueOf(id)};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                    null, DatabaseHandler.KEY_TOWN_ID + "= ?", param, null, null, null);

            if(cursor.moveToFirst()){
                result = cursor.getString(1);
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("DB ERROR",e.toString() );
            e.printStackTrace();
        }
        return result;
    }

    public String getGroupTownByName(String name) {
        String result = "";
        String[] param = {name};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_TOWN,
                    null, DatabaseHandler.KEY_TOWN_NAME + "= ?", param, null, null, null);

            if(cursor.moveToFirst()){
                result = cursor.getString(2);
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("DB ERROR",e.toString() );
            e.printStackTrace();
        }
        return result;
    }



    private Town cursorToTown(Cursor cursor) {
        Town town = new Town();
        town.setId_town(cursor.getInt(0));
        town.setName_town(cursor.getString(1));
        town.setGroup_town(cursor.getString(2));
        town.setInsee_town(cursor.getString(3));
        return town;
    }
}
