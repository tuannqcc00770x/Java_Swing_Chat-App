package com.ui;

import com.business.ClientThread;
import com.dal.AvatarDAO;
import com.entity.MessageDetail;
import com.entity.MessageType;
import com.entity.Server;
import com.entity.Users;
import java.awt.Image;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ClientBox extends javax.swing.JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * Creates new form ClientChat
     */
    public ClientBox() {
        initComponents();
    }

    public JTextArea getTextArea() {
        return txtContent;
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtHostIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        avatar = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        txtMessage = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        btnHistory = new javax.swing.JButton();
        btnUpdateAvatar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Client Chat");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Host IP");
        jLabel1.setToolTipText("");

        txtHostIP.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setText("Port");
        jLabel2.setToolTipText("");

        txtPort.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        txtContent.setEditable(false);
        txtContent.setColumns(20);
        txtContent.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);

        lblStatus.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        lblStatus.setText("Disconnected");
        lblStatus.setToolTipText("");

        txtMessage.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("Username");
        jLabel3.setToolTipText("");

        txtUsername.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        btnSend.setText("Send");
        btnSend.setEnabled(false);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        
        btnHistory.setText("History");
        btnHistory.setEnabled(false);
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnHistoryActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        btnUpdateAvatar.setText("Update Avt");
        btnUpdateAvatar.setEnabled(false);
        btnUpdateAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					btnUpdateAvatarActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(avatar, javax.swing.GroupLayout.Alignment.CENTER)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtHostIP, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(52, 52, 52)
                                            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnUpdateAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtHostIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConnect))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addComponent(avatar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdateAvatar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblStatus)
                .addGap(15, 15, 15))
        );

        pack();
    }

    ClientThread clientThread = null;

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        /*open a connection to server*/
    	if (clientThread == null) {
    		try {
    			if (!txtUsername.getText().equalsIgnoreCase("server") && !txtUsername.getText().equalsIgnoreCase("")) {
    				Server server = new Server(txtHostIP.getText(), Integer.valueOf(txtPort.getText()));
        			clientThread = new ClientThread(server, txtContent);
        			Users u = new Users(txtUsername.getText(), txtUsername.getText());
        			clientThread.send(u);
        			Thread t = new Thread(clientThread);
        			t.start();
        			btnSend.setEnabled(true);
        			btnHistory.setEnabled(true);
        			btnUpdateAvatar.setEnabled(true);
        			lblStatus.setText("Connected to server");
        			btnConnect.setEnabled(false);
        			txtHostIP.setEditable(false);
        			txtPort.setEditable(false);
        			txtUsername.setEditable(false);
        			setTitle("Welcome " + txtUsername.getText());
        			loadAvatar();
    			} else {
    				clientThread = null;
    				String err = "Error conection or wrong username\n"
  						  + "User name can't be \"Server\" or blank\n"
  						  + "HostIP must be \"localhost\" or \"127.0.0.1\""
  						  + "Port must be \"1234\"\n"
    					  + "Start server before connect!";
  			JOptionPane.showMessageDialog(ClientBox.this, err);
    			}
    			
    		} catch (Exception e) {
    			clientThread = null;
    			String err = "Error conection or wrong username\n"
    						  + "User name can't be \"Server\" or blank\n"
    						  + "HostIP must be \"localhost\" or \"127.0.0.1\"\n"
    						  + "Port must be \"1234\"\n"
    						  + "Start server before connect!";
    			JOptionPane.showMessageDialog(ClientBox.this, err);
    		}
    	}
    }//GEN-LAST:event_btnConnectActionPerformed


    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        /*sending a message to server*/
       try {
		String from = txtUsername.getText();
		//String to = clientThread.getServer().getHost();
		String to = "Server";
		String content = txtMessage.getText();
		MessageType type = MessageType.MESSAGE;
		MessageDetail m = new MessageDetail(from, to, new Date(), content, type); 
		clientThread.send(m);
		txtMessage.setText("");
	} catch (Exception e) {
		
		Logger.getLogger(ClientBox.class.getName()).log(Level.SEVERE, null, e);
	}
    }//GEN-LAST:event_btnSendActionPerformed
    
    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) throws Exception {//GEN-FIRST:event_btnHistoryActionPerformed
      HistoryBox h = new HistoryBox(new Users(txtUsername.getText(), txtUsername.getText()));
      h.setVisible(true);
    }//GEN-LAST:event_btnHistoryActionPerformed
    
    private void btnUpdateAvatarActionPerformed(java.awt.event.ActionEvent evt) throws Exception {//GEN-FIRST:event_btnAvatarActionPerformed
    	
    		final JFileChooser  fileDialog = new JFileChooser();
			int returnVal = fileDialog.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
               java.io.File file = fileDialog.getSelectedFile();
               if(ImageIO.read(new FileImageInputStream(file)) != null) {
            	   AvatarDAO avatarDAO = new AvatarDAO();
            	   avatarDAO.updateAvatar(txtUsername.getText(), file.getPath());
            	   ImageIcon icon = new ImageIcon(avatarDAO.getAvatar(txtUsername.getText()));
                   avatar.setIcon(scaleIcon(icon, 100));
                   avatar.setText("");
               } else {
            	   JOptionPane.showMessageDialog(this, "Only accept image file (.jpg, .png)");
               }
               
            }
            else{
            	//attachment.setText("Open command cancelled by user." );           
            }    
    	
    }//GEN-LAST:event_btnAvatarActionPerformed

    //scale image before load
    public ImageIcon scaleIcon(ImageIcon icon, int preferSize) {
  	  int width = icon.getIconWidth();
  	  int height = icon.getIconHeight();
  	  float s = 1;
  	  float newWidth = 1;
  	  float newHeight = 1;
  	  if (width > height) {
  		  newWidth = preferSize;
  		  s = newWidth/width;
  		  newHeight = height*s;  
  	  } else {
  		  newHeight = preferSize;
  		  s = newHeight/height;
  		  newWidth = width*s;  
  	  }
  		Image image = icon.getImage();
  		Image newimg = image.getScaledInstance((int)newWidth, (int)newHeight,  java.awt.Image.SCALE_SMOOTH);
  		ImageIcon newIcon = new ImageIcon(newimg);
  		
  	  return newIcon;
    }
    
    //load avatar
    public void loadAvatar() throws Exception {
    	AvatarDAO avatarDAO = new AvatarDAO();
		byte[] fis = avatarDAO.getAvatar(txtUsername.getText());
		if (fis != null) {
			ImageIcon icon = new ImageIcon(fis);
        	avatar.setText("");
            avatar.setIcon(scaleIcon(icon, 100));
		} else {
			avatar.setText("Please update avatar");
		}
    }
    
    public static void main(String args[]) {
    	
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientBox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientBox().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnHistory;
    private javax.swing.JButton btnUpdateAvatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel avatar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JTextField txtHostIP;
    private javax.swing.JTextField txtMessage;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
