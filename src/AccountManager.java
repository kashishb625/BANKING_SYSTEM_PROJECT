import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
   private Connection con;
   private Scanner sc;

   AccountManager(Connection con, Scanner sc){
       this.con=con;
       this.sc=sc;
   }

    public void credit_money(long account_number)throws Exception{
       sc.nextLine();
       System.out.print("Enter Amount: ");
       double amount=sc.nextDouble();
       sc.nextLine();
       System.out.println("Enter Security Pin: ");
       String security_pin=sc.nextLine();

       try {
           con.setAutoCommit(false);
           if (account_number != 0) {
               PreparedStatement ps = con.prepareStatement("SELECT * FROM ACCOUNTS WHERE ACCOUNT_NUMBER=? AND SECURITY_PIN=?");
               ps.setLong(1, account_number);
               ps.setString(2, security_pin);
               ResultSet rs = ps.executeQuery();

               if (rs.next()) {
                   String credit_query = "UPDATE ACCOUNTS SET BALANCE=BALANCE+? WHERE ACCOUNT_NUMBER=?";
                   PreparedStatement ps1 = con.prepareStatement(credit_query);
                   ps1.setDouble(1, amount);
                   ps1.setLong(2, account_number);
                   int rows_affected = ps.executeUpdate();
                   if (rows_affected > 0) {
                       System.out.println("Rs." + amount + " Credited Successfully");
                       con.commit();
                       con.setAutoCommit(true);
                       return;
                   } else {
                       System.out.println("Transaction failed!!");
                       con.rollback();
                       con.setAutoCommit(true);
                   }
               } else {
                   System.out.println("Invalid Pin!");
               }
           }
       }catch(SQLException e)
       {
           System.out.println(e.getMessage());
       }
       con.setAutoCommit(true);
    }

    public void debit_money(long account_number)throws Exception{
       sc.nextLine();
        System.out.print("Enter Amount: ");
        double amount=sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Security Pin: ");
        String security_pin=sc.nextLine();

        try {
            con.setAutoCommit(false);
            if (account_number != 0) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM ACCOUNTS WHERE ACCOUNT_NUMBER=? AND SECURITY_PIN=?");
                ps.setLong(1, account_number);
                ps.setString(2, security_pin);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    double current_balance = rs.getDouble("BALANCE");
                    if (amount <= current_balance) {
                        String debit_query = "UPDATE ACCOUNTS SET BALANCE=BALANCE-? WHERE ACCOUNT_NUMBER=?";
                        PreparedStatement ps1 = con.prepareStatement(debit_query);
                        ps1.setDouble(1, amount);
                        ps1.setLong(2, account_number);
                        int rows_affected = ps.executeUpdate();
                        if (rows_affected > 0) {
                            System.out.println("Rs." + amount + " Debited Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("Transaction failed!!");
                            con.rollback();
                            con.setAutoCommit(true);
                            return;
                        }
                    } else {
                        System.out.println("Insufficient Balance!");
                    }
                }else{
                    System.out.println("Invalid Pin!");
                }
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        con.setAutoCommit(true);
    }

    public void transfer_money(long sender_account_number)throws Exception{
       sc.nextLine();
        System.out.println("Enter Receiver's Account Number: ");
        long receiver_account_number=sc.nextLong();
        System.out.println("Enter Amount to Transfer: ");
        double amount=sc.nextDouble();
        sc.nextLine();
        System.out.println("Security Pin: ");
        String security_pin=sc.nextLine();
        try{
            con.setAutoCommit(false);
            if(sender_account_number != 0 && receiver_account_number != 0) {
                PreparedStatement ps=con.prepareStatement("SELECT * FROM  ACCOUNTS WHERE ACCOUNT_NUMBER=? AND SECURITY_PIN=?");
                ps.setLong(1, receiver_account_number);
                ps.setString(2, security_pin);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    double current_balance = rs.getDouble("BALANCE");
                    if(amount<=current_balance){
                        String debit_query="UPDATE ACCOUNTS SET BALANCE=BALANCE-? WHERE ACCOUNT_NUMBER=?";
                        String credit_query="UPDATE ACCOUNTS SET BALANCE=BALANCE+? WHERE ACCOUNT_NUMBER=?";
                        PreparedStatement creditps=con.prepareStatement(credit_query);
                        PreparedStatement debitps=con.prepareStatement(debit_query);
                        creditps.setDouble(1, amount);
                        creditps.setLong(2, receiver_account_number);
                        debitps.setDouble(1, amount);
                        debitps.setLong(2, sender_account_number);
                        int rows_affected1 = creditps.executeUpdate();
                        int rows_affected2 = debitps.executeUpdate();
                        if(rows_affected1>0 && rows_affected2>0){
                            System.out.println("Transaction Successful");
                            System.out.println("Rs."+amount+ " Transferred Successfully");
                            con.commit();
                            con.setAutoCommit(true);
                        }
                        else{
                            System.out.println("Transaction Failed!");
                            con.rollback();
                            con.setAutoCommit(true);
                        }
                    }else{
                        System.out.println("Insufficient Balance!");
                    }
                }else{
                    System.out.println("Invalid Pin!");
                }
            }else{
                System.out.println("Invalid Account Number");
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        con.setAutoCommit(true);
    }

    public void getBalance(long account_number){
       sc.nextLine();
        System.out.println("Enter Security Pin: ");
        String security_pin=sc.nextLine();
        try{
            PreparedStatement ps=con.prepareStatement("SELECT BALANCE FROM ACCOUNTS WHERE ACCOUNT_NUMBER=? AND SECURITY_PIN=?");
            ps.setLong(1,account_number);
            ps.setString(2,security_pin);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                double balance=rs.getDouble("BALANCE");
                System.out.println("Balance: "+balance);
            }
            else{
                System.out.println("Invalid Pin!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
