package com.davidelmasllari.dice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.davidelmasllari.dice.enums.PrivatePrefs;
import com.davidelmasllari.dice.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    private ArrayList<ImageView> diceImages = new ArrayList<>();
    private SharedPreferences prefs;
    private static final int DICE_POS_BLACK = 0;
    private static final int DICE_POS_WHITE = 1;
    private static final int DICE_POS_RED = 2;
    private static final int DICE_POS_GOLD = 3;
    private int SELECTED_BG_COLOR;
    private static final int CLEAR_BG_COLOR = Color.TRANSPARENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.settings));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final int selectedDice = prefs.getInt(PrivatePrefs.DICE_COLOR, 2);
        diceImages.add((ImageView) findViewById(R.id.dice_black));
        diceImages.add((ImageView) findViewById(R.id.dice_white));
        diceImages.add((ImageView) findViewById(R.id.dice_red));
        diceImages.add((ImageView) findViewById(R.id.dice_gold));
        SELECTED_BG_COLOR = getResources().getColor(R.color.colorGoldenTransparent);
        diceImages.get(selectedDice).setBackgroundColor(SELECTED_BG_COLOR);

        final Switch rollingSoundSwitch = findViewById(R.id.rolling_sound_switch);
        rollingSoundSwitch.setChecked(prefs.getBoolean(PrivatePrefs.ROLLING_SOUND_ON, true));
        rollingSoundSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSwitchPref(PrivatePrefs.ROLLING_SOUND_ON,rollingSoundSwitch.isChecked());
            }
        });

        final Switch ttsSwitch = findViewById(R.id.tts_switch);
        ttsSwitch.setChecked(prefs.getBoolean(PrivatePrefs.TTS_ON, true));
        ttsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSwitchPref(PrivatePrefs.TTS_ON,ttsSwitch.isChecked());
            }
        });

    }

    public void privacyOnClick(View view) {
        Intent i = new Intent(SettingsActivity.this, PrivacyPolicyActivity.class);
        startActivity(i);
    }

    public void diceClickBlack(View view) {
        selectDiceColor(DICE_POS_BLACK);
    }

    public void diceClickWhite(View view) {
        selectDiceColor(DICE_POS_WHITE);
    }

    public void diceClickRed(View view) {
        selectDiceColor(DICE_POS_RED);
    }

    public void diceClickGold(View view) {
        selectDiceColor(DICE_POS_GOLD);
    }

    private void selectDiceColor(final int position) {
        int selectedDice = prefs.getInt(PrivatePrefs.DICE_COLOR, 2);
        diceImages.get(selectedDice).setBackgroundColor(CLEAR_BG_COLOR);
        diceImages.get(position).setBackgroundColor(SELECTED_BG_COLOR);
        prefs.edit().putInt(PrivatePrefs.DICE_COLOR, position).apply();
    }

    private void saveSwitchPref(@PrivatePrefs final String prefString, final boolean isChecked){
        if (isChecked) {
            prefs.edit().putBoolean(prefString, true).apply();
        } else {
            prefs.edit().putBoolean(prefString, false).apply();
        }
    }

    public void fabOnClick(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(Constants.EMAIL_MIME_TYPE);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.EMAIL_CONTACT});
        intent.putExtra(Intent.EXTRA_SUBJECT, Constants.EMAIL_SUBJECT);
        intent.putExtra(Intent.EXTRA_TEXT, Constants.EMAIL_TEXT);
        startActivity(Intent.createChooser(intent, ""));
    }
}
