package electricity.billing.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment_Bill extends JFrame implements ActionListener {
    JButton back;
    String meter;
    Payment_Bill(String meter){

        super("Payment Bill");

        this.meter = meter;

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try{
            editorPane.setPage("https://paytm.com/online-payments");
            editorPane.setBounds(400, 150, 800, 600);
        }catch(Exception e){
            e.printStackTrace();
            editorPane.setContentType("text/html");
            editorPane.setText("<html>Error! Error! Error! Error! Error!</html>");
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);
        add(scrollPane);

        back = new JButton("Back");
        back.setFont(new Font("Serif", Font.BOLD, 17));
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        editorPane.add(back);

        setSize(800, 600);
        setLocation(300, 80);
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            setVisible(false);
            new Pay_Bill("");
        }
    }
    public static void main(String[] args) {
        new Payment_Bill("");
    }
}
