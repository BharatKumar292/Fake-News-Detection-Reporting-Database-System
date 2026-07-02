import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

//========================================================
// INTERFACE
//========================================================

interface FakeNewsOperations {

    void addUser();

    void addNews();

    void reportNews();

    void factCheckNews();

    void viewReports();

    void viewFakeNews();

}

//========================================================
// DATABASE CONNECTION
//========================================================

class DBConnection {

    static Connection con;

    public static Connection getConnection() {

        try {

            if (con == null || con.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/fakenewsdb",
                        "root",
                        "Bharat@#23");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,
                    "Database Error\n" + e.getMessage());

        }

        return con;

    }

}

//========================================================
// MAIN CLASS
//========================================================

public class DBMS_Project {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new LoginFrame();

        });

    }

}

//========================================================
// LOGIN FRAME
//========================================================

class LoginFrame extends JFrame implements ActionListener {

    JTextField txtUser;

    JPasswordField txtPass;

    JComboBox<String> role;

    JButton login, signup, exit;

    LoginFrame() {

        setTitle("Fake News Detection System");

        // setSize(700,500);
        setSize(900,650);
setResizable(false);
setLocationRelativeTo(null);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        getContentPane().setBackground(new Color(236,240,241));

        JLabel title = new JLabel("Fake News Detection System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setForeground(new Color(44,62,80));

        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(0, 40, 900, 50);

        add(title);

        JLabel subtitle = new JLabel("Database Management Project");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBounds(0, 90, 900, 35);

        add(subtitle);

        JPanel panel = new JPanel();

        panel.setLayout(null);

        panel.setBounds(170,170,560,330);
        panel.setBackground(Color.WHITE);

        panel.setBorder(new LineBorder(new Color(52,152,219),2,true));

        add(panel);



        JLabel l1 = new JLabel("Email");

        l1.setBounds(30,30,100,25);

        panel.add(l1);



        txtUser = new JTextField();

        txtUser.setBounds(140,30,180,30);

        panel.add(txtUser);



        JLabel l2 = new JLabel("Password");

        l2.setBounds(30,80,100,25);

        panel.add(l2);



        txtPass = new JPasswordField();

        txtPass.setBounds(140,80,180,30);

        panel.add(txtPass);



        JLabel l3 = new JLabel("Role");

        l3.setBounds(30,130,100,25);

        panel.add(l3);



        role = new JComboBox<>();

        role.addItem("User");

        role.addItem("Fact Checker");

        role.setBounds(140,130,180,30);

        panel.add(role);



        login = new JButton("LOGIN");
        signup = new JButton("SIGN UP");

        signup.setBounds(190,220,140,45);

        signup.setBackground(new Color(46,204,113));

        signup.setForeground(Color.WHITE);

        signup.setFocusPainted(false);

        signup.setFont(new Font("Segoe UI",Font.BOLD,14));

        signup.addActionListener(this);

        panel.add(signup);


        login.setBounds(30,220,140,45);

        login.setBackground(new Color(41,128,185));

        login.setForeground(Color.WHITE);

        login.setFocusPainted(false);

        login.setFont(new Font("Segoe UI",Font.BOLD,14));

        login.addActionListener(this);

        panel.add(login);



        exit = new JButton("EXIT");

        exit.setBounds(350,220,140,45);

        exit.setBackground(new Color(192,57,43));

        exit.setForeground(Color.WHITE);

        exit.setFocusPainted(false);

        exit.setFont(new Font("Segoe UI",Font.BOLD,14));

        exit.addActionListener(this);

        panel.add(exit);



        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // LOGIN BUTTON
        if (e.getSource() == login) {

            try {

                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM Users WHERE email=? AND password=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, txtUser.getText().trim());
                ps.setString(2, String.valueOf(txtPass.getPassword()));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this, "Login Successful");

                    dispose();

                    new Dashboard();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Invalid Email or Password");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

        }

        // SIGN UP BUTTON
        else if (e.getSource() == signup) {

            new RegisterFrame();

        }

        // EXIT BUTTON
        else if (e.getSource() == exit) {

            System.exit(0);

        }

    }
}



// =====================================
//   Registration Frame
// =====================================

class RegisterFrame extends JFrame implements ActionListener{

    JTextField id,name,email;

    JPasswordField password;

    JComboBox<String> role;

    JButton register;

    Connection con = DBConnection.getConnection();

    RegisterFrame(){

        setTitle("User Registration");

        setSize(450,450);

        setLayout(null);

        setLocationRelativeTo(null);

        JLabel l1=new JLabel("ID");
        l1.setBounds(40,40,100,30);
        add(l1);

        id=new JTextField();
        id.setBounds(160,40,180,30);
        add(id);

        JLabel l2=new JLabel("Name");
        l2.setBounds(40,90,100,30);
        add(l2);

        name=new JTextField();
        name.setBounds(160,90,180,30);
        add(name);

        JLabel l3=new JLabel("Email");
        l3.setBounds(40,140,100,30);
        add(l3);

        email=new JTextField();
        email.setBounds(160,140,180,30);
        add(email);

        JLabel l4=new JLabel("Password");
        l4.setBounds(40,190,100,30);
        add(l4);

        password=new JPasswordField();
        password.setBounds(160,190,180,30);
        add(password);

        JLabel l5=new JLabel("Role");
        l5.setBounds(40,240,100,30);
        add(l5);

        role=new JComboBox<>();

        role.addItem("user");

        role.addItem("fact_checker");

        role.addItem("admin");

        role.setBounds(160,240,180,30);

        add(role);

        register=new JButton("Register");

        register.setBounds(130,320,150,40);

        register.addActionListener(this);

        add(register);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){

        try{

            String sql = "INSERT INTO Users(user_id,name,email,role,password) VALUES(?,?,?,?,?)";            
            PreparedStatement ps=con.prepareStatement(sql);

            ps.setInt(1,Integer.parseInt(id.getText()));

            ps.setString(2,name.getText());

            ps.setString(3,email.getText());

            ps.setString(4,
                    String.valueOf(password.getPassword()));

            ps.setString(5,
                    role.getSelectedItem().toString());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Registration Successful");

            dispose();

        }

        catch(Exception ex){

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());

        }

    }

}


//========================================================
// DASHBOARD
//========================================================

class Dashboard extends JFrame implements ActionListener {

    JButton addUserBtn;
    JButton addNewsBtn;
    JButton reportBtn;
    JButton factBtn;
    JButton reportViewBtn;
    JButton fakeNewsBtn;
    JButton logoutBtn;

    Dashboard() {

        setTitle("Fake News Detection System - Dashboard");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //---------------- TOP PANEL -----------------

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(33,150,243));
        topPanel.setPreferredSize(new Dimension(1000,70));

        JLabel title = new JLabel("FAKE NEWS DETECTION & REPORTING SYSTEM");

        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        title.setForeground(Color.WHITE);

        topPanel.add(title);

        add(topPanel,BorderLayout.NORTH);

        //---------------- LEFT PANEL -----------------

        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(220,600));
        leftPanel.setBackground(new Color(44,62,80));

        leftPanel.setLayout(new GridLayout(7,1,10,15));

        addUserBtn = new JButton("Add User");

        addNewsBtn = new JButton("Add News");

        reportBtn = new JButton("Report News");

        factBtn = new JButton("Fact Check");

        reportViewBtn = new JButton("View Reports");

        fakeNewsBtn = new JButton("Fake News");

        logoutBtn = new JButton("Logout");

        JButton buttons[] = {

                addUserBtn,
                addNewsBtn,
                reportBtn,
                factBtn,
                reportViewBtn,
                fakeNewsBtn,
                logoutBtn

        };

        Font f = new Font("Segoe UI",Font.BOLD,18);

        for(JButton b : buttons){

            b.setFont(f);

            b.setFocusPainted(false);

            b.setBackground(new Color(52,152,219));

            b.setForeground(Color.WHITE);

            b.addActionListener(this);

            leftPanel.add(b);

        }

        add(leftPanel,BorderLayout.WEST);

        //---------------- CENTER PANEL -----------------

        JPanel center = new JPanel();

        center.setBackground(new Color(236,240,241));

        center.setLayout(null);

        JLabel welcome = new JLabel("Welcome to Fake News Detection System");

        welcome.setFont(new Font("Segoe UI",Font.BOLD,28));

        welcome.setBounds(120,40,500,40);

        center.add(welcome);

        JPanel card1 = createCard("Users");

        card1.setBounds(70,130,180,120);

        center.add(card1);

        JPanel card2 = createCard("News");

        card2.setBounds(310,130,180,120);

        center.add(card2);

        JPanel card3 = createCard("Reports");

        card3.setBounds(550,130,180,120);

        center.add(card3);

        JPanel card4 = createCard("Fake News");

        card4.setBounds(190,300,180,120);

        center.add(card4);

        JPanel card5 = createCard("Fact Checks");

        card5.setBounds(450,300,180,120);

        center.add(card5);

        add(center,BorderLayout.CENTER);

        setVisible(true);

    }

    //------------- Dashboard Card ----------------

    JPanel createCard(String text){

        JPanel p = new JPanel();

        p.setBackground(Color.WHITE);

        p.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));

        p.setLayout(new BorderLayout());

        JLabel lbl = new JLabel(text,SwingConstants.CENTER);

        lbl.setFont(new Font("Segoe UI",Font.BOLD,22));

        lbl.setForeground(new Color(41,128,185));

        p.add(lbl);

        return p;

    }

    //---------------- BUTTON EVENTS ----------------

    @Override

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addUserBtn){

            new AddUserFrame();

        }

        else if(e.getSource()==addNewsBtn){

            new AddNewsFrame();

        }

        else if(e.getSource()==reportBtn){

            new ReportNewsFrame();

        }

        else if(e.getSource()==factBtn){

            new FactCheckFrame();

        }

        else if(e.getSource()==reportViewBtn){

            new ViewReportsFrame();

        }

        else if(e.getSource()==fakeNewsBtn){

            new ViewFakeNewsFrame();

        }

        else if(e.getSource()==logoutBtn){

            dispose();

            new LoginFrame();

        }

    }

}

//========================================================
// ADD USER FRAME
//========================================================

class AddUserFrame extends JFrame implements ActionListener {

    JTextField txtId, txtName, txtEmail;
    JPasswordField txtPassword;
    JComboBox<String> roleBox;
    JButton saveBtn, clearBtn;

    Connection con = DBConnection.getConnection();

    AddUserFrame() {

        setTitle("Add New User");
        setSize(500,500);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(245,245,245));

        JLabel heading = new JLabel("ADD USER");
        heading.setFont(new Font("Segoe UI",Font.BOLD,24));
        heading.setBounds(170,20,200,40);
        add(heading);

        JLabel l1 = new JLabel("User ID");
        l1.setBounds(50,90,100,30);
        add(l1);

        txtId = new JTextField();
        txtId.setBounds(170,90,220,30);
        add(txtId);

        JLabel l2 = new JLabel("Name");
        l2.setBounds(50,140,100,30);
        add(l2);

        txtName = new JTextField();
        txtName.setBounds(170,140,220,30);
        add(txtName);

        JLabel l3 = new JLabel("Email");
        l3.setBounds(50,190,100,30);
        add(l3);

        txtEmail = new JTextField();
        txtEmail.setBounds(170,190,220,30);
        add(txtEmail);

        JLabel l4 = new JLabel("Password");
        l4.setBounds(50,240,100,30);
        add(l4);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(170,240,220,30);
        add(txtPassword);

        JLabel l5 = new JLabel("Role");
        l5.setBounds(50,290,100,30);
        add(l5);

        roleBox = new JComboBox<>();

        roleBox.addItem("user");
        roleBox.addItem("fact_checker");
        roleBox.addItem("admin");

        roleBox.setBounds(170,290,220,30);
        add(roleBox);

        saveBtn = new JButton("Save");

        saveBtn.setBounds(90,350,120,40);

        saveBtn.setBackground(new Color(46,204,113));

        saveBtn.setForeground(Color.WHITE);

        saveBtn.addActionListener(this);

        add(saveBtn);

        clearBtn = new JButton("Clear");

        clearBtn.setBounds(250,350,120,40);
        clearBtn.setBackground(new Color(231,76,60));

        clearBtn.setForeground(Color.WHITE);

        clearBtn.addActionListener(this);

        add(clearBtn);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==saveBtn){

            try{

        String sql = "INSERT INTO Users(user_id,name,email,role,password) VALUES(?,?,?,?,?)";
                PreparedStatement ps=con.prepareStatement(sql);

                ps.setInt(1, Integer.parseInt(txtId.getText().trim()));
                ps.setString(2, txtName.getText().trim());
                ps.setString(3, txtEmail.getText().trim());
                ps.setString(4, roleBox.getSelectedItem().toString());
                ps.setString(5, String.valueOf(txtPassword.getPassword()));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "User Added Successfully");

                txtId.setText("");
                txtName.setText("");
                txtEmail.setText("");
                txtPassword.setText("");

            }

            catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

        }

        if(e.getSource()==clearBtn){

            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");

        }

    }

}

//========================================================
// ADD NEWS FRAME
//========================================================

class AddNewsFrame extends JFrame implements ActionListener{

    JTextField txtId;
    JTextField txtTitle;
    JTextArea txtContent;
    JComboBox<Integer> sourceBox;

    JButton saveBtn;
    JButton clearBtn;

    Connection con = DBConnection.getConnection();

    AddNewsFrame(){

        setTitle("Add News");

        setSize(600,550);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel title=new JLabel("ADD NEWS");

        title.setFont(new Font("Segoe UI",Font.BOLD,24));

        title.setBounds(220,20,200,40);

        add(title);

        JLabel l1=new JLabel("News ID");

        l1.setBounds(50,90,120,30);

        add(l1);

        txtId=new JTextField();

        txtId.setBounds(170,90,300,30);

        add(txtId);

        JLabel l2=new JLabel("Title");

        l2.setBounds(50,140,120,30);

        add(l2);

        txtTitle=new JTextField();

        txtTitle.setBounds(170,140,300,30);

        add(txtTitle);

        JLabel l3=new JLabel("Content");

        l3.setBounds(50,190,120,30);

        add(l3);

        txtContent=new JTextArea();

        JScrollPane sp=new JScrollPane(txtContent);

        sp.setBounds(170,190,300,140);

        add(sp);

        JLabel l4=new JLabel("Source");

        l4.setBounds(50,350,120,30);

        add(l4);

        sourceBox=new JComboBox<>();

        loadSources();

        sourceBox.setBounds(170,350,300,30);

        add(sourceBox);

        saveBtn=new JButton("Save");

        saveBtn.setBounds(130,430,120,40);

        saveBtn.setBackground(new Color(39,174,96));

        saveBtn.setForeground(Color.WHITE);

        saveBtn.addActionListener(this);

        add(saveBtn);

        clearBtn=new JButton("Clear");

        clearBtn.setBounds(300,430,120,40);

        clearBtn.setBackground(Color.RED);

        clearBtn.setForeground(Color.WHITE);

        clearBtn.addActionListener(this);

        add(clearBtn);

        setVisible(true);

    }

    void loadSources(){

        try{

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery("SELECT source_id FROM Sources");

            while(rs.next()){

                sourceBox.addItem(rs.getInt(1));

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,e);

        }

    }

    @Override

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==saveBtn){

            try{

                String sql="INSERT INTO News VALUES(?,?,?,?)";

                PreparedStatement ps=con.prepareStatement(sql);

                ps.setInt(1,Integer.parseInt(txtId.getText()));

                ps.setString(2,txtTitle.getText());

                ps.setString(3,txtContent.getText());

                ps.setInt(4,(Integer)sourceBox.getSelectedItem());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "News Added Successfully");

            }

            catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

        }

        if(e.getSource()==clearBtn){

            txtId.setText("");

            txtTitle.setText("");

            txtContent.setText("");

        }

    }

}

//========================================================
// REPORT NEWS FRAME
//========================================================

class ReportNewsFrame extends JFrame implements ActionListener {

    JComboBox<Integer> userBox;
    JComboBox<Integer> newsBox;
    JTextArea reasonArea;

    JButton submitBtn;
    JButton clearBtn;

    Connection con = DBConnection.getConnection();

    ReportNewsFrame(){

        setTitle("Report Fake News");

        setSize(600,500);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading=new JLabel("REPORT NEWS");

        heading.setFont(new Font("Segoe UI",Font.BOLD,24));

        heading.setBounds(180,20,250,40);

        add(heading);

        JLabel l1=new JLabel("User");

        l1.setBounds(50,90,120,30);

        add(l1);

        userBox=new JComboBox<>();

        loadUsers();

        userBox.setBounds(180,90,250,30);

        add(userBox);

        JLabel l2=new JLabel("News");

        l2.setBounds(50,150,120,30);

        add(l2);

        newsBox=new JComboBox<>();

        loadNews();

        newsBox.setBounds(180,150,250,30);

        add(newsBox);

        JLabel l3=new JLabel("Reason");

        l3.setBounds(50,210,120,30);

        add(l3);

        reasonArea=new JTextArea();

        JScrollPane sp=new JScrollPane(reasonArea);

        sp.setBounds(180,210,300,120);

        add(sp);

        submitBtn=new JButton("Submit");

        submitBtn.setBounds(140,370,120,40);

        submitBtn.setBackground(new Color(39,174,96));

        submitBtn.setForeground(Color.WHITE);

        submitBtn.addActionListener(this);

        add(submitBtn);

        clearBtn=new JButton("Clear");

        clearBtn.setBounds(300,370,120,40);

        clearBtn.setBackground(Color.RED);

        clearBtn.setForeground(Color.WHITE);

        clearBtn.addActionListener(this);

        add(clearBtn);

        setVisible(true);

    }

    void loadUsers(){

        try{

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery("SELECT user_id FROM Users");

            while(rs.next()){

                userBox.addItem(rs.getInt(1));

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,e);

        }

    }

    void loadNews(){

        try{

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery("SELECT news_id FROM News");

            while(rs.next()){

                newsBox.addItem(rs.getInt(1));

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,e);

        }

    }

    @Override

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==submitBtn){

            try{

                int reportId=0;

                Statement st=con.createStatement();

                ResultSet rs=st.executeQuery("SELECT IFNULL(MAX(report_id),0)+1 FROM Reports");

                if(rs.next())
                    reportId=rs.getInt(1);

                String sql="INSERT INTO Reports VALUES(?,?,?,?)";

                PreparedStatement ps=con.prepareStatement(sql);

                ps.setInt(1,reportId);

                ps.setInt(2,(Integer)userBox.getSelectedItem());

                ps.setInt(3,(Integer)newsBox.getSelectedItem());

                ps.setString(4,reasonArea.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,"Report Submitted Successfully");

                reasonArea.setText("");

            }

            catch(Exception ex){

                JOptionPane.showMessageDialog(this,ex.getMessage());

            }

        }

        if(e.getSource()==clearBtn){

            reasonArea.setText("");

        }

    }

}

//========================================================
// FACT CHECK FRAME
//========================================================

class FactCheckFrame extends JFrame implements ActionListener {

    JComboBox<Integer> newsBox;

    JTextArea resultArea;

    JButton checkBtn;

    Connection con=DBConnection.getConnection();

    FactCheckFrame(){

        setTitle("Fact Checker");

        setSize(600,450);

        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel title=new JLabel("FACT CHECK");

        title.setFont(new Font("Segoe UI",Font.BOLD,25));

        title.setBounds(180,20,250,40);

        add(title);

        JLabel l1=new JLabel("Reported News");

        l1.setBounds(50,100,150,30);

        add(l1);

        newsBox=new JComboBox<>();

        loadReportedNews();

        newsBox.setBounds(210,100,220,30);

        add(newsBox);

        checkBtn=new JButton("CHECK");

        checkBtn.setBounds(210,160,150,40);

        checkBtn.setBackground(new Color(52,152,219));

        checkBtn.setForeground(Color.WHITE);

        checkBtn.addActionListener(this);

        add(checkBtn);

        resultArea=new JTextArea();

        resultArea.setEditable(false);

        JScrollPane sp=new JScrollPane(resultArea);

        sp.setBounds(80,230,420,130);

        add(sp);

        setVisible(true);

    }

    void loadReportedNews(){

        try{

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery("SELECT DISTINCT news_id FROM Reports");

            while(rs.next()){

                newsBox.addItem(rs.getInt(1));

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,e);

        }

    }

    @Override

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==checkBtn){

            try{

                int newsId=(Integer)newsBox.getSelectedItem();

                PreparedStatement ps=con.prepareStatement(
                        "SELECT COUNT(*) FROM Reports WHERE news_id=?");

                ps.setInt(1,newsId);

                ResultSet rs=ps.executeQuery();

                int reports=0;

                if(rs.next())

                    reports=rs.getInt(1);

                String status;

                String remarks;

                if(reports>=2){

                    status="Fake";

                    remarks="Multiple users reported this news.";

                }

                else{

                    status="Real";

                    remarks="Insufficient reports.";

                }

                resultArea.setText("");

                resultArea.append("News ID : "+newsId+"\n\n");

                resultArea.append("Reports : "+reports+"\n\n");

                resultArea.append("Decision : "+status+"\n\n");

                resultArea.append("Remarks : "+remarks);

                int checkId=1;

                Statement st=con.createStatement();

                ResultSet id=st.executeQuery(
                        "SELECT IFNULL(MAX(check_id),0)+1 FROM Fact_Checks");

                if(id.next())

                    checkId=id.getInt(1);

                PreparedStatement insert=con.prepareStatement(

                        "INSERT INTO Fact_Checks VALUES(?,?,?,?,?)"

                );

                insert.setInt(1,checkId);

                insert.setInt(2,newsId);

                insert.setInt(3,1);

                insert.setString(4,status);

                insert.setString(5,remarks);

                insert.executeUpdate();

            }

            catch(Exception ex){

                JOptionPane.showMessageDialog(this,ex.getMessage());

            }

        }

    }

}


//========================================================
// VIEW REPORTS FRAME
//========================================================

class ViewReportsFrame extends JFrame {

    JTable table;

    DefaultTableModel model;

    Connection con = DBConnection.getConnection();

    ViewReportsFrame() {

        setTitle("View Reports");

        setSize(850,500);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel title = new JLabel("ALL REPORTED NEWS", JLabel.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));

        title.setOpaque(true);

        title.setBackground(new Color(52,152,219));

        title.setForeground(Color.WHITE);

        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Report ID");
        model.addColumn("User");
        model.addColumn("News");
        model.addColumn("Reason");

        table = new JTable(model);

        table.setRowHeight(28);

        JScrollPane pane = new JScrollPane(table);

        add(pane, BorderLayout.CENTER);

        loadReports();

        setVisible(true);

    }

    void loadReports() {

        try {

            String sql =
                    "SELECT Reports.report_id, Users.name, News.title, Reports.reason " +
                    "FROM Reports " +
                    "JOIN Users ON Reports.user_id=Users.user_id " +
                    "JOIN News ON Reports.news_id=News.news_id";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt(1),

                        rs.getString(2),

                        rs.getString(3),

                        rs.getString(4)

                });

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,e.getMessage());

        }

    }

}

//========================================================
// VIEW FAKE NEWS
//========================================================

class ViewFakeNewsFrame extends JFrame {

    JTable table;

    DefaultTableModel model;

    Connection con = DBConnection.getConnection();

    ViewFakeNewsFrame(){

        setTitle("Fake News List");

        setSize(800,450);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel heading = new JLabel("FAKE NEWS DETECTED", JLabel.CENTER);

        heading.setFont(new Font("Segoe UI",Font.BOLD,24));

        heading.setBackground(new Color(231,76,60));

        heading.setForeground(Color.WHITE);

        heading.setOpaque(true);

        add(heading,BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("News ID");

        model.addColumn("News Title");

        model.addColumn("Status");

        model.addColumn("Remarks");

        table = new JTable(model);

        table.setRowHeight(30);

        JScrollPane pane = new JScrollPane(table);

        add(pane,BorderLayout.CENTER);

        loadFakeNews();

        setVisible(true);

    }

    void loadFakeNews(){

        try{

            String sql=

            "SELECT News.news_id,News.title,Fact_Checks.status,Fact_Checks.remarks " +

            "FROM Fact_Checks JOIN News " +

            "ON Fact_Checks.news_id=News.news_id " +

            "WHERE Fact_Checks.status='Fake'";

            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getInt(1),

                        rs.getString(2),

                        rs.getString(3),

                        rs.getString(4)

                });

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(this,e.getMessage());

        }

    }

}