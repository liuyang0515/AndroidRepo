package com.zarretail.zoney;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 4/10/2015.
 */
public class TermsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getActionBar().hide();

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView txtNavTitle = (TextView)findViewById(R.id.txtNavTitle);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/choplinmedium.otf");
        txtNavTitle.setTypeface(myTypeface);

        TextView txtDetails = (TextView)findViewById(R.id.txtDetail);
        myTypeface = Typeface.createFromAsset(getAssets(), "fonts/NexaRegular.otf");
        txtDetails.setTypeface(myTypeface);
        txtDetails.setText("I . Privacy\n" +
                "It was a humorously perilous business for both of us. For, before we proceed further, it must be said that the monkey-rope was fast at both ends; fast. Queequeg's broad canvas belt, and fast to my narrow leather one. So that for better or for worse, we two, for the time, were wedded; and should poor Queequeg sink to rise no more, then both usage and honour.\n\n" +
                "II. Policy\n" +
                "demanded, that instead of cutting the cord, it should drag me down in his wake. So, then, an elongated Siamese ligature united us. Queequeg was my own inseparable twin brother; nor could I any way get rid of the dangerous liabilities which the hempen bond entailed\"");

    }
}
