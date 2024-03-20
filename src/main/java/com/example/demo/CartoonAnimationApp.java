package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.MenuBar;

import java.util.Objects;

public class CartoonAnimationApp extends Application {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private Canvas canvas;
    private GraphicsContext gc;

    private AnimationController animationController;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Мультфильм");

        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        animationController = new AnimationController(gc);

        VBox root = new VBox();
        root.getChildren().addAll(animationController.createMenuBar(), animationController.createButtonPanel(), canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT + 50);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class AnimationController {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private final GraphicsContext gc;

    private Timeline animationTimeline;
    private int currentFrameIndex = 0;
    private ImageFrame[] frames;

    public AnimationController(GraphicsContext gc) {
        this.gc = gc;
    }

    public MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu aboutMenu = new Menu("О программе");
        MenuItem helpMenuItem = new MenuItem("Справка");
        MenuItem aboutDeveloperMenuItem = new MenuItem("О разработчике");
        helpMenuItem.setOnAction(e -> showHelp());
        aboutDeveloperMenuItem.setOnAction(e -> showAbout());
        aboutMenu.getItems().addAll(helpMenuItem, aboutDeveloperMenuItem);

        menuBar.getMenus().add(aboutMenu);

        return menuBar;
    }

    public HBox createButtonPanel() {
        HBox buttonPanel = new HBox(5);

        Button playButtonGo = new Button("Go");
        playButtonGo.setOnAction(e -> playAnimation("/images/goPerson/", 29));

        Button playButtonRun = new Button("Run");
        playButtonRun.setOnAction(e -> playAnimation("/images/runPerson/", 50));

        Button playButtonSit = new Button("Sit");
        playButtonSit.setOnAction(e -> playAnimation("/images/sitPerson/", 10));

        Button playButtonFlag = new Button("Flag");
        playButtonFlag.setOnAction(e -> playAnimation("/images/flagPerson/", 12));

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> pauseAnimation());

        buttonPanel.getChildren().addAll(playButtonGo, playButtonRun, playButtonSit, playButtonFlag, pauseButton);
        return buttonPanel;
    }

    private void playAnimation(String folderPath, int frameCount) {
        pauseAnimation();
        frames = createFrames(folderPath, frameCount);
        animationTimeline = new Timeline();
        animationTimeline.getKeyFrames().clear();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), e -> animate());
        animationTimeline.getKeyFrames().add(keyFrame);
        animationTimeline.setCycleCount(Timeline.INDEFINITE);
        animationTimeline.play();
    }

    private void animate() {
        drawFrame(currentFrameIndex);
        currentFrameIndex = (currentFrameIndex + 1) % frames.length;
    }

    private ImageFrame[] createFrames(String folderPath, int frameCount) {
        String absolutePath = Objects.requireNonNull(getClass().getResource(folderPath)).toExternalForm();
        ImageFrame[] frames = new ImageFrame[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = new ImageFrame(absolutePath + (i + 1) + ".jpg");
        }
        return frames;
    }

    private void drawFrame(int frameIndex) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(frames[frameIndex].getImage(), 0, 0, WIDTH, HEIGHT);
    }

    private void pauseAnimation() {
        if (animationTimeline != null && animationTimeline.getStatus() == Timeline.Status.RUNNING) {
            animationTimeline.pause();
        }
    }

    private void showHelp() {
        showAlert("Справка", "Это приложение позволяет воспроизводить анимации персонажей.");
    }

    private void showAbout() {
        Image image = new Image(getClass().getResourceAsStream("/images/133156.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);
        showAlert("О разработчике", "", imageView);
    }

    private void showAlert(String title, String content, ImageView imageView) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(imageView, new Label(content));
        alert.getDialogPane().setContent(vbox);
        alert.showAndWait();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

class ImageFrame {
    private String imagePath;

    public ImageFrame(String imagePath) {
        this.imagePath = imagePath;
    }

    public Image getImage() {
        return new Image(imagePath);
    }
}


