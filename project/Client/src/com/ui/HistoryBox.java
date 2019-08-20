package com.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import com.dal.MessageDAO;
import com.entity.MessageDetail;
import com.entity.Users;

public class HistoryBox extends javax.swing.JDialog {
	
	private static final long serialVersionUID = 1L;
	
	Users u;
	
	public HistoryBox(Users u) throws Exception {
		this.u = u;
    	initComponents();
    	loadMsg();
	}
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        txtFrom =  new javax.swing.JTextField();
        txtTo =  new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtContent.setColumns(20);
        txtContent.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);
        
        txtTo.setText("to yyyy/mm/dd");
        txtFrom.setText("from yyyy/mm/dd");
        btnSearch.setText("Search");

        btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dateFrom = txtFrom.getText();
				String dateTo = txtTo.getText();
				try {
					txtContent.setText("");
					java.util.Date from = new SimpleDateFormat("yyyy/MM/dd").parse(dateFrom);
					java.util.Date to = new SimpleDateFormat("yyyy/MM/dd").parse(dateTo);
					java.sql.Date sFrom = new java.sql.Date(from.getTime());
					java.sql.Date sTo = new java.sql.Date(to.getTime());
					MessageDAO md = new MessageDAO();
					   List<MessageDetail> msg = md.getHistoryByDate(u, sFrom, sTo);
					   if (!msg.isEmpty()) {
						   txtContent.append("Messages from " + sFrom + " to " + sTo +"\n");
						   txtContent.append("----------------------------------------------------------------------------\n");
						   for (MessageDetail messageDetail : msg) {
							txtContent.append(messageDetail.getFromUser() + ": " + messageDetail.getContent() + "\n");
						   }
					   } else {
						   	txtContent.append("No history!");
					   }
					
				}  catch (Exception exc) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(HistoryBox.this, "Date format must be yyyy/mm/dd");
					exc.printStackTrace();
				}
			}
		});
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
            	.addComponent(btnSearch)
            	.addComponent(txtFrom)
            	.addComponent(txtTo)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addContainerGap()
                .addGroup(layout.createParallelGroup()
                		.addComponent(btnSearch)
                		.addComponent(txtFrom)
                		.addComponent(txtTo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    

   private void loadMsg() throws Exception {
	   MessageDAO md = new MessageDAO();
	   List<MessageDetail> msg = md.getHistory(u);
	   if (!msg.isEmpty()) {
		   for (MessageDetail messageDetail : msg) {
			txtContent.append(messageDetail.getFromUser() + ": " + messageDetail.getContent() + "\n");
		   }
	   } else {
		   	txtContent.append("No history! Username field is empty or you are new!");
	   }
   }
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JButton btnSearch;
    private javax.swing.JTextField txtFrom;
    private javax.swing.JTextField txtTo;
    // End of variables declaration//GEN-END:variables
}
