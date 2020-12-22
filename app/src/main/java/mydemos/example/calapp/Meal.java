package mydemos.example.calapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Meal extends AppCompatActivity {

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, Meal.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        
    }
}