package sdis.projet.app.etare.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sdis.projet.app.etare.adapter.AccesAdapter;
import sdis.projet.app.etare.adapter.ContactsAdapter;
import sdis.projet.app.etare.R;
import sdis.projet.app.etare.dao.AccessSpotDAO;
import sdis.projet.app.etare.dao.CategoryDAO;
import sdis.projet.app.etare.dao.ContactDAO;
import sdis.projet.app.etare.dao.EtareDAO;
import sdis.projet.app.etare.dao.LevelDAO;
import sdis.projet.app.etare.dao.LocationDAO;
import sdis.projet.app.etare.dao.TownDAO;
import sdis.projet.app.etare.model.AccessSpot;
import sdis.projet.app.etare.model.Contact;
import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.model.Location;

public class CreationActivity extends AppCompatActivity {

    /** Déclaration des variables **/

    // Variables pour le dialogue avec la BDD
    AccessSpotDAO accessSpotDAO;
    EtareDAO etareDAO;
    LocationDAO locationDAO;
    LevelDAO levelDAO;
    TownDAO townDAO;
    ContactDAO contactDAO;
    CategoryDAO categoryDAO;

    // Variables pour la création de model de donnée
    AccessSpot accessSpot;
    Contact contact;

    // Tableau de données Acces
    ArrayList<AccessSpot> accessSpotArrayList;
    // RecyclerView Adapter pour les Acces
    RecyclerView.Adapter accessSpotAdapter;

    // Tableau de données Contact
    ArrayList<Contact> contactArrayList;
    // RecyclerView Adapter pour les Contacts
    RecyclerView.Adapter contactAdapter;


    Button btn_bloc_note;
    ImageButton imageButton_bloc_note;

    //Button pour retourner à la liste des ETARE
    Button btn_liste_etare;

    // Buttons pour ajouter un nouvel accès
    Button btn_ajout_acces;
    ImageButton imgbtn_ajout_acces;

    // Buttons pour ajouter un nouveau contact
    Button btn_ajout_contact;
    ImageButton imgbtn_ajout_contact;

    // Button de création de la fiche ETARE
    Button btn_creation_etare;

    //Spinner du choix du niveau d'ETARE
    Spinner spinner_niveau_etare;

    // EditText pour le nom et l'adresse de l'ETARE
    EditText et_nom;
    EditText et_adresse;

    // AutoCompleteTextView pour la commune sur laquelle se trouve l'ETARE
    AutoCompleteTextView et_commune;

    // Variables pour les données utilisateurs
    SharedPreferences pref;
    String user;
    int droit_user;

    // TextView pour l'affichage du nom d'utilisateur
    TextView username;

    /** Fonction appelé a la création de l'activité Creation **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        // Définition de etareDAO et ouverture en lecture/écriture de la BDD
        etareDAO =new EtareDAO(this);
        etareDAO.open();

        // Définition de categoryDAO et ouverture en lecture/écriture de la BDD
        categoryDAO = new CategoryDAO(this);
        categoryDAO.open();

        // Définition de levelDAO et ouverture en lecture/écriture de la BDD
        levelDAO= new LevelDAO(this);
        levelDAO.open();

        // Définition de townDAO et ouverture en lecture/écriture de la BDD
        townDAO= new TownDAO(this);
        townDAO.open();

        // Définition de locationDAO et ouverture en lecture/écriture de la BDD
        locationDAO = new LocationDAO(this);
        locationDAO.open();

        // Définition de accessSpotDAO et ouverture en lecture/écriture de la BDD
        accessSpotDAO = new AccessSpotDAO(this);
        accessSpotDAO.open();

        // Définition de contactDAO et ouverture en lecture/écriture de la BDD
        contactDAO = new ContactDAO(this);
        contactDAO.open();

        //Récupération des données de l'utilisateur
        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);
        user = pref.getString("identifiant_user","User");
        droit_user = pref.getInt("droit_user",0);

        // Définit le champ texte avec le nom de l'utilisateur
        username= findViewById(R.id.tvNomPrenom);
        username.setText(user);

        // Définition des tableaux Acces et Contact en ArrayList
        accessSpotArrayList = new ArrayList<>();
        contactArrayList = new ArrayList<>();

        // Définition par défaut d'un Acces vide et d'un Contact vide
        accessSpot = new AccessSpot("","");
        contact = new Contact("","","","","");

        // Définition des buttons d'ajout d'acces avec sa correspondance sur l'interface
        btn_ajout_acces = findViewById(R.id.btn_ajout_acces);
        imgbtn_ajout_acces = findViewById(R.id.imageButton_ajout_acces);

        // Définition des buttons d'ajout de contact avec sa correspondance sur l'interface
        btn_ajout_contact = findViewById(R.id.btn_ajout_contact);
        imgbtn_ajout_contact = findViewById(R.id.imageButton_ajout_contact);

        // Définition du button de creation de l'ETARE avec sa correspondance sur l'interface
        btn_creation_etare =findViewById(R.id.button_creation_etare);

        // Définition du button pour retourner à la liste des ETARE avec sa correspondance sur l'interface
        btn_liste_etare = findViewById(R.id.btn_liste_etare);

        // Définition du spinner pour le choix du niveau d'ETARE avec sa correspondance sur l'interface
        spinner_niveau_etare = findViewById(R.id.spinner_niveau_etare);

        // Définition des EditText avec leurs correspondance sur l'interface
        et_nom = findViewById(R.id.et_nom);
        et_adresse = findViewById(R.id.et_adresse);
        et_commune = findViewById(R.id.et_commune);

        btn_bloc_note = findViewById(R.id.btn_bloc_note);
        imageButton_bloc_note = findViewById(R.id.imageButton_bloc_note);

        // Définition du RecyclerView des Accès afin de faire l'affichage horizontalement
        RecyclerView.LayoutManager LayoutManagerAccessSpot = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        accessSpotAdapter= new AccesAdapter(this,accessSpotArrayList);
        RecyclerView mRecyclerViewAcces = findViewById(R.id.recycler_view_acces);
        mRecyclerViewAcces.setHasFixedSize(true);
        mRecyclerViewAcces.setLayoutManager(LayoutManagerAccessSpot);
        mRecyclerViewAcces.setAdapter(accessSpotAdapter);

        // Définition du RecyclerView des Contact afin de faire l'affichage horizontalement
        RecyclerView.LayoutManager LayoutManagerContact = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        contactAdapter = new ContactsAdapter(this,contactArrayList);
        RecyclerView mRecyclerViewContact = findViewById(R.id.recycler_view_personne);
        mRecyclerViewContact.setHasFixedSize(true);
        mRecyclerViewContact.setLayoutManager(LayoutManagerContact);
        mRecyclerViewContact.setAdapter(contactAdapter);

        // Ajout de l'acces vide et notification du changement des données
        accessSpotArrayList.add(accessSpot);
        accessSpotAdapter.notifyDataSetChanged();

        // Ajout du contact vide et notification du changement des données
        contactArrayList.add(contact);
        contactAdapter.notifyDataSetChanged();

        // Définition d'un adapter pour l'AutoCompleteTextView
        ArrayAdapter<String> adapterTown = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,townDAO.getAllNameTown());
        et_commune.setAdapter(adapterTown);

        // Ajout d'un Listener sur le bouton de création
        btn_creation_etare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ajout d'un Listener sur l'EditText du nom de l'ETARE
                et_nom.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // Set le background normal de l'EditText
                        et_nom.setBackgroundResource(R.drawable.edittext_border_color);
                    }
                });

                // Ajout d'un Listener sur l'EditText de l'adresse de l'ETARE
                et_adresse.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // Set le background normal de l'EditText
                        et_adresse.setBackgroundResource(R.drawable.edittext_border_color);
                    }
                });

                // Ajout d'un Listener sur l'EditText de la commune de l'ETARE
                et_commune.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // Set le background normal de l'EditText
                        et_commune.setBackgroundResource(R.drawable.edittext_border_color);
                    }
                });

                // Verification pour voir si le nom de l'ETARE n'est pas vide
                if(et_nom.getText().toString().equals("")){
                    // Si l'EditText est vide, set background avec border rouge et set message erreur
                    et_nom.setBackgroundResource(R.drawable.edittext_border_color_error);
                    et_nom.setError("Vous devez renseigner le nom de l'ETARE !");
                }// Verification pour voir si l'adresse de l'ETARE n'est pas vide
                else if(et_adresse.getText().toString().equals("")){
                    // Si l'EditText est vide, set background avec border rouge et set message erreur
                    et_adresse.setBackgroundResource(R.drawable.edittext_border_color_error);
                    et_adresse.setError("Vous devez renseigner l'adresse de l'ETARE !");
                }// Verification pour voir si le nom de la commune de l'ETARE n'est pas vide
                else if(et_commune.getText().toString().equals("")){
                    // Si l'EditText est vide, set background avec border rouge et set message erreur
                    et_commune.setBackgroundResource(R.drawable.edittext_border_color_error);
                    et_commune.setError("Vous devez renseigner la commune sur laquelle se trouve l'ETARE !");
                }
                else{
                    // Création d'un tuple dans la table Location
                    locationDAO.createLocation(et_nom.getText().toString(),et_adresse.getText().toString(),"","","","","","","");

                    // Récupération de l'id de la table Town avec le Nom renseigné dans l'EditText
                    int value_id_town = townDAO.getIdTownByName(et_commune.getText().toString());
                    // Verification pour voir s'il n'y a pas un retour négatif ce qui signifie une erreur
                    if (value_id_town == -1){
                        //Affiche un dialog pour indiquer que la commune est mal orthographié
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("Attention, vous avez mal orthographié le nom de la commune")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                    }else{
                        // Création d'un tuple dans la table Location
                        etareDAO.createEtare(new SimpleDateFormat("dd/MM/YYYY").format(new Date()),new SimpleDateFormat("dd/MM/YYYY").format(new Date()),"A Traiter",spinner_niveau_etare.getSelectedItem().toString(), String.valueOf(value_id_town), String.valueOf(locationDAO.getIdLocationByName(et_nom.getText().toString())));
                        // Récupération de l'id de l'etare avec l'id Location
                        int id_etare = etareDAO.getIdEtareByIdLocation(locationDAO.getIdLocationByName(et_nom.getText().toString()));

                        // Pour tout Contact de la ArrayList contact alors on ajoute ce contact a la BDD
                        for(Contact c:contactArrayList){
                            contactDAO.createContact(c.getName_contact(),c.getJob_contact(),c.getPhone_contact(),c.getMobilephone_contact(),c.getEmail_contact(),id_etare,0);

                        }
                        // Pour tout Acces de la ArrayList acces alors on ajoute cet acces a la BDD
                        for(AccessSpot a:accessSpotArrayList){
                            accessSpotDAO.createAccessSpot(a.getCommentary_access(),a.getLocation_access(),id_etare);
                        }

                        // Création des catégories Accès, Moyens de Secours, Dangers et Priorité de sauvegarde, correspondant a l'ETARE
                        categoryDAO.createCategory("Accès","",id_etare);
                        categoryDAO.createCategory("Moyens de Secours","",id_etare);
                        categoryDAO.createCategory("Dangers","",id_etare);
                        categoryDAO.createCategory("Priorité de Sauvegarde","",id_etare);


                        // Permet de quitter l'activité de création et affiche la liste des ETARE
                        Intent intent = new Intent(CreationActivity.this, ListeActivity.class);
                        startActivity(intent);
                        finish();
                    }


                }
            }
        });

        // Ajout d'un Listener sur le bouton de bloc note
        btn_bloc_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setView(R.layout.notes_layout)
                        .setPositiveButton("Sauvegarder", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText et_bloc_note = ((AlertDialog)dialog).findViewById(R.id.et_bloc_note);
                                Log.d("TAG", et_bloc_note.getText().toString());
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        // Ajout d'un Listener sur le bouton d'ajout d'acces
        btn_ajout_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessSpotArrayList.add(new AccessSpot("",""));
                accessSpotAdapter.notifyDataSetChanged();
            }
        });
        // Ajout d'un Listener sur l'image bouton d'ajout d'acces
        imgbtn_ajout_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessSpotArrayList.add(new AccessSpot("",""));
                accessSpotAdapter.notifyDataSetChanged();
            }
        });


        // Ajout d'un Listener sur le bouton d'ajout de contact
        btn_ajout_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactArrayList.add(new Contact("","","","",""));
                contactAdapter.notifyDataSetChanged();
            }
        });
        // Ajout d'un Listener sur l'image bouton d'ajout de contact
        imgbtn_ajout_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactArrayList.add(new Contact("","","","",""));
                contactAdapter.notifyDataSetChanged();
            }
        });

        // Ajout d'un Listener sur le bouton pour retourner a la liste des ETARE
        btn_liste_etare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreationActivity.this, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
