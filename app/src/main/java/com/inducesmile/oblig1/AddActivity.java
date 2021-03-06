package com.inducesmile.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddActivity extends AppCompatActivity {
    private ImageView cameraImage = null;
    private ArrayList<ImageObjects> addDatabase = MainActivity.quizData;
    private EditText savePicName = null;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int RESULT_LOAD_RESULT = 2;
    private Button cam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        cam = (Button) findViewById(R.id.button_camera);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quiz:
                Intent intentQuiz = new Intent(AddActivity.this, QuizActivity.class);
                startActivity(intentQuiz);
                return true;

            case R.id.home:
                Intent intentAdd = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intentAdd);
                return true;

            case R.id.database:
                Intent intentDatabase = new Intent(AddActivity.this, DatabaseActivity.class);
                startActivity(intentDatabase);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cameraImage = (ImageView) findViewById(R.id.camera_imageView);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            cameraImage.setImageBitmap(imageBitmap);

        }else if (requestCode == RESULT_LOAD_RESULT && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();
                Bitmap imageBitmap = null;
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cameraImage.setImageBitmap(imageBitmap);
            }

        }


    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void savePic (View view){
        QuizActivity.hideKeyboard(this);
        if (cameraImage != null ) {
            BitmapDrawable drawable = (BitmapDrawable) cameraImage.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            savePicName = (EditText) findViewById(R.id.save_editText);
            Log.i("Status", "" + savePicName.getText().toString());
            if (!savePicName.getText().toString().equals("")){
                addDatabase.add(new ImageObjects(bitmap, savePicName.getText().toString()));
                Toast.makeText(this, "Bildet ble lagret", Toast.LENGTH_SHORT).show();
                savePicName.setText("");
            }else{
                Toast.makeText(this, "Vennligst gi bildet ett navn", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Du må først velge ett bilde", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadExistingPhoto (View view){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_RESULT);

        }
}
