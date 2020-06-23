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
import sdis.projet.app.etare.model.Contact;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private ArrayList<Contact> contacts;


    public ContactsAdapter(Context context, ArrayList<Contact> contacts){
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Contact contact = contacts.get(position);

        holder.et_nom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contact.setName_contact(String.valueOf(holder.et_nom.getText()));
            }

        });


        holder.et_fonction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                contact.setJob_contact(String.valueOf(holder.et_fonction.getText()));

            }
        });

        holder.et_telFixe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contact.setPhone_contact(String.valueOf(holder.et_telFixe.getText()));

            }
        });

        holder.et_telPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contact.setMobilephone_contact(String.valueOf(holder.et_telPort.getText()));

            }
        });

        holder.et_mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                contact.setEmail_contact(String.valueOf(holder.et_mail.getText()));

            }
        });

        holder.et_mail.setText(contact.getEmail_contact());
        holder.et_nom.setText(contact.getName_contact());
        holder.et_telPort.setText(contact.getMobilephone_contact());
        holder.et_telFixe.setText(contact.getPhone_contact());
        holder.et_fonction.setText(contact.getJob_contact());



    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        EditText et_nom;
        EditText et_fonction;
        EditText et_telFixe;
        EditText et_telPort;
        EditText et_mail;



        ViewHolder(@NonNull View itemView) {
            super(itemView);


            et_nom = itemView.findViewById(R.id.et_nom_item_personne);
            et_fonction = itemView.findViewById(R.id.et_fonction_item_personne);
            et_telFixe = itemView.findViewById(R.id.et_fixe_item_personne);
            et_telPort = itemView.findViewById(R.id.et_portable_item_access);
            et_mail = itemView.findViewById(R.id.et_mail_item_personne);
        }
    }
}
