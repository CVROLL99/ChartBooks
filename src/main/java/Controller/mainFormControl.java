package Controller;

import POJO.Books;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Created by Misha on 26.05.2017.
 */

/**
 * Класс контроллера главного окна
 */
public class mainFormControl {
    @FXML
    public Button toFile;
    @FXML
    public TextField firstField;

    @FXML
    public Label numberZap;
    @FXML
    public TableView<Books> productTableView;
    @FXML
    public Button clearButton;
    @FXML
    public Button chartBtn;

    public static ObservableList booksData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Books, String> nameProduct;

    @FXML
    private TableColumn<Books, Integer> priceProduct;

    public static int col = 0;

    /**
     *
     * Здесь все, что выполняется при запуске.
     * Конкретно - считается количество строк в файле
     */

    @FXML
    public void initialize() {
        try {
            File myFile = new File("c://JavaFiles/test.txt");
            FileReader fileReader = new FileReader(myFile);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

            int lineNumber = 0;
            while (lineNumberReader.readLine() != null) {
                lineNumber++;
            }

            numberZap.setText("Количество записей в базе: " + lineNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Здесь проверка ввода значений
     * и выводятся данные в таблицу по кнопке
     * @throws IOException
     */
    @FXML
    public void onFile() throws IOException {
        File file = new File("C://JavaFiles/test.txt");

        FileReader fileReader = new FileReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);

        int lineNumber = 0;
        while (lineNumberReader.readLine() != null) {
            lineNumber++;
        }

        if (firstField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Вы не ввели число!");
            System.exit(0);
        }
        //Если требуемого файла не существует.
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Файла не существует!");
            System.exit(0);
        }
        int a = Integer.parseInt(firstField.getText());

        if (lineNumber < a) {
            JOptionPane.showMessageDialog(null, "В файле нет такого количества строк!");
            System.exit(0);
        }
        clearData();
        initData();

        nameProduct.setCellValueFactory(new PropertyValueFactory<Books, String>("name"));
        priceProduct.setCellValueFactory(new PropertyValueFactory<Books, Integer>("number"));


        productTableView.setItems(booksData);
    }

    /**
     * Здесь заполняется коллекция, используя сущность Books
     * @throws IOException
     */
    private void initData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("c://JavaFiles/test.txt"));
        String line;
        col = Integer.parseInt(firstField.getText());
        int count=0;
        while ((line = reader.readLine()) != null) {
            if(col>count) {
                count++;
                line = line.trim();
                if (!line.isEmpty()) {
                    int indexOfLastSpace = line.lastIndexOf(' ');
                    String bookName = line.substring(0, indexOfLastSpace);
                    int bookNumber = Integer.parseInt(line.substring(indexOfLastSpace + 1));
                    booksData.add(new Books(bookName, bookNumber));
                }
            }
        }
    }

    /**
     * По этому методу отрабатывает кнопка и открывается второе окно
     * @param mouseEvent
     * @throws IOException
     */
    @FXML
    public void chartBtn(MouseEvent mouseEvent) throws IOException {
        Parent chartPageParent = FXMLLoader.load(getClass().getResource("/jfx/chartFrame.fxml"));
        Scene chartPageScene = new Scene(chartPageParent);
        Stage appStage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setTitle("Chart");
        appStage.setScene(chartPageScene);
        appStage.show();
    }

    /**
     * Метод очищает коллекцию Booksdata
     */
    public void clearData(){
        booksData.clear();
    }

    /**
     * Метод обрабатывает клавишу ентер
     * @param keyEvent
     * @throws IOException
     */
    public void onKeyPressed(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            onFile();
        }
    }


}

