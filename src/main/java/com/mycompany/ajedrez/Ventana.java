/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ajedrez;

import com.mycompany.ajedrez.Piezas.IConstantes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author balaz
 */
//-------------------------- CLASE QUE CREA VENTANA DEL TABLERO -----------------------------------
public class Ventana extends JFrame implements IConstantes{
    private JButton[][] casillas;
    Color cafe;
    JLabel[][] piezasPanelIzq;
    JLabel[][] piezasPanelDer;
    JPanel panelIzq;
    //------------------------------------ CONSTRUTOR ---------------------------------------
    public Ventana(){
        this.setSize(1336, 780);//-- Defino tamaño de ventana
        this.setTitle("Juego de Ajedrez");//-- Pongo el titulo
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//-- Establesco operacion cuando cierro
        this.setLocationRelativeTo(null);//-- Pongo la ventana en el centro
        this.setLayout(null);//Desctivo el diseño por defecto
        this.setIconImage(new ImageIcon(RUTA_NEGRAS+"Peon.png").getImage());
        InitComponets();//-- Llamo al meotodo para crear los componentes
    }
    //-------------------------------- METODO QUE INNCIALIZA COMPONENTES --------------------------
    private void InitComponets(){
        JPanel tablero = new JPanel();//-- Creamos el tablero y ponemos las especificaciones
        tablero.setSize(736, 736);
        tablero.setLayout(null);
        tablero.setLocation(300, 0);//Localizo el panel del tablero en la vista
        this.getContentPane().add(tablero);//-- Añado panel a la ventana
        CrearTablero(tablero);
        crearPanelesLaterales();
        crearMenuBar();
        PintarPiezas();
    }
    //------------------------------ METODO QUE CREA EL TABLERO CON BOTONONES -----------------------
    private void CrearTablero(JPanel tablero){
        int a =0, b=0;
        
        int x = 0,y = 0;//-- Contadores de graficas de etiquetas
        int cuentaColor = 2;
        cafe = new Color(205,133,63);
        Color color;
        //Creamos etiquetas
        casillas = new JButton[8][8];//--Matriz de 8x8 etiquetas
        for(int i = 0;i < 8; i++){//-- Movera las filas
            for(int j = 0; j < 8; j++){//-- Movera las columnas
                casillas[i][j] = new JButton();//-- Creo este label actual
                casillas[i][j].setEnabled(false);
                casillas[i][j].setName(";"+a+","+b);
                b++;
                casillas[i][j].setBounds(x, y, 92, 92);//-- Le digo donde y que tamaño tendra
                casillas[i][j].setHorizontalAlignment(SwingConstants.CENTER);//-- Centrar todas la imagenes que se pongan
                x += 92;//-- Cuento un cuadro mas para graficar el siguiente
                casillas[i][j].setOpaque(true);//-- Habilito la opcion para pintar el label
                cuentaColor++;//Cuento 1 para saber cuales colores tendran las casillas
                color = (cuentaColor % 2 == 1) ? Color.WHITE : cafe;//-- Si es par o impar sera blanca o negra
                casillas[i][j].setBackground(color);// Pinto la casilla
                tablero.add(casillas[i][j]);// y la añado al panel
            }//-- Este proceso se repitara en las 64 casillas
            cuentaColor++;//-- Sumo uno mas para a que ahora empieze con el contario
            x = 0;//-- Regreso hasta la izquierda
            y += 92;//-- Avanzo una casilla hacia abajo
            b = 0;
            a++;
        }
    }
    //-------------------------------- METODO QUE COLOCA LAS PIEZAS EN EL TABLERO ----------------------
    private void PintarPiezas(){
        String[] nombrePieza = {"Torre","Caballo","Alfil","Reina","Rey","Alfil","Caballo","Torre"};//-- Nombre de las piezas
        ImageIcon image;//-- Objeto de imagen que utilizaremos para poner las piezas
        for(int i = 0;i < 8; i++){//-- Recorremos filas
            for(int j = 0;j < 8;j++){//-- Recorremos colimnas
                switch (i) {//-- Evaluo la fila para ver en cual estamos
                    case 0 -> {
                        image = new ImageIcon(RUTA_NEGRAS+nombrePieza[j]+".png");
                        casillas[i][j].setIcon(image);
                    }
                    case 1 -> {//-- En caso de las primeras filas son las fichas negras
                        image = new ImageIcon(RUTA_NEGRAS+"Peon.png");
                        casillas[i][j].setIcon(image);
                    }
                    case 7 -> {
                        image = new ImageIcon(RUTA_BLANCAS+nombrePieza[j]+".png");
                        casillas[i][j].setIcon(image);
                    }
                    case 6 -> {//-- Estas dos ultimas son de piezas blancas
                        image = new ImageIcon(RUTA_BLANCAS+"Peon.png");
                        casillas[i][j].setIcon(image);
                    }
                    default -> {
                    }
                }
            }
        }
    }
    //---------------------- METODO QUE CREA LOS PANELES LATERALES -----------------------------------
    public void crearPanelesLaterales(){
        piezasPanelIzq = new JLabel[6][3];
        piezasPanelDer = new JLabel[6][3];
        int x = 0,y = 20;
        Color color = new Color(255,190,126);
        panelIzq = new JPanel();//-- Creamos los paneles laterales
        panelIzq.setLayout(null);
        JPanel panelDer = new JPanel();//----
        panelDer.setLayout(null);
        panelIzq.setBounds(0, 0, 300, 736);//-- Ponemos locacion y tamaño del panel
        panelDer.setBackground(color);
        panelIzq.setBackground(color);
        this.getContentPane().add(panelIzq);//Añadimos a la ventana
        panelDer.setBounds(1036, 0, 300, 736);//-- Ponemos locacion y tamaño del panel
        this.getContentPane().add(panelDer);//Añadimos a la ventana
        for(int i = 0; i<6; i++){
            for(int j = 0; j<3; j++){
                x += 8;
                piezasPanelIzq[i][j] = new JLabel();
                piezasPanelDer[i][j] = new JLabel();
                piezasPanelIzq[i][j].setSize(92, 92);
                piezasPanelIzq[i][j].setLocation(x, y);
                piezasPanelDer[i][j].setSize(92, 92);
                piezasPanelDer[i][j].setLocation(x, y);
                piezasPanelIzq[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                piezasPanelDer[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                panelIzq.add(piezasPanelIzq[i][j]);
                panelDer.add(piezasPanelDer[i][j]);
                x +=92;
            }
            x = 0;
            y += 100;
        }
    }
    public void crearMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("Modos");
        JMenuItem jugador1 = new JMenuItem("1 Jugador");
        JMenuItem jugador2 = new JMenuItem("2 Jugador");
        JMenuItem restablecer = new JMenuItem("Restablecer");
        JMenuItem acerca  = new JMenuItem("Acerca de");
        acerca.addActionListener((ActionEvent e) -> {
            Acerca acerca1 = new Acerca();
            acerca1.setVisible(true);
        });
        jugador1.setEnabled(false);
        JLabel turno = new JLabel();
        turno.setLocation(120, 650);
        turno.setSize(120, 92);
        panelIzq.add(turno);
        EmpezarJuego empezar = new EmpezarJuego(jugador2,casillas,piezasPanelIzq,piezasPanelDer, turno);
        jugador2.addActionListener(empezar);
        menu1.add(jugador1);
        menu1.add(jugador2);
        menuBar.add(menu1);
        menuBar.add(restablecer);
        menuBar.add(acerca);
        panelIzq.setLayout(new BorderLayout());
        panelIzq.add(menuBar, BorderLayout.NORTH);
    }
    
    
    /*/------------------------ CLASE INTERNA QUE IMPLEMENTARA EL FUCIONANMIENTO DE LAS PIEZAS ------------
    public class Movimiento implements ActionListener{
        private String[] basura, nameID; //-- Se guardara el nombre la casilla
        private int row, column;//-- se convertira en entero para saber cual casilla es la pulsada
        Color colorSeleccion;//-- Creo el color para cuando se selccione una pieza
        Pieza pieza;//-- Creo la varible donde guardare el objeto que tiene el mover las piezas
        boolean empezo;
        //--------------------- CONSTRUCTOR ---------------------------------------------------------
        public Movimiento(){
            colorSeleccion = new Color(135, 206, 250);
            pieza = new Pieza();//-- Creo el objeto -- tiene metodos para el movimiento segun la pieza
            empezo = false;
        }
        //------------------- METODO QUE SE EJECUTA AL HACER ACCION EN LA PIEZA ----------------------
        @Override
        public void actionPerformed(ActionEvent e) {   
            basura = e.toString().split(";");//-- toma el nombre del evento y lo divide para obtener la casilla
            nameID = basura[1].split(",");//-- Divide la fila y columna
            row = Integer.parseInt(nameID[0]);//-- Convierte la fila en entero
            column = Integer.parseInt(nameID[1]);//-- Convierte la columna en entero
            casillas = pieza.movimiento(casillas, row, column, piezasPanelIzq, piezasPanelDer);//-- Ejecuto el metodo de movimiento del objeto "pieza" en caso de no haber un posible movimiento no pasa nada
            if(casillas[row][column].getIcon() != null && !pieza.getConfirmacioMovimiento()){//-- Verifico si la casilla pulsada tiene pieza
                casillas = pieza.posibleMovimiento(casillas[row][column].getIcon().toString(), casillas, row, column);//-- Ejecuto el metodo que regresa posible movimientos segun la pieza y no haya algun movimiento para que no genere mas
            }
        }
    }
    private class EmpezarJuego implements ActionListener{
        private boolean empezo;
        private final JMenuItem jugador;
        public EmpezarJuego(JMenuItem jugador){
            empezo = false;
            this.jugador = jugador;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            Movimiento movimiento = new Movimiento();
                for(int i = 0; i<8; i++){
                    for(int j = 0; j<8; j++){
                        casillas[i][j].addActionListener(movimiento);
                        casillas[i][j].setEnabled(true);
                        empezo = true;
                        jugador.setEnabled(false);
                    }
                }
        }
        //--------------------- METODO QUE ASIGNA SI LA PARTIDA YA EMPEZO -----------------
        public void setEmpezo(boolean empezo){
            this.empezo = empezo;
        }
        //--------------------- METODO QUE DEVUELVE SI EMPEZO LA PARTIDA -----------------------
        public boolean getEmpezo(){
            return empezo;
        }
    }*/
}
/*OBSOLETO-----------------
private class MovimientoTablero extends MouseAdapter{
        private int row; //-- Indice de filas
        private int column;//-- Indice de columnas
        private int column1;
        private int x;//-- Coordenadas del pulso del puntero
        private int y;
        private Color color;//-- Color anteiror acomdodar las casillas
        private final Color colorSeleccion;//-- Color de seleccion

        Pieza pieza = new Pieza();
        
        public MovimientoTablero(){//-- Constructor que mi matrices de casillas ya creadas
            this.row = 0;//-- Inicializamos en 0
            this.column = 0;
            this.colorSeleccion = new Color(135, 206, 250);
        }
        @Override
        public void mouseClicked(MouseEvent e) {//-- Metodo oyente, se ejecuta cada que doy click
            column1 = (column-1 == -1)? 2:column;//-- si es la primer casilla que escoja la derecha y si no escoje la izquierda
            this.color = (casillas[row][column1-1].getBackground() == Color.WHITE)? cafe : Color.WHITE;//-- Verifico el color anterior y acomodo al color que debe ser
            casillas[row][column].setBackground(color);//-- Devuelvo el color original
            this.x = 0;//-- Inicializo x, y para que siempre empieze de 0 cada click
            this.y = 0;
            for(int i = 0; i < 8; i++){//-- Recorro las 8 filas
                if(e.getY() <= y || e.getY() >= y+92){//-- si las cordenadas no se encuentran en la fila actual
                    y += 92;//-- aumento una casilla hacia abajo
                }else{//-- en caso de que si
                    for(int j = 0; j < 8; j++){//-- Recorro las 8 columnas
                        if(e.getX() <= x || e.getX() >= x+92){//-- Si las coordenadas estan en la columna
                            x += 92;//-- aumenta una casilla ala derecha
                        }else{//-- En caso de que si
                            row = i;//-- Encontramos la casilla pulsada
                            column = j;//-- Tomo los valores de i,j para simular las casillas reocrridas
                            break;//-- salimos del ciclo de columnas
                        }
                    }
                    break;//-- salimos del ciclo de las filas
                }
            }
            System.out.println("La casilla seleccionada es :("+row+","+column+")");
            casillas = pieza.movimiento(casillas,row,column);
            if(casillas[row][column].getIcon() != null){//-- Si la casilla tiene pieza
                casillas[row][column].setBackground(colorSeleccion);//-- Resalto la casilla pulsada
                casillas = pieza.posibleMovimiento(casillas[row][column].getIcon().toString(),casillas,row,column);            
            }
        }
    }
*/
