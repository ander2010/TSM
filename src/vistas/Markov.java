/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import servicios.markov;
import servicios.Conexion;
import servicios.Tareas_servicio;
import servicios.bitrigramas;

/**
 *
 * @author letal
 */
public class Markov extends javax.swing.JFrame {
Tareas_servicio tareas_servicio;
 float [][] matrizAjustada;
  Map<Integer, List<Integer>> matriAzignacion= new TreeMap<>();
   Map<String, Integer> mapaDeCaracteres= new TreeMap<>();
    double[][] EstadoMatriz;
     double[][] AleatoriaMatriz;
     ArrayList<String> auxiliarMOstrarNumeros = new ArrayList<>();

Map<String,Integer> palabrasdiferenetes= new TreeMap<>();
    /**
     * Creates new form Markov
     */
    public Markov(Map<String,Integer>cantpalabra) {
        initComponents();
         tareas_servicio= new Tareas_servicio();
         palabrasdiferenetes=cantpalabra;
        
    }
    
    
    public void mostrarCorpusEspecifico(int id)
    {
    
    try {
        list1.clear();
        list2.clear();
        list3.clear();
        list4.clear();
            List<String>corpus=tareas_servicio.recuperarSetenciasOperacion(Conexion.obtener(),id);
       
            bitrigramas bitri= new bitrigramas(corpus);
            String name = JOptionPane.showInputDialog("Inserte el Numero de estados");
		
        
            markov marko= new markov(bitri.getWordCounts(),palabrasdiferenetes,new ArrayList(bitri.getCorpusText()),palabrasdiferenetes.size(),Integer.parseInt(name) );
            
            
                  DefaultListModel modelo = new DefaultListModel(); 
                  DefaultListModel modelo1 = new DefaultListModel(); 
                  DefaultListModel modelo2 = new DefaultListModel(); 
                  
            
             matriAzignacion = marko.getMatriAzignacion();
            mapaDeCaracteres = marko.getMapaDeCaracteres();
              EstadoMatriz= marko.getEstadoMatriz();

           ArrayList<String> auxiliarMOstrarNumeros = marko.getAuxiliarMOstrarNumeros();
            AleatoriaMatriz =marko.getAleatoriaMatriz();
            matrizAjustada= new float[marko.getMatrizAjustada().length][marko.getMatrizAjustada().length];
            matrizAjustada=marko.getMatrizAjustada();
            
            
            
            
            modelo=MostrarMatriz(EstadoMatriz,modelo);
            
           for (int i = 0; i < modelo.size(); i++) {
            list3.add(modelo.get(i).toString());
        }
            
            
            
           
           
                Iterator it = mapaDeCaracteres.keySet().iterator();
        while (it.hasNext()) {
            
            String key = (String) it.next();
          list1.add("Clave: " + key + " -> Valor: " + mapaDeCaracteres.get(key));
//            System.out.println("Clave: " + key + " -> Valor: " + wordCounts.get(key));
        }
         
        
        
        auxiliarMOstrarNumeros= marko.getAuxiliarMOstrarNumeros();
        for (int i = 0; i < auxiliarMOstrarNumeros.size(); i++) {
            list2.add(auxiliarMOstrarNumeros.get(i).toString());
        }
            
           for (int i = 0; i < modelo.size(); i++) {
            list3.add(modelo.get(i).toString());
        }
            
            
            
                modelo1=MostrarMatrizD(matrizAjustada,modelo1);
            
           for (int i = 0; i < modelo1.size(); i++) {
            list4.add(modelo1.get(i).toString());
        }
                
            
        
         } catch (SQLException ex) {
            Logger.getLogger(Markov.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Markov.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
        public DefaultListModel MostrarMatriz(double matriz[][], DefaultListModel modelo) {

            
            String aux="";
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                aux=aux+String.valueOf(matriz[x][y])+"   ";
                System.out.print( matriz[x][y]+ " ");
            }
            modelo.addElement(aux);
            aux="";

        }
        return modelo;

    }
        
        
        public DefaultListModel MostrarMatrizD(float matriz[][], DefaultListModel modelo) {

            
            String aux="";
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                aux=aux+String.valueOf(matriz[x][y])+"   ";
                System.out.print( matriz[x][y]+ " ");
            }
            modelo.addElement(aux);
            aux="";

        }
        return modelo;

    }
        
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        list1 = new java.awt.List();
        list2 = new java.awt.List();
        list3 = new java.awt.List();
        list4 = new java.awt.List();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Operaciones");

        jMenu4.setText("Cargar datos");

        jMenuItem3.setText("Constante");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem4.setText("Vector");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem1.setText("Multiplicacion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Suma");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenu3.add(jMenu4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(list4, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(list3, javax.swing.GroupLayout.PREFERRED_SIZE, 1260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(list4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(44, Short.MAX_VALUE)
                    .addComponent(list3, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(281, 281, 281)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        
          int valor =1;
    mostrarCorpusEspecifico(valor);
    
    
        
    
   
    
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        
         int valor =2;
    mostrarCorpusEspecifico(valor);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        
         int valor =3;
    mostrarCorpusEspecifico(valor);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
         int valor =4;
    mostrarCorpusEspecifico(valor);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Markov.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //  new Markov().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private java.awt.List list1;
    private java.awt.List list2;
    private java.awt.List list3;
    private java.awt.List list4;
    // End of variables declaration//GEN-END:variables
}
