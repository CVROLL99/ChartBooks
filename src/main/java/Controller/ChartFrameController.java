package Controller;

import POJO.PieChartUtil;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.util.calendar.BaseCalendar;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Misha on 02.06.2017.
 */

/**
 * Класс контроллера формы с графиками
 */
public class ChartFrameController {

    @FXML
    public Button backButton;
    @FXML
    public PieChart chart;
    public Label dateLabel;



    /**
     * Метод возвращает нас назад на главное окно
     * @param mouseEvent
     * @throws IOException
     */
    @FXML
    public void onBack (MouseEvent mouseEvent) throws IOException {
        Parent chartPageParent = FXMLLoader.load(getClass().getResource("/jfx/mainForm.fxml"));
        Scene chartPageScene = new Scene(chartPageParent);
        Stage appStage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        appStage.hide();
        appStage.setScene(chartPageScene);
        appStage.setTitle("Работа моя");
        appStage.show();
    }

    /**
     * Метод инициализация, при котором он обращается к сущности PieChartUtil, где формируется
     * коллекция для диаграммы
     * @throws IOException
     */
    @FXML
    public void initialize() throws IOException {
        chart.setTitle("График с книгами");
        chart.setLegendSide(Side.LEFT);
        ObservableList<PieChart.Data> chartData = PieChartUtil.getChartData();
        chart.setData(chartData);


        /**
         * Здесь мы запускаем часы в отдельном потоке
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        final Date date = new Date();
                        final SimpleDateFormat formatForTimeNow = new SimpleDateFormat("E hh:mm:ss");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                dateLabel.setText(formatForTimeNow.format(date));
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

    }
}
