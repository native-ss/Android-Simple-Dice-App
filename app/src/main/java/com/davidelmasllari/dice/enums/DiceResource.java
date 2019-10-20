package com.davidelmasllari.dice.enums;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({DiceResource.BLACK, DiceResource.WHITE, DiceResource.RED, DiceResource.GOLD})
public @interface DiceResource {
    String BLACK = "dice_black_";
    String WHITE = "dice_white_";
    String RED = "dice_red_";
    String GOLD = "dice_gold_";
}
