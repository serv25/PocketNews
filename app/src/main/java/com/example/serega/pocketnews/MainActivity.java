package com.example.serega.pocketnews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout root;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout.LayoutParams textParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = (LinearLayout) findViewById(R.id.root);

        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(3, 3, 3, 3);


        textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textParams.gravity = Gravity.CENTER_VERTICAL;
        textParams.setMargins(10,3,10,3);

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

        List<News> list = News.listAll(News.class);
        LinearLayout wrapNewsLayout;
        ImageView photoView;
        TextView titleView;

        if (list != null) {
            for (News news : list) {
                wrapNewsLayout = new LinearLayout(this);
                wrapNewsLayout.setOrientation(LinearLayout.HORIZONTAL);
                wrapNewsLayout.setLayoutParams(layoutParams);
                wrapNewsLayout.setBackgroundColor(Color.WHITE);
                wrapNewsLayout.setPadding(8, 8, 8, 8);

                photoView = new ImageView(this);
                photoView.setImageBitmap(getDrawableFromStr(news.getPhoto()));

                titleView = new TextView(this);
                titleView.setText(news.getTitle());
                titleView.setLayoutParams(textParams);

                wrapNewsLayout.addView(photoView);
                wrapNewsLayout.addView(titleView);
                root.addView(wrapNewsLayout);
            }
        }

    }

    private Bitmap getDrawableFromStr(String path) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        return BitmapFactory.decodeFile(path, options);
    }
}
