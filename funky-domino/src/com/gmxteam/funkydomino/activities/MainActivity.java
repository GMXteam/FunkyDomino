package com.gmxteam.funkydomino.activities;

import android.os.Bundle;
import com.gmxteam.funkydomino.R;
import com.gmxteam.funkydomino.activities.abstracts.JBox2DActivity;

/**
 * Activité principale de l'application Android.
 * Elle sera construite avec JBox2D et permettera d'accéder aux autres activités.
 * @author Guillaume Poirier-Morency
 */
public class MainActivity extends JBox2DActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        
    }
}
