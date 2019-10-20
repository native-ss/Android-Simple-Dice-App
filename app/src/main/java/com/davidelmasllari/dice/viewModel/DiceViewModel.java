package com.davidelmasllari.dice.viewModel;

import android.app.Application;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.davidelmasllari.dice.utils.LogUtil;
import com.davidelmasllari.dice.R;
import com.davidelmasllari.dice.enums.PrivatePrefs;
import com.davidelmasllari.dice.utils.DiceHelper;

import java.util.ArrayList;
import java.util.Random;

public class DiceViewModel extends AndroidViewModel {

    private Application application;
    private ArrayList<Integer> diceArray = new ArrayList<>();

    private MutableLiveData<Integer> diceValue = new MutableLiveData<>();

    private SharedPreferences prefs = null;
    private TextToSpeech textToSpeech;
    private int currentDiceNumber = 0;
    private boolean txtToSpeechOn;

    private boolean soundOn = true;
    private SoundPool soundPool;
    private int soundId;

    public DiceViewModel(@NonNull Application application) {
        super(application);
        loadServices(application);
        this.application = application;
        LogUtil.d("dave_log", "DiceViewModel: DiceViewModel: ");
    }

    private void loadServices(Application application) {
        prefs = PreferenceManager.getDefaultSharedPreferences(application.getApplicationContext());
        txtToSpeechOn = prefs.getBoolean(PrivatePrefs.TTS_ON, true);
        soundOn = prefs.getBoolean(PrivatePrefs.ROLLING_SOUND_ON, true);
        //initializing text to speech
        textToSpeech = new TextToSpeech(application.getBaseContext(), null);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(application, R.raw.diceroll, 1);

        final int diceColorSelection = prefs.getInt(PrivatePrefs.DICE_COLOR, 2);
        diceArray = DiceHelper.getDiceResourceArray(application, diceColorSelection);
    }

    public void rollDice() {
        LogUtil.d("dave_log", "DiceViewModel: rollDice: ");
        final Random r = new Random();
        //r.nextInt((max-min)+1)+min;
        currentDiceNumber = r.nextInt(6);//0-5
        diceValue.setValue(diceArray.get(currentDiceNumber));
        final String stringResult = String.valueOf(currentDiceNumber + 1);
        DiceHelper.textToSpeech(stringResult, textToSpeech, txtToSpeechOn);
        DiceHelper.playRollingSound(soundId, soundPool, soundOn);
    }

    public void onResumeEvent() {
        LogUtil.d("dave_log", "DiceViewModel: onResumeEvent: ");
        txtToSpeechOn = prefs.getBoolean(PrivatePrefs.TTS_ON, true);
        soundOn = prefs.getBoolean(PrivatePrefs.ROLLING_SOUND_ON, true);
        final int diceColorSelection = prefs.getInt(PrivatePrefs.DICE_COLOR, 2);
        diceArray = DiceHelper.getDiceResourceArray(application, diceColorSelection);
        diceValue.setValue(diceArray.get(currentDiceNumber));
    }

    public void onDestroyEvent() {
        LogUtil.d("dave_log", "DiceViewModel: onDestroyEvent: ");
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    public MutableLiveData<Integer> getDiceValue() {
        return diceValue;
    }

    public ArrayList<Integer> getDiceArray() {
        return diceArray;
    }
}
