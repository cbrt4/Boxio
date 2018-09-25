package dev.challenge.boxio.util;

import org.json.JSONException;
import org.json.JSONObject;

import dev.challenge.boxio.model.Box;
import dev.challenge.boxio.model.Color;
import dev.challenge.boxio.model.User;

public class JSONConverter {

    private static final String USER_NAME_KEY = "user_name";
    private static final String USER_MAIL_KEY = "user_mail";
    private static final String USER_BOX_KEY = "user_box";

    private static final String BOX_SIZE_KEY = "box_size";
    private static final String BOX_COLOR_KEY = "box_color";

    private static final String COLOR_NAME_KEY = "color_name";
    private static final String COLOR_HEX_KEY = "color_hex";

    public static JSONObject createUserJson(User user) {
        JSONObject userJson = new JSONObject();
        try {
            userJson.put(USER_NAME_KEY, user.userName);
            userJson.put(USER_MAIL_KEY, user.userMail);
            userJson.put(USER_BOX_KEY, createBoxJson(user.userBox));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJson;
    }

    public static JSONObject createBoxJson(Box box) {
        JSONObject boxJson = new JSONObject();
        try {
            boxJson.put(BOX_SIZE_KEY, box.boxSize);
            boxJson.put(BOX_COLOR_KEY, createColorJson(box.boxColor));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return boxJson;
    }

    public static JSONObject createColorJson(Color color) {
        JSONObject colorJson = new JSONObject();
        try {
            colorJson.put(COLOR_NAME_KEY, color.colorName);
            colorJson.put(COLOR_HEX_KEY, color.colorHex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return colorJson;
    }

    public static User getUserFromJson(String userInfo) {
        try {
            JSONObject userJson = new JSONObject(userInfo);
            return new User(userJson.getString(USER_NAME_KEY), userJson.getString(USER_MAIL_KEY), getBoxFromJson(userJson.getString(USER_BOX_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Box getBoxFromJson(String boxInfo) {
        try {
            JSONObject boxJson = new JSONObject(boxInfo);
            return new Box(boxJson.getString(BOX_SIZE_KEY), getColorFromJson(boxJson.getString(BOX_COLOR_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Color getColorFromJson(String colorInfo) {
        try {
            JSONObject colorJson = new JSONObject(colorInfo);
            return new Color(colorJson.getString(COLOR_NAME_KEY), colorJson.getString(COLOR_HEX_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
