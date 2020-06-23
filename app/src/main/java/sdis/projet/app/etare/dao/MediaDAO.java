package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import sdis.projet.app.etare.database.DatabaseHandler;


import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.model.Media;

import static java.lang.String.valueOf;

public class MediaDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_MEDIA_ID,
            DatabaseHandler.KEY_MEDIA_NAME,
            DatabaseHandler.KEY_MEDIA_DESC,
            DatabaseHandler.KEY_MEDIA_IMG,
            DatabaseHandler.KEY_MEDIA_ETARE,
            DatabaseHandler.KEY_MEDIA_CATEGORY
    };

    public MediaDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }




    public Media createMedia(String name_media, String desc_media, byte[] img_media, int id_etare, int id_category){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_MEDIA_NAME,name_media);
        values.put(DatabaseHandler.KEY_MEDIA_DESC,desc_media);
        values.put(DatabaseHandler.KEY_MEDIA_IMG,img_media);
        values.put(DatabaseHandler.KEY_MEDIA_ETARE,id_etare);
        values.put(DatabaseHandler.KEY_MEDIA_CATEGORY,id_category);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_MEDIA, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_MEDIA,
                allColumns, DatabaseHandler.KEY_MEDIA_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Media newMedia = cursorToMedia(cursor);
        cursor.close();
        return newMedia;

    }

    public int updateMedia(Media media){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_MEDIA_NAME,media.getName_media());
        values.put(DatabaseHandler.KEY_MEDIA_DESC,media.getDesc_media());
        values.put(DatabaseHandler.KEY_MEDIA_IMG,media.getImg_media());
        values.put(DatabaseHandler.KEY_MEDIA_ETARE,media.getId_etare());
        values.put(DatabaseHandler.KEY_MEDIA_CATEGORY,media.getId_category());
        db.update(DatabaseHandler.TABLE_MEDIA,values, DatabaseHandler.KEY_MEDIA_ID +" = "+media.getId_media(),null);

        db.close();
        close();
        return 0;
    }


    public void deleteMedia(Media media) {
        open();
        int id = media.getId_media();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DatabaseHandler.TABLE_MEDIA, DatabaseHandler.KEY_MEDIA_ID
                + " = " + id, null);
        close();
    }

    public List<Media> getAllMedia() {
        List<Media> medias = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_MEDIA,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Media media = cursorToMedia(cursor);
            medias.add(media);
            cursor.moveToNext();
        }
        cursor.close();
        return medias;
    }

    public List<Media> getMediaByEtareId(int id) {
        List<Media> medias = new ArrayList<>();
        String[] param = {valueOf(id)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_MEDIA,
                allColumns, DatabaseHandler.KEY_MEDIA_ETARE + "=?", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Media media = cursorToMedia(cursor);
            medias.add(media);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return medias;
    }

    public Media getMediaDataByMediaIdAndEtareId(int id_media, int id_etare) {
        Media media = new Media();
        String[] param = {valueOf(id_media),valueOf(id_etare)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_MEDIA,
                allColumns, DatabaseHandler.KEY_MEDIA_ID + "=? AND "+DatabaseHandler.KEY_MEDIA_ETARE + "=?", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            media = cursorToMedia(cursor);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return media;
    }


    private Media cursorToMedia(Cursor cursor) {
        Media media = new Media();
        media.setId_media(cursor.getInt(0));
        media.setName_media(cursor.getString(1));
        media.setDesc_media(cursor.getString(2));
        media.setImg_media(cursor.getBlob(3));
        media.setId_etare(cursor.getInt(4));
        media.setId_category(cursor.getInt(5));
        return media;
    }
}
