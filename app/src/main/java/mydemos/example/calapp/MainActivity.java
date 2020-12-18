package mydemos.example.calapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import mydemos.example.calapp.model.calc;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String sex;
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

    }

    private void setGender() {
        Spinner sex = findViewById(R.id.activity_main_sex_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter);
        sex.setOnItemSelectedListener(this);
    }

    private void setAge() {

        ageEdit = (EditText) findViewById(R.id.activity_main_age_edit);
        ageEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setCalc();
            }
        });

    }

    private void setWeight() {

        weightEdit = (EditText) findViewById(R.id.activity_main_weight_edit);
        weightEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setCalc();
            }
        });

    }

    private void setHeight() {

        heightEdit = (EditText) findViewById(R.id.activity_main_height_edit);
        heightEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                setCalc();
            }
        });

    }

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

        if(sex.equals("Male")){
            calorie = calc.getMaleCalorie(height, weight, age);
        }
        if(sex.equals("Female")){
            calorie = calc.getFemaleCalorie(height, weight, age);
        }

        suggestText.setText("" + calorie + " calories");


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         sex = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}