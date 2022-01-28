/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ajedrez;

import javax.swing.ImageIcon;

/**
 *
 * @author balaz
 */
public class Main {
    public static void main(String[] args) {
        Ventana tablero = new Ventana();  //-- Instanciando mi tablero
        tablero.setVisible(true); //-- Mostrando el tablero
        Acerca acerca = new Acerca();
        acerca.setVisible(true);
    }
}
