package graph;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;



public class Ventana_inicio extends JFrame{
    
    /**
     * Creamos constructor de Ventana 
     */
    public Ventana_inicio() {
        setSize(500,280); //Tamano de la ventana
        setTitle("Selector de archivo ''amazon.txt''"); //Le damos titulo la ventana
        setLocationRelativeTo(null); //Ubico la ventana en medio de la pantalla
        setResizable(false);
        //this.getContentPane().setBackground(Color.darkGray); //Le da color a la ventana
        
        initComponents();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /**
     *Muestro Una ventana en la que se ve el archivo impreso 
     */
    public void mostrarVentana(String filename, String archivo){
        JFrame mostrador_ventana = new JFrame();
        
        mostrador_ventana.setSize(300, 500);
        mostrador_ventana.setLocationRelativeTo(null);
        mostrador_ventana.setTitle(filename);
        
        JPanel mostrador = new JPanel();
        mostrador.setLayout(null);
        mostrador.setSize(300, 500);
        
        JTextArea area = new JTextArea(archivo);
        area.setSize(300, 500);
        
        mostrador.add(area);
        mostrador_ventana.setVisible(true);    
        mostrador_ventana.add(mostrador);
    }
    
    public void creador_de_grafo(String archivo){
        
        String almacenes[] = archivo.split(";");
        
        int cantidad_almacenes = almacenes.length -3;
        
        Graph g = new Graph(cantidad_almacenes); 
        
        int i = 1;
        for(i=1; i<cantidad_almacenes+1; i++){
            //System.out.println(almacenes[i]);
            //System.out.println(nombre_productos.length);
            String nombre_productos[] = almacenes[i].split(":");
            String probando_lista_string = nombre_productos[1];
            String lista_productos[] = probando_lista_string.split("\n");
            Warehouse w1 = new Warehouse(nombre_productos[0]);
            ProductList lista_de_productos = new ProductList();
            int s = 1;
            for (s=1; s<lista_productos.length; s++){
                String lista_productos_almacen = lista_productos[s].toString();
                String lista_casi_productos[] = lista_productos_almacen.split(",");
                String name = lista_casi_productos[0].toString();
                String blob = lista_casi_productos[1].toString();
                String cantidad = blob;
                Integer ammount = Integer.valueOf(blob);
                System.out.println(ammount);
                Product productos_ahora_si = new Product(name, ammount);
                lista_de_productos.addNewProductToStock(name, ammount);
            };
            
            g.addVertex(w1);
        
        };
        
        System.out.println(g);
        //g.addArch(w1.getName(), w2.getName(), 2);
        //g.addArch(w2.getName(), w1.getName(), 2);
        //g.addArch(w3.getName(), w1.getName(), 3);
        //g.addArch(w2.getName(), w3.getName(), 4);
                
        //mainWindow w = new mainWindow(g);
    }
    
    /**
     * Creamos el Panel selector de archivo amazon.txt, con un JFileChooser que
     * tiene filtro de solo archivos ".txt".
     */
    private void initComponents(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        JLabel etiqueta = new JLabel("Bienvenid@ a Amazon!", SwingConstants.CENTER);
        etiqueta.setSize(480, 80);
        etiqueta.setFont(new Font("sans", Font.BOLD, 34));
        etiqueta.setOpaque(false);
        etiqueta.setForeground(Color.WHITE);
        
        JLabel etiqueta2 = new JLabel("Por favor ingrese un archivo amazon.txt", SwingConstants.CENTER);
        etiqueta2.setLocation(0, 50);
        etiqueta2.setSize(480, 80);
        etiqueta2.setFont(new Font("sans serif", Font.ITALIC, 18));
        etiqueta2.setOpaque(false);
        etiqueta2.setForeground(Color.WHITE);
        
        JLabel cajaTexto = new JLabel();
        cajaTexto.setBounds(60, 150, 250, 20);
        cajaTexto.setFont(new Font("sans serif", Font.ITALIC, 14));
        cajaTexto.setOpaque(true);
        cajaTexto.setForeground(Color.BLACK);
        
        JButton button2 = new  JButton("Continuar");
        button2.setBounds(200, 200, 100, 20);
        button2.setOpaque(true);
        button2.setBackground(Color.DARK_GRAY);
        button2.setForeground(Color.WHITE);
        button2.setEnabled(false);
        
        
        JButton button1 = new  JButton("Explorar");
        button1.setBounds(330, 150, 85, 20);
        button1.setBackground(Color.DARK_GRAY);
        button1.setForeground(Color.WHITE);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                chooser.setFileFilter(filter);
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                String filename = f.getAbsolutePath();
                Path path = Paths.get(filename);
                cajaTexto.setText(filename);
                button2.setEnabled(true);
                String archivo = null;
                try {
                    archivo = Files.readString(path, StandardCharsets.ISO_8859_1);
                } catch (IOException ex) {
                    Logger.getLogger(Ventana_inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                creador_de_grafo(archivo);
            }
        });
        
        panel.add(etiqueta);
        panel.add(etiqueta2);
        panel.add(button1);
        panel.add(cajaTexto);
    }
                                 
}
