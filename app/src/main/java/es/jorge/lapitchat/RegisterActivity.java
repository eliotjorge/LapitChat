package es.jorge.lapitchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout mDisplayName;
    private TextInputLayout mEmail;
    private TextInputLayout mPassword;
    private Button mCreateBtn;


    //Firebase
    private FirebaseAuth mAuth;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//Firebase
        mAuth = FirebaseAuth.getInstance();

        //
        mDisplayName = (TextInputLayout) findViewById(R.id.reg_display_name);
        mEmail = (TextInputLayout) findViewById(R.id.reg_email);
        mPassword = (TextInputLayout) findViewById(R.id.reg_password);
        mCreateBtn = (Button) findViewById(R.id.reg_create_btn);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String display_name = mDisplayName.getEditText().getText().toString();
                String email = mEmail.getEditText().getText().toString();
                String password = mPassword.getEditText().getText().toString();


                register_user(email, password);


            }
        });
    }
           /* private void register_user(String display_name, String email, String password) {

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) { //si el usuario se ha registrado

                                    Intent mainIntent = new Intent( RegisterActivity.this, MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();

                                }else{

                                    Toast.makeText(RegisterActivity.this, "You got some errors...", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
    }
}*/
     private void register_user(String email, String password) {

               //Firebase
               mAuth = FirebaseAuth.getInstance();
               //

               mAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   Log.d("1", "createUserWithEmail:success");
                                   FirebaseUser user = mAuth.getCurrentUser();
                                   Toast.makeText(RegisterActivity.this, "Registrado!!!",
                                           Toast.LENGTH_SHORT).show();

                               } else {
                                   // If sign in fails, display a message to the user.
                                   Log.d("2", "createUserWithEmail:failure", task.getException());
                                   Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
               // [END create_user_with_email]
           }
}

