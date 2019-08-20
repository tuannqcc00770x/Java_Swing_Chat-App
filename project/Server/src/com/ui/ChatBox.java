package com.ui;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.business.ClientHandler;
import com.business.ServerThread;
import com.entity.MessageDetail;
import com.entity.MessageType;

public class ChatBox extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	
    private ClientHandler cs;
    @SuppressWarnings("unused")
	private ServerBox server;
    @SuppressWarnings("unused")
	private String username;
    
    public void setUsername(String username) {
        this.username = username;
        cs =  ServerThread.clients.get(username);
        cs.setTxtContent(txtContent);
        new Thread(cs).start();
        setTitle("Chat with " + cs.getClient().getUsername());
    }
    
    
    public ChatBox(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        server = (ServerBox)parent;
       
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        txtMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtContent.setColumns(20);
        txtContent.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtContent.setRows(5);
        txtContent.setEditable(false);
        jScrollPane1.setViewportView(txtContent);

        txtMessage.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtMessage.setText("");

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtMessage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
       /*code for sending a message to client*/
    try {
       //String from = username;
       String from = "Server";
       String to = cs.getClient().getUsername();
       String content = txtMessage.getText();
       MessageType type = MessageType.MESSAGE;
       MessageDetail m = new MessageDetail(from, to, new Date(), content, type);
       cs.send(m);
       txtMessage.setText("");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, e);
	}
    }//GEN-LAST:event_btnSendActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
