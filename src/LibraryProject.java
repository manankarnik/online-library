import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryProject {
    private static JFrame frame;
    private JPanel basePanel;
    private JCheckBox loginShowPasswordCheckBox;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField loginUsernameOrEmailTextField;
    private JPasswordField loginPasswordField;
    private JPanel homePanel;
    private JPanel borrowedBooksPanel;
    private JScrollPane catalogPane;
    private JPanel baseCardPanel;
    private JPanel mainCardPanel;
    private JLabel welcomeLabel;
    private JButton signOutButton;
    private JButton homeButton;
    private JButton catalogButton;
    private JButton borrowedBooksButton;
    private JPanel recommendedBooksPanel;
    private JButton borrowButton1;
    private JButton borrowButton2;
    private JButton borrowButton3;
    private JButton borrowButton4;
    private JButton borrowButton5;
    private JButton borrowButton6;
    private JButton borrowButton7;
    private JButton borrowButton8;
    private JButton borrowButton9;
    private JButton borrowButton10;
    private JButton borrowButton11;
    private JButton borrowButton12;
    private JButton borrowButton13;
    private JButton borrowButton14;
    private JButton borrowButton15;
    private JButton borrowButton16;
    private JTable borrowedBooksTable;
    private JScrollPane tablePane;
    private JComboBox<?> durationComboBox1;
    private JComboBox<?> durationComboBox2;
    private JComboBox<?> durationComboBox3;
    private JComboBox<?> durationComboBox4;
    private JComboBox<?> durationComboBox5;
    private JComboBox<?> durationComboBox6;
    private JComboBox<?> durationComboBox7;
    private JComboBox<?> durationComboBox8;
    private JComboBox<?> durationComboBox9;
    private JComboBox<?> durationComboBox10;
    private JComboBox<?> durationComboBox11;
    private JComboBox<?> durationComboBox12;
    private JComboBox<?> durationComboBox13;
    private JComboBox<?> durationComboBox14;
    private JComboBox<?> durationComboBox15;
    private JComboBox<?> durationComboBox16;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField emailTextField;
    private JPasswordField registerPasswordField;
    private JTextField registerUsernameTextField;
    private JCheckBox registerShowPasswordCheckBox;
    private JButton registerHereButton;
    private JButton loginHereButton;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JLabel profileLabel;

    private final CardLayout pageLayout = (CardLayout) baseCardPanel.getLayout();
    private final CardLayout tabLayout = (CardLayout) mainCardPanel.getLayout();
    private int tableHoverRow = -1;
    private int tableHoverCol = -1;

    public LibraryProject() {
        Color defaultForegroundColor = Color.WHITE;
        Color activeForegroundColor = new Color(87, 246, 233);
        Color hoverBackgroundColor = new Color(58, 60, 68);
        Color defaultBackgroundColor = new Color(21, 23, 29);

        JRadioButton[] radioButtons = {maleRadioButton, femaleRadioButton};

        JButton[] buttons = {
                loginButton, registerHereButton, loginHereButton, registerButton, signOutButton,
                homeButton, catalogButton, borrowedBooksButton, borrowButton1,
                borrowButton2, borrowButton3, borrowButton4, borrowButton5,
                borrowButton6, borrowButton7, borrowButton8, borrowButton9,
                borrowButton10, borrowButton11, borrowButton12, borrowButton13,
                borrowButton14, borrowButton15, borrowButton16
        };
        
        JButton[] blackButtons = {registerHereButton, loginHereButton};

        JButton[] borrowButtons = {
                borrowButton1, borrowButton2, borrowButton3, borrowButton4,
                borrowButton5, borrowButton6, borrowButton7, borrowButton8,
                borrowButton9, borrowButton10, borrowButton11, borrowButton12,
                borrowButton13, borrowButton14, borrowButton15, borrowButton16
        };

        JComboBox<?>[] durationComboBoxes = new JComboBox[]{
                durationComboBox1, durationComboBox2, durationComboBox3, durationComboBox4,
                durationComboBox5, durationComboBox6, durationComboBox7, durationComboBox8,
                durationComboBox9, durationComboBox10, durationComboBox11, durationComboBox12,
                durationComboBox13, durationComboBox14, durationComboBox15, durationComboBox16,
        };

        maleRadioButton.setSelected(true);
        catalogPane.getVerticalScrollBar().setUnitIncrement(30);
        catalogPane.getViewport().setOpaque(false);
        recommendedBooksPanel.setBackground(new Color(0, 0, 0, 0.6f));

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Book Name");
        tableModel.addColumn("Borrow Date");
        tableModel.addColumn("Return Date");
        tableModel.addColumn("Return Book");

        JTableHeader borrowedBooksTableHeader = borrowedBooksTable.getTableHeader();
        DefaultTableCellRenderer tableHeaderCellRenderer = (DefaultTableCellRenderer) borrowedBooksTableHeader.getDefaultRenderer();
        tableHeaderCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        borrowedBooksTableHeader.setOpaque(false);
        borrowedBooksTableHeader.setBackground(defaultBackgroundColor);
        borrowedBooksTableHeader.setForeground(activeForegroundColor);
        borrowedBooksTableHeader.setFont(new Font(tableHeaderCellRenderer.getFont().getName(), tableHeaderCellRenderer.getFont().getStyle(), 20));
        borrowedBooksTableHeader.setBorder(BorderFactory.createLineBorder(hoverBackgroundColor));
        borrowedBooksTableHeader.setPreferredSize(new Dimension(borrowedBooksTableHeader.getWidth(),40));
        borrowedBooksTableHeader.setResizingAllowed(false);
        borrowedBooksTableHeader.setReorderingAllowed(false);

        borrowedBooksTable.setModel(tableModel);
        borrowedBooksTable.setFocusable(false);
        borrowedBooksTable.setBorder(BorderFactory.createLineBorder(hoverBackgroundColor));
        borrowedBooksTable.setFont(new Font(borrowedBooksTable.getFont().getName(), borrowedBooksTable.getFont().getStyle(), 16));
        borrowedBooksTable.setBackground(defaultBackgroundColor);
        borrowedBooksTable.setRowHeight(50);

        DefaultTableCellRenderer TableCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if(row == tableHoverRow && column == 3 && tableHoverCol == 3) {
                    c.setBackground(hoverBackgroundColor);
                    c.setForeground(activeForegroundColor);
                } else {
                    c.setBackground(table.getBackground());
                    c.setForeground(table.getForeground());
                }
                return c;
            }
        };
        TableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < tableModel.getColumnCount(); i++){
            borrowedBooksTable.getColumnModel().getColumn(i).setCellRenderer(TableCellRenderer);
        }
        ((DefaultTableCellRenderer) borrowedBooksTable.getDefaultRenderer(Object.class)).setOpaque(false);

        tablePane.getViewport().setOpaque(false);

        for (JRadioButton button : radioButtons) {
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setForeground(activeForegroundColor);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setForeground(defaultForegroundColor);
                }
            });
            if (button.isSelected()) {
                button.setForeground(activeForegroundColor);
            }
        }

        for (JButton button : buttons) {
            button.setBackground(defaultBackgroundColor);
            button.setForeground(defaultForegroundColor);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (button.isEnabled()) {
                        if (!Arrays.asList(blackButtons).contains(button)) {
                            button.setBackground(hoverBackgroundColor);
                        }
                        button.setForeground(activeForegroundColor);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!Arrays.asList(blackButtons).contains(button)) {
                        button.setBackground(defaultBackgroundColor);
                    }
                    button.setForeground(defaultForegroundColor);
                    if (homePanel.isVisible()) {
                        homeButton.setForeground(activeForegroundColor);
                    } else if (catalogPane.isVisible()) {
                        catalogButton.setForeground(activeForegroundColor);
                    } else if (borrowedBooksPanel.isVisible()){
                        borrowedBooksButton.setForeground(activeForegroundColor);
                    }
                }
            });
        }
        for (JButton blackButton :
                blackButtons) {
            blackButton.setBackground(Color.BLACK);
        }
        for (JButton borrowButton : borrowButtons) {
            borrowButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Component label = borrowButton.getParent().getComponent(1);
                    Component comboBox = borrowButton.getParent().getComponent(3);
                    if (label instanceof JLabel && comboBox instanceof JComboBox<?>) {
                        String bookName = ((JLabel) label).getText();
                        borrowButton.setEnabled(false);
                        comboBox.setEnabled(false);
                        borrowButton.setText("Borrowed");
                        borrowButton.setBackground(defaultBackgroundColor);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String borrowDate = dateFormat.format(new Date());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());

                        String duration = (String) ((JComboBox<?>) comboBox).getSelectedItem();
                        switch (Objects.requireNonNull(duration)) {
                            case "1 Day" -> cal.add(Calendar.DATE, 1);
                            case "1 Week" -> cal.add(Calendar.DATE, 7);
                            case "2 Weeks" -> cal.add(Calendar.DATE, 14);
                            case "1 Month" -> cal.add(Calendar.MONTH, 1);
                            case "3 Months" -> cal.add(Calendar.MONTH, 3);
                            case "6 Months" -> cal.add(Calendar.MONTH, 6);
                            case "1 Year" -> cal.add(Calendar.YEAR, 1);
                        }

                        String returnDate = dateFormat.format(cal.getTime());
                        tableModel.addRow(new Object[] {bookName, borrowDate, returnDate, "Return"});
                    }
                }
            });
        }
        registerHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                registerUsernameTextField.setText("");
                emailTextField.setText("");
                registerPasswordField.setText("");
                firstNameTextField.grabFocus();
                pageLayout.show(baseCardPanel, "registerCard");
            }
        });
        loginHereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUsernameOrEmailTextField.setText("");
                loginPasswordField.setText("");
                loginUsernameOrEmailTextField.grabFocus();
                pageLayout.show(baseCardPanel, "loginCard");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameOrEmail = loginUsernameOrEmailTextField.getText();
                String password = String.valueOf(loginPasswordField.getPassword());
                Properties p = new Properties();

                try {
                    p.load(new FileReader("userdata.properties"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
                if (usernameOrEmail.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty", "Empty Username", JOptionPane.ERROR_MESSAGE);
                    loginUsernameOrEmailTextField.grabFocus();
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Password cannot be empty", "Empty Password", JOptionPane.ERROR_MESSAGE);
                    loginPasswordField.grabFocus();
                }
                else if (!password.equals(p.getProperty(usernameOrEmail))) {
                    JOptionPane.showMessageDialog(frame, "Incorrect Username or Password", "Invalid Login", JOptionPane.ERROR_MESSAGE);
                    loginUsernameOrEmailTextField.setText("");
                    loginPasswordField.setText("");
                    loginUsernameOrEmailTextField.grabFocus();
                } else {
                    welcomeLabel.setText(welcomeLabel.getText() + p.getProperty(usernameOrEmail + "->FirstName") + " " + p.getProperty(usernameOrEmail + "->LastName") + "!");
                    if (p.getProperty(usernameOrEmail + "->Gender").equals("Male")) {
                        try {
                            profileLabel.setIcon(new ImageIcon(ImageIO.read(LibraryProject.class.getResourceAsStream("images/maleProfile.png"))));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            throw new RuntimeException(ex);
                        }
                    } else {
                        try {
                            profileLabel.setIcon(new ImageIcon(ImageIO.read(LibraryProject.class.getResourceAsStream("images/femaleProfile.png"))));

                        } catch (IOException ex) {
                            ex.printStackTrace();
                            throw new RuntimeException(ex);
                        }                    }
                    pageLayout.show(baseCardPanel, "mainCard");
                    loginUsernameOrEmailTextField.setText("");
                    loginPasswordField.setText("");
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String username = registerUsernameTextField.getText();
                String gender  = maleRadioButton.isSelected() ? maleRadioButton.getText() : femaleRadioButton.getText();
                String password = String.valueOf(registerPasswordField.getPassword());
                String email = emailTextField.getText();

                Properties p = new Properties();
                try {
                    p.load(new FileReader("userdata.properties"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }

                if (firstName.equals("")) {
                    JOptionPane.showMessageDialog(frame, "First Name cannot be empty", "First Name Empty", JOptionPane.ERROR_MESSAGE);
                    firstNameTextField.grabFocus();
                } else if (lastName.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Last Name cannot be empty", "Last Name Empty", JOptionPane.ERROR_MESSAGE);
                    lastNameTextField.grabFocus();
                } else if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid Email Address", "Invalid Email", JOptionPane.ERROR_MESSAGE);
                    emailTextField.setText("");
                    emailTextField.grabFocus();
                } else if (p.containsKey(username)) {
                    JOptionPane.showMessageDialog(frame, "Choose a different Username", "Username Already Registered", JOptionPane.ERROR_MESSAGE);
                    registerUsernameTextField.setText("");
                    registerUsernameTextField.grabFocus();
                } else if (username.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Username cannot be empty", "Empty Username", JOptionPane.ERROR_MESSAGE);
                    registerUsernameTextField.grabFocus();
                } else if (password.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Password cannot be empty", "Empty Password", JOptionPane.ERROR_MESSAGE);
                    registerPasswordField.grabFocus();
                } else if (password.length() < 8) {
                    JOptionPane.showMessageDialog(frame, "Password should be at least 8 characters", "Weak Password", JOptionPane.ERROR_MESSAGE);
                    registerPasswordField.setText("");
                    registerPasswordField.grabFocus();
                } else {
                    p.setProperty(username, password);
                    p.setProperty(username + "->FirstName", firstName);
                    p.setProperty(username + "->LastName", lastName);
                    p.setProperty(username + "->Gender", gender);
                    p.setProperty(email, password);
                    p.setProperty(email + "->FirstName", firstName);
                    p.setProperty(email + "->LastName", lastName);
                    p.setProperty(email + "->Gender", gender);

                    try {
                        p.store(new FileWriter("userdata.properties"), "");
                        JOptionPane.showMessageDialog(frame, "Please login to continue", "Registration Successful", JOptionPane.PLAIN_MESSAGE);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        throw new RuntimeException(ex);
                    }

                    firstNameTextField.setText("");
                    lastNameTextField.setText("");
                    registerUsernameTextField.setText("");
                    emailTextField.setText("");
                    registerPasswordField.setText("");
                    loginUsernameOrEmailTextField.setText("");
                    loginPasswordField.setText("");
                    pageLayout.show(baseCardPanel, "loginCard");
                }
            }
        });

        loginShowPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginShowPasswordCheckBox.isSelected()) {
                    loginPasswordField.setEchoChar((char)0);
                    loginPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 13));

                } else {
                    loginPasswordField.setEchoChar('•');
                    loginPasswordField.setFont(new Font("Tahoma", Font.BOLD, 13));
                }
            }
        });
        registerShowPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (registerShowPasswordCheckBox.isSelected()) {
                    registerPasswordField.setEchoChar((char)0);
                    registerPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 13));

                } else {
                    registerPasswordField.setEchoChar('•');
                    registerPasswordField.setFont(new Font("Tahoma", Font.BOLD, 13));
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabLayout.show(mainCardPanel, "homeCard");
                homeButton.setForeground(activeForegroundColor);
                catalogButton.setForeground(defaultForegroundColor);
                borrowedBooksButton.setForeground(defaultForegroundColor);
            }
        });
        catalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabLayout.show(mainCardPanel, "catalogCard");
                homeButton.setForeground(defaultForegroundColor);
                catalogButton.setForeground(activeForegroundColor);
                borrowedBooksButton.setForeground(defaultForegroundColor);
            }
        });
        borrowedBooksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabLayout.show(mainCardPanel, "borrowedBooksCard");
                homeButton.setForeground(defaultForegroundColor);
                catalogButton.setForeground(defaultForegroundColor);
                borrowedBooksButton.setForeground(activeForegroundColor);
            }
        });
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcomeLabel.setText("Welcome, ");
                tableModel.setRowCount(0);
                for (JComboBox<?> durationComboBox :
                        durationComboBoxes) {
                    durationComboBox.setSelectedIndex(0);
                    if (!durationComboBox.isEnabled()){
                        durationComboBox.setEnabled(true);
                    }
                }
                for (JButton borrowButton : borrowButtons) {
                    if (!borrowButton.isEnabled()) {
                        borrowButton.setText("Borrow");
                        borrowButton.setEnabled(true);
                    }
                }
                pageLayout.show(baseCardPanel, "loginCard");
            }
        });
        borrowedBooksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = borrowedBooksTable.rowAtPoint(e.getPoint());
                int col = borrowedBooksTable.columnAtPoint(e.getPoint());
                if (row >= 0 && col == 3) {
                    String bookName = tableModel.getValueAt(row, 0).toString();
                    for (JButton borrowButton : borrowButtons) {
                        if (!borrowButton.isEnabled()) {
                            Component label = borrowButton.getParent().getComponent(1);
                            Component comboBox = borrowButton.getParent().getComponent(3);
                            if (label instanceof JLabel && comboBox instanceof JComboBox<?> && ((JLabel) label).getText().equals(bookName)) {
                                borrowButton.setEnabled(true);
                                comboBox.setEnabled(true);
                                ((JComboBox<?>) comboBox).setSelectedIndex(0);
                                borrowButton.setText("Borrow");
                            }
                        }
                    }
                    tableModel.removeRow(row);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                tableHoverRow = -1;
                tableHoverCol = -1;
                borrowedBooksTable.repaint();
            }
        });
        borrowedBooksTable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                tableHoverRow = borrowedBooksTable.rowAtPoint(e.getPoint());
                tableHoverCol = borrowedBooksTable.columnAtPoint(e.getPoint());
                borrowedBooksTable.repaint();
            }
        });
    }
    public static boolean isValidEmail(String email) {
        Matcher match = Pattern.compile("^(.+)@(.+)$").matcher(email);
        return match.matches();
    }

    public static void main(String[] args) {
        frame = new JFrame("Online Library Project");

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        }
        catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

        try {
            final Image backgroundImage = ImageIO.read(LibraryProject.class.getResourceAsStream("images/background.jpg"));
            frame.setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        frame.add(new LibraryProject().basePanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}