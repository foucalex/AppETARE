package sdis.projet.app.etare.activity.admin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import sdis.projet.app.etare.R;

/*******************************************************************************
 * Created by Alexandre Foucaud on 29/5/2020
 * Last modified 29/05/20 14:57
 ******************************************************************************/

public class UserManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_users);
    }
}
