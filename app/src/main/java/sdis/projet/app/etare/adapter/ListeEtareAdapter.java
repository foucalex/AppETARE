package sdis.projet.app.etare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import sdis.projet.app.etare.R;
import sdis.projet.app.etare.dao.TownDAO;
import sdis.projet.app.etare.model.ETARE;
import sdis.projet.app.etare.model.Location;

public class ListeEtareAdapter extends ArrayAdapter<ETARE> {

    public ListeEtareAdapter(Context context, ArrayList<ETARE> allEtare){
        super(context, R.layout.item_liste_etare, allEtare);
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView= inflater.inflate(R.layout.item_liste_etare,parent,false);

        TextView tv_nom= rowView.findViewById(R.id.item_nom_etare);
        TextView tv_commune= rowView.findViewById(R.id.item_commune_etare);
        TextView tv_groupement= rowView.findViewById(R.id.item_groupement_etare);
        TextView tv_date_creation= rowView.findViewById(R.id.item_date_creation);
        TextView tv_date_update= rowView.findViewById(R.id.item_date_update);
        TextView tv_niveau_etare= rowView.findViewById(R.id.item_niveau_etare);
        TextView tv_statut_etare= rowView.findViewById(R.id.item_statut_etare);

        final ETARE etare = getItem(position);
        final Location location = getItem(position);
        TownDAO townDAO = new TownDAO(getContext());
        townDAO.open();
        String commune = townDAO.getNameTownById(etare.getId_town());
        String groupement = townDAO.getGroupTownByName(commune);

        tv_nom.setText(location.getName_location());
        tv_commune.setText(commune);
        tv_groupement.setText(groupement);
        tv_date_creation.setText(etare.getCreation_date());
        tv_date_update.setText(etare.getUpdate_date());
        tv_niveau_etare.setText(""+etare.getId_level());

        tv_statut_etare.setText(etare.getStatus());
        if (etare.getStatus().equals("A Traiter")){
            tv_statut_etare.setTextColor(Color.parseColor("#FFB900"));
        }
        if (etare.getStatus().equals("A Valider")){
            tv_statut_etare.setTextColor(Color.parseColor("#43A047"));
        }

        return rowView;


    }
}
