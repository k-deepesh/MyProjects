import java.util.*;

public class Quiz1 {
    public static void main(String[] args) {
        System.out.println("Enter your Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println("Hi " + name);
        System.out.println("Are you ready to start the game: \nEnter yes/No");
        String opinion = scanner.next();
        if (opinion.equalsIgnoreCase("No")) {
            System.out.println("Thank you!\nHave a nice day!!");
        } else {
            // Create questions
            Question[] questions = {
                    new Question("What is 2 + 2 ?", 4),
                    new Question("What is 2 * 2 ?", 4),
                    new Question("What is 1000 - 996 ?", 4)
            };
            // Start the quiz
            Quiz quiz = new Quiz(questions, scanner);
            quiz.takeQuiz();
        }
    }
}

class Question {
    private String question;
    private int answer;

    public Question(String question, int answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public int getAnswer() {
        return answer;
    }
}

class Quiz {
    private Question[] questions;
    private Scanner scanner;
    private int score;

    public Quiz(Question[] questions, Scanner scanner) {
        this.questions = questions;
        this.scanner = scanner;
        this.score = 0;
    }

    public void takeQuiz() {
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt();
            if (userAnswer == question.getAnswer()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect!\n");
            }
        }
        System.out.println("Your final score is: " + score + "/" + questions.length);
    }
}