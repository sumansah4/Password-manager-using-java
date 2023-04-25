import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.HashMap;


// Using inbuilt hashMap to store account and password.
class HashtablePassword {
    private final HashMap<Object, Object> map; 
    private final float loadFactor; 

    public HashtablePassword(int capacity, float loadFactor) {
        map = new HashMap<>(capacity, loadFactor);
        this.loadFactor = loadFactor;
    }
  
    public int add_Acc(Object Account, Object password) {
        map.put(Account, password);
        return Account.hashCode();
    }
 
    public Object get_Acc(Object Account) {
        return map.get(Account);
    }
    
    public Object remove_Acc(Object Account) {
        return map.remove(Account);
    }
}

// Generating Random password and using stringBuilder to concatenate the password string.
class PasswordGenerator {
    private static final Random random = new Random();
    private static final String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String small_caps = "abcdefghijklmnopqrstuvwxyz";
    private static final String Numeric = "1234567890";
    private static final String special_char = "@_";
    private static final String dic = caps + small_caps + Numeric + special_char;

    public String generatePassword(int len) {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length());
            password.append(dic.charAt(index));
        }
        return password.toString();
    }
}

class PasswordManager implements ActionListener {

    HashtablePassword data = new HashtablePassword(10, 0.8F);

    // GUI variables declaration
    JFrame frame;
    JFrame frame2;
    JLabel background; 
    Container conn1, conn2; 
    JLabel lAcc, lPass;
    JTextArea genePassArea, searchPassArea; 
    JButton PassGeneBtn, PassStoreBtn, PassSearchBtn, AccAddBtn, PassDeleteBtn; 
    JTextField tAcc, tPass; 

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // Frame settings
    public static void FrameGUI(JFrame frame) {
        frame.setVisible(true); 
        frame.setLayout(null); 
        frame.setLocationRelativeTo(null);
    }

    // container settings
    public static void ContainerGUI(Container conn) {
        conn.setVisible(true);
        conn.setBackground(Color.getHSBColor(20.4f, 10.5f, 12.9f));
    }

    // GUI Buttons settings
    public void GUIButtonsSetting(JButton btn) {
        btn.setBackground(new Color(0xDAB5A5));
        btn.setForeground(Color.DARK_GRAY); 
        btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        btn.setFocusable(false);
        Font fn = new Font("Roboto", Font.BOLD, 16);
        btn.setFont(fn);
    }

    /****** Inner GUI of STORE PASSWORD, this will execute when we click on that button ******/
    public void StoringGUI() {
        frame2 = new JFrame("Store Password Here");
        frame2.setBounds(1400, 300, 800, 500);
        frame2.setSize(400, 400);
        FrameGUI(frame2); 
        conn2 = frame2.getContentPane();

        ContainerGUI(conn2);
        Font fn = new Font("Roboto", Font.BOLD, 20);

        // 'Account' Label
        lAcc = new JLabel("ACCOUNT NAME");
        lAcc.setBounds(90, 23, 380, 20);
        lAcc.setFont(fn);
        conn2.add(lAcc);

        // 'Account' textField
        tAcc = new JTextField();
        tAcc.setBounds(90, 70, 200, 50);
        tAcc.setFont(fn);
        tAcc.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        tAcc.setForeground(Color.DARK_GRAY);
        conn2.add(tAcc);

        // 'Password' Label
        lPass = new JLabel("PASSWORD");
        lPass.setBounds(90, 160, 380, 20);
        lPass.setFont(fn);
        conn2.add(lPass);

        // 'Password' textfield
        tPass = new JTextField();
        tPass.setBounds(90, 200, 200, 50);
        tPass.setFont(fn);
        tPass.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        tPass.setForeground(Color.DARK_GRAY);
        conn2.add(tPass);

        // Store button
        AccAddBtn = new JButton("STORE");
        AccAddBtn.setBounds(120, 290, 150, 50);
        conn2.add(AccAddBtn);
        GUIButtonsSetting(AccAddBtn);
    }

    // This text area will be used by password generator and search password result. 
    public void textArea(String Pass, JTextArea TA) {
        TA.setText(Pass);
        Font fn = new Font("Roboto", Font.BOLD, 20);
        TA.setFont(fn);
        TA.setCaretPosition(0); 
    }

    // GUI of Password Manager
    PasswordManager() {
        frame = new JFrame("Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 650);
        frame.setResizable(false);
        ImageIcon img = new ImageIcon("pexels-photo-5474298.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 400, 750);
        background.setVisible(true);
        frame.add(background);

        // Set up the main frame
        FrameGUI(frame);
        conn1 = frame.getContentPane();
        ContainerGUI(conn1); 


        /***** GENERATE PASSWORD *****/

        PassGeneBtn = new JButton("GENERATE PASSWORD");
        PassGeneBtn.setBounds(130, 40, 250, 60);
        conn1.add(PassGeneBtn);
        GUIButtonsSetting(PassGeneBtn);

        // Generate Password action
        PassGeneBtn.addActionListener(e -> {
            if (PassGeneBtn == e.getSource()) // check if the button and listener source is same. 
            {
                try {
                    int len = Integer.parseInt(JOptionPane.showInputDialog("Enter the password length"));
                    if (len >= 8) {
                        PasswordGenerator pass = new PasswordGenerator();
                        String passwd = pass.generatePassword(len);
                        genePassArea = new JTextArea(5, 4);
                        textArea(passwd, genePassArea);
                        JOptionPane.showMessageDialog(conn1, genePassArea);
                    }
                    else if( len <= 0){
                        JOptionPane.showMessageDialog(conn1, "Can't take input value as 0 or -ve ");
                    }
                    else
                        JOptionPane.showMessageDialog(conn1, "Small passwords are not so secure!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(conn1, "Give some value");
                }
            }
        });


        /***** STORE PASSWORD *****/

        PassStoreBtn = new JButton("STORE PASSWORD");
        PassStoreBtn.setBounds(130, 190, 250, 60);
        conn1.add(PassStoreBtn);
        GUIButtonsSetting(PassStoreBtn);

        // Store Password action
        PassStoreBtn.addActionListener(e -> {
            if (PassStoreBtn == e.getSource()) {
                StoringGUI(); // Inner GUI of STORE PASSWORD
                // action when user clicks on the STORE btn
                AccAddBtn.addActionListener(e4 -> {
                    try {
                        if (AccAddBtn == e4.getSource()) {
                            String account_name = tAcc.getText(); // get the account name form textField
                            String acc_pass = tPass.getText(); // get the password from textField
                            if (!account_name.isEmpty() && !acc_pass.isEmpty()) {
                                data.add_Acc(account_name, acc_pass);
                                JOptionPane.showMessageDialog(conn2, "Account is added !");
                                tAcc.setText(null); // set the textField to NULL so we don't have to explicitly backspace it.
                                tPass.setText(null);
                            } else {
                                throw new Exception("Input Missing");
                            }
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(conn2, ex.getMessage()); // catching the exception message, the one we have explicitly thrown.
                    }
                });
            }
        });
        

        /***** SEARCH PASSWORD *****/

        PassSearchBtn = new JButton("SEARCH PASSWORD");
        GUIButtonsSetting(PassSearchBtn);
        PassSearchBtn.setBounds(130, 340, 250, 60);
        conn1.add(PassSearchBtn);

        // Searching Password action
        PassSearchBtn.addActionListener(e -> {
            if (PassSearchBtn == e.getSource()) {
                try {
                    String acc_name = JOptionPane.showInputDialog("Enter your Account Name"); 
                    if (!acc_name.isBlank()) { 
                        Object pass = data.get_Acc(acc_name.toLowerCase()); 
                        if (pass != null) { // password could be a NULL value so check
                            searchPassArea = new JTextArea(4, 5); 
                            textArea(String.valueOf(pass), searchPassArea); 
                            JOptionPane.showMessageDialog(conn1,searchPassArea, "Password Found",  JOptionPane.INFORMATION_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(conn1, "Account not Found!");
                    }
                    else{
                        throw new Exception("Write Something");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(conn1, ex.getMessage());
                }
            }
        });


        /***** DELETE PASSWORD *****/

        PassDeleteBtn = new JButton("DELETE PASSWORD");
        GUIButtonsSetting(PassDeleteBtn);
        PassDeleteBtn.setBounds(130, 490, 250, 60);
        conn1.add(PassDeleteBtn);

        // Deleting Password action
        PassDeleteBtn.addActionListener(e -> {
            if (PassDeleteBtn == e.getSource()) { 
                try {
                    String acc_name = JOptionPane.showInputDialog("Enter the Account Name"); 
                    if (!acc_name.isBlank()) {
                        Object find = data.get_Acc(acc_name.toLowerCase()); 
                        if(find != null){ // account could be a NULL value so check.
                            data.remove_Acc(acc_name.toLowerCase()); 
                            JOptionPane.showMessageDialog(conn1, "Delete successfully!"); 
                        }
                        else{
                            JOptionPane.showMessageDialog(conn1, "Account Not found!");
                        }
                    } else
                        throw new Exception("Give some input");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(conn1, ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            new PasswordManager();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred");
        }
    }
}
