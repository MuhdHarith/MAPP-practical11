package edu.sp.p0123456.mapp_p11_sharedpref;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";
    // Current count.
    private int mCount = 0;
    // Current background color.
    private int mColor = 0;
    // Text view to display both count and color.
    private TextView mShowCountTextView;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "edu.sp.mapp.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views, color
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mShowCountTextView = (TextView) findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this, R.color.default_background);

        // Restore the saved state. See onSaveInstanceState() for what gets saved.
        mCount = mPreferences.getInt(COUNT_KEY, 0);
        mShowCountTextView.setText(String.format("Harith: %s", mCount));
        mColor = mPreferences.getInt(COLOR_KEY, mColor);
        mShowCountTextView.setBackgroundColor(mColor);
    }

    /**
     * Handles the onClick for the background color buttons.  Gets background color of the button
     * that was clicked and sets the textview background to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }

    /**
     * Handles the onClick for the Count button.  Increments the value of the mCount global and
     * updates the textview.
     *
     * @param view The view (Button) that was clicked.
     */
    public void countUp(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("Harith: %s", mCount));
    }

    /**
     * Saves the instance state if the activity is restarted (for example, on device rotation.)
     * Here you save the values for the count and the background color.
     *
     * @param outState The state data.
     */
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(COUNT_KEY, mCount);
        outState.putInt(COLOR_KEY, mColor);
    }*/

    /**
     * Handles the onClick for the Reset button.  Resets the global count and background
     * variables to the defaults, resets the views to those values, and clears the shared
     * preferences
     *
     * @param view The view (Button) that was clicked.
     */
    public void reset(View view) {
        // Reset count
        mCount = 0;
        mShowCountTextView.setText(String.format("Harith: %s", mCount));

        // Reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);

        //clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COLOR_KEY, mColor);
        preferencesEditor.apply();
    }
}
