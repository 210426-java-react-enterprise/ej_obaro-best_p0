package com.revature.project0.daos;
import com.revature.project0.models.UserTransactionHistory;
import com.revature.project0.util.LinkedList;
import com.revature.project0.models.AppUser;
import com.revature.project0.models.UserAccount;
import com.revature.project0.util.ConnectionFactory;

import java.sql.*;

public class UserDAO {
    public void save(AppUser newUser){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertUser = "insert into bigballerbank.customer (username , password , email , first_name , last_name , user_age ) values (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser,new String[]{"user_id"});
            pstmt.setString(1,newUser.getUsername());
            pstmt.setString(2,newUser.getPassword());
            pstmt.setString(3,newUser.getEmail());
            pstmt.setString(4,newUser.getFirstName());
            pstmt.setString(5,newUser.getLastName());
            pstmt.setInt(6,newUser.getAge());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newUser.setId(rs.getInt("user_id"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public void getTransactionHistory(AppUser user){
//        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
//            String sqlInsertUser = "insert into quizzard.users (username , password , email , first_name , last_name , age ) values (?,?,?,?,?,?)";
//            PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser,new String[]{"user_id"});
//            pstmt.setString(1,newUser.getUsername());
//            pstmt.setString(2,newUser.getPassword());
//            pstmt.setString(3,newUser.getEmail());
//            pstmt.setString(4,newUser.getFirstName());
//            pstmt.setString(5,newUser.getLastName());
//            pstmt.setInt(6,newUser.getAge());
//            int rowsInserted = pstmt.executeUpdate();
//
//            if (rowsInserted != 0){
//                ResultSet rs = pstmt.getGeneratedKeys();
//                while(rs.next()){
//                    newUser.setId(rs.getInt("user_id"));
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public AppUser loginValidation(String username,String password){
        AppUser user = null;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from bigballerbank.customer where username = ? and password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user = new AppUser();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getInt("user_age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // TODO implement me: You can only delete an account when signed in
    public void deleteAccount(AppUser user){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "delete * from bigballerbank.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user.getId());


            ResultSet rs = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Boolean isEmailAvailible(String email){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from bigballerbank.users where email = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public Boolean isUsernameAvailible(String username){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from bigballerbank.users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
                //Mark:: ----ALL USER ACCOUNT METHODS----//
    public void saveAccount(UserAccount newAccount){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertAccount = "insert into bigballerbank.account (user_id, balance, account_type) values (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAccount,new String[]{"account_id"});
            pstmt.setInt(1,newAccount.getUserId());
            pstmt.setDouble(2,newAccount.getBalance());
            pstmt.setString(3,newAccount.getAccountType());
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0){
                ResultSet rs = pstmt.getGeneratedKeys();
                while(rs.next()){
                    newAccount.setAccountId(rs.getInt("account_id"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public UserAccount getUserAccount(String accountName,int userId){
        UserAccount userAccount = null;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from bigballerbank.account where account_type = ? and user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,accountName);
            pstmt.setInt(2,userId);

                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    userAccount = new UserAccount();
                    userAccount.setAccountId(rs.getInt("account_id"));
                    userAccount.setUserId(rs.getInt("user_id"));
                    userAccount.setBalance(rs.getDouble("balance"));
                    userAccount.setAccountType(rs.getString("account_type"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return userAccount;
        }

    public LinkedList getAllCurrentUserAccounts(Integer currentUserId){
        //this method will grab all user accounts,store it into UserAccount
        //object and add it to a linkedlist called userAccountLinkedList
        LinkedList<UserAccount> currentUserAccounts = new LinkedList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            UserAccount userAccount = null;
            String sql = "select * from bigballerbank.account where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,currentUserId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                userAccount = new UserAccount();
                userAccount.setAccountId(rs.getInt("account_id"));
                userAccount.setUserId(rs.getInt("user_id"));
                userAccount.setBalance(rs.getDouble("balance"));
                userAccount.setAccountType(rs.getString("account_type"));
                currentUserAccounts.add(userAccount);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentUserAccounts;
    }

    public void updateUserBalance(AppUser currentUser, String accountName, Double balance) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "update account set balance = ? where user_id = ? and account_type = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,currentUser.getId());
            pstmt.setString(2,accountName);
    } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void updateTransactionsTable(String transactionType, AppUser currentUser, UserAccount currentAccount, String date){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "bigballerbank.customer (user_id , transaction_type , transaction_date , account_type , account_id ) values (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,currentUser.getId());
            pstmt.setString(2,transactionType);
            pstmt.setString(3,date);
            pstmt.setString(4,currentAccount.getAccountType());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public LinkedList getAllTransactions(int currentUserId){
        LinkedList<UserTransactionHistory> currentUserTransactions = new LinkedList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            UserTransactionHistory userTransaction = null;
            String sql = "select * from bigballerbank.transactions where user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,currentUserId);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                userTransaction = new UserTransactionHistory();
                userTransaction.setAccountId(rs.getInt("account_id"));
                userTransaction.setUserId(rs.getInt("user_id"));
                userTransaction.setTransactionId(rs.getInt("transaction_id"));
                userTransaction.setAccountType(rs.getString("account_type"));
                userTransaction.setTransactionType(rs.getString("transaction_type"));
                userTransaction.setTransactionAmount(rs.getDouble("amount"));
                userTransaction.setTransactionDate(rs.getString("transaction_date"));
                currentUserTransactions.add(userTransaction);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return currentUserTransactions;
    }
}
