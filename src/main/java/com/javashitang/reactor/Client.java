package com.javashitang.reactor;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author lilimin
 * @since 2022-01-27
 */
public class Client {

    public static final String host = "127.0.0.1";
    public static final int port = 8080;

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket(host, port);
        while (true) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("now is " + System.currentTimeMillis());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(in.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
