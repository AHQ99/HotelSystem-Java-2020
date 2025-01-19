package hotelsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class LoginFrame extends JFrame implements ActionListener{
    JButton Login, Exit, Register,admine,Employee;
    JLabel Username, Password,who;
    JTextField User;
    JPasswordField Pass;
    JPanel p1,p2,p3,p4;
    int a;
    String fnameget,lnameget,fulnameget,phone88;
    Connection connection = null;
    public LoginFrame(){
        super("Login");
        
        Username = new JLabel("Username: ");
        Password = new JLabel("Password: ");
        who = new JLabel("you want login as: ");
        User = new JTextField(8);
        Pass = new JPasswordField(9);
        admine = new JButton("admine");
        Employee = new JButton("Employee");
        Login = new JButton("Usere");
        Exit = new JButton("Exit");
        Register = new JButton("Register");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(Username);
        p1.add(User);
        this.add(p1, BorderLayout.NORTH);
        p2.setLayout(new FlowLayout());
        p2.add(Password);
        p2.add(Pass);
        this.add(p2, BorderLayout.CENTER);
        
        p3.setLayout(new FlowLayout());
        p3.add(who);
        p3.add(admine);
        p3.add(Employee);
        p3.add(Login);
        p3.add(Register);
        p3.add(Exit);
        this.add(p3, BorderLayout.SOUTH);
        
        admine.addActionListener(this);
        Employee.addActionListener(this);
        Login.addActionListener(this);
        Register.addActionListener(this);
        Exit.addActionListener(this);
        
        try{
            String db_url="jdbc:mysql://localhost:3306/hotel_reservation_db103";
            String db_username="root";
            String db_password="9517538624";
             connection = DriverManager.getConnection(db_url, db_username, db_password);
        }
        catch(SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Exit){
            this.dispose();
        }
        if(e.getSource()==admine){
          String uname =User.getText();
            String pass =Pass.getText();
            String sql="SELECT account_name,account_pass FROM hotel_reservation_db103.admin;";
            //Check the database then go into this conditions
            if(User.getText().isEmpty() || Pass.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Username and Password are Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }
            else {
                try{
                Statement s = connection.createStatement();
                ResultSet resultSet = s.executeQuery(sql);
                while(resultSet.next()){
                
                if(uname.equals(resultSet.getObject(1))&&pass.equals(resultSet.getObject(2))){
                JOptionPane.showMessageDialog(this,"Hello Mr." + User.getText()+" you are the MANAGER and the success of this hotel depends on you.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                
                a=1;
                 
               
                this.dispose();
                adminForm a = new adminForm();
                a.setVisible(true);
                }
                }
                if(a!=1){
                    JOptionPane.showMessageDialog(this,"Username or Password is wrong" , "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                }
                catch(SQLException sqlException){
                System.out.println(sqlException.getMessage());
                }
                
                
            }
        }
        if(e.getSource()==Employee){
            String uname =User.getText();
            String pass =Pass.getText();
            String sql="SELECT account_name,account_pass FROM hotel_reservation_db103.emp;";
            //Check the database then go into this conditions
            if(User.getText().isEmpty() || Pass.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Username and Password are Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }
            else {
                try{
                Statement s = connection.createStatement();
                ResultSet resultSet = s.executeQuery(sql);
                while(resultSet.next()){
                
                if(uname.equals(resultSet.getObject(1))&&pass.equals(resultSet.getObject(2))){
                JOptionPane.showMessageDialog(this,"Hello Mr." + User.getText()+" The success of theis hotel depends on you.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                
                a=1;
                 
               
                this.dispose();
                employeeform empF = new employeeform();
                empF.setVisible(true);
                }
                }
                if(a!=1){
                    JOptionPane.showMessageDialog(this,"Username or Password is wrong" , "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                }
                catch(SQLException sqlException){
                System.out.println(sqlException.getMessage());
                }
                
                
            }
        }
        else if(e.getSource()==Login){
            String uname =User.getText();
            String pass =Pass.getText();
            
            String sql="SELECT account_name,account_pass FROM hotel_reservation_db103.users;";
            //Check the database then go into this conditions
            if(User.getText().isEmpty() || Pass.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Username and Password are Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
                
            }
            else {
                try{
                Statement s = connection.createStatement();
                ResultSet resultSet = s.executeQuery(sql);
                while(resultSet.next()){
                
                if(uname.equals(resultSet.getObject(1))&&pass.equals(resultSet.getObject(2))){
                JOptionPane.showMessageDialog(this,"Hello Mr." + User.getText()+" welcome to our Hotel, you can reserve a room  with our application.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                String name =User.getText();
                a=1;
                
        try{
            String db_url="jdbc:mysql://localhost:3306/hotel_reservation_db103";
            String db_username="root";
            String db_password="9517538624";
            Connection con = DriverManager.getConnection(db_url, db_username, db_password);
            Statement st = con.createStatement();
             sql = "select * from hotel_reservation_db103.users where account_name like'"+name+"';";
            ResultSet rs =st.executeQuery(sql);
            while(rs.next()){
               // fnameget  = String.valueOf(rs.getInt("firstname"));
                fnameget = rs.getString("firstname");
                lnameget = rs.getString("surname");
                fulnameget=fnameget+" "+lnameget;
                phone88 = rs.getString("mobile");
                
            }
            con.close();
        }
        catch(Exception a){
            System.out.println(a.getMessage());
        }
               
                this.dispose();
                userform f=new  userform();
                f.customername.setText(fulnameget);
                f.Cphone.setText(phone88);
                f.phone1232.setText(phone88);
                f.setVisible(true);
                }
                }
                if(a!=1){
                    JOptionPane.showMessageDialog(this,"Username or Password is wrong" , "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                }
                catch(SQLException sqlException){
                System.out.println(sqlException.getMessage());
                }
                
                
            }
        }
        else if(e.getSource()==Register){
            RegisterFrame R1 = new RegisterFrame();
            this.dispose();
        }
    }
}
