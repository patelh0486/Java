/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datingapp_hinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hinal
 */
public class FriendList {
    
    public FriendList (User_Account user){
        
    }
    User_Account user;
    public FriendList(){
        this.user = User_Account.curr;
    }
    
 ////////////////////////////////////////////////  Display Table to view all Friends///////////////////////////// 
    public void DisplayAll(String sendID, String receivID){
        
       Scanner input = new Scanner(System.in);
       ArrayList<User_Account> userlist = new ArrayList<>(); 
       
       final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(DB_URL, "patelh0486", "1839363");
            st = conn.createStatement();                        
            rs = st.executeQuery("select user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3, last_login from user_account where user_id != '"+ sendID +"'");
             
            System.out.println("UserId  firstname  Lastname    Gender  Age  City Interest  Interest  Interest  Last login");
                 
                 
            while(rs.next()){
            User_Account users = new User_Account(         rs.getString("user_id"),
                                                           rs.getString("first_name"),
                                                           rs.getString("last_name"),
                                                           rs.getString("gender"),
                                                           rs.getInt("age"),
                                                           rs.getString("city"),
                                                           rs.getString("interests1"),
                                                           rs.getString("interests2"), 
                                                           rs.getString("interests3"),
                                                           rs.getTimestamp("last_login") );    
                      userlist.add(users);   
               } 
         
            for(User_Account user1: userlist)
                     {                        
                       System.out.println(user1.getUser_id()+ "\t" +user1.getFirst_name() +"\t \t"+ user1.getLast_name() +"\t \t \t"+ user1.getGender() +"\t \t"+ user1.getAge() + "\t \t"+ user1.getCity() +"\t \t"+ user1.getInterests1() +"\t \t"+ user1.getInterests2() +"\t \t"+ user1.getInterests3() +"\t"+ user1.getDatetime() );
                     }            
           
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally {
            //close the database
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }       
    }
    
///////////////////////////////////////////////Request friend///////////////////////////////////////    
     public void RequestFriends(String sendID, String receivID){
         
         FriendList fl = new FriendList();
         fl.DisplayAll(sendID, receivID);
         
         String status = "";
         Scanner input = new Scanner(System.in);
         System.out.println("Enter userid to whom you want to send request:");
         receivID = input.next();
         
     //     curr = sendID;
         final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(DB_URL, "patelh0486", "1839363");
            st = conn.createStatement();    
            rs = st.executeQuery("select Receiver_id from friendrequest where Receiver_id = '" +receivID+"' ");
            if(rs.next()){
                System.out.println("***************You have already send request, Please send request to other person !!!!!");
            }     
            else{
            String sql = "Insert into friendrequest values('"+receivID+"','"+sendID+"','Pending' )";
            st.executeUpdate(sql);
            
            System.out.println("-----Your request has been sent-------");
            }
            
         //  conn.commit();
         //  conn.setAutoCommit(true);
         
           
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally {
            //close the database
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }       
     
         
     }
///////////////////////////////////////////View friendlist///////////////////////////////////////////////     
    public void ViewFriends(){
        
    }
////////////////////////////////////////////////////notification///////////////////////////////////////////    
    public void ViewNotification(String sendID, String receivID){
       String status = "";
         Scanner input = new Scanner(System.in);
         
         
         
     //     curr = sendID;
         final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(DB_URL, "patelh0486", "1839363");
            st = conn.createStatement();    
            rs = st.executeQuery("select * from friendrequest where Receiver_id = '" +receivID+"' ");
            if(rs.next()){
                System.out.println("***************You have already send request, Please send request to other person !!!!!");
            }     
            else{
            
            System.out.println("-----Your request has been sent-------");
            }
            
         //  conn.commit();
         //  conn.setAutoCommit(true);
         
           
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        } 
        finally {
            //close the database
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }     
    }
 }
