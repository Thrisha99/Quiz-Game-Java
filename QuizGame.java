import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGame extends JFrame implements ActionListener {

    String[] questions = {
        "Who is the current President of India?",
        "Which city hosted the G20 Summit in 2023?",
        "What is India’s first AI-based supercomputer called?",
        "Which Indian startup became a unicorn in 2024?",
        "What is the goal of the Digital India Act 2024?"
    };

    String[][] options = {
        {"Droupadi Murmu", "Ram Nath Kovind", "Pratibha Patil", "Narendra Modi"},
        {"Bengaluru", "New Delhi", "Mumbai", "Hyderabad"},
        {"AIRAVAT", "Vayu", "BharatGPT", "VedAI"},
        {"Zepto", "Ola", "Dunzo", "ShareChat"},
        {"Ban social media", "Protect digital rights", "Build mobile towers", "Subsidize smartphones"}
    };

    String[] answers = {
        "Droupadi Murmu",
        "New Delhi",
        "AIRAVAT",
        "Zepto",
        "Protect digital rights"
    };

    int index = 0;
    int score = 0;

    JLabel questionLabel;
    JButton[] optionButtons = new JButton[4];
    JButton nextButton;

    public QuizGame() {
        setTitle("QUIZ TIME");
        setSize(650, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(34, 40, 49)); // Cozy dark background

        questionLabel = new JLabel();
        questionLabel.setBounds(40, 30, 570, 60);
        questionLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        questionLabel.setForeground(new Color(220, 220, 220));
        add(questionLabel);

        int y = 110;
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setBounds(60, y, 500, 40);
            optionButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setBackground(new Color(60, 63, 65));
            optionButtons[i].setForeground(Color.WHITE);
            optionButtons[i].addActionListener(this);
            add(optionButtons[i]);
            y += 50;
        }

        nextButton = new JButton("Next →");
        nextButton.setBounds(250, 320, 120, 40);
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nextButton.setBackground(new Color(51, 153, 102));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        nextButton.setVisible(false);
        add(nextButton);

        loadQuestion();
        setVisible(true);
    }

    public void loadQuestion() {
        questionLabel.setText("Q" + (index + 1) + ": " + questions[index]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[index][i]);
            optionButtons[i].setEnabled(true);
            optionButtons[i].setBackground(new Color(60, 63, 65));
        }
        nextButton.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == nextButton) {
            index++;
            if (index < questions.length) {
                loadQuestion();
            } else {
                showResult();
            }
        } else {
            String selected = clicked.getText();
            for (JButton btn : optionButtons) {
                btn.setEnabled(false);
                if (btn.getText().equals(answers[index])) {
                    btn.setBackground(new Color(88, 194, 141)); // Green
                }
            }

            if (selected.equals(answers[index])) {
                score++;
            } else {
                clicked.setBackground(new Color(225, 77, 77)); // Soft Red
            }

            nextButton.setVisible(true);
        }
    }

    public void showResult() {
        getContentPane().removeAll();
        repaint();

        String comment;
        if (score == questions.length) {
            comment = "🌟 Outstanding! You nailed it!";
        } else if (score >= 3) {
            comment = "👍 Great job! Keep it up!";
        } else {
            comment = "✨ Nice try! Give it another go!";
        }

        JLabel resultLabel = new JLabel("Your Score: " + score + " / " + questions.length);
        resultLabel.setBounds(160, 130, 400, 50);
        resultLabel.setFont(new Font("Segoe Script", Font.BOLD, 24));
        resultLabel.setForeground(new Color(240, 240, 180));
        add(resultLabel);

        JLabel commentLabel = new JLabel(comment);
        commentLabel.setBounds(140, 180, 400, 40);
        commentLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
        commentLabel.setForeground(Color.LIGHT_GRAY);
        add(commentLabel);

        revalidate();
    }

    public static void main(String[] args) {
        new QuizGame();
    }
}
