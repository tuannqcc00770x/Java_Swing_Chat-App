/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.context.DBContext;
import com.entity.MessageDetail;
import com.entity.MessageType;
import com.entity.Users;

public class MessageDAO {
   
    //add a new message detail
    public void addMessageDetail(MessageDetail u) throws Exception { 
        /*code for inserting a new MessageDetail to table MessageDetail*/
        String insert = "insert into MessageDetail values(?,?,CURRENT_TIMESTAMP,?,?)";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1, u.getFromUser());
        ps.setString(2, u.getToUser());
        ps.setString(3,  u.getContent());
        if (u.getMessageType() == MessageType.LOGIN) {
        	ps.setString(4,  "Login");
        } else if (u.getMessageType() == MessageType.LOGOUT) {
        	ps.setString(4,  "Logout");
        } else {
        	ps.setString(4,  "Message");
        }
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    
    //get list of history
    public List<MessageDetail> getHistory(Users u) throws Exception {
    	String select = "select * from MessageDetail where FromUser = ? or ToUser = ? order by DateCreate asc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(select);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getUsername());
        ResultSet rs = ps.executeQuery();
        List<MessageDetail> msg = new ArrayList<>();
        while (rs.next()) {
			String fromUser = rs.getString("FromUser");
			String toUser = rs.getString("ToUser");
			Date dateCreated = rs.getDate("DateCreate");
			String content = rs.getString("Content");
			String messageType = rs.getString("MessageType");
			MessageDetail m = null;
			switch (messageType) {
			case "Login":
				m = new MessageDetail(fromUser, toUser, dateCreated, content, MessageType.LOGIN);
				break;
			case "Logout":
				m = new MessageDetail(fromUser, toUser, dateCreated, content, MessageType.LOGOUT);
				break;	
			default:
				m = new MessageDetail(fromUser, toUser, dateCreated, content, MessageType.MESSAGE);
				break;
			}
			msg.add(m);
		}
        rs.close();
        conn.close();
        return msg;
    }
    
    //get list of history
    public List<MessageDetail> getHistoryByDate(Users u, Date dateFrom, Date dateTo) throws Exception {
    	String select = "select * from MessageDetail where FromUser = ? or ToUser = ? order by DateCreate asc";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(select);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getUsername());
        ResultSet rs = ps.executeQuery();
        List<MessageDetail> msg = new ArrayList<>();
        while (rs.next()) {
			String fromUser = rs.getString("FromUser");
			String toUser = rs.getString("ToUser");
			Date dateCreated = rs.getDate("DateCreate");
			String content = rs.getString("Content");
			String messageType = rs.getString("MessageType");
			MessageDetail m = null;
			switch (messageType) {
			case "Login":
				m = new MessageDetail(fromUser, toUser, dateCreated, content, MessageType.LOGIN);
				break;
			case "Logout":
				m = new MessageDetail(fromUser, toUser, dateCreated, content, MessageType.LOGOUT);
				break;	
			default:
				m = new MessageDetail(fromUser, toUser, dateCreated, content, MessageType.MESSAGE);
				break;
			}
			if (!dateCreated.before(dateFrom) && !dateCreated.after(dateTo)) msg.add(m);
		}
        rs.close();
        conn.close();
        return msg;
    }
}
