package com.example.levis.appcompactthemenightmode;



/**
 * Created by Levis on 7/29/2017.
 */
public class Themes {

    public static Integer getTheme(int themid){
        Integer[] themes = {R.style.Light, R.style.Dark, R.style.Light_NoActionBar, R.style.Dark_NoActionBar};
        return themes[themid];
    }
}
