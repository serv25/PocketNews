package com.example.serega.pocketnews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = (GridLayout) findViewById(R.id.grid_layout);
        showNews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_news:
                startActivity(new Intent(MainActivity.this, AddNewsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNews() {
        grid.removeAllViews();

        List<News> list = News.listAll(News.class);
        LinearLayout linearLayout;
        ImageView photoView;
        TextView titleView;
        TextView textView;

        if (list != null) {
            for (News news : list) {
                linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);

                photoView = new ImageView(this);
                photoView.setImageBitmap(getDrawableFromStr(news.getPhoto()));

                titleView = new TextView(this);
                titleView.setText(news.getTitle());

                textView = new TextView(this);
                textView.setText(news.getText());

                linearLayout.addView(photoView);
                linearLayout.addView(titleView);
                linearLayout.addView(textView);

                grid.addView(linearLayout);
            }
        }

    }

    private Bitmap getDrawableFromStr(String path) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        return BitmapFactory.decodeFile(path, options);
    }
}
