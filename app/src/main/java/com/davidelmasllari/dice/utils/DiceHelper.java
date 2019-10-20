package com.davidelmasllari.dice.utils;

import android.content.Context;
import android.content.res.Resources;
import android.media.SoundPool;
import android.speech.tts.TextToSpeech;

import com.davidelmasllari.dice.enums.DiceResource;

import java.util.ArrayList;

public class DiceHelper {

    public static void textToSpeech(final String string, final TextToSpeech tts, final boolean txtToSpeechOn) {
        if (txtToSpeechOn) {
            tts.speak(string, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            LogUtil.d("dave_log", "DiceHelper: textToSpeech: tts off");
        }
    }

    public static void playRollingSound(final int soundId, final SoundPool sp, final boolean soundOn) {
        if (soundOn) {
            sp.play(soundId, 1, 1, 0, 0, 1);
        } else {
            LogUtil.d("dave_log", "DiceHelper: playRollingSound: soundOff");
        }
    }

    public static ArrayList<Integer> getDiceResourceArray(final Context context, final int diceSelection) {
        final ArrayList<Integer> diceArray = new ArrayList<>();
        final Resources resources = context.getResources();
        final String resourceName;
        if (diceSelection == 0) {
            resourceName = DiceResource.BLACK;
        } else if (diceSelection == 1) {
            resourceName = DiceResource.WHITE;
        } else if (diceSelection == 2) {
            resourceName = DiceResource.RED;
        } else if (diceSelection == 3) {
            resourceName = DiceResource.GOLD;
        } else {
            resourceName = null;
        }

        for (int i = 1; i <= 6; i++) {
            if (resourceName != null) {
                final int resourceId = resources.getIdentifier(resourceName + i, "drawable",
                        context.getPackageName());
                diceArray.add(resourceId);
            }
        }

        return diceArray;
    }
}
