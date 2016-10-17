package com.example.serega.pocketnews.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serega.pocketnews.R;
import com.example.serega.pocketnews.models.News;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewsActivity extends Activity {

    private TextView photoSrcView;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        photoSrcView = (TextView) findViewById(R.id.photo_src);

        Button btnPublish = (Button) findViewById(R.id.publish_btn);
        btnPublish.setOnClickListener(onClickPublishBtn);

        Button btnTakePhoto = (Button) findViewById(R.id.take_photo);
        btnTakePhoto.setOnClickListener(onClickTakePhotoBtn);

    }

    private View.OnClickListener onClickPublishBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText addedTitle = (EditText) findViewById(R.id.add_title);
            String title = addedTitle.getText().toString();

            EditText addedText = (EditText) findViewById(R.id.add_text);
            String text = addedText.getText().toString();

            if (!title.equals("") && !text.equals("")) {
                News news = new News(title, photoPath, text);
                news.save();
                startActivity(new Intent(AddNewsActivity.this, MainActivity.class));
            } else {
                Toast.makeText(AddNewsActivity.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener onClickTakePhotoBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri file = Uri.fromFile(getOutputMediaFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            startActivityForResult(intent, 100);
        }
    };


    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        photoPath = mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg";
        return new File(photoPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                photoSrcView.setText(photoPath);
            } else {
                Toast.makeText(AddNewsActivity.this, "Unable to load photo!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}