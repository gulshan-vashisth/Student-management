import java.sql.*;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args){
        try{
            Connection con= DriverManager.getConnection(DBConfigStd.url,DBConfigStd.username,DBConfigStd.password);
            Scanner sc = new Scanner(System.in);

            while(true){
                System.out.print("=== STUDENT MANAGEMENT SYSTEM\n");
                System.out.println("1. Insert Student");
                System.out.println("2. Update Marks");
                System.out.println("3. Delete Student");
                System.out.println("4. Search Student");
                System.out.println("5. Exit");

                System.out.println("Enter the choice: ");

                int choice= sc.nextInt();
                switch(choice){
                    case 1:
                        System.out.println("Enter the roll no: ");
                        int roll=sc.nextInt();
                        System.out.println("Enter name: ");
                        String name=sc.next();
                        System.out.println("Enter marks: ");
                        double marks=sc.nextDouble();

                        // query: insert into student
                        PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?)");
                        ps.setInt(1,roll);
                        ps.setString(2,name);
                        ps.setDouble(3,marks);

                        int rows=ps.executeUpdate();
                        System.out.println(rows+ "record updated");
                        break;

                    case 2:
                        System.out.println("Enter roll no to update");
                        int rollu=sc.nextInt();
                        System.out.println("Enter the marks: ");
                        double newMarks= sc.nextDouble();

                        PreparedStatement ps1= con.prepareStatement("update student set marks=? where roll=?");
                        ps1.setDouble(1,newMarks);
                        ps1.setInt(2,rollu);

                        int updated=ps1.executeUpdate();

                        if(updated>0){
                            System.out.println("record updated");
                        }
                        else{
                            System.out.println("roll no not found");
                        }
                        break;

                    case 3: //delete
                        System.out.println("Enter nollno to delete: ");
                        int rolld=sc.nextInt();

                        PreparedStatement ps2= con.prepareStatement("delete from student where roll=? ");

                        ps2.setInt(1,rolld);
                        int deleted=ps2.executeUpdate();

                        if(deleted>0){
                            System.out.println("record deleted");
                        }
                        else{
                            System.out.println("roll no not found");
                        }
                        break;

                    case 4: //search
                        System.out.println("Enter the roll no to search: ");
                        int rolls=sc.nextInt();

                        PreparedStatement ps3= con.prepareStatement("select * from student where roll = ? ");
                        ps3.setInt(1,rolls);

                        ResultSet rs= ps3.executeQuery();
                        if(rs.next()){
                            System.out.println("data found: "+rs.getInt(1)+" | "+rs.getString(2)+" | "+rs.getDouble(3));
                        }
                        else{
                            System.out.println("student     not found");
                        }
                        break;

                    case 5:
                        System.out.println("Existing...");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again...");


                }
            }


        }
        catch (SQLException e){
            System.out.println("Database error: "+e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
