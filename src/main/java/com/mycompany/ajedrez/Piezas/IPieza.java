/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ajedrez.Piezas;

import javax.swing.JButton;
import javax.swing.ImageIcon;

/**
 *
 * @author balaz
 */
public interface IPieza {
    /**
     *
     * @param nameIcon
     * @param casillas
     * @param row
     * @param column
     * @return
     */
    public JButton[][] posibleMovimiento(String nameIcon, JButton[][] casillas, int row, int column);
}
