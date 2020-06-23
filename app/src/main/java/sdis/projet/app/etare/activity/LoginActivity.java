package sdis.projet.app.etare.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sdis.projet.app.etare.R;
import sdis.projet.app.etare.dao.UserDAO;
import sdis.projet.app.etare.model.User;

/*******************************************************************************
 * Created by Alexandre Foucaud on 25/05/20 16:55
 * Last modified 25/05/20 14:35
 ******************************************************************************/

public class LoginActivity extends AppCompatActivity {

    EditText et_identifiant;
    EditText et_mdp;
    Button btn_connexion;

    String identifiant;
    String mdp;

    UserDAO userDAO;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDAO = new UserDAO(this);
        userDAO.open();

        et_identifiant = findViewById(R.id.et_identifiant);
        et_mdp = findViewById(R.id.et_mdp);
        btn_connexion = findViewById(R.id.btn_connexion);



        pref = getApplicationContext().getSharedPreferences("Preference",MODE_PRIVATE);

        if(!pref.getString("identifiant_user", "User").equals("User")){
            Intent intent = new Intent(LoginActivity.this, ListeActivity.class);
            startActivity(intent);
            finish();
        }
        for(User u :userDAO.getAllUser()){
            Log.d("TAG", "User: id :" +u.getId_user()+"\n login : "+u.getLogin_user()+"\n mdp : "+u.getPwd_user()+"\n admin : "+u.getAdmin());
        }
        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                identifiant = String.valueOf(et_identifiant.getText());
                mdp = String.valueOf(et_mdp.getText());

                int result = userDAO.getStateUser(identifiant,mdp);
                Log.d("TAG", "Result : "+result);
                if(result == -1){
                    //Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("\t\tAttention !\n L'identifiant ou le mot de passe est incorrect. ")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }else if(result == 0){
                    Toast.makeText(LoginActivity.this, "User", Toast.LENGTH_LONG).show();

                    String identifiant_user = userDAO.getUserData(identifiant,mdp).getLogin_user();
                    editor = pref.edit();
                    editor.putString("identifiant_user",identifiant_user);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, ListeActivity.class);
                    startActivity(intent);
                    finish();

                }else if(result == 1){
                    Toast.makeText(LoginActivity.this, "Admin", Toast.LENGTH_LONG).show();
                    String identifiant_user = userDAO.getUserData(identifiant,mdp).getLogin_user();
                    editor = pref.edit();
                    editor.putString("identifiant_user",identifiant_user);
                    editor.putInt("droit_user",result);
                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, ListeActivity.class);
                    startActivity(intent);
                    finish();

                    //TODO Faire interface pour administration users et validation ETARE
                }
            }
        });

    }
}
