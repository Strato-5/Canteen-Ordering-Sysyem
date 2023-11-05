package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;   
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class OrderMenu {

    private JFrame frame;
    static JTextField textField;
    private JTextField textField_1;
    //private JTextArea textArea;
    static String ID;
    

    /**
     * Launch the application.
     */
    public void main(String[] args) {
                

                /*

                
                // Set a timer to close the message box after 5 seconds
                Timer timer = new Timer(800, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                    }
                });
                timer.setRepeats(false);
                timer.start();

                dialog.setVisible(true);*/
        ConsoleTerminal.main(args);
        JDBCdemo.main(args);//ADD YOUR SQL COMMAND HERE
		OrderMenu.ID = ConsoleTerminal.text.toString().trim(); 

        EventQueue.invokeLater(new Runnable() {             
            public void run() {
                try {
                    OrderMenu window = new OrderMenu();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * 
     * @throws IOException
     */
    public OrderMenu() throws IOException {
        System.out.println(gui.ConsoleTerminal.text);
        createAndShowGUI();
    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws IOException
     */


    void createAndShowGUI() throws IOException {
        frame = new JFrame("Order Form");
        frame.setBounds(100, 100, 420, 200); // x,y,width,height
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JLabel lblFillInDetails = new JLabel("Fill in Details");
        lblFillInDetails.setBounds(120, 11, 131, 26);
        lblFillInDetails.setFont(new Font("Serif", Font.PLAIN, 16));
        lblFillInDetails.setForeground(Color.BLUE);
        frame.getContentPane().add(lblFillInDetails);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(10, 46, 46, 14);
        frame.getContentPane().add(lblName);

        textField = new JTextField();
        textField.setBounds(82, 43, 176, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        
        JLabel lblAddress = new JLabel("UID");
        lblAddress.setBounds(10, 92, 46, 14);
        frame.getContentPane().add(lblAddress);

        textField_1 = new JTextField(OrderMenu.ID);
        textField_1.setBounds(82, 89, 176, 20);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        

        JButton btnCancel = new JButton("Cancel ");
        btnCancel.setBounds(130, 130, 89, 23);
        frame.getContentPane().add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FoodMenu food = new FoodMenu();
                try {
                    food.createAndShowGUI();
                    food.setVisible(true);
                    setVisible(false);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                frame.dispose();
            }
        });

		//JDBCdemo.firstCol.get(1);
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(229, 130, 89, 23);
        frame.getContentPane().add(btnConfirm);
		

        btnConfirm.addActionListener(new ActionListener() {
			int i=0;
            public void actionPerformed(ActionEvent e) {
				
                if (textField.getText().equals("") || textField_1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Field cannot be empty");
                } 
				int f =0;
				while(i<JDBCdemo.firstCol.size()){
                    if (i < JDBCdemo.firstCol.size() && i < JDBCdemo.secondCol.size()) {

					if (JDBCdemo.firstCol.get(i).equals(OrderMenu.ID) && JDBCdemo.secondCol.get(i).equals(textField.getText().trim()))
					{
						JOptionPane.showMessageDialog(null, "Your foods will soon be ready to serve, thanks for ordering. ");
                        new MainMenu();
						f=1;
					}
					i++;
                }
			}
				if(f==0)
					JOptionPane.showMessageDialog(null, "INVALID ID");


                    MainMenu main = new MainMenu();
                    try {
                        MainMenu.main(null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    main.setVisible(true);
                    frame.dispose();
			}
            });
            
        Image image = ImageIO.read(this.getClass().getResource("/order.png"));
        Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(imageScaled);
        JLabel lblNewLabel = new JLabel(imageIcon);
        lblNewLabel.setBounds(268, 19, 126, 90);
        frame.getContentPane().add(lblNewLabel);
        //ConsoleTerminal.comPorts[port].closePort();

    }

    public void setVisible(boolean b) {
        

    }
}