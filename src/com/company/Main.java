package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {

        try {
            Socket clientSocket = new Socket ("212.3.141.67",1002);
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            byte[] bytes1 = { 0x5a,0x00,0x3b,0x10 }; //проверка канала связи
            System.out.println("проверка канала связи");
            out.write(bytes1);
            int c, count=0;
            while(( c = in.read()) != -1) {
                count++;
                System.out.print(Integer.toHexString(c));
                if (count ==4) break;
            }
            byte[] bytes2 = {0x5a, 0x01, 0x02, 0x02, 0x02, 0x02, 0x02, 0x02, 0x02, 0x32, (byte) 0xa4};//открытие канала связи
            out.write(bytes2);
            count=0;
            System.out.println("\nоткрытие канала связи");
            while(( c = in.read()) != -1) {
                count++;
                System.out.print(Integer.toHexString(c));
                if (count ==4) break;
            }

            byte[] bytes3 = {0x5a, 0x08, 0x12, (byte) 0xd6, 0x1e};//фиксация данных
            out.write(bytes3);
            count=0;
            System.out.println("\nчтение времени контроля");
            while(( c = in.read()) != -1) {
                count++;
                System.out.print(Integer.toHexString(c));
                if (count ==4) break;
            }

            byte[] bytes4 = {0x5a, 0x05, 0x00, 0x00, 0x02, (byte) 0xfd};//чтение показаний счетчика
            out.write(bytes4);
            count=0;
            System.out.println("\nчтение показаний счетчика");
            while(( c = in.read()) != -1) {
                count++;
                System.out.print(c+" ");
               // System.out.print(Integer.toHexString(c));
                if (count ==150) break;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
