package sdis.projet.app.etare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
 * Created by Alexandre Foucaud on 25/05/20 16:55
 * Last modified 25/05/20 14:35
 ******************************************************************************/

public class PrioriteActivity extends AppCompatActivity  {

    EditText et_commentaire_priorite_sauvegarde;

    SharedPreferences pref;
    int id_etare;

    // Variables pour les données utilisateurs
    String user;
    int droit_user;

    // TextView pour l'affichage du nom d'utilisateur
    TextView username;

    String categoryName;
    CategoryDAO categoryDAO;
    EtareDAO etareDAO;

    Category category ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priorite);

        categoryDAO = new CategoryDAO(this);
        categoryDAO.open();

        etareDAO = new EtareDAO(this);
        etareDAO.open();

        categoryName = "Priorité de Sauvegarde";
        et_commentaire_priorite_sauvegarde = findViewById(R.id.et_commentaire_priorite_sauvegarde);

        Button btn_infos_gen = findViewById(R.id.btn_info_gene);
        Button btn_connaissance = findViewById(R.id.btn_connaissance);
        Button btn_moyen_de_secours = findViewById(R.id.btn_moyens_de_secours);
        Button btn_dangers = findViewById(R.id.btn_dangers);
        Button btn_acces = findViewById(R.id.btn_acces);
        Button btn_priorite = findViewById(R.id.btn_priorite);
        Button btn_analyse = findViewById(R.id.btn_analyse);
        Button btn_consignes = findViewById(R.id.btn_consignes);
        Button btn_media = findViewById(R.id.btn_media);

        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);
        id_etare = pref.getInt("id_etare",0);

        // Définit le champ texte avec le nom de l'utilisateur
        username= findViewById(R.id.tvNomPrenom);
        username.setText(user);

        category = categoryDAO.getCategoryDataByEtareIdAndCategory(id_etare,categoryName);

        et_commentaire_priorite_sauvegarde.setText(category.getCommentary_category());

        et_commentaire_priorite_sauvegarde.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                category.setCommentary_category(String.valueOf(et_commentaire_priorite_sauvegarde.getText()));
                categoryDAO.updateCategory(category);

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
                Intent intent = new Intent(PrioriteActivity.this, ListeActivity.class);
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
                Intent intent = new Intent(PrioriteActivity.this, ListeActivity.class);
                startActivity(intent);
                finish();
            }
        });





        btn_infos_gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, InformationsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_connaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, ConnaissanceLieuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_moyen_de_secours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, MoyenDeSecoursActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_dangers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, DangersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, AccesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_analyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, AnalyseRisqueActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_consignes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, ConsignesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrioriteActivity.this, MediaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
