package sdis.projet.app.etare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import sdis.projet.app.etare.adapter.ListeEtareAdapter;
import sdis.projet.app.etare.dao.TownDAO;
import sdis.projet.app.etare.database.DatabaseHandler;
import sdis.projet.app.etare.R;
import sdis.projet.app.etare.dao.EtareDAO;
import sdis.projet.app.etare.dao.LocationDAO;
import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.model.Location;
import sdis.projet.app.etare.model.Town;

/*******************************************************************************
 * Created by Alexandre Foucaud on 25/05/20 16:55
 * Last modified 25/05/20 14:35
 ******************************************************************************/

public class ListeActivity extends AppCompatActivity {

    ListView listView;
    private ListeEtareAdapter listeEtareAdapter;
    private final ArrayList<ETARE> etareList = new ArrayList<>();
    private ArrayList<ETARE> filteredList = new ArrayList<>();
    private SearchView searchView;
    private DatabaseHandler db;
    EtareDAO etareDAO;
    LocationDAO locationDAO;
    TownDAO townDAO;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String user;
    int droit_user;
    int id_etare;
    int id_location;
    Boolean state_search = false;
    private boolean verificationImportTableTown = false;

    ImageView imageViewDeco;
    TextView tv_deconnexion;

    TextView username;

    Button btn_admin_users;
    Button btn_admin_etare;

    @Override
    protected void onResume() {
        super.onResume();
        Set_Refresh_Data();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        etareDAO =new EtareDAO(this);
        etareDAO.open();

        locationDAO =new LocationDAO(this);
        locationDAO.open();

        townDAO= new TownDAO(this);
        townDAO.open();

        Button btn_creation_etare = findViewById(R.id.btn_creation_etare);
        searchView = findViewById(R.id.searchview_liste);
        listView = findViewById(R.id.listview_etare);
        listeEtareAdapter = new ListeEtareAdapter(this, etareList);
        listView.setAdapter(listeEtareAdapter);

        imageViewDeco = findViewById(R.id.imgDeconnexion);
        tv_deconnexion = findViewById(R.id.tvDeconnexion);

        btn_admin_etare = findViewById(R.id.btn_admin_etare);
        btn_admin_users = findViewById(R.id.btn_admin_users);

        Set_Refresh_Data();

        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);
        verificationImportTableTown = pref.getBoolean("ImportDataTown",false);
        user = pref.getString("identifiant_user","User");
        droit_user = pref.getInt("droit_user",0);

        if(droit_user==1){
            btn_admin_users.setVisibility(View.VISIBLE);
            btn_admin_etare.setVisibility(View.VISIBLE);
        }

        if(!verificationImportTableTown){

            readTownData();
            verificationImportTableTown = true;
            editor = pref.edit();
            editor.putBoolean("ImportDataTown",verificationImportTableTown);
            editor.apply();
        }

        imageViewDeco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = "User";
                editor = pref.edit();
                editor.putString("identifiant_user",user);
                editor.putInt("droit_user",0);
                editor.apply();
                Intent intent = new Intent(ListeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tv_deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = "User";
                editor = pref.edit();
                editor.putString("identifiant_user",user);
                editor.putInt("droit_user",0);
                editor.apply();
                Intent intent = new Intent(ListeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        username= findViewById(R.id.tvNomPrenom);
        username.setText(user);
        //Recherche de la fiche ETARE par nom
        searchView.setQueryHint("Recherche");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return false;
            }
        });

        //Fonction pour aller a la page de création d'un etare
        btn_creation_etare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeActivity.this, CreationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        listeEtareAdapter.notifyDataSetChanged();
    }

    /**
     * Fonction permettant de supprimer un ETARE de la liste
     * @param view
     */
    public void DeleteEtare(View view){
        LinearLayout parentRow = (LinearLayout) view.getParent();
        ImageButton btn_delete = parentRow.findViewById(R.id.item_btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item = listView.getPositionForView(v);
                etareList.remove(item);
                listeEtareAdapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * Fonction permettant d'ouvrir les pages d'édition de l'ETARE
     * @param view
     */
    public void EditEtare(View view){
        LinearLayout parentRow = (LinearLayout) view.getParent();
        ImageButton btn_edit = parentRow.findViewById(R.id.item_btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                if (state_search){
                    name = filteredList.get(listView.getPositionForView(v)).getName_location();
                }else{
                    name = etareList.get(listView.getPositionForView(v)).getName_location();
                }
                Log.d("TAG", "onClick: "+ name);
                id_location = locationDAO.getIdLocationByName(name);
                id_etare=etareDAO.getIdEtareByIdLocation(id_location);
                Log.d("TAG", "id_location: "+id_location);
                Log.d("TAG", "id_etare: "+id_etare);


                editor = pref.edit();
                editor.putInt("id_etare",id_etare);
                editor.apply();

                Intent intent = new Intent(ListeActivity.this, InformationsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Fonction pour afficher la visualisation de l'ETARE
     * @param view
     */
    public void ViewEtare(View view){
        LinearLayout parentRow = (LinearLayout) view.getParent();
        ImageButton btn_view = parentRow.findViewById(R.id.item_btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item = listView.getPositionForView(v);
                Log.d("TAG", "onClick: view "+item);
            }
        });
    }

    /**
     * Fonction de récupération des données avec la recherche par nom
     * @param query Chaîne de caractère de la recherche
     */
    private void getData(String query){
        filteredList.clear();
        if(searchView!= null){
            state_search = true;
            for (ETARE item:etareList){
                if(item.getName_location().toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                    listeEtareAdapter.notifyDataSetChanged();
                }
            }
        }
        else{
            state_search = false;
            filteredList = etareList;
        }
        listeEtareAdapter = new ListeEtareAdapter(this,filteredList);
        listView.setAdapter(listeEtareAdapter);
        listeEtareAdapter.notifyDataSetChanged();
    }

    /**
     * Fonction pour le rafraichissement des items de la liste
     */
    private void Set_Refresh_Data(){
        etareList.clear();
        db = new DatabaseHandler(ListeActivity.this);
        List<ETARE> contact_array_from_db=etareDAO.getAllEtare();
        List<Location> location_array_from_db=locationDAO.getAllLocation();

        for (int i=0;i<contact_array_from_db.size();i++){
            int idEtare = contact_array_from_db.get(i).getId_etare();
            String name = location_array_from_db.get(i).getName_location();
            int idTown = contact_array_from_db.get(i).getId_town();
            int idLocation = contact_array_from_db.get(i).getId_location();
            String creation =contact_array_from_db.get(i).getCreation_date();
            String update =contact_array_from_db.get(i).getUpdate_date();
            int idLevel =contact_array_from_db.get(i).getId_level();
            String status = contact_array_from_db.get(i).getStatus();


            ETARE etare =new ETARE();
            etare.setId_etare(idEtare);
            etare.setName_location(name);
            etare.setCreation_date(creation);
            etare.setUpdate_date(update);
            etare.setStatus(status);
            etare.setId_level(idLevel);
            etare.setId_town(idTown);
            etare.setId_location(idLocation);
            etareList.add(etare);
        }

        db.close();
        listeEtareAdapter = new ListeEtareAdapter(this,etareList);
        listView.setAdapter(listeEtareAdapter);
        listeEtareAdapter.notifyDataSetChanged();
    }

    public void readTownData(){
        InputStream is = getResources().openRawResource(R.raw.town);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("windows-1252"))
        );

        String line ="";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(";");
                Town town = new Town();
                town.setName_town(token[0].toLowerCase());
                town.setGroup_town(token[1].toLowerCase());
                town.setInsee_town(token[2].toLowerCase());
                townDAO.createTown(token[0],token[1],token[2]);
            }
        } catch (IOException e) {
            Log.e("MyActivity","Error reading data file on line " + line, e);
            e.printStackTrace();
        }

    }

}
