import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Quiz1 {
	
	static Scanner user = new Scanner(System.in);
	static Connection conn = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Please enter the following details: ");
		System.out.print("Lastname: ");
		String Lname = user.nextLine();
		System.out.print("Firstname: ");
		String Fname = user.nextLine();
		System.out.print("Middlename: ");
		String Mname = user.nextLine();
		System.out.print("Age: ");
		int age = user.nextInt();
		
		System.out.println("Do you want to add another details? y/n");
		Scanner ans = new Scanner(System.in);
		String answer = ans.nextLine();
		
		if(answer.equalsIgnoreCase("y")) {
			main(args);
			
		}else if(answer.equalsIgnoreCase("n")) {
		
			try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz1?autoReconnect=true&useSSL=false","root","krates112918");
			
			pst = conn.prepareStatement("insert into user_details (last_name, first_name,middle_name, age)values(?,?,?,?)");
			pst.setString(1, Lname);
			pst.setString(2, Fname);
			pst.setString(3,Mname);
			pst.setInt(4, age);
			
			pst.executeUpdate();
			
			} catch (Exception e) {
			e.printStackTrace();
			
			} 
		}else
			System.out.println("invalid input");
		
		System.out.println("Choose from the following:");
		System.out.println("1-update  2-delete  3-select");
		System.out.print("Enter chosen number: ");
		int num = user.nextInt();
		
		switch(num) {
		case 1: 
			String ch;
			do {	
				System.out.println("What do you want to update? (a-lastname, b-firstname, c-middlename, d-age)");
				System.out.print("Enter your choice: ");
				Scanner choice = new Scanner(System.in);
				String a = choice.nextLine();
				System.out.print("Enter the id number: ");
				int id = choice.nextInt();
				System.out.print("Enter updated value: ");
				Scanner choice1 = new Scanner(System.in);
				String val = choice1.nextLine();
					
				if(a.equalsIgnoreCase("a")) {
					 pst = conn.prepareStatement("UPDATE user_details SET last_name = ? WHERE id = ?");
					 pst.setString(1, val);
					 pst.setInt(2, id);
					 pst.executeUpdate();
				}else if(a.equalsIgnoreCase("b")) {
					pst = conn.prepareStatement("UPDATE user_details SET first_name = ? WHERE id = ?");
				    pst.setString(1, val);
				    pst.setInt(2, id);
				    pst.executeUpdate();
				}else if(a.equalsIgnoreCase("c")) {
					pst = conn.prepareStatement("UPDATE user_details SET middle_name = ? WHERE id = ?");
				    pst.setString(1, val);
				    pst.setInt(2, id);
				    pst.executeUpdate();
				}else if(a.equalsIgnoreCase("d")) {
					pst = conn.prepareStatement("UPDATE user_details SET age = ? WHERE id = ?");
				    pst.setString(1, val);
				    pst.setInt(2, id);
				    pst.executeUpdate();
				}else
					System.out.println("invalid");
				
				System.out.print("do you still want to update? y/n: ");
				Scanner choice2 = new Scanner(System.in);
				ch = choice2.nextLine();
				
			}while(ch.equalsIgnoreCase("y"));
				
			break;
			
		case 2:
			System.out.print("Enter the id number you want to delete: ");
			Scanner sc = new Scanner(System.in);
		    int d = sc.nextInt();
		    pst = conn.prepareStatement("DELETE FROM user_details WHERE id = ?");
		    pst.setInt(1, d);
		    pst.execute();
			
		    break;
			
		case 3: 
			System.out.println("Please input the id number you want to select");
			Scanner sc1 = new Scanner(System.in);
		    int select = sc1.nextInt();
		    pst = conn.prepareStatement("SELECT * FROM user_details WHERE ID = ?");
		    pst.setInt(1, select);
		    rs = pst.executeQuery();

		    while(rs.next()) {
		    System.out.println(rs.getString("last_name") + " " +rs.getString("first_name") +  " " + rs.getString("middle_name") +  " " + rs.getString("age"));
		    
		    break;
			}
		} 
		 main(args);
	}
		
}