import java.sql.*;
import java.util.Scanner;

// Interface (Abstraction)
interface FakeNewsOperations {
    void addUser();
    void addNews();
    void reportNews();
    void factCheckNews();
    void viewReports();
    void viewFakeNews();
}

// DB Connection Class
class DBConnection {
    static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/fakenewsdb",
                    "root",
                    "Bharat@#23");
        } catch (Exception e) {
            System.out.println("Connection Error: " + e);
        }
        return con;
    }
}

// Service Class (Implements Interface)
class FakeNewsService implements FakeNewsOperations {

    Connection con = DBConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    // Add User
    public void addUser() {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Role (user/fact_checker): ");
            String role = sc.nextLine();

            String query = "INSERT INTO Users VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, role);

            ps.executeUpdate();
            System.out.println("User Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Add News
    public void addNews() {
        try {
            System.out.print("Enter News ID: ");
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Content: ");
            String content = sc.nextLine();

            System.out.print("Enter Source ID: ");
            int sid = sc.nextInt();

            String query = "INSERT INTO News VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, content);
            ps.setInt(4, sid);

            ps.executeUpdate();
            System.out.println("News Added Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Report News
    public void reportNews() {
        try {
            System.out.print("Enter Report ID: ");
            int rid = sc.nextInt();

            System.out.print("Enter User ID: ");
            int uid = sc.nextInt();

            System.out.print("Enter News ID: ");
            int nid = sc.nextInt(); sc.nextLine();

            System.out.print("Enter Reason: ");
            String reason = sc.nextLine();

            String query = "INSERT INTO Reports VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, rid);
            ps.setInt(2, uid);
            ps.setInt(3, nid);
            ps.setString(4, reason);

            ps.executeUpdate();
            System.out.println("Report Submitted");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

public void factCheckNews() {
    try {
        System.out.print("Enter News ID to check: ");
        int nid = sc.nextInt();

        // Check if news is reported
        String checkQuery = "SELECT COUNT(*) FROM Reports WHERE news_id = ?";
        PreparedStatement ps1 = con.prepareStatement(checkQuery);
        ps1.setInt(1, nid);

        ResultSet rs1 = ps1.executeQuery();
        int reportCount = 0;

        if (rs1.next()) {
            reportCount = rs1.getInt(1);
        }

        if (reportCount == 0) {
            System.out.println("This news is not reported. Cannot fact-check.");
            return;
        }

        // Apply logic
        String status;
        String remarks;

        if (reportCount >= 2) {
            status = "Fake";
            remarks = "Multiple reports";
        } else {
            status = "Real";
            remarks = "Few reports";
        }

        System.out.println("System Decision: " + status);

        // Store result
        System.out.print("Enter Check ID: ");
        int cid = sc.nextInt();

        System.out.print("Enter Checker ID: ");
        int checker = sc.nextInt();

        String insertQuery = "INSERT INTO Fact_Checks VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps2 = con.prepareStatement(insertQuery);

        ps2.setInt(1, cid);
        ps2.setInt(2, nid);
        ps2.setInt(3, checker);
        ps2.setString(4, status);
        ps2.setString(5, remarks);

        ps2.executeUpdate();

        System.out.println("Fact Check Stored");

    } catch (Exception e) {
        System.out.println(e);
    }
}

    // View Reports (JOIN)
    public void viewReports() {
        try {
            String query = "SELECT Users.name, News.title, Reports.reason " +
                    "FROM Reports " +
                    "JOIN Users ON Reports.user_id = Users.user_id " +
                    "JOIN News ON Reports.news_id = News.news_id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Reports ---");

            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("reason"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // View Fake News
    public void viewFakeNews() {
        try {
            String query = "SELECT News.title, Fact_Checks.status " +
                    "FROM Fact_Checks " +
                    "JOIN News ON Fact_Checks.news_id = News.news_id " +
                    "WHERE status = 'Fake'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Fake News ---");

            while (rs.next()) {
                System.out.println(
                        rs.getString("title") + " | " +
                        rs.getString("status"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Main Class
public class DB_Project {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FakeNewsOperations obj = new FakeNewsService();

        while (true) {
            System.out.println("\n1. Add User");
            System.out.println("2. Add News");
            System.out.println("3. Report News");
            System.out.println("4. Fact Check");
            System.out.println("5. View Reports");
            System.out.println("6. View Fake News");
            System.out.println("7. Exit");

            System.out.print("Enter Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1: obj.addUser(); break;
                case 2: obj.addNews(); break;
                case 3: obj.reportNews(); break;
                case 4: obj.factCheckNews(); break;
                case 5: obj.viewReports(); break;
                case 6: obj.viewFakeNews(); break;
                case 7: System.out.println("Exiting..."); System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }
}