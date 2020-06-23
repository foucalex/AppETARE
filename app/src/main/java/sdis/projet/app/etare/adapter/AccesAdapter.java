package sdis.projet.app.etare.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sdis.projet.app.etare.R;
import sdis.projet.app.etare.model.AccessSpot;

public class AccesAdapter extends RecyclerView.Adapter<AccesAdapter.ViewHolder> {

    private ArrayList<AccessSpot> accessSpots;

    public AccesAdapter(Context context, ArrayList<AccessSpot> accessSpots){
        this.accessSpots = accessSpots;
    }

    @NonNull
    @Override
    public AccesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_acces,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final AccessSpot accessSpot = accessSpots.get(position);

        holder.et_commentaire.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                accessSpot.setCommentary_access(String.valueOf(holder.et_commentaire.getText()));

            }
        });

        holder.et_acces.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                accessSpot.setLocation_access(String.valueOf(holder.et_acces.getText()));

            }

        });

        holder.et_commentaire.setText(accessSpot.getCommentary_access());
        holder.et_acces.setText(accessSpot.getLocation_access());

    }

    @Override
    public int getItemCount() {
        return accessSpots.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        EditText et_commentaire;
        EditText et_acces;



        ViewHolder(@NonNull View itemView) {
            super(itemView);

            et_commentaire = itemView.findViewById(R.id.et_commentaire_item_acces);
            et_acces = itemView.findViewById(R.id.et_acces_item_acces);

        }
    }


}
