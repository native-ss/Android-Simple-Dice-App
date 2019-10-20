package com.davidelmasllari.dice.enums;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({PrivatePrefs.TTS_ON, PrivatePrefs.ROLLING_SOUND_ON, PrivatePrefs.DICE_COLOR, PrivatePrefs.DICE_COLOR})
public @interface PrivatePrefs {
    String TTS_ON = "tts_sound";
    String ROLLING_SOUND_ON = "rolling_sound_switch";
    String DICE_COLOR = "diceColPref";
}