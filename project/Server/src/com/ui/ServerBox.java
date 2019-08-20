package com.ui;

import com.business.ServerThread;
import com.entity.Client;
import com.entity.Server;

import javax.swing.DefaultListModel;


public class ServerBox extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	public static DefaultListModel<Client> clients = new DefaultListModel<>();
    public final String SERVER_NAME = "localhost";
    public final int PORT = 1234;
    
    @SuppressWarnings("unchecked")
	public ServerBox() {
        initComponents();
        /*insert code for starting server here*/
        lstClients.setModel(clients);
        if (serverThread == null) {
        	try {
				Server server = new Server(SERVER_NAME, PORT);
				serverThread = new ServerThread(server);
				new Thread(serverThread).start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }
    }

    @SuppressWarnings({ "rawtypes" })
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        //buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstClients = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Chat");

        lstClients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstClients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstClientsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lstClients);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lstClientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstClientsMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2) {
            ChatBox sc = new ChatBox(this, false);
            sc.setUsername(lstClients.getSelectedValue().toString());
            sc.setVisible(true);
            
        }
    }//GEN-LAST:event_lstClientsMouseClicked

    ServerThread serverThread = null;
   
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerBox().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    @SuppressWarnings("rawtypes")
	private javax.swing.JList lstClients;
    // End of variables declaration//GEN-END:variables
}
