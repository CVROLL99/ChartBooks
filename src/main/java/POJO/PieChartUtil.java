package POJO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static Controller.mainFormControl.col;

/**
 * POJO для графика
 */
public class PieChartUtil {
    public static ObservableList<PieChart.Data> getChartData() throws IOException {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        BufferedReader reader = new BufferedReader(new FileReader("c://JavaFiles/test.txt"));
        String line;
        int count=0;
        while ((line = reader.readLine()) != null) {
            if(col>count) {
                count++;
                line = line.trim();
                if (!line.isEmpty()) {
                    int indexOfLastSpace = line.lastIndexOf(' ');
                    String bookName = line.substring(0, indexOfLastSpace);
                    int bookNumber = Integer.parseInt(line.substring(indexOfLastSpace + 1));
                    data.add(new PieChart.Data(bookName, bookNumber));
                }
            }
        }
        return data;
    }
}


