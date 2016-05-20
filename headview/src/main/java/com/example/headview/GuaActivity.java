package com.example.headview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cooltechworks.views.ScratchTextView;

public class GuaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gua);
       /* ScratchImageView scratchImageView = new ScratchImageView(this);
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView tv) {
                // on reveal
            }
        });*/

        ScratchTextView scratchTextView = new ScratchTextView(this);

        scratchTextView.setRevealListener(new ScratchTextView.IRevealListener() {
            @Override
            public void onRevealed(ScratchTextView tv) {
                //on reveal
            }
        });

    }
}
