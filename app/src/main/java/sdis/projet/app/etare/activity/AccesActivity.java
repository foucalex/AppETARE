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
import sdis.projet.app.etare.dao.CategoryDAO;
import sdis.projet.app.etare.dao.EtareDAO;
import sdis.projet.app.etare.model.Category;
import sdis.projet.app.etare.model.ETARE;


/*******************************************************************************
 * Created by Alexandre on 26/05/20 14:55
 * Last modified 26/05/20 14:54
 ******************************************************************************/

/**
 * Constructeur de l'activité Accès
 */
public class AccesActivity extends AppCompatActivity {


    /** EditText pour le champ texte commentaire de l'onglet ACCES**/
    EditText et_commentaire_acces;


    /**Variable pour la récupération de l'espace de stockage de l'application **/
    SharedPreferences pref;

    /** Variable de récupération de l'id de l'ETARE**/
    int id_etare;

     /** Variable pour le nom de la catégorie**/
    String categoryName;

    /** Variables pour le dialogue avec la table Category de la BDD**/
    CategoryDAO categoryDAO;

    /** Variables pour le dialogue avec la table ETARE de la BDD**/
    EtareDAO etareDAO;

    /** Variable pour le modèle de données Category**/
    Category category ;

     /** Variables pour stocker le nom de l'utilisateur**/
    String user;

    /** Variable pour la définiton des droits utilisateur**/
    int droit_user;

     /** TextView pour l'affichage du nom d'utilisateur**/
    TextView username;

    /** Fonction appelé a la création de l'activité Acces **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acces);

        /** Définition de categoryDAO et ouverture en lecture/écriture de la BDD**/
        categoryDAO = new CategoryDAO(this);
        categoryDAO.open();

        /** Définition de etareDAO et ouverture en lecture/écriture de la BDD**/
        etareDAO = new EtareDAO(this);
        etareDAO.open();

        /** Définition du nom de la catégorie (Utile pour la modification en base)**/
        categoryName = "Accès";

        /** Définition de l'EditText avec sa correspondance sur l'interface**/
        et_commentaire_acces = findViewById(R.id.et_commentaire_acces);

        /** Récupération de l'id de l'ETARE séléctionné**/
        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);
        id_etare = pref.getInt("id_etare",0);

        /**Récupération des données de l'utilisateur**/
        user = pref.getString("identifiant_user","User");
        droit_user = pref.getInt("droit_user",0);

        /** Définit le champ texte avec le nom de l'utilisateur**/
        username= findViewById(R.id.tvNomPrenom);
        username.setText(user);

        /** Récupération des données de category en fonction de l'id et de la catégorie**/
        category = categoryDAO.getCategoryDataByEtareIdAndCategory(id_etare,categoryName);

        /** Définit le champ texte avec la valeur connu en BDD**/
        et_commentaire_acces.setText(category.getCommentary_category());

        /** Ajout d'un Listener sur l'EditText**/
        et_commentaire_acces.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /** Après le changement de texte, définit le commentaire de l'accès avec le texte présent et actualise la base de données avec cette valeur**/
                category.setCommentary_category(String.valueOf(et_commentaire_acces.getText()));
                categoryDAO.updateCategory(category);

            }
        });

        /** Définition des Buttons avec leurs correspondances sur l'interface**/
        Button btn_sauvegarder = findViewById(R.id.btn_sauvegarder);
        ImageButton imageButton_sauvegarder = findViewById(R.id.imgSauvegarder);

        /** Déclaration et Ajout de Listeners sur le bouton "Sauvegarder et Quitter"**/
        btn_sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETARE etare = etareDAO.getEtareDataById(id_etare);
                etare.setUpdate_date(new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
                etare.setStatus("A Valider");
                etareDAO.updateEtare(etare);
                id_etare = 0;
                Intent intent = new Intent(AccesActivity.this, ListeActivity.class);
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
                Intent intent = new Intent(AccesActivity.this, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /**Déclaration et ajout de listener des buttons de navigation lors de la modification des données de l'ETARE**/
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
                Intent intent = new Intent(AccesActivity.this, InformationsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_connaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesActivity.this, ConnaissanceLieuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_moyen_de_secours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesActivity.this, MoyenDeSecoursActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_dangers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesActivity.this, DangersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Category c: categoryDAO.getAllCategory()){
                    Log.d("TAG", "onClick: "+c.getId_category()+" "+c.getName_category()+" "+c.getCommentary_category()+" "+c.getId_etare());
                }
            }
        });

        btn_priorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesActivity.this, PrioriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_analyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesActivity.this, AnalyseRisqueActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_consignes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesActivity.this, ConsignesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccesActivity.this, MediaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
