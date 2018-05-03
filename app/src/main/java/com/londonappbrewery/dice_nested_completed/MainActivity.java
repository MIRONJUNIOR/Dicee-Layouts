package com.londonappbrewery.dice_nested_completed;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int mDefaultLang = -1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            mDefaultLang = savedInstanceState.getInt("Lang");
        }
        setContentView(R.layout.activity_main);

        // Link the views in the layout xml file to the java code
        final ImageView leftDice = (ImageView) findViewById(R.id.image_leftDice);
        final ImageView rightDice = (ImageView) findViewById(R.id.image_rightDice);

        final Spinner languages = (Spinner) findViewById(R.id.languages);

        String[] items = new String[] {"Default","Português","Japanese"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languages.setAdapter(adapter);

        languages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0 && mDefaultLang!=i){

                    Toast.makeText(getApplicationContext(),"default",Toast.LENGTH_SHORT).show();
                    setLocale("default");
                    mDefaultLang = i;
                }
                if(i==1 && mDefaultLang!=i){
                    Toast.makeText(getApplicationContext(),"pt-br",Toast.LENGTH_SHORT).show();
                    setLocale("pt");
                    mDefaultLang = i;

                }
                if(i==2 && mDefaultLang!=i){
                    setLocale("ja");
                    Toast.makeText(getApplicationContext(),"ja",Toast.LENGTH_SHORT).show();
                    mDefaultLang = i;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



                Button myButton = (Button) findViewById(R.id.rollButton);

        // Store the dice images in an array (collection)
        final int[] diceArray = new int[] {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6};

        // Tell the button to listen for clicks
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This code gets executed when the button is pressed

                // Create a random number generator
                Random randomNumberGenerator = new Random();

                // Make the random number generator spit out a new random number
                int number = randomNumberGenerator.nextInt(6);

                // Print this random number to the logcat to see it in the Android monitor
                Log.d("Dicee", "The number is " + number );

                // grab a random dice image from the diceArray. Then tell the ImageView to display
                // this image
                leftDice.setImageResource(diceArray[number]);

                // Create a new random number
                number = randomNumberGenerator.nextInt(6);

                // Set the right dice image using an image from the diceArray.
                rightDice.setImageResource(diceArray[number]);

            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Lang",mDefaultLang);

    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
//        Resources resources = getResources();
//        Configuration configuration = resources.getConfiguration();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//        configuration.setLocale(locale);
//        resources.updateConfiguration(configuration,displayMetrics);
//        recreate();
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }
}
