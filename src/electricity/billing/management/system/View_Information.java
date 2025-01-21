package electricity.billing.management.system;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View_Information extends JFrame implements ActionListener{
    String view;
    JButton cancel, update;
    View_Information(String view){

        super("View Information");

        this.view = view;

        setBounds(340, 130, 700, 500);
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        setLayout(null);

        Border backLine = BorderFactory.createLineBorder(Color.GRAY);

        JLabel heading = new JLabel("View Customer Information");
        heading.setFont(new Font("Serif", Font.BOLD, 20));
        heading.setBounds(75, 5, 500, 40);
        add(heading);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Serif", Font.BOLD, 17));
        nameLabel.setBounds(50, 80, 100, 20);
        add(nameLabel);

        JLabel nameLabelText = new JLabel("");
        nameLabelText.setBorder(backLine);
        nameLabelText.setBounds(150, 80, 200, 20);
        add(nameLabelText);

        JLabel meterNoLabel = new JLabel("Meter No: ");
        meterNoLabel.setFont(new Font("Serif", Font.BOLD, 17));
        meterNoLabel.setBounds(50, 120, 100, 20);
        add(meterNoLabel);

        JLabel meterNoLabelText = new JLabel("");
        meterNoLabelText.setBorder(backLine);
        meterNoLabelText.setBounds(150, 120, 200, 20);
        add(meterNoLabelText);

        JLabel addressLabel = new JLabel("Address: ");
        addressLabel.setFont(new Font("Serif", Font.BOLD, 17));
        addressLabel.setBounds(50, 160, 100, 20);
        add(addressLabel);

        JLabel addressLabelText = new JLabel("");
        addressLabelText.setBorder(backLine);
        addressLabelText.setBounds(150, 160, 200, 20);
        add(addressLabelText);

        JLabel cityLabel = new JLabel("City: ");
        cityLabel.setFont(new Font("Serif", Font.BOLD, 17));
        cityLabel.setBounds(50, 200, 100, 20);
        add(cityLabel);

        JLabel cityLabelText = new JLabel("");
        cityLabelText.setBorder(backLine);
        cityLabelText.setBounds(150, 200, 200, 20);
        add(cityLabelText);

        JLabel stateLabel = new JLabel("State: ");
        stateLabel.setFont(new Font("Serif", Font.BOLD, 17));
        stateLabel.setBounds(50, 240, 100, 20);
        add(stateLabel);

        JLabel stateLabelText = new JLabel("");
        stateLabelText.setBorder(backLine);
        stateLabelText.setBounds(150, 240, 200, 20);
        add(stateLabelText);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Serif", Font.BOLD, 17));
        emailLabel.setBounds(50, 280, 100, 20);
        add(emailLabel);

        JLabel emailLabelText = new JLabel("");
        emailLabelText.setBorder(backLine);
        emailLabelText.setBounds(150, 280, 200, 20);
        add(emailLabelText);

        JLabel phoneLabel = new JLabel("Phone No: ");
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 17));
        phoneLabel.setBounds(50, 320, 100, 20);
        add(phoneLabel);

        JLabel phoneLabelText = new JLabel("");
        phoneLabelText.setBorder(backLine);
        phoneLabelText.setBounds(150, 320, 200, 20);
        add(phoneLabelText);

        try{
            Db db = new Db();
            String q = "select * from new_customer where meterNo = ?";
            PreparedStatement ps = db.connection.prepareStatement(q);
            ps.setString(1, view.toString());
            ResultSet resultSet = ps.executeQuery();

            if(resultSet.next()){
                nameLabelText.setText(resultSet.getString("name"));
                meterNoLabelText.setText(resultSet.getString("meterNo"));
                addressLabelText.setText(resultSet.getString("address"));
                cityLabelText.setText(resultSet.getString("city"));
                stateLabelText.setText(resultSet.getString("state"));
                emailLabelText.setText(resultSet.getString("email"));
                phoneLabelText.setText(resultSet.getString("phoneNo"));
            }
        }catch(Exception E){
            E.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Serif", Font.BOLD, 16));
        cancel.setBounds(80, 390, 100, 25);
        cancel.addActionListener(this);
        add(cancel);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setFont(new Font("Serif", Font.BOLD, 16));
        update.setBounds(200, 390, 100, 25);
        update.addActionListener(this);
        add(update);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/wall.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(310, 500, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon2);
        imageLabel.setBounds(355, 0, 350, 500);
        add(imageLabel);

        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == cancel){
            this.dispose();
        }else if(e.getSource() == update){
            new Update_Information(view);
            this.dispose();
        }
    }
    public static void main(String[] args) {
        new View_Information("");
    }
}
