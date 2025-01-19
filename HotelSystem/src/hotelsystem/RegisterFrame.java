package hotelsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
public class RegisterFrame extends JFrame implements ActionListener{
    JLabel UserName,Password, FirstName,LastName,Address,EmailAddress,Gender,phonenum,passportnum1;
    JTextField UName,FName,LName,Add,EAddress,phoneNumber,passportnum;
    JPasswordField Pass;
    JRadioButton Male,Female;
    ButtonGroup g1;
    JButton Register,Exit;
    Connection connection=null;
    public RegisterFrame(){
        super("Register");
        this.setVisible(true);
        this.setLocation(750, 270);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(270, 350);
        this.setResizable(false);
        UserName = new JLabel("Username :           ");
        Password = new JLabel("\nPassword : ");
        FirstName = new JLabel("First Name : ");
        LastName = new JLabel("Last Name : ");
        passportnum1 = new JLabel("passport Number : ");
        Address = new JLabel("Address :     ");
        EmailAddress = new JLabel("Email :           ");
        Gender = new JLabel("Gender : ");
        phonenum = new JLabel("phone Number : ");
        
        UName = new JTextField(12);
        FName = new JTextField(12);
        LName = new JTextField(12);
        Add = new JTextField(12);
        EAddress = new JTextField(12);
        phoneNumber = new JTextField(12);
        passportnum = new JTextField(12);
        Pass = new JPasswordField(12);
        
        Male = new JRadioButton("Male");
        Female = new JRadioButton("Female");
        g1 = new ButtonGroup();
        g1.add(Male);
        g1.add(Female);
        
        Register = new JButton("Register");
        Exit = new JButton("Exit");
        
        this.setLayout(new FlowLayout());
        
        this.add(UserName);
        this.add(UName);
        
        this.add(Password);
        this.add(Pass);
        
        this.add(FirstName);
        this.add(FName);
        
        this.add(LastName);
        this.add(LName);
        
        this.add(phonenum);
        this.add(phoneNumber);
        
        this.add(passportnum1);
        this.add(passportnum);
        
        this.add(Address);
        this.add(Add);
        
        this.add(EmailAddress);
        this.add(EAddress);
        
        this.add(Gender);
        this.add(Male);
        this.add(Female);
        
        this.add(Register);
        this.add(Exit);
        
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
        if(e.getSource()==Register){
            //Sign it to the database
            if(Male.isSelected() == false && Female.isSelected() == false && Pass.getText().isEmpty() && EAddress.getText().isEmpty() && Add.getText().isEmpty() && phoneNumber.getText().isEmpty() && LName.getText().isEmpty() && FName.getText().isEmpty() && UName.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"All info are Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(UName.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Username is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(FName.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"First Name is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(LName.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Last Name is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(phoneNumber.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"phone Number is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(passportnum.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"passport Number is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(Add.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Address is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(EAddress.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Email Address is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else if(Pass.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"Password is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            else if(Male.isSelected() == false && Female.isSelected() == false){
                JOptionPane.showMessageDialog(this,"Gender is Required!!" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
            else{
                String account_name=UName.getText();
                String account_pass=Pass.getText();
                String firstname=FName.getText();
                String surname=LName.getText();
                String mobile=phoneNumber.getText();
                String address=Add.getText();
                String email_address=EAddress.getText();
                String passport_num=passportnum.getText();
                String gender;
                
                if(Male.isSelected()){
                gender="Male";
                }
                else{
                gender="Female";
                }
               String userscol=gender;
                
                String sql = String.format("INSERT INTO users(account_name, account_pass, address, email_address, firstname, surname, mobile, passport_num, userscol)\n"+
                "Values('%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                 account_name, account_pass, address, email_address, firstname, surname, mobile, passport_num, userscol);
                this.dispose();
                JOptionPane.showMessageDialog(this,"successfully registered" , "ERROR", JOptionPane.INFORMATION_MESSAGE);
                LoginFrame L1 = new LoginFrame();
                L1.setVisible(true);
                L1.setLocation(750, 350);
                L1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                L1.setSize(550, 150);
                L1.setResizable(false);
                try{
                 Statement s = connection.createStatement();
                s.executeUpdate(sql);
                }
                catch(SQLException sqlException){
                System.out.println(sqlException.getMessage());
                }
            }
            
        }
        else if(e.getSource()==Exit){
            this.dispose();
            LoginFrame L1 = new LoginFrame();
        }
    }
}
