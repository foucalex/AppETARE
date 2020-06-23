package sdis.projet.app.etare.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sdis.projet.app.etare.R;
import sdis.projet.app.etare.adapter.ListeEtareAdapter;
import sdis.projet.app.etare.adapter.MediaAdapter;
import sdis.projet.app.etare.dao.EtareDAO;
import sdis.projet.app.etare.dao.MediaDAO;
import sdis.projet.app.etare.database.DatabaseHandler;
import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.model.Location;
import sdis.projet.app.etare.model.Media;

/*******************************************************************************
 * Created by Alexandre Foucaud on 25/05/20 16:55
 * Last modified 25/05/20 14:35
 ******************************************************************************/

public class MediaActivity extends AppCompatActivity {


    SharedPreferences pref;
    int id_etare;

    // Variables pour les données utilisateurs
    String user;
    int droit_user;

    // TextView pour l'affichage du nom d'utilisateur
    TextView username;

    Button ajout_media;
    Button supprimer_media;
    Button lier_pictogramme;
    Spinner spinner_category;
    EditText desc_media;
    DatabaseHandler db;
    MediaDAO mediaDAO;
    RecyclerView mRecyclerView;

    //ImageView imageGrandFormat;
    EtareDAO etareDAO;


    Bitmap bitmap;
    Uri filePath;
    ArrayList<Media> arrayListMedia = new ArrayList<>();
    RecyclerView.Adapter adapterMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);
        id_etare = pref.getInt("id_etare",0);

        //Récupération des données de l'utilisateur
        user = pref.getString("identifiant_user","User");
        droit_user = pref.getInt("droit_user",0);

        // Définit le champ texte avec le nom de l'utilisateur
        username= findViewById(R.id.tvNomPrenom);
        username.setText(user);

        mediaDAO = new MediaDAO(this);
        mediaDAO.open();

        etareDAO = new EtareDAO(this);
        etareDAO.open();

        //imageGrandFormat = findViewById(R.id.imageView);
        desc_media = findViewById(R.id.description_media);
        supprimer_media = findViewById(R.id.supprimer_photo);
        lier_pictogramme = findViewById(R.id.liaison_pictogramme);
        spinner_category = findViewById(R.id.spinner_category_media);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adapterMedia = new MediaAdapter(arrayListMedia);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapterMedia);

        ajout_media = findViewById(R.id.btn_import);
        ajout_media.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
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
                Intent intent = new Intent(MediaActivity.this, ListeActivity.class);
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
                Intent intent = new Intent(MediaActivity.this, ListeActivity.class);
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
                Intent intent = new Intent(MediaActivity.this, InformationsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_connaissance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, ConnaissanceLieuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_moyen_de_secours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, MoyenDeSecoursActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_dangers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, DangersActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_acces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, AccesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_priorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, PrioriteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_analyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, AnalyseRisqueActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_consignes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaActivity.this, ConsignesActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Set_Refresh_Data();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

//                Media media = new Media();
//                media.setDesc_media(desc_media.getText().toString());
//                media.setName_media("Toto");
//                media.setImg_media(BitMapToByte(bitmap));
//                media.setId_etare(id_etare);

                mediaDAO.createMedia("Toto","",BitMapToByte(bitmap),id_etare,0);
                adapterMedia.notifyDataSetChanged();
                Set_Refresh_Data();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void Set_Refresh_Data(){
        arrayListMedia.clear();
        db = new DatabaseHandler(MediaActivity.this);
        List<Media> media_array_from_db = mediaDAO.getMediaByEtareId(id_etare);

        for (int i=0;i<media_array_from_db.size();i++){

            int id_media = media_array_from_db.get(i).getId_media();
            String name_media = media_array_from_db.get(i).getName_media();
            String desc_media =media_array_from_db.get(i).getDesc_media();
            byte[] img_media =media_array_from_db.get(i).getImg_media();
            int id_etare = media_array_from_db.get(i).getId_etare();
            int id_category=media_array_from_db.get(i).getId_category();


            Media media = new Media();
            media.setId_media(id_media);
            media.setName_media(name_media);
            media.setDesc_media(desc_media);
            media.setImg_media(img_media);
            media.setId_etare(id_etare);
            media.setId_category(id_category);
            arrayListMedia.add(media);
            Log.d("TAG", "Set_Refresh_Data: id :"+media.getId_media()+"\n name : "+ Arrays.toString(media.getImg_media())+"\n name: "+media.getName_media());
        }

        db.close();
        adapterMedia = new MediaAdapter(arrayListMedia);
        mRecyclerView.setAdapter(adapterMedia);
        adapterMedia.notifyDataSetChanged();
    }




    private static byte[] BitMapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);
        return stream.toByteArray();
    }

    private Bitmap ByteToBitMap(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        Log.d("TAG", "ByteToBitMap: "+bitmap.getConfig());
        return bitmap;
    }
}
