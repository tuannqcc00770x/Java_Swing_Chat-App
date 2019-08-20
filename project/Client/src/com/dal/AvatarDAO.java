package com.dal;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.context.DBContext;

public class AvatarDAO {
	
	//add avatar for user
    public void updateAvatar(String username, String path) throws Exception { 
    	File file = new File(path);
    	FileInputStream fis = new FileInputStream(file);
        String insert = "update Avatar set Avatar = ? where UserName = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setBinaryStream(1, fis);
        ps.setString(2, username);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    
    public byte[] getAvatar(String username) throws Exception { 
    	
        String select = "select Avatar from Avatar where UserName = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(select);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        byte[] fileBytes = null;
        while (rs.next()) fileBytes = rs.getBytes(1);  
        ps.close();
        rs.close();
        conn.close();
        return fileBytes;
        
    }
    
}
