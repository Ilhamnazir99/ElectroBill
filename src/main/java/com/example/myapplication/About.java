package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Set up toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable back navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the button and its click listener
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> openGitHubLink());
    }

    // Method to open GitHub link
    private void openGitHubLink() {
        String githubUrl = "https://github.com/haydarlars/Calc-A-Watt/tree/main/CalcApp"; // Replace with your actual GitHub URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle toolbar item clicks
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to MainActivity
            startActivity(new Intent(About.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
