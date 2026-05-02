import java.sql.*;
import java.util.Scanner;

public class Accounts {
    private Connection con;
    private Scanner sc;
    public Accounts(Connection con, Scanner sc) {
        this.con=con;
        this.sc=sc;
    }

    public long Open_account(String email){
        if(!account_exists(email)){
            String open_account_query="INSERT INTO ACCOUNTS(ACCOUNT_NUMBER,FULL_NAME,EMAIL,BALANCE,SECURITY_PIN) VALUES(?,?,?,?,?)";
            sc.nextLine();
            System.out.println("Enter Full Name: ");
            String full_name=sc.nextLine();
            System.out.println("Enter Initial amount: ");
            double balance= sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter Security PIN: ");
            String security_pin=sc.nextLine();
            try{
                long account_number=generateAccountNumber();
                PreparedStatement ps=con.prepareStatement(open_account_query);
                ps.setLong(1,account_number);
                ps.setString(2,full_name);
                ps.setString(3,email);
                ps.setDouble(4,balance);
                ps.setString(5,security_pin);
                int rows_affected=ps.executeUpdate();
                if(rows_affected>0){
                    return account_number;
                }
                else{
                    throw new RuntimeException("Account Creation Failed");
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        throw new RuntimeException("Account Already Exists");

    }


    public long get_Account_Number(String email){
        String query="SELECT ACCOUNT_NUMBER FROM ACCOUNTS WHERE EMAIL=?;";
        try{
           PreparedStatement ps=con.prepareStatement(query);
           ps.setString(1,email);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               return rs.getLong("ACCOUNT_NUMBER");
           }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        throw new RuntimeException("Account number does not exist!1");
    }


    public long generateAccountNumber(){
        try{
            Statement st=con.createStatement();
            String query="SELECT ACCOUNT_NUMBER FROM ACCOUNTS ORDER BY ACCOUNT_NUMBER DESC LIMIT 1";
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                long last_account_number=rs.getLong("ACCOUNT_NUMBER");
                return last_account_number+1;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return 10000100;
    }


    public boolean account_exists(String email){
        String query="SELECT ACCOUNT_NUMBER FROM ACCOUNTS WHERE EMAIL=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
