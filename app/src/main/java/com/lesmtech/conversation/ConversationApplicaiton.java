package com.lesmtech.conversation;

import android.app.Application;

import com.parse.Parse;

/**
 * @author Rindt
 * @version 0.1
 * @since 6/18/15
 */
public class ConversationApplicaiton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "Vk3AaePCuYeTaJYZEiC3A4GAiu5xjbkHysnvSZs0", "dPKvxBph0TcsF7t9StVDPfBbgUpzXGGvx8luIcRg");
    }
}
