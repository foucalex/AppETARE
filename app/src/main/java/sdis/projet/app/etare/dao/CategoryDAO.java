package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.database.DatabaseHandler;
import sdis.projet.app.etare.model.Category;
import sdis.projet.app.etare.model.ETARE;

public class CategoryDAO {


    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_CATEGORY_ID,
            DatabaseHandler.KEY_CATEGORY_NAME,
            DatabaseHandler.KEY_CATEGORY_COMMENTARY,
            DatabaseHandler.KEY_CATEGORY_ETARE
    };

    public CategoryDAO(Context context){dbHelper = new DatabaseHandler(context);}

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public Category createCategory(String name_category, String commentary_category, int id_etare){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CATEGORY_NAME,name_category);
        values.put(DatabaseHandler.KEY_CATEGORY_COMMENTARY,commentary_category);
        values.put(DatabaseHandler.KEY_CATEGORY_ETARE,id_etare);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_CATEGORY, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_CATEGORY,
                allColumns, DatabaseHandler.KEY_CATEGORY_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Category newCategory = cursorToCategory(cursor);
        cursor.close();
        return newCategory;

    }

    public int updateCategory(Category category){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CATEGORY_NAME,category.getName_category());
        values.put(DatabaseHandler.KEY_CATEGORY_COMMENTARY,category.getCommentary_category());
        values.put(DatabaseHandler.KEY_CATEGORY_ETARE,category.getId_etare());
        db.update(DatabaseHandler.TABLE_CATEGORY,values, DatabaseHandler.KEY_CATEGORY_ID +" = "+category.getId_category(),null);

        db.close();
        close();
        return 0;
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_CATEGORY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            categories.add(category);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return categories;
    }

    public Category getCategoryDataByEtareIdAndCategory(int id,String categoryName){
        Category category = new Category();
        String[] param = {String.valueOf(id),categoryName};

        Cursor cursor = db.query(DatabaseHandler.TABLE_CATEGORY,
            allColumns,DatabaseHandler.KEY_CATEGORY_ETARE+" = ? AND "+DatabaseHandler.KEY_CATEGORY_NAME+" = ?",param,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            category = cursorToCategory(cursor);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return category;
    }

    public void deleteCategory(Category category) {
        open();
        int id = category.getId_category();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DatabaseHandler.TABLE_CATEGORY, DatabaseHandler.KEY_CATEGORY_ID
                + " = " + id, null);
        close();
    }

    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setId_category(cursor.getInt(0));
        category.setName_category(cursor.getString(1));
        category.setCommentary_category(cursor.getString(2));
        category.setId_etare(cursor.getInt(3));
        return category;
    }
}
