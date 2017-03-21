package com.codeclan.xwingsquadviewer;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SharedPrefsManager {

    public static final String SQUADS = "squads";

    public static Squad loadIndividualSquad(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squadString = sharedPref.getString("individualSquad", "Nothing Found");
        TypeToken<Squad> squadTypeToken = new TypeToken<Squad>(){};
        Squad squad = gson.fromJson(squadString, squadTypeToken.getType());
        return squad;
    }

}
