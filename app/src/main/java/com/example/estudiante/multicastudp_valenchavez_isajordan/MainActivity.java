package com.example.estudiante.multicastudp_valenchavez_isajordan;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Comunicacion.OnMessage {
    DrawView lienzo;
    Comunicacion com;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///HABILITAR MULTICAST

        WifiManager wm=(WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiManager.MulticastLock multicastLock = wm.createMulticastLock("mydebuginfo");
        multicastLock.acquire();

        lienzo= findViewById(R.id.lienzo);
        com= new Comunicacion();
        com.setObserver(this);
        com.start();

        //permite saber en qué coordenada lo toqué
        lienzo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        lienzo.addPoint(motionEvent.getX(),motionEvent.getY());
                        int x= (int) motionEvent.getX();
                        int y= (int) motionEvent.getY();
                        com.enviar(x + ","+y);

                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;

                }

                return true;
            }
        });
    }

    @Override
    public void onReceived(String input) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
