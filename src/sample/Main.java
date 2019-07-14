package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Formatter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));

        int randomNumber1 = this.generateRandomNumber();
        int randomNumber2 = this.generateRandomNumber();

        GridPane quizPane = new GridPane();
        mainPane.setCenter(quizPane);
        quizPane.setAlignment(Pos.CENTER);
        quizPane.setHgap(10);
        quizPane.setVgap(10);

        Label heading = new Label("Two randomly generated numbers are: " +
                randomNumber1 + " and " +randomNumber2);
        quizPane.add(heading, 0, 0);
        GridPane.setColumnSpan(heading, 2);

        Label labelAddition = new Label("What is the addition of ");
        TextField textAddition = new TextField();
        quizPane.add(labelAddition, 0, 1);
        quizPane.add(textAddition, 1, 1);

        Label labelSubtraction = new Label("What is subtraction of ");
        TextField textSubtraction = new TextField();
        quizPane.add(labelSubtraction, 0, 2);
        quizPane.add(textSubtraction, 1, 2);

        Label labelMultiplication = new Label("What is multiplication of ");
        TextField textMultiplication = new TextField();
        quizPane.add(labelMultiplication, 0, 3);
        quizPane.add(textMultiplication, 1, 3);

        Label labelDivision = new Label("What is division of ");
        TextField textDivision = new TextField();
        quizPane.add(labelDivision, 0, 4);
        quizPane.add(textDivision, 1, 4);

        Label labelCorrect = new Label("Number of correct answers: ");
        Label labelWrong = new Label("Number of wrong answers: ");
        quizPane.add(labelCorrect, 0, 5);
        quizPane.add(labelWrong, 0, 6);
        GridPane.setColumnSpan(labelCorrect, 2);
        GridPane.setColumnSpan(labelWrong, 2);

        HBox tryAgain = new HBox();
        mainPane.setBottom(tryAgain);
        tryAgain.setAlignment(Pos.CENTER);
        BorderPane.setMargin(tryAgain, new Insets(10, 10, 10, 10));


        Label labelTry = new Label("");
        tryAgain.getChildren().addAll(labelTry);

        quizPane.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                try{
                    if( textAddition.getText().equals("") ||
                            textSubtraction.getText().equals("") ||
                            textMultiplication.getText().equals("") ||
                            textDivision.getText().equals("")){
                        throw new Exception();
                    }
                    int inputAddition = Integer.parseInt(textAddition.getText());
                    int inputSubtraction = Integer.parseInt(textSubtraction.getText());
                    int inputMultiplication = Integer.parseInt(textMultiplication.getText());
                    double inputDivision = Double.parseDouble(textDivision.getText());

                    int correctCounter = 0;
                    if (inputAddition == (randomNumber1 + randomNumber2)) {
                        correctCounter++;
                    }
                    if(inputSubtraction == (randomNumber1 - randomNumber2)){
                        correctCounter++;
                    }
                    if(inputMultiplication == (randomNumber1 * randomNumber2)){
                        correctCounter++;
                    }
                    Formatter div = new Formatter("0.00");
                    if(div.format(String.valueOf(inputDivision)) == div.format(String.valueOf(randomNumber1 / randomNumber2))){
                        correctCounter++;
                    }
                    labelCorrect.setText("Number of Correct Answers: " +correctCounter);
                    labelWrong.setText("Number of Wrong Answers: " +(4 - correctCounter));

                    labelTry.setText("Would you like to try another quiz: ");
                    TextField textTry = new TextField();
                    textTry.setPrefWidth(30);

                    tryAgain.getChildren().clear();
                    tryAgain.getChildren().addAll(labelTry, textTry);
                }
                catch (Exception ex){
                    this.showAlert(Alert.AlertType.ERROR, "Error", "There was an error", ex.toString());
                }
            }
        });

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(mainPane, 350, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public int generateRandomNumber (){
        return (int) Math.round(Math.random() * 5) + 1;
    }

    public void showAlert(Alert.AlertType type, String title, String header, String message){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
    }

}
