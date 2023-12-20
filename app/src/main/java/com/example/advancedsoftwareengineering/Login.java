package com.example.advancedsoftwareengineering;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.advancedsoftwareengineering.databinding.ActivityLoginBinding;
import static java.lang.Thread.sleep;


//extends AppCompatActivity
public class Login extends AppCompatActivity {
    private View WelcomeImage;
    private ActivityLoginBinding binding;

    private Sysadmin root, admin1;
    private User user1;
    private ITworker it1;
    private CafeWorker cafe1;
    private Actor actor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WelcomeImage = binding.loadingImage;
        View loginView = binding.loginView;
        int shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime) * 10;

        //Makes the opacity 0 for the login view but keeps it visible
        loginView.setAlpha(0f);
        loginView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity and clear any animation
        loginView.animate()
                .alpha(1f)
                .setDuration(shortAnimationDuration)
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step so it doesn't participate in layout passes.
        WelcomeImage.animate()
                .alpha(0f)
                .setDuration(shortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        WelcomeImage.setVisibility(View.GONE);
                    }
                });




        // ----------------------------- INITIALIZATION -----------------------//
        root = new Sysadmin("root", "root", "root", "root");
        root.createUserAccount("user1", "user1", "user1", "user1");
        root.createAdminAccount("admin2","admin2","admin2","admin2");
        root.createITWorkerAccount("it1","it1","it1","it1");
        root.createCafeWorkerAccount("cafe1","cafe1","cafe1","cafe1");


        // -----------------------------  -----------------------//

    }

    public void loginAuthentication(View view){
        String username, password;
        username = binding.usernameTextbox.getText().toString();
        password = binding.passswordTextBox.getText().toString();

        actor = Authenticator.authenticate(username, password);

        if(actor instanceof User){
            user1 = (User)actor;
            if(user1 != null){
                Toast.makeText(this,"Welcome "+user1.getName(),Toast.LENGTH_SHORT).show();
                root.addCreditToUser(user1, 100);

                Intent intent = new Intent(this, Dashboard.class);
                intent.putExtra("User",user1.getName());
                intent.putExtra("Pass",user1.getPassword());

                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Wrong username or password",Toast.LENGTH_SHORT).show();
            }
        } else if (actor instanceof ITworker) {
            it1 = (ITworker)actor;
            if(it1 != null){
                Toast.makeText(this,"Welcome "+it1.getName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, Dashboard.class);

                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Wrong username or password",Toast.LENGTH_SHORT).show();
            }
        } else if (actor instanceof CafeWorker) {
            cafe1 = (CafeWorker)actor;
            if(cafe1 != null){
                Toast.makeText(this,"Welcome "+cafe1.getName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, Dashboard.class);

                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Wrong username or password",Toast.LENGTH_SHORT).show();
            }
        } else if (actor instanceof Sysadmin) {
            admin1 = (Sysadmin) actor;
            if(admin1 != null){
                Toast.makeText(this,"Welcome "+admin1.getName(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, Dashboard.class);

                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Wrong username or password",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Wrong username or password",Toast.LENGTH_SHORT).show();
        }


    }

}