/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatserverudp;

import java.net.DatagramSocket;

public class ChatServerUdp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      int porta=11234;//porta del server
      new Server(porta);
    }
    
}


