import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exam extends JFrame implements ActionListener {

    JLabel questionLabel;
    JRadioButton option1, option2, option3, option4;
    ButtonGroup options;
    JButton nextButton, previousButton, submitButton;

    int currentQuestion = 0;
    int score = 0;

    String[] questions = {
        "1. Which language is used to create Java programs?",
        "2. Which keyword is used to create a class in Java?",
        "3. Which method is the starting point of a Java program?",
        "4. Which symbol is used to end a statement in Java?",
        "5. Which of these is an OOP concept?"
    };

    String[][] choices = {
        {"HTML", "Java", "CSS", "SQL"},
        {"class", "object", "new", "create"},
        {"start()", "run()", "main()", "begin()"},
        {".", ":", ";", ","},
        {"Inheritance", "Compiler", "Database", "Keyboard"}
    };

    int[] answers = {1, 0, 2, 2, 0};

    public Exam() {

        setTitle("Online Examination");
        setSize(650, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("ONLINE EXAMINATION");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(180, 30, 300, 40);
        add(title);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBounds(50, 90, 550, 40);
        add(questionLabel);

        option1 = new JRadioButton();
        option1.setBounds(70, 150, 450, 30);
        add(option1);

        option2 = new JRadioButton();
        option2.setBounds(70, 190, 450, 30);
        add(option2);

        option3 = new JRadioButton();
        option3.setBounds(70, 230, 450, 30);
        add(option3);

        option4 = new JRadioButton();
        option4.setBounds(70, 270, 450, 30);
        add(option4);

        options = new ButtonGroup();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);

        previousButton = new JButton("Previous");
        previousButton.setBounds(80, 340, 120, 40);
        previousButton.addActionListener(this);
        add(previousButton);

        nextButton = new JButton("Next");
        nextButton.setBounds(220, 340, 120, 40);
        nextButton.addActionListener(this);
        add(nextButton);

        submitButton = new JButton("Submit Exam");
        submitButton.setBounds(360, 340, 140, 40);
        submitButton.addActionListener(this);
        add(submitButton);

        showQuestion();

        setVisible(true);
    }

    void showQuestion() {

        questionLabel.setText(questions[currentQuestion]);

        option1.setText(choices[currentQuestion][0]);
        option2.setText(choices[currentQuestion][1]);
        option3.setText(choices[currentQuestion][2]);
        option4.setText(choices[currentQuestion][3]);

        options.clearSelection();

        if (currentQuestion == 0) {
            previousButton.setEnabled(false);
        } else {
            previousButton.setEnabled(true);
        }

        if (currentQuestion == questions.length - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == nextButton) {

            checkAnswer();

            if (currentQuestion < questions.length - 1) {
                currentQuestion++;
                showQuestion();
            }
        }

        else if (e.getSource() == previousButton) {

            if (currentQuestion > 0) {
                currentQuestion--;
                showQuestion();
            }
        }

        else if (e.getSource() == submitButton) {

            checkAnswer();
     ExamResult.score = score;
   ExamResult.total = questions.length;

            JOptionPane.showMessageDialog(
                this,
                "Exam Submitted!\nYour Score: "
                + score + " / " + questions.length
            );

            dispose();
        }
    }

    void checkAnswer() {

        if (option1.isSelected() && answers[currentQuestion] == 0) {
            score++;
        }

        else if (option2.isSelected() && answers[currentQuestion] == 1) {
            score++;
        }

        else if (option3.isSelected() && answers[currentQuestion] == 2) {
            score++;
        }

        else if (option4.isSelected() && answers[currentQuestion] == 3) {
            score++;
        }
    }

    public static void main(String[] args) {
        new Exam();
    }
}