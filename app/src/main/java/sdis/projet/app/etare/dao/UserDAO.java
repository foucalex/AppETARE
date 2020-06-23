package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.model.Level;
import sdis.projet.app.etare.model.Location;
import sdis.projet.app.etare.model.User;
import sdis.projet.app.etare.database.DatabaseHandler;

import static java.lang.String.valueOf;

public class UserDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_USER_ID,
            DatabaseHandler.KEY_USER_LOGIN,
            DatabaseHandler.KEY_USER_PASSWORD,
            DatabaseHandler.KEY_USER_ADMIN
    };

    public UserDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public User createUser(String login_user, String pwd_user, int admin){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_USER_LOGIN,login_user);
        values.put(DatabaseHandler.KEY_USER_PASSWORD,pwd_user);
        values.put(DatabaseHandler.KEY_USER_ADMIN,admin);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_USER, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_USER,
                allColumns, DatabaseHandler.KEY_USER_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;

    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_USER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return users;
    }

    public int getStateUser(String identifiant,String mdp) {
        int result = -1;
        String[] param = {identifiant,mdp};
        try{
            Cursor cursor = db.query(DatabaseHandler.TABLE_USER,
                    null, DatabaseHandler.KEY_USER_LOGIN + "=? AND "+DatabaseHandler.KEY_USER_PASSWORD + "=?", param, null, null, null);

            if(cursor.moveToFirst()){
                Log.d("TAG", "getStateUser: "+ cursor.getInt(1));
                result = cursor.getInt(3);
            }
            cursor.close();
        }catch (SQLException e){
            Log.e("DB ERROR",e.toString() );
            e.printStackTrace();
        }
        return result;
    }

    public User getUserData(String identifiant,String mdp){
        User user = new User();
        String[] param = {identifiant,mdp};

        Cursor cursor = db.query(DatabaseHandler.TABLE_USER,
                null, DatabaseHandler.KEY_USER_LOGIN + "= ? AND "+DatabaseHandler.KEY_USER_PASSWORD + "= ?", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            user = cursorToUser(cursor);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return user;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId_user(cursor.getInt(0));
        user.setLogin_user(cursor.getString(1));
        user.setPwd_user(cursor.getString(2));
        user.setAdmin(cursor.getInt(3));
        return user;
    }
}
