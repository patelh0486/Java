package datingapp_hinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author hinal
 */
public class Profile_page {

    private String user_id;
    private String first_name;
    private String password;
    //constructor

    Profile_page() {

    }

    public Profile_page(String uid, String fname, String psw) {
        this.user_id = uid;
        this.first_name = fname;
        this.password = psw;
        //at the biginning, there is no login account

    }


    ////////////////////////////////////////////////////////////////Reset pasword///////////////////////////////////////////  

    public void ResetPassword(String password) {
        //varaibles
        User_Account u = new User_Account();
        
        Scanner input = new Scanner(System.in);
        String oldpassword, new1, new2;

        System.out.println("Please enter your current password");
        oldpassword = input.next();

        System.out.println("Please enter your new password");
        new1 = input.next();

        System.out.println("Please confirm your new password");
        new2 = input.next();

        String sql;

        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486";

        Connection conn = null;
        Statement st = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, "patelh0486", "1839363");

            st = conn.createStatement();
            // rs = st.executeQuery(" select password from user_account where password ='"+oldpassword+"';");
            // rs = st.executeQuery(" select password from user_account where password = 'vasu0607'");
            
            //check the current password
            if (oldpassword.equals(password)) {
                //good to go to check new psw
                if (new1.equals(new2)) {
                    //good to go to change it
                    oldpassword = new1;

                    sql = "update user_account set password = '" + new1 + "' where password = '" + oldpassword + "'";
                    pst = conn.prepareStatement(sql);
                    pst.executeUpdate();
                
                    
                    System.out.println("Your new password is " + new1 + " and it is successfully changed");
                     conn.commit();
                     conn.setAutoCommit(true);

                } else {
                    System.out.println("The new passwords did not match!\n");
                }
            } else {
                System.out.println("Your old password is not correct!\n");
            }
            // st.executeUpdate("Update user_account set password = '" + new1 + "' where password = '"+ oldpassword + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                st.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    ////////////////////////////////////////////////View profile ///////////////////////////////////////////////////////    

    public void ViewPofile(String first_name, String password) {

        
        final String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486";

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(DB_URL, "patelh0486", "1839363");
            // create s sql statement object for query
            st = conn.createStatement();

            String Profilegender;
            String Profileage;
            String Profilecity;
            String Profileinterests1;
            String Profileinterests2;
            String Profileinterests3;

            rs = st.executeQuery("Select * from user_account  where first_name = '" + first_name + "' and password = '" + password + "'");

            if (rs.next()) {
                //  rs.getString("first_name");
                // rs.getString("last_name");
                System.out.println("***** Your Profile  ********");
                System.out.println("");

                Profilegender = rs.getString("gender");
                System.out.println("Your gender is : " + Profilegender);

                Profileage = rs.getString("age");
                System.out.println("Your age is : " + Profileage);

                Profilecity = rs.getString("city");
                System.out.println("Your city is : " + Profilecity);

                Profileinterests1 = rs.getString("interests1");
                System.out.println("Your interest 1 is : " + Profileinterests1);

                Profileinterests2 = rs.getString("interests2");
                System.out.println("Your interest 2 is : " + Profileinterests2);

                Profileinterests3 = rs.getString("interests3");
                System.out.println("Your interest 3 is : " + Profileinterests3);

                System.out.println("");
                System.out.println("*******************************************");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    ///////////////////////////////////////////////////////Data search////////////////////////////////////////////////////// 

    public void Search(String first_name, String password) {
        
    ArrayList<User_Account> userlist = new ArrayList<>();     //define userlist from useer_account

        Scanner input = new Scanner(System.in);
        String interest = "";
        String selection = "";
        
              while (!selection.equals("2")) {

                System.out.println("+++++++++++++++++++++++++++++++++");
                System.out.println(" Search for your best match using:- :");
                System.out.println("+++++++++++++++++++++++++++++++++");
                System.out.println("1: Age, city, gender, hobby ?");
             //   System.out.println("2: City ?");
             //   System.out.println("3: Interest ;) ");
             //   System.out.println("4: all ;) ");
               System.out.println("2: Go back :( ");

                selection = input.nextLine();
                System.out.println();

                if (selection.equals("1")) {
                      Data_Search ds = new Data_Search();
                      ds.SearchByAge(first_name, password);
                      
              } 
  //              else if (selection.equals("2")) {
//
//                } else if (selection.equals("3")) {
//
//                } else if (selection.equals("4")) {
//                   
//                }
                 else if (selection.equals("2")) {
                    break;
                }
            }
        } 
      
    
  ///////////////////////////////////////////////////logout/////////////////////////////////  
 public void logout(String fname, String pswd){
        
        String DB_URL = "jdbc:mysql://mis-sql.uhcl.edu/patelh0486";        
        Connection conn = null;
        PreparedStatement ps = null;
        String sql;
        try{
            conn = DriverManager.getConnection(DB_URL, "patelh0486","1839363");
            sql = "update user_account set isLogin = 0 where first_name = '" + fname + "' and password = '" + pswd + "'";
            ps = conn.prepareStatement(sql);            
            ps.executeUpdate();
            System.out.println("*** Thank you for using Finder, Love to see you again!!   ****");
            System.out.println("");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try
            {
                conn.close();
                ps.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        

}
///////////////////////////////////////////////getter setter methods/////////////////////////////////
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
