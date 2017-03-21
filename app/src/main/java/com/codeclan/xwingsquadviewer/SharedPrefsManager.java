package com.codeclan.xwingsquadviewer;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

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

    public static ArrayList<Squad> loadSquadList(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        ArrayList<Squad> squadList = gson.fromJson(squads, squadArrayList.getType());
        return squadList;
    }

    public static ArrayList<Squad> loadFilteredList(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("filteredList", "Nothing found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        ArrayList<Squad> filteredList = gson.fromJson(squads, squadArrayList.getType());
        return filteredList;
    }

    public static void saveIndividualSquad(Squad squad, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("individualSquad", gson.toJson(squad));
        editor.apply();
    }

    public static void saveSquadList(ArrayList<Squad> squadList, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();
    }

    public static void saveFilteredList(ArrayList<Squad> filteredSquads, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("filteredList", gson.toJson(filteredSquads));
        editor.apply();
    }

}
