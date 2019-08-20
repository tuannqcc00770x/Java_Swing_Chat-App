package com.dal;

import com.context.DBContext;
import com.entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    //return a list of available users in the system
    public List<Users> selectAll()throws Exception {
        String select = "select * from users";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        List<Users> users = new ArrayList<>();
        while (rs.next()) {
			String userName = rs.getString("username");
			String displayName = rs.getString("displayName");
			users.add(new Users(userName, displayName));
		}
        rs.close();
        conn.close();
        return users;
    }
    
    public void addUser(Users u)throws Exception {
       /*code for inserting a new User to table Users*/
       List<Users> users = selectAll();
       boolean foundUser = false;
       for (Users x : users) {
		if (x.getUsername().equalsIgnoreCase(u.getUsername())) foundUser = true;
       }
       if (!foundUser) {
    	   String insert = "insert into Users values(?,?)";
    	   Connection conn =  new DBContext().getConnection();
    	   
    	   PreparedStatement ps = conn.prepareStatement(insert);
    	   ps.setString(1, u.getUsername());
    	   ps.setString(2, u.getDisplayName());
    	   ps.executeUpdate();
    	   
    	   insert = "insert into Avatar values(?,null)";
    	   ps = conn.prepareStatement(insert);
    	   ps.setString(1, u.getUsername());
    	   ps.executeUpdate();
    	   
    	   ps.close();
    	   conn.close();
       }
    }
    
    
}
    