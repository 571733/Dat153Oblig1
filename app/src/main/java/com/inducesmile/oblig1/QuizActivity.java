package com.inducesmile.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
    private int poeng = 0;
   private int antallQuizSpm = 0;
   private TextView galtSvar;
   private Button svarButtonOn;
   private EditText svarEditText;
   private ImageView correctAnswerIcon;
   private ImageView wrongAnswerIcon;

ArrayList<ImageObjects> quizDatabases = MainActivity.quizData;

    //Kopi av "databasen. Denne vil bli tømt etterhvert som man går videre i quizen
    List<ImageObjects> quizDatabase = new ArrayList<>(quizDatabases);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        galtSvar = (TextView) findViewById(R.id.galtSvar_textView);
        svarButtonOn = (Button) findViewById(R.id.svar_button);
        svarEditText = (EditText) findViewById(R.id.svar_editText);
        correctAnswerIcon = (ImageView) findViewById(R.id.correctAnswer_imageView);
        wrongAnswerIcon = (ImageView) findViewById(R.id.wrongAnswer_imageView);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item ) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intentQuiz = new Intent(QuizActivity.this, MainActivity.class);
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
    //DatabaseActivity databaseActivity = new DatabaseActivity();
    //List<Integer> kopi = new ArrayList<>(Arrays.asList(databaseActivity.IMAGES));
    //List<String> kopiNames = new ArrayList<>(Arrays.asList(databaseActivity.NAMES));

    //DatabaseActivity databaseNames = new DatabaseActivity();



    int antKlikk = 0;
    int globalIndex = 0;


    public void quizpic (View view) {
        if (!(quizDatabase.size() == 0)) {

        correctAnswerIcon.setVisibility(View.INVISIBLE);
        wrongAnswerIcon.setVisibility(View.INVISIBLE);
        svarEditText.setEnabled(true);
        antallQuizSpm = quizDatabase.size();
        antKlikk++;
        galtSvar.setText("");
        //  Log.i("Ant bilder ", ""+antallQuizSpm);
        Log.i("Ant klikk ", "" + antKlikk);
        Button quizButton = (Button) findViewById(R.id.quiz_button);
        quizButton.setEnabled(false);

        if (!quizButton.getText().equals("Neste")) {
            quizButton.setText("Neste");
            svarButtonOn.setVisibility(View.VISIBLE);
            svarEditText.setVisibility(View.VISIBLE);


        }
        if (antKlikk == antallQuizSpm) {
            //  Toast.makeText(this, "Du har nå fullført quizen", Toast.LENGTH_LONG).show();
            // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        }


        loadbilde();

        svarButtonOn.setEnabled(true);

        EditText EmptyEditText = (EditText) findViewById(R.id.svar_editText);
        EmptyEditText.setText("");
        Log.i("quizIndex ", "" + globalIndex);
        svarEditText.setHint("Hvem er på bildet?");
    }else{
            Toast.makeText(this, "Det er ingen bilder i databasen. Velg \"Database\" for å legge til bilder ", Toast.LENGTH_LONG).show();
        }

    }

    public void loadbilde(){
        ImageView imgView = (ImageView) findViewById(R.id.imageView_quiz);
        int index = new Random().nextInt(quizDatabase.size());
        globalIndex = index;
        imgView.setImageBitmap(quizDatabase.get(index).getImage());



        //quizDatabase.remove(index);
        //kopiNames.remove(index);


    }
    public void svar (View view){
        hideKeyboard(this);
        Button svarButtonOff = (Button) findViewById(R.id.svar_button);
        svarButtonOff.setEnabled(false);
        Log.i("Antall klikker: ",""+antKlikk);
        Log.i("Antall QuizSpmer: ",""+antallQuizSpm);
        Button turnOnQuizButton = (Button) findViewById(R.id.quiz_button);
        if (antallQuizSpm > 1) {
            turnOnQuizButton.setEnabled(true);
        }else{
            Toast.makeText(this, "Du har nå fullført quizen", Toast.LENGTH_LONG).show();
            //quizDatabase = MainActivity.quizData; //Virker ikke
        }

        Log.i("svarIndex ", ""+globalIndex);

        String dbName = quizDatabase.get(globalIndex).getName();

        Log.i("dataBaseName ",""+dbName);
        Log.i("input", ""+ svarEditText.getText().toString());


        if (dbName.toUpperCase().equals(svarEditText.getText().toString().toUpperCase())){
            poeng++;
            correctAnswerIcon.setVisibility(View.VISIBLE);
            Log.i("Funket", "ja");
        }else {
            wrongAnswerIcon.setVisibility(View.VISIBLE);
            galtSvar.setText("Feil svar! Riktig svar er "+dbName);
        }
        TextView score = (TextView) findViewById(R.id.score_textView);
        score.setText("Din score "+Integer.toString(poeng)+" / "+Integer.toString(antKlikk));

        quizDatabase.remove(globalIndex);
        turnOnQuizButton.setFocusable(true);
        svarEditText.setEnabled(false);
        svarEditText.setHint("");
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
