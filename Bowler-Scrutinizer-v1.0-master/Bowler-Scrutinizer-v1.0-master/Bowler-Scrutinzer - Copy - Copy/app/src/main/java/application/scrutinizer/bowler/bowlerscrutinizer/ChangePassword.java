package application.scrutinizer.bowler.bowlerscrutinizer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    ProgressDialog dialog;

    EditText newP ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();

    }

    public void init(){
        newP = (EditText) findViewById(R.id.mode);
    }


    public void Change_P(View view) {

       final String TAG = "ChangePassword";

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPass = newP.getText().toString();



        user.updatePassword(newPass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User password updated.");

                            Intent intent = new Intent(ChangePassword.this , LoginActivity.class);
                            startActivity(intent);

                        }
                    }
                });

    }
}
