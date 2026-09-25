import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {

    public static void main(String[] args) {

        Database.createTables();

        showLogin();
    }

    // ================= LOGIN =================

    static void showLogin() {

        JFrame frame = new JFrame("Online Examination System");

        JLabel title = new JLabel("ONLINE EXAMINATION SYSTEM");
        title.setBounds(80, 30, 300, 30);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(70, 80, 100, 30);

        JTextField userField = new JTextField();
        userField.setBounds(160, 80, 180, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(70, 130, 100, 30);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(160, 130, 180, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 190, 120, 40);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(140, 240, 120, 40);

        frame.add(title);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);
        frame.add(registerButton);

        frame.setSize(400, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // LOGIN
        loginButton.addActionListener(e -> {

            String username = userField.getText();
            String password =
                    new String(passField.getPassword());

            if (username.equals("admin")
                    && password.equals("1234")) {

                frame.dispose();

                showDashboard(username);

            } else {

                checkStudentLogin(
                        frame,
                        username,
                        password
                );
            }
        });

        // REGISTER
        registerButton.addActionListener(e -> {

            showRegistration();
        });
    }

    // ================= STUDENT LOGIN =================

    static void checkStudentLogin(
            JFrame frame,
            String username,
            String password) {

        String sql =
                "SELECT * FROM students " +
                "WHERE username=? AND password=?";

        try {

            Connection con =
                    Database.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            var rs = ps.executeQuery();

            if (rs.next()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Login Successful!"
                );

                frame.dispose();

                showDashboard(username);

            } else {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Username or Password"
                );
            }

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Database Error: "
                    + e.getMessage()
            );
        }
    }

    // ================= REGISTRATION =================

    static void showRegistration() {

        JFrame frame =
                new JFrame("Student Registration");

        JLabel title =
                new JLabel("STUDENT REGISTRATION");

        title.setBounds(
                100, 30, 250, 30
        );

        JLabel nameLabel =
                new JLabel("Name:");

        nameLabel.setBounds(
                60, 80, 100, 30
        );

        JTextField nameField =
                new JTextField();

        nameField.setBounds(
                150, 80, 180, 30
        );

        JLabel userLabel =
                new JLabel("Username:");

        userLabel.setBounds(
                60, 130, 100, 30
        );

        JTextField userField =
                new JTextField();

        userField.setBounds(
                150, 130, 180, 30
        );

        JLabel passLabel =
                new JLabel("Password:");

        passLabel.setBounds(
                60, 180, 100, 30
        );

        JPasswordField passField =
                new JPasswordField();

        passField.setBounds(
                150, 180, 180, 30
        );

        JButton registerButton =
                new JButton("Register");

        registerButton.setBounds(
                130, 240, 140, 40
        );

        frame.add(title);
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(registerButton);

        frame.setSize(400, 350);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        registerButton.addActionListener(e -> {

            String name =
                    nameField.getText();

            String username =
                    userField.getText();

            String password =
                    new String(
                            passField.getPassword()
                    );

            if (name.isEmpty()
                    || username.isEmpty()
                    || password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Please fill all fields"
                );

                return;
            }

            String sql =
                    "INSERT INTO students " +
                    "(name, username, password) " +
                    "VALUES (?, ?, ?)";

            try {

                Connection con =
                        Database.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1, name);
                ps.setString(2, username);
                ps.setString(3, password);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        frame,
                        "Registration Successful!"
                );

                frame.dispose();

                con.close();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Registration Failed!\n"
                        + ex.getMessage()
                );
            }
        });
    }

    // ================= DASHBOARD =================

    static void showDashboard(String username) {

        JFrame dashboard =
                new JFrame("Student Dashboard");

        JLabel title =
                new JLabel(
                        "WELCOME, " + username
                );

        title.setBounds(
                120, 30, 250, 30
        );

        JButton startExam =
                new JButton("Start Exam");

        startExam.setBounds(
                120, 90, 150, 40
        );

        JButton resultButton =
                new JButton("View Result");

        resultButton.setBounds(
                120, 150, 150, 40
        );

        JButton logoutButton =
                new JButton("Logout");

        logoutButton.setBounds(
                120, 210, 150, 40
        );

        dashboard.add(title);
        dashboard.add(startExam);
        dashboard.add(resultButton);
        dashboard.add(logoutButton);

        dashboard.setSize(400, 320);
        dashboard.setLayout(null);

        dashboard.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);

        startExam.addActionListener(e -> {

            startExam.addActionListener(evt -> {

    new Exam();

});
        });

        resultButton.addActionListener(e -> {

        JOptionPane.showMessageDialog(
    dashboard,
    "EXAM RESULT\n\n"
    + "Score: " + ExamResult.score + " / " + ExamResult.total
);
        });

        logoutButton.addActionListener(e -> {

            dashboard.dispose();

            showLogin();
        });
    }
}