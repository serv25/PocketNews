package com.example.serega.pocketnews.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.serega.pocketnews.R;
import com.example.serega.pocketnews.models.News;

import static com.orm.SugarRecord.findById;


public class ReadNewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);

        TextView titleView = (TextView)findViewById(R.id.titleView);
        TextView textView = (TextView)findViewById(R.id.textView);
        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 0L);
        News news = findById(News.class, id);

        titleView.setText(news.getTitle());
        textView.setText(news.getText());
        imageView.setImageBitmap(getDrawableFromStr(news.getPhoto()));
    }

    private Bitmap getDrawableFromStr(String path) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        return BitmapFactory.decodeFile(path, options);
    }
}
