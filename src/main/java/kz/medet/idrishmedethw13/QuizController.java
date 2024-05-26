package kz.medet.idrishmedethw13;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class QuizController {
    @FXML
    private Label questionLabel;
    @FXML
    private ToggleGroup answersGroup = new ToggleGroup();
    @FXML
    private RadioButton answer1;
    @FXML
    private RadioButton answer2;
    @FXML
    private RadioButton answer3;
    @FXML
    private RadioButton answer4;
    @FXML
    private Label feedbackLabel;

    private int currentQuestion = 0;
    private int score = 0;

    private String[][] questions = {
            {"Who is Author of Abay Zholy?", "Auezov", "Abay", "Saken", "Magzhan"},
            {"What is 10 * 5 ?", "500", "50", "105", "5"},
            {"Who won 2nd World War?", "Germany", "Usa", "SSSR", "Japan"},
            {"Who wrote 'Harry Potter'?", "Rouling", "Dickens", "Lev", "Donald"}
    };
    private int[] correctAnswers = {0, 1, 2, 0};

    @FXML
    public void initialize() {
        answer1.setToggleGroup(answersGroup);
        answer2.setToggleGroup(answersGroup);
        answer3.setToggleGroup(answersGroup);
        answer4.setToggleGroup(answersGroup);
        showNextQuestion();
    }

    @FXML
    private void handleNextButtonAction() {
        RadioButton selectedAnswer = (RadioButton) answersGroup.getSelectedToggle();
        if (selectedAnswer != null) {
            int selectedIndex = getSelectedIndex();
            if (selectedIndex == correctAnswers[currentQuestion - 1]) {
                score++;
                feedbackLabel.setText("Correct!");
            } else {
                feedbackLabel.setText("Wrong! The correct answer was: " + questions[currentQuestion - 1][correctAnswers[currentQuestion - 1] + 1]);
            }
        }
        showNextQuestion();
    }

    private int getSelectedIndex() {
        if (answer1.isSelected()) return 0;
        if (answer2.isSelected()) return 1;
        if (answer3.isSelected()) return 2;
        if (answer4.isSelected()) return 3;
        return -1;
    }

    private void showNextQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion][0]);
            answer1.setText(questions[currentQuestion][1]);
            answer2.setText(questions[currentQuestion][2]);
            answer3.setText(questions[currentQuestion][3]);
            answer4.setText(questions[currentQuestion][4]);
            answersGroup.selectToggle(null);
            currentQuestion++;
        } else {
            questionLabel.setText("Quiz Over! Your score: " + score + "/" + questions.length);
            answer1.setDisable(true);
            answer2.setDisable(true);
            answer3.setDisable(true);
            answer4.setDisable(true);
        }
    }
}
