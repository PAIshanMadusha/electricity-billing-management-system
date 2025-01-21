package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main_Class extends JFrame implements ActionListener {

    String accType;
    String meter_pass;
    Main_Class(String accType, String meter_pass){

        super("Electricity Billing Management System");

        this.accType = accType;
        this.meter_pass = meter_pass;

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/electricity.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(1400, 900, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        JLabel mainName = new JLabel("ELECTRICITY BILLING MANAGEMENT SYSTEM");
        mainName.setForeground(new Color(198, 245, 215, 255));
        mainName.setFont(new Font("System", Font.TYPE1_FONT, 32));
        mainName.setBounds(40, 290, 800, 100);
        imageLabel.add(mainName);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif", Font.BOLD, 15));

        JMenuItem newCustomer = new JMenuItem("New Customer");
        newCustomer.setBackground(Color.WHITE);
        newCustomer.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon customerImg = new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
        Image imageCustomer = customerImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(imageCustomer));
        newCustomer.addActionListener(this);
        menu.add(newCustomer);
        menu.addSeparator();

        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.setBackground(Color.WHITE);
        customerDetails.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon cusDetailsImg = new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
        Image imageCusDetails = cusDetailsImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(imageCusDetails));
        customerDetails.addActionListener(this);
        menu.add(customerDetails);
        menu.addSeparator();

        JMenuItem depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setBackground(Color.WHITE);
        depositDetails.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon depDetailsImg = new ImageIcon(ClassLoader.getSystemResource("icon/depositdetails.png"));
        Image imageDepDetails = depDetailsImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(imageDepDetails));
        depositDetails.addActionListener(this);
        menu.add(depositDetails);
        menu.addSeparator();

        JMenuItem calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setBackground(Color.WHITE);
        calculateBill.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon calBillImg = new ImageIcon(ClassLoader.getSystemResource("icon/calculatorbills.png"));
        Image imageCallBill = calBillImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(imageCallBill));
        calculateBill.addActionListener(this);
        menu.add(calculateBill);

        JMenu info = new JMenu("Information");
        info.setFont(new Font("serif", Font.BOLD, 15));

        JMenuItem upInfo = new JMenuItem("Update Information");
        upInfo.setBackground(Color.WHITE);
        upInfo.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon upInfoImg = new ImageIcon(ClassLoader.getSystemResource("icon/refresh.png"));
        Image imageUpInfo = upInfoImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        upInfo.setIcon(new ImageIcon(imageUpInfo));
        upInfo.addActionListener(this);
        info.add(upInfo);
        info.addSeparator();

        JMenuItem viewInfo = new JMenuItem("View Information");
        viewInfo.setBackground(Color.WHITE);
        viewInfo.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon viewInfoImg = new ImageIcon(ClassLoader.getSystemResource("icon/information.png"));
        Image imageViewInfo = viewInfoImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        viewInfo.setIcon(new ImageIcon(imageViewInfo));
        viewInfo.addActionListener(this);
        info.add(viewInfo);

        JMenu user = new JMenu("User");
        user.setFont(new Font("serif", Font.BOLD, 15));

        JMenuItem payBill = new JMenuItem("Pay Bill");
        payBill.setBackground(Color.WHITE);
        payBill.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon payBillImg = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image imagePayBill = payBillImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(imagePayBill));
        payBill.addActionListener(this);
        user.add(payBill);
        user.addSeparator();

        JMenuItem billDetails = new JMenuItem("Bill Details");
        billDetails.setBackground(Color.WHITE);
        billDetails.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon billDetailsImg = new ImageIcon(ClassLoader.getSystemResource("icon/detail.png"));
        Image imageBillDetails = billDetailsImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(imageBillDetails));
        billDetails.addActionListener(this);
        user.add(billDetails);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("serif", Font.BOLD, 15));


        JMenuItem genBill = new JMenuItem("Generate Bill");
        genBill.setBackground(Color.WHITE);
        genBill.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon genBillImg = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image imageGenBill = genBillImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        genBill.setIcon(new ImageIcon(imageGenBill));
        genBill.addActionListener(this);
        bill.add(genBill);

        JMenu utility = new JMenu("Utility");
        utility.setFont(new Font("serif", Font.BOLD, 15));


        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(Color.WHITE);
        notepad.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon notepadImg = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png"));
        Image imageNotepad = notepadImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(imageNotepad));
        notepad.addActionListener(this);
        utility.add(notepad);
        utility.addSeparator();

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setBackground(Color.WHITE);
        calculator.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon calculatorImg = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png"));
        Image imageCalculator = calculatorImg.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(imageCalculator));
        calculator.addActionListener(this);
        utility.add(calculator);

        JMenu exit = new JMenu("Exit");
        exit.setFont(new Font("serif", Font.BOLD, 15));

        JMenuItem exit2 = new JMenuItem("Exit");
        exit2.setBackground(Color.WHITE);
        exit2.setFont(new Font("serif", Font.BOLD, 15));
        ImageIcon exit2Img = new ImageIcon(ClassLoader.getSystemResource("icon/exit.png"));
        Image imageExit2 = exit2Img.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        exit2.setIcon(new ImageIcon(imageExit2));
        exit2.addActionListener(this);
        exit.add(exit2);

        if(accType.equals("Admin")){
            menuBar.add(menu);
        }else{
            menuBar.add(info);
            menuBar.add(user);
            menuBar.add(bill);
        }
        menuBar.add(utility);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if(msg.equals("New Customer")){
            new NewCustomer();

        }else if(msg.equals("Customer Details")){
            new CustomerDetails();

        }else if(msg.equals("Deposit Details")){
            new Deposit_Details();

        }else if(msg.equals("Calculate Bill")){
            new CalculateBill();

        }else if(msg.equals("View Information")){
            new View_Information(meter_pass);

        }else if(msg.equals("Update Information")){
            new Update_Information(meter_pass);

        }else if(msg.equals("Bill Details")) {
            new Bill_Details(meter_pass);

        }else if(msg.equals("Pay Bill")) {
            new Pay_Bill(meter_pass);

        }else if (msg.equals("Generate Bill")) {
            new Generate_Bill(meter_pass);

        }else if(msg.equals("Calculator")){
            try{
                Runtime.getRuntime().exec("calc.exe");
            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(msg.equals("Notepad")){
            try{
                Runtime.getRuntime().exec("notepad.exe");
            }catch(Exception E){
                E.printStackTrace();
            }
        }else if(msg.equals("Exit")){
            new Login();
            this.dispose();
        }
    }
    public static void main(String args[]){
        new Main_Class("", "");
    }
}
