package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.application.Application.launch;

public class CartoonAnimationApp extends Application {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private Canvas canvas;
    private GraphicsContext gc;
    private int currentFrame = 0;
    private ImageFrame[] frames;
    private Timeline animationTimeline;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Мультфильм");

        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        frames = createFrames(); // Реализуйте этот метод для создания кадров мультфильма

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> playAnimation());

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> pauseAnimation());

        VBox root = new VBox(10, canvas, playButton, pauseButton);

        Scene scene = new Scene(root, WIDTH, HEIGHT + 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageFrame[] createFrames() {
        // Ваш код для создания кадров мультфильма
        // Например, frames = new ImageFrame[]{new ImageFrame(...), new ImageFrame(...)};

        return null;
    }

    private void playAnimation() {
        animationTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> animate()));
        animationTimeline.setCycleCount(frames.length);
        animationTimeline.play();
    }

    private void animate() {
        drawFrame(currentFrame);
        currentFrame = (currentFrame + 1) % frames.length;
    }

    private void drawFrame(int frameIndex) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(frames[frameIndex].getImage(), 0, 0, WIDTH, HEIGHT);
    }

    private void pauseAnimation() {
        if (animationTimeline != null) {
            animationTimeline.pause();
        }
    }
}

class ImageFrame {
    private String imagePath; // Путь к изображению
    // Дополнительные параметры для анимации, например, координаты и т. д.

    public ImageFrame(String imagePath) {
        this.imagePath = imagePath;
        // Инициализация других параметров
    }

    public String getImagePath() {
        return imagePath;
    }

// Дополнительные методы и параметры для анимации


}
