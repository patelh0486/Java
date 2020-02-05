/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datingapp_hinal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hinal
 */
public class Data_Search {
    
//    public Data_Search( User_Account user )
//    {
//        this.curr = user;
//    }
    
    User_Account user;
    public Data_Search(){//constuctor
        this.user = User_Account.curr;
    }
    
   /////////////////////////search by just age///////////////////////////////////////
    public void SearchByAge(String first_name, String password){
        
        Scanner input = new Scanner(System.in);
        int Minage = 0;
        int Maxage = 0;
        String city = "";
        String gender = "";
        String interest = "";
        
        ArrayList<User_Account> userlist = new ArrayList<>(); /// array list of users from database
        
         System.out.println("Which gender you want:");
        gender = input.next();
        
        System.out.println("Enter Minimum Age :");
        Minage = input.nextInt();

//       System.out.println("Enter Maximum Age:");
//        Maxage = input.nextInt();
//        
        System.out.println("In which city you wanna date:");
        city = input.next();        
        
        System.out.println("What are your intrests:");
        interest = input.next();
        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(DB_URL, "patelh0486", "1839363");
            st = conn.createStatement();
           // rs = st.executeQuery("select first_name, last_name, gender, age, city, interests1, interests2, interests3, last_login from user_account where age between '" + Minage + "' AND '" + Maxage + "'  AND NOT first_name In ( select first_name from user_account where first_name = '" + first_name + "' And password = '" + password + "') ");
              
   rs = st.executeQuery("select user_id, first_name, last_name, gender, age, city, interests1, interests2, interests3, last_login from user_account where gender = '"+gender+"' AND age = '"+ Minage+"' AND city = '"+city+"' And (interests1 = '"+interest+"' or interests2 = '"+interest+"' or interests3 = '"+interest+"') AND  first_name != '" + first_name + "' And password != '" + password + "'");
             
            System.out.println("userID firstname Lastname  Gender  Age  City Interest  Interest  Interest  Last login");
                // String column[] = {"firstname","Lastname","Gender"," Age","  City"," Interest","  Interest","  Interest","  Last"," login"};
                // DefaultTableModel Searchtable = new DefaultTableModel(column, 0);// The 0 argument is number rows.
                // JTable table = new JTable(Searchtable);   
                 
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
 //                       String a = rs.getString("first_name");
//                         String b = rs.getString("last_name");
//                         String c = rs.getString("gender"); 
//                         int d =    rs.getInt("age");
//                         String e = rs.getString("city");
//                         String f = rs.getString("interests1");
//                         String g = rs.getString("interests2"); 
//                         String h = rs.getString("interests3");
//                         Date i =   rs.getDate("last_login");
 //                           System.out.println(a);
//                         Searchtable.addRow(new Object[]{a, b, c, d, e, f, g, h, i});
               } 
           // table.setModel(Searchtable);
            for(User_Account user1: userlist)
                     {                        
                       System.out.println(user1.getUser_id()+"\t\t"+user1.getFirst_name() +"\t \t"+ user1.getLast_name() +"\t \t"+ user1.getGender() +"\t \t"+ user1.getAge() + "\t \t"+ user1.getCity() +"\t \t"+ user1.getInterests1() +"\t \t"+ user1.getInterests2() +"\t \t"+ user1.getInterests3() +"\t"+ user1.getDatetime() );
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
}
