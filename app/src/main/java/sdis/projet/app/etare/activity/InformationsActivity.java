package sdis.projet.app.etare.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import sdis.projet.app.etare.R;
import sdis.projet.app.etare.adapter.AccesAdapter;
import sdis.projet.app.etare.adapter.ContactsAdapter;
import sdis.projet.app.etare.dao.AccessSpotDAO;
import sdis.projet.app.etare.dao.ContactDAO;
import sdis.projet.app.etare.dao.EtareDAO;
import sdis.projet.app.etare.dao.LocationDAO;
import sdis.projet.app.etare.dao.TownDAO;
import sdis.projet.app.etare.model.AccessSpot;
import sdis.projet.app.etare.model.Contact;
import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.model.Location;

/*******************************************************************************
 * Created by Alexandre Foucaud on 25/05/20 16:55
 * Last modified 25/05/20 14:35
 ******************************************************************************/

public class InformationsActivity extends AppCompatActivity {

    ContactDAO contactDAO;
    AccessSpotDAO accessSpotDAO;
    LocationDAO locationDAO;
    EtareDAO etareDAO;
    TownDAO townDAO;

    ETARE etare = new ETARE();
    Location location = new Location();

    Button btn_ajout_acces;
    ImageButton imgbtn_ajout_acces;

    Button btn_ajout_contact;
    ImageButton imgbtn_ajout_contact;

    ArrayList<AccessSpot> accessSpotArrayList;
    ArrayList<AccessSpot> accessSpotArrayListNoSave;
    RecyclerView.Adapter accessSpotAdapter;

    ArrayList<Contact> contactArrayList;
    ArrayList<Contact> contactArrayListNoSave;
    RecyclerView.Adapter contactAdapter;

    AccessSpot accessSpot;
    Contact contact;

    SharedPreferences pref;
    int id_etare;

    // Variables pour les données utilisateurs
    String user;
    int droit_user;

    // TextView pour l'affichage du nom d'utilisateur
    TextView username;


    EditText et_nom;
    EditText et_adresse;
    AutoCompleteTextView et_commune;
    Spinner level_etare;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        contactDAO = new ContactDAO(this);
        contactDAO.open();

        accessSpotDAO = new AccessSpotDAO(this);
        accessSpotDAO.open();

        etareDAO = new EtareDAO(this);
        etareDAO.open();

        locationDAO = new LocationDAO(this);
        locationDAO.open();

        townDAO = new TownDAO(this);
        townDAO.open();


        et_nom = findViewById(R.id.et_nom);
        et_adresse = findViewById(R.id.et_adresse);
        et_commune = findViewById(R.id.et_commune);
        level_etare = findViewById(R.id.spinner_niveau_etare);

        Button btn_infos_gen = findViewById(R.id.btn_info_gene);
        Button btn_connaissance = findViewById(R.id.btn_connaissance);
        Button btn_moyen_de_secours = findViewById(R.id.btn_moyens_de_secours);
        Button btn_dangers = findViewById(R.id.btn_dangers);
        Button btn_acces = findViewById(R.id.btn_acces);
        Button btn_priorite = findViewById(R.id.btn_priorite);
        Button btn_analyse = findViewById(R.id.btn_analyse);
        Button btn_consignes = findViewById(R.id.btn_consignes);
        Button btn_media = findViewById(R.id.btn_media);

        btn_ajout_acces = findViewById(R.id.btn_ajout_acces);
        imgbtn_ajout_acces = findViewById(R.id.imageButton_ajout_acces);
        btn_ajout_contact = findViewById(R.id.btn_ajout_contact);
        imgbtn_ajout_contact = findViewById(R.id.imageButton_ajout_contact);

        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);
        id_etare = pref.getInt("id_etare",0);

        //Récupération des données de l'utilisateur
        user = pref.getString("identifiant_user","User");
        droit_user = pref.getInt("droit_user",0);

        // Définit le champ texte avec le nom de l'utilisateur
        username= findViewById(R.id.tvNomPrenom);
        username.setText(user);

        Log.d("TAG", "Information activity id_etare: "+ id_etare);


        et_nom.setText(locationDAO.getLocationById(etareDAO.getIdLocationByIdEtare(id_etare)).getName_location());
        et_adresse.setText(locationDAO.getLocationById(etareDAO.getIdLocationByIdEtare(id_etare)).getAddress_location());
        et_commune.setText(townDAO.getTownDataById(etareDAO.getEtareDataById(id_etare).getId_town()).getName_town());
        level_etare.setSelection(etareDAO.getEtareDataById(id_etare).getId_level()-1);

        ArrayAdapter<String> adapterTown = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,townDAO.getAllNameTown());
        et_commune.setAdapter(adapterTown);

        accessSpotArrayList = new ArrayList<>();
        for(AccessSpot a: accessSpotDAO.getAccessSpotByEtareId(id_etare)){
            accessSpot = new AccessSpot(a.getCommentary_access(),a.getLocation_access());
            accessSpotArrayList.add(accessSpot);
            Log.d("TAG", "Accès: location : "+a.getLocation_access()+"\t commentaire:"+a.getCommentary_access());

        }

        contactArrayList = new ArrayList<>();
        for(Contact c : contactDAO.getContactVisitByEtareId(id_etare)){
            contact = new Contact(c.getName_contact(),c.getJob_contact(),c.getPhone_contact(),c.getMobilephone_contact(),c.getEmail_contact());
            contactArrayList.add(contact);
            Log.d("TAG", "Contact : Name : " +c.getName_contact()+ "\t job : "+c.getJob_contact()+ "\t phone : "+c.getPhone_contact()+ "\t mobile : "+c.getMobilephone_contact()+ "\t email : "+c.getEmail_contact());

        }



        RecyclerView.LayoutManager LayoutManagerAccessSpot = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        accessSpotAdapter= new AccesAdapter(this,accessSpotArrayList);
        RecyclerView mRecyclerViewAcces = findViewById(R.id.recycler_view_acces);
        mRecyclerViewAcces.setHasFixedSize(true);
        mRecyclerViewAcces.setLayoutManager(LayoutManagerAccessSpot);
        mRecyclerViewAcces.setAdapter(accessSpotAdapter);

        RecyclerView.LayoutManager LayoutManagerContact = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        contactAdapter = new ContactsAdapter(this,contactArrayList);
        RecyclerView mRecyclerViewContact = findViewById(R.id.recycler_view_personne);
        mRecyclerViewContact.setHasFixedSize(true);
        mRecyclerViewContact.setLayoutManager(LayoutManagerContact);
        mRecyclerViewContact.setAdapter(contactAdapter);

        accessSpotAdapter.notifyDataSetChanged();
        contactAdapter.notifyDataSetChanged();

        location = locationDAO.getLocationById(etareDAO.getIdLocationByIdEtare(id_etare));
        etare = etareDAO.getEtareDataById(id_etare);

        et_nom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(et_nom.getText().toString().equals("")){
                    et_nom.setBackgroundResource(R.drawable.edittext_border_color_error);
                    et_nom.setError("Vous devez renseigner le nom de l'ETARE !");
                }else{
                    et_nom.setBackgroundResource(R.drawable.edittext_border_color);
                    et_nom.setError(null);
                    location.setName_location(String.valueOf(et_nom.getText()));
                    locationDAO.updateLocation(location);
                }
            }
        });

        et_adresse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(et_adresse.getText().toString().equals("")){
                    et_adresse.setBackgroundResource(R.drawable.edittext_border_color_error);
                    et_adresse.setError("Vous devez renseigner l'adresse de l'ETARE !");
                }else{
                    et_adresse.setBackgroundResource(R.drawable.edittext_border_color);
                    et_adresse.setError(null);
                    location.setAddress_location(String.valueOf(et_adresse.getText()));
                    locationDAO.updateLocation(location);
                }
            }
        });

        et_commune.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int value_id_town = townDAO.getIdTownByName(et_commune.getText().toString());
                if(et_commune.getText().toString().equals("")){
                    et_commune.setBackgroundResource(R.drawable.edittext_border_color_error);
                    et_commune.setError("Vous devez renseigner la commune sur laquelle se trouve l'ETARE !");
                }else if (value_id_town == -1) {
                    et_commune.setBackgroundResource(R.drawable.edittext_border_color_error);
                    et_commune.setError("Attention, vous avez mal orthographié le nom de la commune");

                }else{
                        et_commune.setBackgroundResource(R.drawable.edittext_border_color);
                        et_commune.setError(null);
                        etare.setId_town(townDAO.getIdTownByName(et_commune.getText().toString()));
                        etareDAO.updateEtare(etare);
                }
            }
        });


        accessSpotArrayListNoSave = new ArrayList<>();
        btn_ajout_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessSpotArrayListNoSave.add(new AccessSpot("",""));
                accessSpotArrayList.addAll(accessSpotArrayListNoSave);
                for(AccessSpot a: accessSpotArrayListNoSave){
                    accessSpotDAO.createAccessSpot(a.getCommentary_access(),a.getLocation_access(),id_etare);
                }
                accessSpotAdapter.notifyDataSetChanged();
                accessSpotArrayListNoSave.clear();
            }
        });

        imgbtn_ajout_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accessSpotArrayListNoSave.add(new AccessSpot("",""));
                accessSpotArrayList.addAll(accessSpotArrayListNoSave);
                for(AccessSpot a: accessSpotArrayListNoSave){
                    Log.d("TAG", "onClick: "+a.getCommentary_access()+"           "+a.getLocation_access());
                    accessSpotDAO.createAccessSpot(a.getCommentary_access(),a.getLocation_access(),id_etare);
                }
                accessSpotArrayListNoSave.clear();
                accessSpotAdapter.notifyDataSetChanged();

            }
        });


        contactArrayListNoSave = new ArrayList<>();
        btn_ajout_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactArrayListNoSave.add(new Contact("","","","",""));
                contactArrayList.addAll(contactArrayListNoSave);
                contactAdapter.notifyDataSetChanged();
            }
        });

        imgbtn_ajout_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactArrayListNoSave.add(new Contact("","","","",""));
                contactArrayList.addAll(contactArrayListNoSave);
                contactAdapter.notifyDataSetChanged();
            }
        });



        // Définition des Buttons avec leurs correspondances sur l'interface
        Button btn_sauvegarder = findViewById(R.id.btn_sauvegarder);
        ImageButton imageButton_sauvegarder = findViewById(R.id.imgSauvegarder);

        // Déclaration et Ajout de Listeners sur le bouton "Sauvegarder et Quitter"
        btn_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETARE etare = etareDAO.getEtareDataById(id_etare);
                etare.setUpdate_date(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
                etare.setStatus("A Valider");
                etareDAO.updateEtare(etare);
                id_etare = 0;
                Intent intent = new Intent(InformationsActivity.this, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imageButton_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETARE etare = etareDAO.getEtareDataById(id_etare);
                etare.setUpdate_date(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
                etare.setStatus("A Valider");
                etareDAO.updateEtare(etare);
                id_etare = 0;
                Intent intent = new Intent(InformationsActivity.this, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        });



        btn_connaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, ConnaissanceLieuActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btn_moyen_de_secours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, MoyenDeSecoursActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_dangers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, DangersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, AccesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_priorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, PrioriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_analyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, AnalyseRisqueActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_consignes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, ConsignesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationsActivity.this, MediaActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
