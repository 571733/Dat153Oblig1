package com.inducesmile.oblig1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

   public static ArrayList<ImageObjects> standardObjects;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        standardObjects = MainActivity.quizData;
        ListView listView = (ListView) findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();



            listView.setAdapter(customAdapter);

        Log.i("DENNYE", ""+standardObjects.size());
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {

            return standardObjects.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            View v = view;
            view = getLayoutInflater().inflate(R.layout.custom_layout, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView_name = (TextView) view.findViewById(R.id.textView_name);
            imageView.setImageBitmap(standardObjects.get(position).getImage());
            textView_name.setText(standardObjects.get(position).getName());



            //ImageButton removeFav = (ImageButton)v;
                ImageButton deleteButton = (ImageButton) view.findViewById(R.id.button_delete);



            deleteButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(DatabaseActivity.this, "Du klikket", Toast.LENGTH_SHORT).show();
                    standardObjects.remove(position);
                    // After you delete the object from Parse database here,
                    notifyDataSetChanged();

                }
            });


            return view;
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quiz:
                Intent intentQuiz = new Intent(DatabaseActivity.this, QuizActivity.class);
                startActivity(intentQuiz);
                return true;

            case R.id.add:
                Intent intentAdd = new Intent(DatabaseActivity.this, AddActivity.class);
                startActivity(intentAdd);
                return true;

           /* case R.id.database:
                Intent intentDatabase = new Intent(DatabaseActivity.this, DatabaseActivity.class);
                startActivity(intentDatabase);
                return true;
*/
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




}
