package dev.challenge.boxio.view.activities;

import org.json.JSONObject;

import dev.challenge.boxio.view.AbstractView;

public interface MainActivityView extends AbstractView {

    void onUserProfileSubmitted(JSONObject userJson);
}
