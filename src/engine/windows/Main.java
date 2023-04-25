/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.windows;

import engine.windows.session4.Session4;

import java.io.IOException;

/**
 * @author Tdh4vn
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //Start Game Windows
        GameWindows gameWindows = new GameWindows();
        gameWindows.start();
        Session4 session4 = new Session4();
        session4.start();
    }
}
