package com.example.serega.pocketnews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        Button addNewsBtn = (Button) findViewById(R.id.add_news);
        addNewsBtn.setOnClickListener(onClickAddButton);
    }

    private View.OnClickListener onClickAddButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText addedTitle = (EditText) findViewById(R.id.add_title);
            String title = addedTitle.getText().toString();

            EditText addedText = (EditText) findViewById(R.id.add_text);
            String text = addedText.getText().toString();

        }
    };
}
