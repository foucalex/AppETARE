package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.model.Level;
import sdis.projet.app.etare.database.DatabaseHandler;

public class LevelDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_LEVEL_ID,
            DatabaseHandler.KEY_LEVEL_DESC
    };

    public LevelDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Level createLevel(String id_level, String desc_level){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_LEVEL_ID,id_level);
        values.put(DatabaseHandler.KEY_LEVEL_DESC,desc_level);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_LEVEL, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_LEVEL,
                allColumns, DatabaseHandler.KEY_LEVEL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Level newLevel = cursorToLevel(cursor);
        cursor.close();
        return newLevel;

    }

    public List<Level> getAllLevel() {
        List<Level> levels = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_LEVEL,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Level level = cursorToLevel(cursor);
            levels.add(level);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return levels;
    }

    private Level cursorToLevel(Cursor cursor) {
        Level level = new Level();
        level.setId_level(cursor.getInt(0));
        level.setDesc_level(cursor.getString(1));
        return level;
    }
}
