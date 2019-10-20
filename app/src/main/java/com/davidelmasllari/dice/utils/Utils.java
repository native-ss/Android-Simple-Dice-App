package com.davidelmasllari.dice.utils;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Utils {


    public static void analyticsEvent(final Context context, final String eventLogName, final String id,
                                      final String name, final String contentType) {
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        firebaseAnalytics.logEvent(eventLogName, bundle);
    }
}
