package clientudp;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ricevi extends Thread {
private DatagramSocket socket;
private DatagramPacket packet;
private byte[] buffer;
    public Ricevi(DatagramSocket socket) {
        this.socket=socket;
    }
    public void run(){
       

                    // Ciclo infinito per ricevere messaggi continuamente
                    while (true) { 
                        try {
                            // Buffer per memorizzare i dati ricevuti
                            buffer = new byte[1024];
                            // Preparazione di un pacchetto per ricevere dati
                            packet = new DatagramPacket(buffer, buffer.length);
                            
                            // Ricezione del pacchetto
                            socket.receive(packet);
                            
                            // Conversione dei dati ricevuti in una stringa
                            String receivedMessage = new String(packet.getData(),
                                    packet.getOffset(), 
                                    packet.getLength());
                            
                            // Stampa del messaggio ricevuto
                            System.out.println("Messaggio ricevuto: " + receivedMessage);
                        } catch (IOException ex) {
                            Logger.getLogger(Ricevi.class.getName()).log(Level.SEVERE,
                                    null, ex);
                        }
                    }
    }
    
}
