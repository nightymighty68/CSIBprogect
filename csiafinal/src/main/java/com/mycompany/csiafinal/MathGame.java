import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class MathGame extends JFrame {
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;

    private int score;
    private int numQuestions;
    private Random random;

    public MathGame() {
        setTitle("Simple Math Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(questionLabel);

        answerField = new JTextField();
        answerField.setHorizontalAlignment(JTextField.CENTER);
        mainPanel.add(answerField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        mainPanel.add(submitButton);

        resultLabel = new JLabel();
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(resultLabel);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(scoreLabel);

        add(mainPanel);

        random = new Random();
        score = 0;
        numQuestions = 0;

        generateQuestion();

        setVisible(true);
    }

    private void generateQuestion() {
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int operation = random.nextInt(4); // 0: Addition, 1: Subtraction, 2: Multiplication, 3: Division
        String operator = "";

        int correctAnswer = 0;
        switch (operation) {
            case 0:
                operator = "+";
                correctAnswer = num1 + num2;
                break;
            case 1:
                operator = "-";
                correctAnswer = num1 - num2;
                break;
            case 2:
                operator = "*";
                correctAnswer = num1 * num2;
                break;
            case 3:
                operator = "/";
                // Ensure non-zero divisor
                while (num2 == 0) {
                    num2 = random.nextInt(10);
                }
                // Ensure integer division
                num1 *= num2;
                correctAnswer = num1 / num2;
                break;
        }

        questionLabel.setText(num1 + " " + operator + " " + num2 + " = ");
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String answerText = answerField.getText();
            if (!answerText.isEmpty()) {
                int answer = Integer.parseInt(answerText);
                int correctAnswer = evaluateQuestion();

                if (answer == correctAnswer) {
                    resultLabel.setText("Correct!");
                    score++;
                } else {
                    resultLabel.setText("Incorrect! Correct answer is " + correctAnswer);
                }

                numQuestions++;
                scoreLabel.setText("Score: " + score);
                generateQuestion();
                answerField.setText("");
            }
        }

        private int evaluateQuestion() {
            String questionText = questionLabel.getText();
            String[] parts = questionText.split(" ");
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[2]);
            String operator = parts[1];
            int correctAnswer = 0;

            switch (operator) {
                case "+":
                    correctAnswer = num1 + num2;
                    break;
                case "-":
                    correctAnswer = num1 - num2;
                    break;
                case "*":
                    correctAnswer = num1 * num2;
                    break;
                case "/":
                    correctAnswer = num1 / num2;
                    break;
            }

            return correctAnswer;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MathGame::new);
    }
}
