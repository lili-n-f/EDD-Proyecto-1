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
 * Clase de la ventana de eliminación de un almacén.
 * @author Liliana Nóbrega
 */
public class deleteWarehouseWindow extends javax.swing.JFrame {

    public static Graph g;
    public static mainWindow prevWindow;
    
    /** Creates new form deleteWarehouseWindow */
    public deleteWarehouseWindow(Graph g, mainWindow prevWindow) {
        initComponents();
        this.g = g;
        this.prevWindow = prevWindow;
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        bExistingWarehouses.setToolTipText("Haz click aquí para ver los nombres de los almacenes ya existentes.");
        nameTxt.setToolTipText("Tip: para asegurarte de que estás introduciendo el nombre de un almacén existente, puedes ver los almacenes preexistentes.");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        bExistingWarehouses = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        bDelete = new javax.swing.JButton();
        bBack = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ELIMINAR UN ALMACÉN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 300, -1));

        bExistingWarehouses.setText("Ver almacenes preexistentes");
        bExistingWarehouses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExistingWarehousesActionPerformed(evt);
            }
        });
        jPanel1.add(bExistingWarehouses, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 300, -1));

        jLabel3.setText("A continuación, escribe el nombre del almacén que ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel4.setText("deseas eliminar. Si la eliminación deja almacenes ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        nameTxt.setText("Ingresa el nombre del almacén");
        jPanel1.add(nameTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 170, -1));

        bDelete.setText("ELIMINAR");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(bDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 200, -1, -1));

        bBack.setText("< Volver");
        bBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackActionPerformed(evt);
            }
        });
        jPanel1.add(bBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel2.setText("aislados, no se realizará.");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExistingWarehousesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExistingWarehousesActionPerformed
        JTextArea text = new JTextArea(g.warehouses.getWarehouseNames());
        text.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(text);  
        scrollPane.setPreferredSize(new Dimension(200, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Almacenes", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_bExistingWarehousesActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        if (nameTxt.getText().isBlank() || nameTxt.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debes ingresar el nombre de un almacén para eliminarlo.");
        }else{
            g.deleteWarehouse(nameTxt.getText());
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackActionPerformed
        prevWindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bBackActionPerformed

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
            java.util.logging.Logger.getLogger(deleteWarehouseWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(deleteWarehouseWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(deleteWarehouseWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(deleteWarehouseWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new deleteWarehouseWindow(g, prevWindow).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBack;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bExistingWarehouses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nameTxt;
    // End of variables declaration//GEN-END:variables

}
