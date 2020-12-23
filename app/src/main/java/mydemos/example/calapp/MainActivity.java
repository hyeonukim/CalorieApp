package mydemos.example.calapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import mydemos.example.calapp.model.calc;

public class MainActivity extends AppCompatActivity{

    private String Sex;
    private String Diet;
    private String Active;
    private double calorie;

    private EditText heightEdit;
    private EditText ageEdit;
    private EditText weightEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setHeight();
        setWeight();
        setAge();
        setGender();
        setDiet();
        setMeal();
        setActive();

    }

    //sets user's active level
    private void setActive() {
        Spinner active = findViewById(R.id.activity_main_active_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Active, android.R.layout.simple_spinner_item);
        active.setAdapter(adapter);

        active.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Active = adapterView.getItemAtPosition(i).toString();
                setCalc();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void setMeal() {
        Button meal = findViewById(R.id.activity_main_meal_button);
        meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Meal.makeLaunchIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }


    //gets user's option for either lose, maintain, gain weight
    private void setDiet() {
        Spinner diet = findViewById(R.id.activity_main_diet_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Diet, android.R.layout.simple_spinner_item);
        diet.setAdapter(adapter);
        diet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Diet = adapterView.getItemAtPosition(i).toString();
                setCalc();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //gets user's option for gender
    private void setGender() {
        Spinner sex = findViewById(R.id.activity_main_sex_spinner);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter);
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Sex = adapterView.getItemAtPosition(i).toString();
                setCalc();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //gets user's age
    private void setAge() {

        ageEdit = findViewById(R.id.activity_main_age_edit);
        final SharedPreferences prefs = getSharedPreferences("age", MODE_PRIVATE);
        ageEdit.setText(prefs.getString("age", ""));
        ageEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.edit().putString("age", editable.toString()).apply();
                setCalc();
            }
        });

    }

    //gets user's weight
    private void setWeight() {

        weightEdit = findViewById(R.id.activity_main_weight_edit);
        final SharedPreferences prefs = getSharedPreferences("age", MODE_PRIVATE);
        weightEdit.setText(prefs.getString("weight", ""));
        weightEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.edit().putString("weight", editable.toString()).apply();
                setCalc();
            }
        });

    }

    //gets user's height
    private void setHeight() {

        heightEdit = findViewById(R.id.activity_main_height_edit);
        final SharedPreferences prefs = getSharedPreferences("age", MODE_PRIVATE);
        heightEdit.setText(prefs.getString("height", ""));
        heightEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.edit().putString("height", editable.toString()).apply();
                setCalc();
            }
        });

    }

    //calculates calories intake suggested for user
    @SuppressLint("SetTextI18n")
    private void setCalc() {

        calc calc = new calc();

        double height;
        if (TextUtils.isEmpty(heightEdit.getText().toString())){
            return;
        }else{
            height = Double.parseDouble(heightEdit.getText().toString());
            if(height <= 0){
                return;
            }
        }

        double weight;
        if (TextUtils.isEmpty(weightEdit.getText().toString())){
            return;
        }else{
            weight = Double.parseDouble(weightEdit.getText().toString());
            if(weight <= 0){
                return;
            }
        }

        int age;
        if (TextUtils.isEmpty(ageEdit.getText().toString())){
            return;
        }else{
            age = Integer.parseInt(ageEdit.getText().toString());
            if(age <= 0){
                return;
            }
        }

        TextView suggestText = findViewById(R.id.activity_main_suggest_text);

        if (Diet == null){
            Diet = "Lose weight";
        }
        if (Active == null){
            Active = "sedentary (little or no exercise)";
        }
        if(Sex.equals("Male")){
            if(Diet.equals("Lose weight")){
                calorie = calc.getMaleLoseCalorie(height, weight, age);
            }
            if(Diet.equals("Maintain weight")) {
                calorie = calc.getMaleMaintainCalorie(height, weight, age);
            }
            if(Diet.equals("Gain weight")){
                calorie = calc.getMaleGainCalorie(height, weight, age);
            }
        }
        if(Sex.equals("Female")){
            if(Diet.equals("Lose weight")){
                calorie = calc.getFemaleLoseCalorie(height, weight, age);
            }
            if(Diet.equals("Maintain weight")) {
                calorie = calc.getFemaleMaintainCalorie(height, weight, age);
            }
            if(Diet.equals("Gain weight")){
                calorie = calc.getFemaleGainCalorie(height, weight, age);
            }
        }

        if(Active.equals("sedentary (little or no exercise)")){
            calorie *= 1.2;
        }
        if(Active.equals("lightly active (light exercise/sports 1–3 days/week)")){
            calorie *= 1.375;
        }
        if(Active.equals("moderately active (moderate exercise/sports 3–5 days/week)")){
            calorie *= 1.55;
        }
        if(Active.equals("very active (hard exercise/sports 6–7 days/week)")){
            calorie *= 1.725;
        }
        if(Active.equals("extra active (very hard exercise/sports, physical job or 2x training)")){
            calorie *= 1.9;
        }

        DecimalFormat df = new DecimalFormat("###.##");
        suggestText.setText("" + df.format(calorie) + " calories");
    }

}