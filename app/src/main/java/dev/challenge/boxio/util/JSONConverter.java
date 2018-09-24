package dev.challenge.boxio.util;

import org.json.JSONException;
import org.json.JSONObject;

import dev.challenge.boxio.model.BoxEntity;
import dev.challenge.boxio.model.ColorEntity;
import dev.challenge.boxio.model.UserEntity;

public class JSONConverter {

    private static final String USER_NAME_KEY = "user_name";
    private static final String USER_MAIL_KEY = "user_mail";
    private static final String USER_BOX_KEY = "user_box";

    private static final String BOX_SIZE_KEY = "box_size";
    private static final String BOX_COLOR_KEY = "box_color";

    private static final String COLOR_NAME_KEY = "color_name";
    private static final String COLOR_HEX_KEY = "color_hex";

    public static JSONObject createUserJson(UserEntity userEntity) {
        JSONObject userJson = new JSONObject();
        try {
            userJson.put(USER_NAME_KEY, userEntity.userName);
            userJson.put(USER_MAIL_KEY, userEntity.userMail);
            userJson.put(USER_BOX_KEY, createBoxJson(userEntity.userBox));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return userJson;
    }

    public static JSONObject createBoxJson(BoxEntity boxEntity) {
        JSONObject boxJson = new JSONObject();
        try {
            boxJson.put(BOX_SIZE_KEY, boxEntity.boxSize);
            boxJson.put(BOX_COLOR_KEY, createColorJson(boxEntity.boxColor));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return boxJson;
    }

    public static JSONObject createColorJson(ColorEntity colorEntity) {
        JSONObject colorJson = new JSONObject();
        try {
            colorJson.put(COLOR_NAME_KEY, colorEntity.colorName);
            colorJson.put(COLOR_HEX_KEY, colorEntity.colorHex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return colorJson;
    }

    public static UserEntity getUserFromJson(String userInfo) {
        try {
            JSONObject userJson = new JSONObject(userInfo);
            return new UserEntity(userJson.getString(USER_NAME_KEY), userJson.getString(USER_MAIL_KEY), getBoxFromJson(userJson.getString(USER_BOX_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BoxEntity getBoxFromJson(String boxInfo) {
        try {
            JSONObject boxJson = new JSONObject(boxInfo);
            return new BoxEntity(boxJson.getString(BOX_SIZE_KEY), getColorFromJson(boxJson.getString(BOX_COLOR_KEY)));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ColorEntity getColorFromJson(String colorInfo) {
        try {
            JSONObject colorJson = new JSONObject(colorInfo);
            return new ColorEntity(colorJson.getString(COLOR_NAME_KEY), colorJson.getString(COLOR_HEX_KEY));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
