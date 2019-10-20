package com.davidelmasllari.dice.observers;

import android.app.Activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.davidelmasllari.dice.utils.LogUtil;
import com.davidelmasllari.dice.utils.Utils;

public class DiceLifeCycleObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreateEvent(LifecycleOwner lifecycleOwner) {
        Activity activity = null;
        if (lifecycleOwner instanceof Activity) {
            activity = (Activity) lifecycleOwner;
            Utils.analyticsEvent(activity, "ON_CREATE", "id_onCreate",
                    "Activity Created", "appOpened");
            LogUtil.d("dave_log", "DiceLifeCycleObserver: onCreateEvent: "
                    + activity.getClass().getName());
        } else {
            LogUtil.d("dave_log", "DiceLifeCycleObserver: onCreateEvent: "
                    + lifecycleOwner.getClass().getName());
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStartEvent() {
        LogUtil.d("dave_log", "DiceLifeCycleObserver: onStartEvent: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResumeEvent() {
        LogUtil.d("dave_log", "DiceLifeCycleObserver: onResumeEvent: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPauseEvent() {
        LogUtil.d("dave_log", "DiceLifeCycleObserver: onPauseEvent: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStopEvent() {
        LogUtil.d("dave_log", "DiceLifeCycleObserver: onStopEvent: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroyEvent() {
        LogUtil.d("dave_log", "DiceLifeCycleObserver: onDestroyEvent: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAnyEvent() {
    }
}
