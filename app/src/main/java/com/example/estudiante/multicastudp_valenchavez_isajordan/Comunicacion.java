package com.example.estudiante.multicastudp_valenchavez_isajordan;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Comunicacion extends Thread{

    private boolean conectado;
    MulticastSocket socket;
    InetAddress grupo;

    OnMessage observer;


    public Comunicacion() {
        conectado = false;
    }

    @Override
    public void run() {
        try {
            while(true) {
                if(!conectado) {
                    grupo = InetAddress.getByName("228.0.0.1");
                    socket = new MulticastSocket(5000);
                    System.out.println("Intentando unirse al grupo");
                    socket.joinGroup(grupo);
                    System.out.println("Te uniste al grupo!");
                    conectado = true;
                }else {
                    //RECIBIR DATOS: Se ejecuta en bucle
                    recibirDatos();
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void recibirDatos() {
        try {
            byte[] buffer = new byte[1000];
            DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);
            System.out.println("Esperando mensaje...");
            socket.receive(datagrama);
            System.out.println("Mensaje recibido:");
            String mensaje = new String(datagrama.getData());
            observer.onReceived(mensaje);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public interface OnMessage{
        public void onReceived(String input);
    }

    public void setObserver(OnMessage observer) {
        this.observer = observer;
    }

    public void enviar(final String punto){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramPacket datagram= new DatagramPacket(punto.getBytes(),punto.getBytes().length,grupo,5000);

                    socket.send(datagram);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }


}


