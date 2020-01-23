package com.inducesmile.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
int poeng = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //createCheckTable();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.quiz:
                Intent intentQuiz = new Intent(QuizActivity.this, QuizActivity.class);
                startActivity(intentQuiz);
                return true;

            case R.id.add:
                Intent intentAdd = new Intent(QuizActivity.this, AddActivity.class);
                startActivity(intentAdd);
                return true;

            case R.id.database:
                Intent intentDatabase = new Intent(QuizActivity.this, DatabaseActivity.class);
                startActivity(intentDatabase);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    DatabaseActivity databaseActivity = new DatabaseActivity();
    List<Integer> kopi = new ArrayList<>(Arrays.asList(databaseActivity.IMAGES));
    List<String> kopiNames = new ArrayList<>(Arrays.asList(databaseActivity.NAMES));

    DatabaseActivity databaseNames = new DatabaseActivity();



    int antKlikk = 0;
    int globalIndex = -1;
    int antallQuizSpm = kopi.size();

    public void quizpic (View view){

        antKlikk++;
      //  Log.i("Ant bilder ", ""+antallQuizSpm);
        Log.i("Ant klikk ", ""+antKlikk);
        Button quizButton = (Button) findViewById(R.id.quiz_button);
        quizButton.setEnabled(false);

        if (!quizButton.getText().equals("Neste")) {
            quizButton.setText("Neste");
        }
        if (antKlikk == antallQuizSpm){
          //  Toast.makeText(this, "Du har nå fullført quizen", Toast.LENGTH_LONG).show();
           // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        }


       loadbilde();
        Button svarButtonOn = (Button) findViewById(R.id.svar_button);
       svarButtonOn.setEnabled(true);

       EditText EmptyEditText = (EditText) findViewById(R.id.svar_editText);
       EmptyEditText.setText("");
        Log.i("quizIndex ", ""+globalIndex);



    }

    public void loadbilde(){
        ImageView imgView = (ImageView) findViewById(R.id.imageView_quiz);
        int index = new Random().nextInt(kopi.size());
        globalIndex = index;
        imgView.setImageResource(kopi.get(index));



        kopi.remove(index);
        //kopiNames.remove(index);


    }
    public void svar (View view){
        hideKeyboard(this);
        Button svarButtonOff = (Button) findViewById(R.id.svar_button);
        svarButtonOff.setEnabled(false);

        Button turnOnQuizButton = (Button) findViewById(R.id.quiz_button);
        if (!(antKlikk == antallQuizSpm)) {
            turnOnQuizButton.setEnabled(true);
        }else{
            Toast.makeText(this, "Du har nå fullført quizen", Toast.LENGTH_LONG).show();
        }

        Log.i("svarIndex ", ""+globalIndex);
        EditText svar = (EditText) findViewById(R.id.svar_editText);
        String dbName = kopiNames.get(globalIndex);

        Log.i("dataBaseName ",""+dbName);
        Log.i("input", ""+svar.getText().toString());


        if (dbName.equals(svar.getText().toString())){
            poeng++;

            Log.i("Funket", "ja");
        }
        TextView score = (TextView) findViewById(R.id.score_textView);
        score.setText("Din score "+Integer.toString(poeng)+" / "+Integer.toString(antKlikk));

        kopiNames.remove(globalIndex);
        turnOnQuizButton.setFocusable(true);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
