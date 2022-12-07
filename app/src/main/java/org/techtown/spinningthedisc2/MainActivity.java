
package org.techtown.spinningthedisc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    boolean blnButtonRotation = true;
    int intNumber = 8;
    long lngDegree = 0;
    SharedPreferences sharedPreferences;

    ImageView selected, imageRoulette;

    Button b_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(1024);
        requestWindowFeature(1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_start = findViewById(R.id.buttonStart);


         selected = findViewById(R.id.imageSelected);
         imageRoulette = findViewById(R.id.rouletteImage);


        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.intNumber = this.sharedPreferences.getInt("INT_NUMBER", 8);
    }


    @Override
    public void onAnimationStart(Animation animation) {
        this.blnButtonRotation = false;
        b_start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast toast = Toast.makeText(this, " " + String.valueOf((char) (((double)this.intNumber + 64) - Math.floor(((double)this.lngDegree)/(360.0d / ((double)this.intNumber)))))+"가 선택되었습니다. ",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();
        this.blnButtonRotation = true;
        b_start.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation(View v){
        if(this.blnButtonRotation){
            int ran = new Random().nextInt(360) + 3600;
            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegree, (float) (this.lngDegree + ((long)ran)), 1, 0.5f, 1, 0.5f);

            this.lngDegree = (this.lngDegree + ((long) ran)) %360;
            rotateAnimation.setDuration((long) ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);

        }
    }
}