package dev.challenge.boxio.util;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dev.challenge.boxio.model.Box;
import dev.challenge.boxio.model.Color;
import dev.challenge.boxio.model.User;

public class JSONConverter {

    private final String USER_NAME_KEY = "user_name";
    private final String USER_MAIL_KEY = "user_mail";
    private final String USER_BOX_KEY = "user_box";

    private final String BOX_SIZE_KEY = "box_size";
    private final String BOX_COLOR_KEY = "box_color";

    private final String COLOR_NAME_KEY = "color_name";
    private final String COLOR_HEX_KEY = "color_hex";

    @Inject
    public JSONConverter() {
    }

    public JSONObject createUserJson(User user) {
        JSONObject userJson = new JSONObject();
        try {
            userJson.put(USER_NAME_KEY, user.getUserName());
            userJson.put(USER_MAIL_KEY, user.getUserMail());
            userJson.put(USER_BOX_KEY, createBoxJson(user.getUserBox()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJson;
    }

    public JSONObject createBoxJson(Box box) {
        JSONObject boxJson = new JSONObject();
        try {
            boxJson.put(BOX_SIZE_KEY, box.getBoxSize());
            boxJson.put(BOX_COLOR_KEY, createColorJson(box.getBoxColor()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return boxJson;
    }

    public JSONObject createColorJson(Color color) {
        JSONObject colorJson = new JSONObject();
        try {
            colorJson.put(COLOR_NAME_KEY, color.getColorName());
            colorJson.put(COLOR_HEX_KEY, color.getColorHex());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return colorJson;
    }

    public User getUserFromJson(String userInfo) {
        try {
            JSONObject userJson = new JSONObject(userInfo);
            return new User(userJson.getString(USER_NAME_KEY), userJson.getString(USER_MAIL_KEY), getBoxFromJson(userJson.getString(USER_BOX_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Box getBoxFromJson(String boxInfo) {
        try {
            JSONObject boxJson = new JSONObject(boxInfo);
            return new Box(Box.BoxSize.valueOf(boxJson.getString(BOX_SIZE_KEY)), getColorFromJson(boxJson.getString(BOX_COLOR_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Color getColorFromJson(String colorInfo) {
        try {
            JSONObject colorJson = new JSONObject(colorInfo);
            return new Color(colorJson.getString(COLOR_NAME_KEY), colorJson.getString(COLOR_HEX_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
