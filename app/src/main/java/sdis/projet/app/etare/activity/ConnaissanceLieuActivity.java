package sdis.projet.app.etare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import sdis.projet.app.etare.R;
import sdis.projet.app.etare.dao.EtareDAO;
import sdis.projet.app.etare.dao.LocationDAO;
import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.model.Location;

/*******************************************************************************
 * Created by Alexandre on $file.getCreationDate in AppETARE
 * Last modified 25/05/20 17:02
 ******************************************************************************/

public class ConnaissanceLieuActivity extends AppCompatActivity {

    /** Déclaration des variables **/

    // Variables de récupération de l'id de l'ETARE
    SharedPreferences pref;
    int id_etare;

    // Variables pour le dialogue avec la BDD
    LocationDAO locationDAO;
    EtareDAO etareDAO;
    Location location;

    // EditText pour les champs texte activité du lieu, capacité du lieu, infrastructure, et raison de classement
    EditText et_activity_location;
    EditText et_capacity_location;
    EditText et_infrastructure_location;
    EditText et_classification_reason;

    // Variables pour les données utilisateurs
    String user;
    int droit_user;

    // TextView pour l'affichage du nom d'utilisateur
    TextView username;

    /** Fonction appelé a la création de l'activité ConnaissanceLieu **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connaissance_lieu);

        // Définition de LocationDAO et ouverture en lecture/écriture de la BDD
        locationDAO = new LocationDAO(this);
        locationDAO.open();

        // Définition de EtareDAO et ouverture en lecture/écriture de la BDD
        etareDAO = new EtareDAO(this);
        etareDAO.open();

        // Récupération de l'id de l'ETARE séléctionné
        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);
        id_etare = pref.getInt("id_etare",0);

        //Récupération des données de l'utilisateur
        user = pref.getString("identifiant_user","User");
        droit_user = pref.getInt("droit_user",0);

        // Définit le champ texte avec le nom de l'utilisateur
        username= findViewById(R.id.tvNomPrenom);
        username.setText(user);

        // Récupération des données du lieu (location) avec l'id de l'ETARE
        location = locationDAO.getLocationById(etareDAO.getIdLocationByIdEtare(id_etare));

        // Définition des EditText avec leurs correspondance sur l'interface
        et_activity_location =findViewById(R.id.et_activity_location);
        et_capacity_location=findViewById(R.id.et_capacity_location);
        et_infrastructure_location=findViewById(R.id.et_infrastructure_location);
        et_classification_reason=findViewById(R.id.et_classification_reason);

        // Définit les champs texte avec la valeur correspondante connu en BDD
        et_activity_location.setText(location.getActivity_location());
        et_capacity_location.setText(location.getCapacity_location());
        et_infrastructure_location.setText(location.getInfrastructure_location());
        et_classification_reason.setText(location.getClassification_reason_location());

        // Ajout des Listener sur chaque EditText
        et_activity_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Après le changement de texte, définit le texte présent en base avec celui de l'EditTExt
                location.setActivity_location(String.valueOf(et_activity_location.getText()));
                locationDAO.updateLocation(location);

            }
        });
        et_capacity_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Après le changement de texte, définit le texte présent en base avec celui de l'EditTExt
                location.setCapacity_location(String.valueOf(et_capacity_location.getText()));
                locationDAO.updateLocation(location);
            }
        });
        et_infrastructure_location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Après le changement de texte, définit le texte présent en base avec celui de l'EditTExt
                location.setInfrastructure_location(String.valueOf(et_infrastructure_location.getText()));
                locationDAO.updateLocation(location);
            }
        });
        et_classification_reason.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Après le changement de texte, définit le texte présent en base avec celui de l'EditTExt
                location.setClassification_reason_location(String.valueOf(et_classification_reason.getText()));
                locationDAO.updateLocation(location);
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
                Intent intent = new Intent(ConnaissanceLieuActivity.this, ListeActivity.class);
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
                Intent intent = new Intent(ConnaissanceLieuActivity.this, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btn_infos_gen = findViewById(R.id.btn_info_gene);
        Button btn_connaissance = findViewById(R.id.btn_connaissance);
        Button btn_moyen_de_secours = findViewById(R.id.btn_moyens_de_secours);
        Button btn_dangers = findViewById(R.id.btn_dangers);
        Button btn_acces = findViewById(R.id.btn_acces);
        Button btn_priorite = findViewById(R.id.btn_priorite);
        Button btn_analyse = findViewById(R.id.btn_analyse);
        Button btn_consignes = findViewById(R.id.btn_consignes);
        Button btn_media = findViewById(R.id.btn_media);

        btn_infos_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, InformationsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_connaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Location l:locationDAO.getAllLocation()){
                    Log.d("TAG", "All Location : id : "+ l.getId_location() + "\t name : "+l.getName_location()+ "\t addr : "+l.getAddress_location()+ "\t activity : "+l.getActivity_location()+ "\t capacity : "+l.getCapacity_location()+ "\t reason : "+l.getClassification_reason_location()+ "\t infra : "+l.getInfrastructure_location());

                }
            }
        });

        btn_moyen_de_secours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, MoyenDeSecoursActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_dangers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, DangersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, AccesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_priorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, PrioriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_analyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, AnalyseRisqueActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_consignes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, ConsignesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConnaissanceLieuActivity.this, MediaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
