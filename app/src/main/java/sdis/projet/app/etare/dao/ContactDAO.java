package sdis.projet.app.etare.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.model.Contact;
import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.database.DatabaseHandler;

import static java.lang.String.valueOf;

public class ContactDAO {

    private SQLiteDatabase db;
    private DatabaseHandler dbHelper;
    private String[] allColumns = { DatabaseHandler.KEY_CONTACT_ID,
            DatabaseHandler.KEY_CONTACT_NAME,
            DatabaseHandler.KEY_CONTACT_JOB,
            DatabaseHandler.KEY_CONTACT_PHONE,
            DatabaseHandler.KEY_CONTACT_MOBILEPHONE,
            DatabaseHandler.KEY_CONTACT_EMAIL,
            DatabaseHandler.KEY_CONTACT_ETARE,
            DatabaseHandler.KEY_CONTACT_URGENCE

    };

    public ContactDAO(Context context){
        dbHelper = new DatabaseHandler(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public List<Contact> getAllContact() {
        List<Contact> contacts = new ArrayList<>();

        Cursor cursor = db.query(DatabaseHandler.TABLE_CONTACT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = cursorToContact(cursor);
            contacts.add(contact);
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }


    public Contact createContact(String name_contact, String job_contact, String phone_contact, String mobilephone_contact, String email_contact, int id_etare, int urgence){
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CONTACT_NAME,name_contact);
        values.put(DatabaseHandler.KEY_CONTACT_JOB,job_contact);
        values.put(DatabaseHandler.KEY_CONTACT_PHONE,phone_contact);
        values.put(DatabaseHandler.KEY_CONTACT_MOBILEPHONE,mobilephone_contact);
        values.put(DatabaseHandler.KEY_CONTACT_EMAIL,email_contact);
        values.put(DatabaseHandler.KEY_CONTACT_ETARE,id_etare);
        values.put(DatabaseHandler.KEY_CONTACT_URGENCE, urgence);
        int insertId = (int) db.insert(DatabaseHandler.TABLE_CONTACT, null,
                values);
        Cursor cursor = db.query(DatabaseHandler.TABLE_CONTACT,
                allColumns, DatabaseHandler.KEY_CONTACT_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Contact newContact = cursorToContact(cursor);
        cursor.close();
        return newContact;

    }
    public int updateContact(Contact contact){
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CONTACT_NAME,contact.getName_contact());
        values.put(DatabaseHandler.KEY_CONTACT_JOB,contact.getJob_contact());
        values.put(DatabaseHandler.KEY_CONTACT_PHONE,contact.getPhone_contact());
        values.put(DatabaseHandler.KEY_CONTACT_MOBILEPHONE,contact.getMobilephone_contact());
        values.put(DatabaseHandler.KEY_CONTACT_EMAIL,contact.getEmail_contact());
        values.put(DatabaseHandler.KEY_CONTACT_ETARE,contact.getId_etare());
        values.put(DatabaseHandler.KEY_CONTACT_URGENCE,contact.getUrgence());
        db.update(DatabaseHandler.TABLE_CONTACT,values, DatabaseHandler.KEY_CONTACT_ID +" = "+contact.getId_contact(),null);

        db.close();
        close();
        return 0;
    }


    public void deleteEtare(Contact contact) {
        open();
        int id = contact.getId_contact();
        System.out.println("Comment deleted with id: " + id);
        db.delete(DatabaseHandler.TABLE_CONTACT, DatabaseHandler.KEY_CONTACT_ID
                + " = " + id, null);
        close();
    }

    public List<Contact> getContactVisitByEtareId(int id) {
        List<Contact> contacts = new ArrayList<>();
        String[] param = {valueOf(id)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_CONTACT,
                allColumns, DatabaseHandler.KEY_CONTACT_ETARE + "=? AND "+DatabaseHandler.KEY_CONTACT_URGENCE+"= 0", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = cursorToContact(cursor);
            contacts.add(contact);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return contacts;
    }

    public List<Contact> getContactUrgenceByEtareId(int id) {
        List<Contact> contacts = new ArrayList<>();
        String[] param = {valueOf(id)};

        Cursor cursor = db.query(DatabaseHandler.TABLE_CONTACT,
                allColumns, DatabaseHandler.KEY_CONTACT_ETARE + "=? AND "+DatabaseHandler.KEY_CONTACT_URGENCE+"= 1", param, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact = cursorToContact(cursor);
            contacts.add(contact);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return contacts;
    }


    private Contact cursorToContact(Cursor cursor) {
        Contact contact = new Contact();
        contact.setId_contact(cursor.getInt(0));
        contact.setName_contact(cursor.getString(1));
        contact.setJob_contact(cursor.getString(2));
        contact.setPhone_contact(cursor.getString(3));
        contact.setMobilephone_contact(cursor.getString(4));
        contact.setEmail_contact(cursor.getString(5));
        contact.setId_etare(cursor.getInt(6));
        contact.setUrgence(cursor.getInt(7));
        return contact;
    }
}
