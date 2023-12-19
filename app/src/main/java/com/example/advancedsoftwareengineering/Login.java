package com.example.advancedsoftwareengineering;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.advancedsoftwareengineering.databinding.ActivityLoginBinding;

//extends AppCompatActivity
public class Login extends Activity {
    private View WelcomeImage;
    private ActivityLoginBinding binding;
    private View LoginView;
    private int shortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WelcomeImage = binding.loadingImage;
        LoginView = binding.loginView;
        shortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime) * 10;

        //Makes the opacity 0 for the login view but keeps it visible
        LoginView.setAlpha(0f);
        LoginView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity and clear any animation
        LoginView.animate()
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
    }
}