package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // client
        Socket s = new Socket("LocalHost", 3000);
        System.out.println("il client si è collegato");

        Scanner in = new Scanner(new InputStreamReader(System.in));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        BufferedReader rispostaServer = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String n = "";
        String risultatoOperazione = "";
        String nTentativi = "";

        do {

            System.out.println("\n" + " -------INDOVINA IL NUMERO---------");
            System.out.println("scrivi il numero da indovinare: ");
            n = in.nextLine();
            out.writeBytes(n + "\n");
            risultatoOperazione = rispostaServer.readLine();
            if(risultatoOperazione.equals("<")){
                System.out.println("numero troppo grande");
            }else{
                if (risultatoOperazione.equals(">")) {
                    System.out.println("numero troppo piccolo");
                }else{
                    if(risultatoOperazione.equals(null)){
                        System.out.println("errore nell'inserimento dei dati");
                    }
                }
            }
            //controllo se il numero esce dall'intervallo
            if(!risultatoOperazione.equals(">") && !risultatoOperazione.equals("<") && !risultatoOperazione.equals("=")  ){
                System.out.println(risultatoOperazione + "\n");
            }

 
            
        } while (!risultatoOperazione.equals("="));
        
        System.out.println("Complimenti hai vinto!!! \n");
        System.out.println("Numero di tentativi: ");

        nTentativi = rispostaServer.readLine();
        System.out.println(nTentativi);

        s.close();
        in.close();
        System.out.println("fine connessione");
    }
}