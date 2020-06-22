package com.maks.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Set;

public class CalculatorController {

    @FXML
    Pane calculatorPane;

    @FXML
    TextArea calculatorTextArea; // TODO: 15/06/2020 usunac kursor tekstowy po najechaniu na pole tekstowe

    private static final Set<Character> mathOperators = Set.of('+', '-', '/', '*');
    private ScriptEngineManager scriptEngineManager;
    private ScriptEngine engine;
    private StringBuilder mathEquation;
    private boolean clearOnNext;

    public void initialize() {
        calculatorTextArea.setEditable(false);
        calculatorTextArea.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            calculatorPane.requestFocus();
        });

        initMenu();

        scriptEngineManager = new ScriptEngineManager();
        engine = scriptEngineManager.getEngineByName("JavaScript");
        mathEquation = new StringBuilder();
        clearOnNext = false;
    }

    private void initMenu() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem settings = new MenuItem("Settings");
//        settings.

        fileMenu.getItems().add(settings);

        menuBar.getMenus().add(fileMenu);
        menuBar.useSystemMenuBarProperty().set(true);

        calculatorPane.getChildren().add(menuBar);
    }

    @FXML
    public void onEqualsButtonMouseClick(MouseEvent mouseEvent) {
        Double doubleValue = null;
        Integer intValue = null;

        try {
            doubleValue = (Double) engine.eval(calculatorTextArea.getText());
            System.out.println("Double: " + doubleValue);

        } catch (ScriptException e) {
            calculatorTextArea.setStyle("-fx-font-size: 16px");
            calculatorTextArea.setText("Invalid math expression");
        } catch (Exception ignored) {
            // ignore exception
        } finally {
            if (doubleValue != null) {
                calculatorTextArea.setText(String.format("%.2f", doubleValue));
            }
        }

        try {
            intValue = (Integer) engine.eval(calculatorTextArea.getText());
            System.out.println("Int: " + intValue);

        } catch (ScriptException e) {
            calculatorTextArea.setStyle("-fx-font-size: 16px");
            calculatorTextArea.setText("Invalid math expression");
        } catch (Exception ignored) {
            // ignore exception
        } finally {
            if (doubleValue == null && intValue != null) {
                calculatorTextArea.setText(intValue.toString());
            }
        }
    }

    @FXML
    public void onACButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setStyle("-fx-font-size: 32px");
        calculatorTextArea.clear();
        mathEquation.setLength(0);
    }

    public void beforeTextChange() {
        if (clearOnNext) {
            calculatorTextArea.clear();
            clearOnNext = false;
        }
    }

    @FXML
    public void onOneButtonClick(MouseEvent mouseEvent) {
        beforeTextChange();
        calculatorTextArea.setText(calculatorTextArea.getText() + 1);
        mathEquation.append(1);
    }

    @FXML
    public void onTwoButtonClick(MouseEvent mouseEvent) {
        beforeTextChange();
        calculatorTextArea.setText(calculatorTextArea.getText() + 2);
        mathEquation.append(2);
    }

    @FXML
    public void onThreeButtonClick(MouseEvent mouseEvent) {
        beforeTextChange();
        mathEquation.append(3);
        calculatorTextArea.setText(mathEquation.toString());
    }

    @FXML
    public void onMinusButtonClick(MouseEvent mouseEvent) {
//        String text = calculatorTextArea.getText();
//        if (!text.isEmpty() ) {
//            if (mathOperators.contains(text.charAt(text.length() - 1))) {
//                calculatorTextArea.setText(text.substring(0, text.length() - 1) + "-");
//            } else {
//                calculatorTextArea.setText(text + "-");
//            }
//        }


    }

    @FXML
    public void onPlusButtonClick(MouseEvent mouseEvent) {
//        String text = calculatorTextArea.getText();
//        if (!text.isEmpty() ) {
//            if (mathOperators.contains(text.charAt(text.length() - 1))) {
//                calculatorTextArea.setText(text.substring(0, text.length() - 1) + "+");
//            } else {
//                calculatorTextArea.setText(text + "+");
//            }
//        }

        calculate();
        calculatorTextArea.setText(mathEquation.toString());
        mathEquation.append('+');
        clearOnNext = true;
    }

    @FXML
    public void onSixButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "6");
    }

    @FXML
    public void onFiveButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "5");
    }

    @FXML
    public void onFourButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "4");
    }

    @FXML
    public void onNineButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "9");
    }

    @FXML
    public void onEightButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "8");
    }

    @FXML
    public void onSevenButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "7");
    }

    @FXML
    public void onPlusOrNegativeButtonClick(MouseEvent mouseEvent) {
        String text = calculatorTextArea.getText();

        if (!text.isEmpty()) {
            if (text.charAt(0) == '-') {
                calculatorTextArea.setText("+" + text.substring(1));
            } else if (text.charAt(0) == '+') {
                calculatorTextArea.setText("-" + text.substring(1));
            } else {
                calculatorTextArea.setText("-" + text);
            }
        }
    }

    @FXML
    public void onPercentButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "%");
    }

    @FXML
    public void onMultiplyButtonClick(MouseEvent mouseEvent) {
        String text = calculatorTextArea.getText();
        if (!text.isEmpty()) {
            if (mathOperators.contains(text.charAt(text.length() - 1))) {
                calculatorTextArea.setText(text.substring(0, text.length() - 1) + "*");
            } else {
                calculatorTextArea.setText(text + "*");
            }
        }
    }

    @FXML
    public void onDivideButtonClick(MouseEvent mouseEvent) {
        String text = calculatorTextArea.getText();
        if (!text.isEmpty()) {
            if (mathOperators.contains(text.charAt(text.length() - 1))) {
                calculatorTextArea.setText(text.substring(0, text.length() - 1) + "/");
            } else {
                calculatorTextArea.setText(text + "/");
            }
        }
    }

    @FXML
    public void onZeroButtonClick(MouseEvent mouseEvent) {
        calculatorTextArea.setText(calculatorTextArea.getText() + "0");
    }

    @FXML
    public void onComaButtonClick(MouseEvent mouseEvent) {
        String text = calculatorTextArea.getText();
        if (text.isEmpty() || (text.charAt(text.length() - 1) != '.' && text.charAt(text.length() - 1) != ',' && hasNoCommaAfterLastMathOperator())) {
            beforeTextChange();
            calculatorTextArea.setText(calculatorTextArea.getText() + '.');
            mathEquation.append('.');
        }
    }

    private boolean hasNoCommaAfterLastMathOperator() {
        String text = calculatorTextArea.getText();
        for (int i = text.length() - 1; i >= 0; i--) {
            if (text.charAt(i) == ',' || text.charAt(i) == '.') {
                return false;
            } else if (mathOperators.contains(text.charAt(i))) {
                return true;
            }
        }

        return true;
    }

    private void calculate() {
        try {
            String currentMathEquation = mathEquation.toString();
            mathEquation.setLength(0);
            mathEquation.append(engine.eval(currentMathEquation));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }


    //     123

}
