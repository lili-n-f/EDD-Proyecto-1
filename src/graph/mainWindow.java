/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graph;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * Clase de la interfaz principal del programa (ver disponibilidad, realizar un pedido, y sección de administración que te redirecciona a otras ventanas especialmente para estas acciones).
 * @author Liliana Nóbrega
 */
public class mainWindow extends javax.swing.JFrame {

    public static Graph g;
    
    /** Creates new form mainWindow */
    public mainWindow(Graph g) {
        initComponents();
        this.g = g;
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        bExit.setToolTipText("Haz click aquí para guardar y cerrar.");
        bAddWarehouse.setToolTipText("Haz click aquí para agregar un nuevo almacén al grafo.");
        bDeleteWarehouse.setToolTipText("Haz click aquí para eliminar un almacén del grafo.");
        bAdminStock.setToolTipText("Haz click aquí para gestionar el stock de uno de los almacenes.");
        bSeeGraph.setToolTipText("Haz click aquí para ver el grafo.");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bAvailableProduct = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        warehouseNameTxt = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        bAvailableProdsTotal = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        bExistingWarehouses = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bProductName = new javax.swing.JTextField();
        bChooseWarehouse = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        availableNameTxt = new javax.swing.JTextField();
        sProductAmmount = new javax.swing.JSpinner();
        bFinishOrder = new javax.swing.JButton();
        bAddOrder = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bAddWarehouse = new javax.swing.JButton();
        bSeeGraph = new javax.swing.JButton();
        bDeleteWarehouse = new javax.swing.JButton();
        bAdminStock = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 410, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("REALIZAR PEDIDO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jLabel5.setText("ver disponibilidad y escoger productos");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 260, -1));

        bAvailableProduct.setText("VER DISPONIBILIDAD");
        jPanel1.add(bAvailableProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        jLabel6.setText("VER DISPONIBILIDAD POR ALMACENES: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel7.setText("cuya disponibilidad desea ver.");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        warehouseNameTxt.setText("Introduce el nombre del almacén");
        jPanel1.add(warehouseNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 220, -1));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 410, 10));

        jLabel8.setText("VER DISPONIBILIDAD POR PRODUCTO: introduzca el nombre del producto");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        bAvailableProdsTotal.setText("VER DISPONIBILIDAD");
        bAvailableProdsTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAvailableProdsTotalActionPerformed(evt);
            }
        });
        jPanel1.add(bAvailableProdsTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 410, 10));

        bExistingWarehouses.setText("Ver almacenes preexistentes");
        bExistingWarehouses.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                bExistingWarehousesMouseMoved(evt);
            }
        });
        bExistingWarehouses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExistingWarehousesActionPerformed(evt);
            }
        });
        jPanel1.add(bExistingWarehouses, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, 30));

        jLabel9.setText("ESCOGER EL ALMACÉN DE DONDE ORDENAR: introduzca el nombre del");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel10.setText("almacén de donde ordenará.");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        bProductName.setText("Introduce el nombre del producto");
        jPanel1.add(bProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 210, -1));

        bChooseWarehouse.setText("ESCOGER ALMACÉN");
        jPanel1.add(bChooseWarehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, -1));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 410, 10));

        jLabel11.setText("REALIZAR PEDIDO: introduzca el nombre del producto que desea ordenar");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        jLabel12.setText("y la cantidad. Luego de agregar al carrito, puede ingresar otro producto.");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        availableNameTxt.setText("Introduce el nombre del producto");
        jPanel1.add(availableNameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 220, -1));

        sProductAmmount.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jPanel1.add(sProductAmmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, 70, -1));

        bFinishOrder.setText("TERMINAR ORDEN");
        jPanel1.add(bFinishOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, -1, -1));

        bAddOrder.setText("AÑADIR AL CARRITO");
        jPanel1.add(bAddOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        jTabbedPane1.addTab("CLIENTES", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 52, 400, 10));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ADMINISTRACIÓN");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 140, -1));

        jLabel2.setText("Haga click en el botón cuya acción quiere realizar:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 320, -1));

        bAddWarehouse.setText("AGREGAR UN ALMACÉN");
        bAddWarehouse.setToolTipText("Haz click aquí para agregar un nuevo almacén al grafo.");
        bAddWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddWarehouseActionPerformed(evt);
            }
        });
        jPanel2.add(bAddWarehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 190, 30));

        bSeeGraph.setText("VER GRAFO");
        bSeeGraph.setToolTipText("Haz click aquí para ver el grafo.");
        jPanel2.add(bSeeGraph, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 190, 30));

        bDeleteWarehouse.setText("ELIMINAR UN ALMACÉN");
        bDeleteWarehouse.setToolTipText("Haz click aquí para eliminar un almacén del grafo.");
        bDeleteWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteWarehouseActionPerformed(evt);
            }
        });
        jPanel2.add(bDeleteWarehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 190, 30));

        bAdminStock.setText("GESTIÓN DE STOCK");
        bAdminStock.setToolTipText("Haz click aquí para gestionar el stock de uno de los almacenes.");
        bAdminStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAdminStockActionPerformed(evt);
            }
        });
        jPanel2.add(bAdminStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 190, 30));

        jLabel3.setText("almacenes y stock");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 130, -1));

        jTabbedPane1.addTab("ADMINISTRACIÓN", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 450, 470));

        bExit.setBackground(new java.awt.Color(255, 102, 153));
        bExit.setForeground(new java.awt.Color(255, 255, 255));
        bExit.setText("X");
        bExit.setToolTipText("Haz click aquí para guardar y cerrar.");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });
        getContentPane().add(bExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 50, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        //FALTA GUARDAR LOS DATOS DEL GRAFO EN EL TXT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        JOptionPane.showMessageDialog(null, "Hasta pronto, gracias por elegir Amazon.");
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void bAddWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddWarehouseActionPerformed
        this.setVisible(false);
        addWarehouseWindow aww = new addWarehouseWindow(g, this);
    }//GEN-LAST:event_bAddWarehouseActionPerformed

    private void bDeleteWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteWarehouseActionPerformed
        this.setVisible(false);
        deleteWarehouseWindow dww = new deleteWarehouseWindow(g, this);
    }//GEN-LAST:event_bDeleteWarehouseActionPerformed

    private void bAdminStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAdminStockActionPerformed
        this.setVisible(false);
        stockAdministrationWindow saw = new stockAdministrationWindow(g, this);
    }//GEN-LAST:event_bAdminStockActionPerformed

    private void bExistingWarehousesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExistingWarehousesMouseMoved

    }//GEN-LAST:event_bExistingWarehousesMouseMoved

    private void bExistingWarehousesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExistingWarehousesActionPerformed
        JTextArea text = new JTextArea(g.warehouses.getWarehouseNames());
        text.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setPreferredSize(new Dimension(200, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Almacenes", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_bExistingWarehousesActionPerformed

    private void bAvailableProdsTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAvailableProdsTotalActionPerformed
        
    }//GEN-LAST:event_bAvailableProdsTotalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow(g).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField availableNameTxt;
    private javax.swing.JButton bAddOrder;
    private javax.swing.JButton bAddWarehouse;
    private javax.swing.JButton bAdminStock;
    private javax.swing.JButton bAvailableProdsTotal;
    private javax.swing.JButton bAvailableProduct;
    private javax.swing.JButton bChooseWarehouse;
    private javax.swing.JButton bDeleteWarehouse;
    private javax.swing.JButton bExistingWarehouses;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bFinishOrder;
    private javax.swing.JTextField bProductName;
    private javax.swing.JButton bSeeGraph;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner sProductAmmount;
    private javax.swing.JTextField warehouseNameTxt;
    // End of variables declaration//GEN-END:variables

}
