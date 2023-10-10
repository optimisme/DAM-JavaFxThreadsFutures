package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Controller0 {

    @FXML
    private Button button0, button1;
    @FXML
    private AnchorPane container;
    @FXML
    private Label percentatge0, percentatge1;
    
    private ExecutorService executor = Executors.newFixedThreadPool(2); // Creem una pool de dos fils

    @FXML
    private void animateToView1(ActionEvent event) {
        UtilsViews.setViewAnimating("View1");
    }

    @FXML
    private void runTask() {

        backgroundTask(0);
        backgroundTask(1);
    }

    private void backgroundTask(int index) {
        // Executar la tasca
        executor.submit(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    final int currentValue = i;
    
                    if (index == 0) {
                        // Actualitzar el Label en el fil d'aplicació de l'UI
                        Platform.runLater(() -> {
                            percentatge0.setText(String.valueOf(currentValue) + "%");
                        });
                        Thread.sleep(20);

                    }

                    if (index == 1) {
                        // Actualitzar el Label en el fil d'aplicació de l'UI
                        Platform.runLater(() -> {
                            percentatge1.setText(String.valueOf(currentValue) + "%");
                        });
                        Thread.sleep(40);
                    }

                    System.out.println("Updating label: " + index + ", Value: " + currentValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    
    // Aquesta funció la cridaries quan vulguis tancar l'executor (per exemple, quan tanquis la teva aplicació)
    public void stopExecutor() {
        executor.shutdown();
    }

}