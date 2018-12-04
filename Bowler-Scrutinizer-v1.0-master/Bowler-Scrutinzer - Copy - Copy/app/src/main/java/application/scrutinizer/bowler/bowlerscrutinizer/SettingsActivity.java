package application.scrutinizer.bowler.bowlerscrutinizer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsActivity extends AppCompatActivity {

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void Change_Password(View view) {

        Intent intent = new Intent(SettingsActivity.this , ChangePassword.class);
        startActivity(intent);
    }

    public void Account_Dec(View view) {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();
        final String TAG = "SettingsActivity";


        AlertDialog.Builder a_Builder = new AlertDialog.Builder(this);
        a_Builder.setMessage("Are you sure that you want to Deactivate your Account")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "User account deleted.");
                                    finish();
                                    Intent intent = new Intent(SettingsActivity.this , MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });

            }
        })

        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialog.cancel();

            }
        });

        AlertDialog alert = a_Builder.create();
        alert.setTitle("Alert");
        alert.show();


//        user.delete()
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "User account deleted.");
//                            finish();
//                            Intent intent = new Intent(SettingsActivity.this , MainActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//                });

//        if(user != null){
//            dialog.setMessage("Please Wait..... Deactivating ");
//            dialog.show();
//            finish();
//            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//
//
//                    if(task.isSuccessful()){
//                        Toast.makeText(SettingsActivity.this , "Account Deactivated",Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(SettingsActivity.this , MainActivity.class);
//                        startActivity(intent);
//                    }
//
//                    else{
//                        Toast.makeText(SettingsActivity.this , "Account can not be Deactivated",Toast.LENGTH_LONG).show();
//                    }
//
//
//                }
//            });
//
//        }
//
//        else{
//
//            Toast.makeText(SettingsActivity.this , "Account can not be Deactivated",Toast.LENGTH_LONG).show();
//
//        }


    }

    public void Log_out(View view) {

        AlertDialog.Builder a_Builder = new AlertDialog.Builder(this);
        a_Builder.setMessage("Are you sure that you want to Deactivate your Account")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseAuth.getInstance().signOut();
                        finish();

                        startActivity(new Intent(SettingsActivity.this , LoginActivity.class));


                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialog.cancel();

                    }
                });

        AlertDialog alert = a_Builder.create();
        alert.setTitle("Alert");
        alert.show();




    }
}
