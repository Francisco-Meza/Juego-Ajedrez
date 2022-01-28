/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ajedrez;
import com.mycompany.ajedrez.Piezas.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author balaz
 */
public class Pieza implements IPieza, IConstantes{
    private final List<Integer> movimientoX;//-- Que guardara el fila de las casillas con posibilidad
    private final List<Integer> movimientoY;//-- guardara la columna de las casillas
    private boolean bo;
    private Color color;//-- Color anteiror acomdodar las casillas
    private int column1;
    private final Color cafe;//-- Color cafede casillas
    private boolean confirmacionMovimiento;
    private boolean turno;//-- Si es verdad sera turno de balnco si no es de negro
    private String[] colorPiezaContraria; 
    private String colorPieza;
    private int xB,yB;
    private int xN,yN;
    //------------------------------------- CONSTRUCTOR ---------------------------------------
    public Pieza(){
     movimientoX = new ArrayList<>();//-- Inicializamos las listas
     movimientoY = new ArrayList<>();
     cafe = new Color(205,133,63);//-- Creo el color cafe
     turno = true;//-- Iniciamos con piezas blancas
     xB=0;
     yB=0;
     xN=0;
     yN=0;
    }
//-------------------- METODO ABSTRACTO ---------------------------------------------------
    //-- Dira cuales son mis posibles movimientos segun la figura
    @Override
    public JButton[][] posibleMovimiento(String nameIcon, JButton[][] casillas, int row, int column) {
        movimientoX.clear();//
        movimientoY.clear();//-. Limpiamos las listas despues de cada pulso
        movimientoX.add(row);//-- Añado la casilla donde esta la ficha pulsada.
        movimientoY.add(column);
        if(turno){//-- Verifico turnos si es verdad es blanco de lo contario toca a negro
            switch(nameIcon){//-- Evaluo que pieza fue la seleccionada
                case RUTA_BLANCAS+"Peon.png"->peonBlanco(casillas, row, column);
                case RUTA_BLANCAS+"Alfil.png"->alfil(casillas, row, column);
                case RUTA_BLANCAS+"Torre.png"->torre(casillas, row, column);
                case RUTA_BLANCAS+"Reina.png"->reina(casillas, row, column);
                case RUTA_BLANCAS+"Rey.png"->rey(casillas, row, column);
                case RUTA_BLANCAS+"Caballo.png"-> caballo(casillas, row, column);
            }
        }else{
            switch(nameIcon){//-- Evaluo que pieza fue la seleccionada
                case RUTA_NEGRAS+"Peon.png"->peonNegro(casillas, row, column);
                case RUTA_NEGRAS+"Alfil.png"->alfil(casillas, row, column);
                case RUTA_NEGRAS+"Torre.png"->torre(casillas, row, column);
                case RUTA_NEGRAS+"Reina.png"->reina(casillas, row, column);
                case RUTA_NEGRAS+"Rey.png"->rey(casillas, row, column);
                case RUTA_NEGRAS+"Caballo.png"->caballo(casillas, row, column);
            }
        }
        return casillas;
    }
    //--------------------------------- METODO QUE HACE MOVIMIENTOS DE PIEZAS-----------------------
    public JButton[][] movimiento(JButton[][] casillas, int row, int column, JLabel[][] panelIzq, JLabel[][] panelDer){
        if(movimientoX.size() > 1){//-- Veo si la lista de posibibles casillas no esta vacia
            //-- Revisare si la casilla pulsada es una casilla de posible movimiento
            if(casillas[row][column].getIcon() != null){//-- Veo si hay seleccion o pieza(propia o enemiga)
              if(casillas[row][column].getIcon() == SELECCION || casillas[row][column].getBackground()== Color.red){ //-- Verifico que la casilla seleccionada sea de posiblw movimiento
                  //-- Recorro el arreglo con el for-each para saber cual pieza es la pulsada------
                  for (String PIEZAS1 : PIEZAS) {
                      //--- EN CASO DE SER PIEZA BLANCAS O NEGRAS ------------------------------------------
                      boolean blanco = casillas[movimientoX.get(0)][movimientoY.get(0)].getIcon().toString().equals(RUTA_BLANCAS + PIEZAS1);
                      boolean negro = casillas[movimientoX.get(0)][movimientoY.get(0)].getIcon().toString().equals(RUTA_NEGRAS + PIEZAS1);
                      if (blanco || negro) {//-- Verifico cual fue la pieza pulsada a mover
                          if(casillas[row][column].getBackground()== Color.red){//-- Verifico si es posible a comer
                              if("Rey.png".equals(getNombrePieza(casillas, row, column))){
                                  for(int i = 0; i<8; i++){
                                      for(int j =0; j<8; j++){
                                          casillas[i][j].setEnabled(false);
                                      }
                                  }
                                  if(blanco){
                                      JOptionPane.showMessageDialog(null, "¡Felicidades jugador blanco has ganado!");
                                  }else{
                                      JOptionPane.showMessageDialog(null,"¡Felicidades jugador negro has ganado!");
                                  }
                                }else{
                                    PintarCasillas(casillas, row, column);//-- regreso el color a la casilla
                                if("Piezas Blancas".equals(getColorPieza(casillas, row, column))){//-- Verifico de que color es
                                    panelIzq[xB][yB].setIcon(casillas[row][column].getIcon());//-- La muevo a su lado que es 
                                    xB = (yB+1 > 2)? xB+1: xB;
                                    yB = (yB+1 > 2)? 0 : yB+1;//-- Y avanzo el indice
                                }else{
                                    panelDer[xN][yN].setIcon(casillas[row][column].getIcon());//-- La muevo a su lado que es
                                    xN = (yN+1 > 2)? xN+1: xN;
                                    yN = (yN+1 > 2)? 0 : yN+1;//-- Y avanzo el indice 
                                }
                              }
                          }
                          if(blanco)casillas[row][column].setIcon(new ImageIcon(RUTA_BLANCAS + PIEZAS1)); //-- Muevo el peon a la casilla pulsada
                          else if (negro) casillas[row][column].setIcon(new ImageIcon(RUTA_NEGRAS + PIEZAS1)); //-- Muevo el peon a la casilla pulsada
                          casillas[movimientoX.get(0)][movimientoY.get(0)].setIcon(null);//-- Y quito de la casilla anterior
                          confirmacionMovimiento = true;//-- Confirmo movimiento*/
                          turno = !turno;//-- SI hay movimiento toca al turno contrario
                          break;//-- Salgo del bucle porque ya se movio la pieza
                      }
                  }
                }
                else{//-- En caso de que no se pulso una seleccion
                confirmacionMovimiento = false;//-- No se confirma movimiento
              }
            }
            for (int i = 1; i < movimientoX.size(); i++) {//-- Recorre la lista de las casillas posibles
                row = movimientoX.get(i);//-- Obtiene la fila actual
                column = movimientoY.get(i);//-- Obtiene la columna actual
                bo = (casillas[row][column].getBackground() == Color.RED);
                if(bo){
                    PintarCasillas(casillas, row, column);//-- Pinta la casilla a normal
                }else if(casillas[row][column].getIcon() == SELECCION){// Si la casilla seleccionada es igual a una seleccion
                    casillas[row][column].setIcon(null);//-- Borra la seleccion
                }
            }
        }
        return casillas;
    }
    //--------------------------- METODO QUE DEVOLVERA EL COLOR ORIGINAL A LA CASILLA ----------------
    public void PintarCasillas(JButton[][] casillas, int row, int column){
        column1 = (column-1 == -1)? 2:column;//-- si es la primer casilla que escoja la derecha y si no escoje la izquierda
        this.color = (casillas[row][column1-1].getBackground() == Color.WHITE)? cafe : Color.WHITE;//-- Verifico el color anterior y acomodo al color que debe ser
        casillas[row][column].setBackground(color);//-- Devuelvo el color original
    }
    //------------------------- METODO QUE REGRESA SI HUBO ALGUN MOVIMIENTO -------------------------
    public boolean getConfirmacioMovimiento(){
        return confirmacionMovimiento;
    }
    //------------------- METODO QUE REGRESA EL TURNO ACTUAL ----------------------------------------
    public boolean getTurno(){
        return turno;
    }
    //------------------------ METODO QUE REGRESA EL COLOR DE LA PIEZA EN STRING -------------------
    public String getColorPieza(JButton[][] casillas, int row, int column){
        colorPiezaContraria = casillas[row][column].getIcon().toString().split("/");
        return colorPiezaContraria[1];
    }
    //--------------------- METODO QUE REGRESA EL NOMBE DE LA PIEZA ---------------------------
    public String getNombrePieza(JButton[][] casillas, int row, int column){
        colorPiezaContraria = casillas[row][column].getIcon().toString().split("/");
        return colorPiezaContraria[2];
    }
    //-------------------------- METODOS DE PIEZAS ------------------------------------------------
    public void peonBlanco(JButton[][] casillas, int row, int column){//-- PEON BLANCO
        if(column-1 != -1 && row-1 != -1){// Verifico si la casilla existe en diagonal izquierda
            if(casillas[row-1][column-1].getIcon() != null){//-- Si hay icono hay figura
                colorPieza = getColorPieza(casillas, row-1, column-1);//-- Obtengo el color de la pieza
                if("Piezas Negras".equals(colorPieza)){//-- Verifico que sea pieza contraria
                    casillas[row-1][column-1].setBackground(Color.red);//-- pinto de rojo como opcion a comer
                    movimientoX.add(row-1);//-- guardo la casilla seleccionada para futuro
                    movimientoY.add(column-1);//
                }
            }
        }
        if(column+1 != 8 && row-1 != -1){// Movimiento de comer diagonal. derecha verifico que exista la casilla
            if(casillas[row-1][column+1].getIcon() != null ){// si hay icono hay pieza
                colorPieza = getColorPieza(casillas, row-1, column+1);//-- Obtengo el color de la pieza
                if("Piezas Negras".equals(colorPieza)){//-- Verifico que sea pieza contraria
                    casillas[row-1][column+1].setBackground(Color.red);//-- pinto de rojo como opcion a comer
                    movimientoX.add(row-1);//-- guardo la casilla seleccionada para futuro
                    movimientoY.add(column+1);//
                }
            }
        }
        if(row-1 != -1){//-- verifico que no salga del tablero en algun punto
            if(row != 6){// si no estoy en la fila 6 significa que ya no es el primer movimiento
                if(casillas[row-1][column].getIcon() == null){//-- verifico que no haya pieza adelante
                    casillas[row-1][column].setIcon(SELECCION);//-- pongo la seleccion
                    movimientoX.add(row-1);//-- guardo casilla
                    movimientoY.add(column);
                }
            }else{
                if(casillas[row-1][column].getIcon() == null){
                    casillas[row-1][column].setIcon(SELECCION);//-- Pinto
                    movimientoX.add(row-1);//-- Guardo las casillas
                    movimientoY.add(column);
                    if(casillas[row-2][column].getIcon() == null){//-- En caso de que haya pieza dos casillas adelante vemos si en una no hay
                        casillas[row-2][column].setIcon(SELECCION);//-- pinto
                        movimientoX.add(row-2);//-- Guardo casillas
                        movimientoY.add(column);
                    }
                }
            } 
        }
    }
//-------------------------- PEON NEGRO --------------------------------------------------------
    private void peonNegro(JButton[][] casillas, int row, int column) {//-- PEON NEGRO
        if(column-1 != -1 && row+1 != 8){// Verifico si la casilla existe en diagonal izquierda
            if(casillas[row+1][column-1].getIcon() != null){//-- Si hay icono hay figura
                colorPieza = getColorPieza(casillas, row+1, column-1);//-- Obtengo el color de la pieza
                if("Piezas Blancas".equals(colorPieza)){//-- Verifico que sea pieza contraria
                    casillas[row+1][column-1].setBackground(Color.red);//-- pinto de rojo como opcion a comer
                    movimientoX.add(row+1);//-- guardo la casilla seleccionada para futuro
                    movimientoY.add(column-1);//                    
                }
            }
        }
        if(column+1 != 8 && row+1 != 8){// Movimiento de comer diagonal. derecha verifico que exista la casilla
            if(casillas[row+1][column+1].getIcon() != null ){// si hay icono hay pieza
                colorPieza = getColorPieza(casillas, row+1, column+1);//-- Obtengo el color de la pieza
                if("Piezas Blancas".equals(colorPieza)){//-- Verifico que sea pieza contraria
                    casillas[row+1][column+1].setBackground(Color.red);//-- pinto de rojo como opcion a comer
                    movimientoX.add(row+1);//-- guardo la casilla seleccionada para futuro
                    movimientoY.add(column+1);//                    
                }
            }
        }
        if(row+1 != 8){//-- verifico que no salga del tablero en algun punto
            if(row != 1){// si no estoy en la fila 6 significa que ya no es el primer movimiento
                if(casillas[row+1][column].getIcon() == null){//-- verifico que no haya pieza adelante
                    casillas[row+1][column].setIcon(SELECCION);//-- pongo la seleccion
                    movimientoX.add(row+1);//-- guardo casilla
                    movimientoY.add(column);
                }
            }else{
                if(casillas[row+1][column].getIcon() == null){
                    casillas[row+1][column].setIcon(SELECCION);//-- Pinto
                    movimientoX.add(row+1);//-- Guardo las casillas
                    movimientoY.add(column);
                    if(casillas[row+2][column].getIcon() == null){//-- En caso de que haya pieza dos casillas adelante vemos si en una no hay
                        casillas[row+2][column].setIcon(SELECCION);//-- pinto
                        movimientoX.add(row+2);//-- Guardo casillas
                        movimientoY.add(column);
                    }
                }
            } 
        }
    }
    //--------------- METODO DEL ALFIL ---------------------
    public void alfil(JButton[][] casillas, int row, int column){
        int r,c;//-- Declaro las filas y columnas que recorreran el ciclo
        int a=0,b=0;//--segun la vuelta revisara las 4 diagonales
        for(int i =0; i<4;i++){//-- recorre las 4 vueltas
            r = row;//-- Inicializamos
            c = column;
            switch(i){//-- Evaluo en que vuelta estoy
                case 0->{//En la primer reeccorera diagonal superior derecha
                    a = -1;
                    b = 1;
                }
                case 1->{//-- Segunda vuelta recorre digonal superior izquierda
                    a = -1;
                    b = -1;
                }
                case 2->{//-- terceara vuelta recorre digaonal inferior izquierda
                    a = 1;
                    b = -1;
                }
                case 3->{//-- en cuarta vuelta recorre diagonal inferior derecha
                    a = 1;
                    b = 1;
                }
            }
            while(true){//-- entramos a un bucle infinito para generar la lista de posiciones
                r = r+a;//-- decimos que la fila + a sera la nueva posicion
                c = c+b;//-- decimos que la columna + b sera la nueva posicion
                if(r == -1 || r == 8 || c== -1 || c == 8){//-- Evaluamos que no salga del limite del tablero
                    break;//-- si sale del tablero para el bucle
                }else{//-- si esta dentro del tablero la casilla
                    if(casillas[r][c].getIcon() != null){//-- verficar que no haya una pieza en el camino
                        if("Piezas Blancas".equals(getColorPieza(casillas, row, column))){
                            if("Piezas Negras".equals(getColorPieza(casillas, r, c))){
                                casillas[r][c].setBackground(Color.red);
                                movimientoX.add(r);//-- añadp la casilla a la lista
                                movimientoY.add(c);
                            }
                        }else{
                            if("Piezas Blancas".equals(getColorPieza(casillas, r, c))){
                                casillas[r][c].setBackground(Color.red);
                                movimientoX.add(r);//-- añadp la casilla a la lista
                                movimientoY.add(c);
                            }
                        }
                        break;
                    }else{//-- si no hay pieza generar una seleccion
                        casillas[r][c].setIcon(SELECCION);//-- Pinto la seleccion
                        movimientoX.add(r);//-- añadp la casilla a la lista
                        movimientoY.add(c);
                    }
                }
            }
        }
    }
    //------------------ TORRE ------------------------------------------------------------
    public void torre(JButton[][] casillas, int row, int column){
        int r,c;//-- Declaro las filas y columnas que recorreran el ciclo
        int a=0,b=0;//--segun la vuelta revisara las 4 diagonales
        for(int i =0; i<4;i++){//-- recorre las 4 vueltas
            r = row;//-- Inicializamos
            c = column;
            switch(i){//-- Evaluo en que vuelta estoy
                case 0->{//En la primer reeccorera arriba
                    a = -1;
                    b = 0;
                }
                case 1->{//-- Segunda vuelta recorre izquierda
                    a = 0;
                    b = -1;
                }
                case 2->{//-- terceara vuelta recorre abajo
                    a = 1;
                    b = 0;
                }
                case 3->{//-- en cuarta vuelta recorre derecha
                    a = 0;
                    b = 1;
                }
            }
            while(true){//-- entramos a un bucle infinito para generar la lista de posiciones
                r = r+a;//-- decimos que la fila + a sera la nueva posicion
                c = c+b;//-- decimos que la columna + b sera la nueva posicion
                if(r == -1 || r == 8 || c== -1 || c == 8){//-- Evaluamos que no salga del limite del tablero
                    break;//-- si sale del tablero para el bucle
                }else{//-- si esta dentro del tablero la casilla
                    if(casillas[r][c].getIcon() != null){//-- verficar que no haya una pieza en el camino
                        if("Piezas Blancas".equals(getColorPieza(casillas, row, column))){
                            if("Piezas Negras".equals(getColorPieza(casillas, r, c))){
                                casillas[r][c].setBackground(Color.red);
                                movimientoX.add(r);//-- añadp la casilla a la lista
                                movimientoY.add(c);
                            }
                        }else{
                            if("Piezas Blancas".equals(getColorPieza(casillas, r, c))){
                                casillas[r][c].setBackground(Color.red);
                                movimientoX.add(r);//-- añadp la casilla a la lista
                                movimientoY.add(c);
                            }
                        }
                        break;
                    }else{//-- si no hay pieza generar una seleccion
                        casillas[r][c].setIcon(SELECCION);//-- Pinto la seleccion
                        movimientoX.add(r);//-- añadp la casilla a la lista
                        movimientoY.add(c);
                    }
                }
            }
        }
    }
    //-------------------------------- REINA ---------------------------------------
    public void reina(JButton[][] casillas, int row, int column){
        torre(casillas, row, column);
        alfil(casillas, row, column);
    }
    public void rey(JButton[][] casillas, int row, int column){
        int r,c;//-- Declaro las filas y columnas que recorreran el ciclo
        int a=0,b=0;//--segun la vuelta revisara las 4 diagonales
        
        for(int i =0; i<8;i++){//-- recorre las 4 vueltas
            r = row;//-- Inicializamos
            c = column;
            switch(i){//-- Evaluo en que vuelta estoy
                case 0->{//En la primer reeccorera diagonal superior derecha
                    a = -1;
                    b = 1;
                }
                case 1->{//-- Segunda vuelta recorre digonal superior izquierda
                    a = -1;
                    b = -1;
                }
                case 2->{//-- terceara vuelta recorre digaonal inferior izquierda
                    a = 1;
                    b = -1;
                }
                case 3->{//-- en cuarta vuelta recorre diagonal inferior derecha
                    a = 1;
                    b = 1;
                }
                case 4->{//En la primer reeccorera arriba
                    a = -1;
                    b = 0;
                }
                case 5->{//-- Segunda vuelta recorre izquierda
                    a = 0;
                    b = -1;
                }
                case 6->{//-- terceara vuelta recorre abajo
                    a = 1;
                    b = 0;
                }
                case 7->{//-- en cuarta vuelta recorre derecha
                    a = 0;
                    b = 1;
                }
            }
            r = r+a;//-- decimos que la fila + a sera la nueva posicion
            c = c+b;//-- decimos que la columna + b sera la nueva posicion
            generaListaMovimientos(casillas, row, column, r, c);
        }
    }
    public void caballo(JButton[][] casillas, int row, int column){
        int r,c;//-- Declaro las filas y columnas que recorreran el ciclo
        int a=0,b=0;//--segun la vuelta revisara las 4 diagonales
        for(int i = 0;i<8; i++){
            r = row;//-- Inicializamos
            c = column;
            switch(i){
                case 0->{//-- revisa arriba-derecha
                    a=-2;
                    b=1;
                }
                case 1->{//-- revisa arriba-izquiersa
                    a=-2;
                    b=-1;
                }
                case 2->{//-- revisa izquierda-arriba
                    a=-1;
                    b=-2;
                }
                case 3->{//-- revisa izquierda-abajo
                    a=1;
                    b=-2;
                }
                case 4->{//-- revisa abajo-izquierda
                    a=2;
                    b=-1;
                }
                case 5->{//-- revisa abajo-derecha
                    a=2;
                    b=1;
                }
                case 6->{//-- revisa derecha-abajo
                    a=1;
                    b=2;
                }
                case 7->{// revisa derecha-arriba
                    a=-1;
                    b=2;
                }
            }
            r = r+a;//-- decimos que la fila + a sera la nueva posicion
            c = c+b;//-- decimos que la columna + b sera la nueva posicion
            generaListaMovimientos(casillas, row, column, r, c);
        }
    }
    
    //----------------- METODO QUE GENERA LISTA DE MOVIMIENTOS SIN BUCLES ------------------------
    public void generaListaMovimientos(JButton[][] casillas, int row, int column, int r, int c){
        if(r > -1 && r < 8 && c > -1 && c < 8){//-- Evaluamos que no salga del limite del tablero
                    if(casillas[r][c].getIcon() != null){//-- verficar que no haya una pieza en el camino
                        if("Piezas Blancas".equals(getColorPieza(casillas, row, column))){
                            if("Piezas Negras".equals(getColorPieza(casillas, r, c))){
                                casillas[r][c].setBackground(Color.red);
                                movimientoX.add(r);//-- añadp la casilla a la lista
                                movimientoY.add(c);
                            }
                        }else{
                            if("Piezas Blancas".equals(getColorPieza(casillas, r, c))){
                                casillas[r][c].setBackground(Color.red);
                                movimientoX.add(r);//-- añadp la casilla a la lista
                                movimientoY.add(c);
                            }
                        }
                    }else{//-- si no hay pieza generar una seleccion
                        casillas[r][c].setIcon(SELECCION);//-- Pinto la seleccion
                        movimientoX.add(r);//-- añadp la casilla a la lista
                        movimientoY.add(c);
                    }
                }
    }
}
