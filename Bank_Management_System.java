package miniproject;
import java.util.Scanner;
import java.sql.*;
class Bank
{
	int pin;
	int i ;
	int desposit;
	long amount;
	String query,querydeposit,queryBalanceEnquiry,querywithdarw;
	
	void NewAccount() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm1_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		query = "insert into AccountHolder1(Name,EmailId,ContanctNumber,Branch,Type,AdharCard,PanCard, balanace,pin,Address)values (?,?,?,?,?,?,?,?,?,?)";
		System.out.println("WELCOME TO ATM");
		System.out.println("Enter the Name");
		String Name=sc.next();
		System.out.println("Enter the EmailId");
		String EmailId=sc.next();
		System.out.println("Enter the ContanctNumber");
		Long ContanctNumber=sc.nextLong();
		System.out.println("Enter the Branch");
		String Branch=sc.next();
		System.out.println("Enter the Type(Saving/Current)");
		String Type=sc.next();
		System.out.println("Enter the AdharCard");
		Long AdharCard=sc.nextLong();
		System.out.println("Enter the PanCard");
		Long PanCard=sc.nextLong();
		System.out.println("Enter the balanace");
		Long balanace=sc.nextLong();
		System.out.println("Enter the pin");
		int pin=sc.nextInt();
		System.out.println("Enter the Address");
		String Address=sc.next();
		PreparedStatement p = conn.prepareStatement(query);
		p.setString(1, Name);
		p.setString(2, EmailId);
		p.setLong(3, ContanctNumber);
		p.setString(4, Branch);
		p.setString(5, Type);
		p.setLong(6, AdharCard);
		p.setLong(7, PanCard);
		p.setLong(8, balanace);
		p.setInt(9, pin);
		p.setString(10, Address);
		int i = p.executeUpdate();
    	if(i>0)
    	{
    		System.out.println("Account Created Successfully");
    	}
    	else
    	{
    		System.out.println("Account Creation Failed..");
    	}
		
	}
	void DepositAmount() throws ClassNotFoundException, SQLException
	{
		Scanner sc=new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm1_darshan", "root", "darshan3009");
		Statement st = conn.createStatement();
		query = "SELECT balance FROM Accountholder1 WHERE pin = ?";
			System.out.println("Enter the pin");
			pin=sc.nextInt();
			PreparedStatement pr=conn.prepareStatement(query);
			pr.setInt(1, pin);
			ResultSet resultSet = pr.executeQuery();
			if (resultSet.next()) 
			{
				System.out.println(resultSet.getLong(1));
				long bal = resultSet.getLong(1);
				
				System.out.println("Enter The Deposit Amount: ");
				long Amount=sc.nextLong();
				bal = bal + Amount;
				querydeposit = "update Accountholder1 set balance = ? where pin = ?";
				PreparedStatement pr1=conn.prepareStatement(querydeposit);
				pr1.setLong(1,bal);
				pr1.setInt(2, pin);
				int i1 = pr1.executeUpdate();
				if(i1>0)
				{
					System.out.println("Amount deposited");
				}
				else
				{
					System.out.println("Transaction failed.");
				}
			}
	}
			void WithdrawAmount() throws ClassNotFoundException, SQLException
			{
				Scanner sc=new Scanner(System.in);
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm1_darshan", "root", "darshan3009");
				Statement st = conn.createStatement();
				query = "SELECT balance FROM Accountholder1 WHERE pin = ?";	
	  				System.out.println("Enter the pin");
	  				pin=sc.nextInt();
	  				PreparedStatement pr1=conn.prepareStatement(query);
	  				pr1.setInt(1, pin);
	  				ResultSet resultSet1 = pr1.executeQuery();
	  				if (resultSet1.next()) 
	  				{
	  					System.out.println(resultSet1.getLong(1));
	  					long bal = resultSet1.getLong(1);
	  					System.out.println("Enter The withdraw Amount: ");
		  				long Amount=sc.nextLong();
		  				//bal = bal -Amount;
		  			if (Amount > 0 && Amount <= bal) 
		          {
		              bal -= Amount;
		              System.out.println("Withdrawal successful. Remaining balance: $" + bal);
		          } 
		          else 
		          {
		              System.out.println("Invalid withdrawal amount or insufficient funds.");
		          }
		  				querydeposit = "update Accountholder1 set balance = ? where pin = ?";
		  				PreparedStatement pr11=conn.prepareStatement(querydeposit);
		  				pr11.setLong(1,bal);
		  				pr11.setInt(2, pin);
		  				
		  				int i1 = pr11.executeUpdate();
		  				if(i1>0)
		  				{
		  					System.out.println("withdraw deposited");
		  				}
		  				else
		  				{
		  					System.out.println("Transaction failed.");
		  				}
	  				}
			}
			void BalanceEnquiry() throws ClassNotFoundException, SQLException
			{
				Scanner sc=new Scanner(System.in);
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm1_darshan", "root", "darshan3009");
				Statement st = conn.createStatement();
				System.out.println("Enter the pin");
				int pin=sc.nextInt();
				queryBalanceEnquiry="select balance from   accountholder1 where pin=?";
				PreparedStatement pr1=conn.prepareStatement(queryBalanceEnquiry);
  				pr1.setInt(1, pin);
  				ResultSet resultSet1 = pr1.executeQuery();
  				while(resultSet1.next()) 
  				{
  					System.out.println("Balance Enquiry is:" +resultSet1.getLong(1));
  			    }
			}
			void AllAccountHolderlist() throws ClassNotFoundException, SQLException
			{
				Scanner sc=new Scanner(System.in);
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm1_darshan", "root", "darshan3009");
				Statement st = conn.createStatement();
				query="select *from accountholder1";
				ResultSet rs = st.executeQuery(query);
				while(rs.next()) 
				{
					System.out.println(rs.getInt(1) + " " +rs.getString(2)+" "+rs.getString(3)+" "+rs.getLong(4)+" "+rs.getString(5)+" "+rs.getString(6)+" "+rs.getLong(7)+" "+rs.getLong(8)+" "+rs.getLong(9)+" "+rs.getInt(10)+" "+rs.getString(11));
			    }
			}
			
			void CloseAccount() throws ClassNotFoundException, SQLException
			{
				Scanner sc=new Scanner(System.in);
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm1_darshan", "root", "darshan3009");
				Statement st = conn.createStatement();
				System.out.println("Enter the number");
				int number=sc.nextInt();
				query="delete from accountholder1 where number=?";
				PreparedStatement pr1=conn.prepareStatement(query);
				pr1.setInt(1, number);
				int i=pr1.executeUpdate();
				System.out.println(i+"rows is delete");	
			}
			
			void ModifyAccount() throws ClassNotFoundException, SQLException
			{
				Scanner sc=new Scanner(System.in);
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm1_darshan", "root", "darshan3009");
				Statement st = conn.createStatement();
				System.out.println("Enter the number");
				int Number=sc.nextInt();
				System.out.println("Enter the Name");
				String Name=sc.next();
				System.out.println("Enter the EmailId");
				String EmailId=sc.next();
				System.out.println("Enter the ContanctNumber");
				Long ContanctNumber=sc.nextLong();
				System.out.println("Enter the Branch");
				String Branch=sc.next();
				System.out.println("Enter the Type");
				String Type=sc.next();
				query="update Accountholder1 set name=?,EmailId=?,ContanctNumber=?,Branch=?,Type=? where number= ?";
				PreparedStatement pr1=conn.prepareStatement(query);
				pr1.setString(1, Name);
				pr1.setString(2, EmailId);
				pr1.setLong(3, ContanctNumber);
				pr1.setString(4, Branch);
				pr1.setString(5,Type);
				pr1.setInt(6,Number);
				int i1 = pr1.executeUpdate();
	  				if(i1>0)
	  				{
	  					System.out.println("Modify Change Successfully");
	  				}
	  				else
	  				{
	  					System.out.println("Modify is Not Change");
	  				}
			}
    }
public class Bank_Management_System 
{
	public static void main(String args[])throws ClassNotFoundException, SQLException
	{
		int choice;
		Bank b=new Bank();
		do
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("***********************");
			System.out.println("BANK MANAGEMENT SYSTEM");
			System.out.println("***********************");
			System.out.println("1.NEW ACCOUNT");
			System.out.println("2.DEPOSIT AMOUNT");
			System.out.println("3.WITHDRAW AMOUNT");
			System.out.println("4.BALANCE ENQUIRY");
			System.out.println("5.ALL ACCOUNT HOLDER LIST");
			System.out.println("6.CLOSE AN ACCOUNT");
			System.out.println("7.MODIFY AN ACCOUNT");
			System.out.println("8.EXIT");
			System.out.println("Enter the Choice[1-8]");
		    choice=sc.nextInt();
		    switch(choice)
		    {
		    case 1:
		    	b.NewAccount();
		    	break;
		    case 2:
		    	b.DepositAmount();
		    	break;
		    case 3:
		    	b.WithdrawAmount();
		    	break;
		    case 4:
		    	b.BalanceEnquiry();
		    	break;
		    case 5:
		    	b.AllAccountHolderlist();
		    	break;
		    case 6:
		    	b.CloseAccount();
		    	break;
		    case 7:
		    	b.ModifyAccount();
		    	break;
		    case 8:
		    	System.out.println("Exit");
		    	break;
		    	default:
		    		   System.out.println("Invalid choice. Please enter a valid option (1-8).");	
		    }
		}while(choice<8);
	}
}