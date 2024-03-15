package com.example.demo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private int currentFrameGo = 0;
    private int currentFrameRun = 0;
    private int currentFrameSit = 0;
    private int currentFrameFlag = 0;
    private ImageFrame[] framesGo;
    private ImageFrame[] framesRun;
    private ImageFrame[] framesSit;
    private ImageFrame[] framesFlag;
    private Timeline animationTimelineGo;
    private Timeline animationTimelineRun;
    private Timeline animationTimelineSit;
    private Timeline animationTimelineFlag;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Мультфильм");

        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        framesGo = createFramesGo();
        framesRun = createFramesRun();
        framesSit = createFramesSit();
        framesFlag = createFramesFlag();

        Button playButtonGo = new Button("Go");
        playButtonGo.setOnAction(e -> playAnimationGo());

        Button playButtonRun = new Button("Run");
        playButtonRun.setOnAction(e -> playAnimationRun());

        Button playButtonSit = new Button("Sit");
        playButtonSit.setOnAction(e -> playAnimationSit());

        Button playButtonFlag = new Button("Flag");
        playButtonFlag.setOnAction(e -> playAnimationFlag());

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> pauseAnimation());

        MenuBar menuBar = createMenuBar();

        HBox buttonPanel = new HBox(5, playButtonGo, playButtonRun, playButtonSit, playButtonFlag, pauseButton);

        VBox root = new VBox(5, menuBar, canvas, buttonPanel);

        Scene scene = new Scene(root, WIDTH, HEIGHT + 50);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private MenuBar createMenuBar() {
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    private ImageFrame[] createFramesGo() {
        String absolutePath = Objects.requireNonNull(getClass().getResource("/images/")).toExternalForm();
        // создание кадров мультфильма
        framesGo = new ImageFrame[] {
                new ImageFrame(absolutePath + "goPerson/1.jpg"),
                new ImageFrame(absolutePath + "goPerson/2.jpg"),
                new ImageFrame(absolutePath + "goPerson/3.jpg"),
                new ImageFrame(absolutePath + "goPerson/4.jpg"),
                new ImageFrame(absolutePath + "goPerson/5.jpg"),
                new ImageFrame(absolutePath + "goPerson/6.jpg"),
                new ImageFrame(absolutePath + "goPerson/7.jpg"),
                new ImageFrame(absolutePath + "goPerson/8.jpg"),
                new ImageFrame(absolutePath + "goPerson/9.jpg"),
                new ImageFrame(absolutePath + "goPerson/10.jpg"),
                new ImageFrame(absolutePath + "goPerson/11.jpg"),
                new ImageFrame(absolutePath + "goPerson/12.jpg"),
                new ImageFrame(absolutePath + "goPerson/13.jpg"),
                new ImageFrame(absolutePath + "goPerson/14.jpg"),
                new ImageFrame(absolutePath + "goPerson/15.jpg"),
                new ImageFrame(absolutePath + "goPerson/16.jpg"),
                new ImageFrame(absolutePath + "goPerson/17.jpg"),
                new ImageFrame(absolutePath + "goPerson/18.jpg"),
                new ImageFrame(absolutePath + "goPerson/19.jpg"),
                new ImageFrame(absolutePath + "goPerson/20.jpg"),
                new ImageFrame(absolutePath + "goPerson/21.jpg"),
                new ImageFrame(absolutePath + "goPerson/22.jpg"),
                new ImageFrame(absolutePath + "goPerson/23.jpg"),
                new ImageFrame(absolutePath + "goPerson/24.jpg"),
                new ImageFrame(absolutePath + "goPerson/25.jpg"),
                new ImageFrame(absolutePath + "goPerson/26.jpg"),
                new ImageFrame(absolutePath + "goPerson/27.jpg"),
                new ImageFrame(absolutePath + "goPerson/28.jpg"),
                new ImageFrame(absolutePath + "goPerson/29.jpg")
        };
        return framesGo;
    }

    private void playAnimationGo() {
        pauseAnimation();
        animationTimelineGo = new Timeline(); // Создание нового объекта Timeline
        animationTimelineGo.getKeyFrames().clear();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), e -> animateGo());
        animationTimelineGo.getKeyFrames().add(keyFrame);
        animationTimelineGo.setCycleCount(Timeline.INDEFINITE);
        animationTimelineGo.play();
    }

    private void animateGo() {
        drawFrame(currentFrameGo, framesGo);
        currentFrameGo = (currentFrameGo + 1) % framesGo.length;
    }

    private ImageFrame[] createFramesRun() {
        String absolutePath = Objects.requireNonNull(getClass().getResource("/images/")).toExternalForm();
        // создание кадров мультфильма
        framesRun = new ImageFrame[] {
                new ImageFrame(absolutePath + "runPerson/1.jpg"),
                new ImageFrame(absolutePath + "runPerson/2.jpg"),
                new ImageFrame(absolutePath + "runPerson/3.jpg"),
                new ImageFrame(absolutePath + "runPerson/4.jpg"),
                new ImageFrame(absolutePath + "runPerson/5.jpg"),
                new ImageFrame(absolutePath + "runPerson/6.jpg"),
                new ImageFrame(absolutePath + "runPerson/7.jpg"),
                new ImageFrame(absolutePath + "runPerson/8.jpg"),
                new ImageFrame(absolutePath + "runPerson/9.jpg"),
                new ImageFrame(absolutePath + "runPerson/10.jpg"),
                new ImageFrame(absolutePath + "runPerson/11.jpg"),
                new ImageFrame(absolutePath + "runPerson/12.jpg"),
                new ImageFrame(absolutePath + "runPerson/13.jpg"),
                new ImageFrame(absolutePath + "runPerson/14.jpg"),
                new ImageFrame(absolutePath + "runPerson/15.jpg"),
                new ImageFrame(absolutePath + "runPerson/16.jpg"),
                new ImageFrame(absolutePath + "runPerson/17.jpg"),
                new ImageFrame(absolutePath + "runPerson/18.jpg"),
                new ImageFrame(absolutePath + "runPerson/19.jpg"),
                new ImageFrame(absolutePath + "runPerson/20.jpg"),
                new ImageFrame(absolutePath + "runPerson/21.jpg"),
                new ImageFrame(absolutePath + "runPerson/22.jpg"),
                new ImageFrame(absolutePath + "runPerson/23.jpg"),
                new ImageFrame(absolutePath + "runPerson/24.jpg"),
                new ImageFrame(absolutePath + "runPerson/25.jpg"),
                new ImageFrame(absolutePath + "runPerson/26.jpg"),
                new ImageFrame(absolutePath + "runPerson/27.jpg"),
                new ImageFrame(absolutePath + "runPerson/28.jpg"),
                new ImageFrame(absolutePath + "runPerson/29.jpg"),
                new ImageFrame(absolutePath + "runPerson/30.jpg"),
                new ImageFrame(absolutePath + "runPerson/31.jpg"),
                new ImageFrame(absolutePath + "runPerson/32.jpg"),
                new ImageFrame(absolutePath + "runPerson/33.jpg"),
                new ImageFrame(absolutePath + "runPerson/34.jpg"),
                new ImageFrame(absolutePath + "runPerson/35.jpg"),
                new ImageFrame(absolutePath + "runPerson/36.jpg"),
                new ImageFrame(absolutePath + "runPerson/37.jpg"),
                new ImageFrame(absolutePath + "runPerson/38.jpg"),
                new ImageFrame(absolutePath + "runPerson/39.jpg"),
                new ImageFrame(absolutePath + "runPerson/40.jpg"),
                new ImageFrame(absolutePath + "runPerson/41.jpg"),
                new ImageFrame(absolutePath + "runPerson/42.jpg"),
                new ImageFrame(absolutePath + "runPerson/43.jpg"),
                new ImageFrame(absolutePath + "runPerson/44.jpg"),
                new ImageFrame(absolutePath + "runPerson/45.jpg"),
                new ImageFrame(absolutePath + "runPerson/46.jpg"),
                new ImageFrame(absolutePath + "runPerson/47.jpg"),
                new ImageFrame(absolutePath + "runPerson/48.jpg"),
                new ImageFrame(absolutePath + "runPerson/49.jpg"),
                new ImageFrame(absolutePath + "runPerson/50.jpg")
        };
        return framesRun;
    }

    private void playAnimationRun() {
        pauseAnimation();
        animationTimelineRun = new Timeline();
        animationTimelineRun.getKeyFrames().clear();
        KeyFrame keyFrameRun = new KeyFrame(Duration.millis(100), e -> animateRun());
        animationTimelineRun.getKeyFrames().add(keyFrameRun);
        animationTimelineRun.setCycleCount(Timeline.INDEFINITE);
        animationTimelineRun.play();
    }

    private void animateRun() {
        drawFrame(currentFrameRun, framesRun);
        currentFrameRun = (currentFrameRun + 1) % framesRun.length;
    }

    private ImageFrame[] createFramesSit() {
        String absolutePath = Objects.requireNonNull(getClass().getResource("/images/")).toExternalForm();
        // создание кадров мультфильма
        framesSit = new ImageFrame[] {
                new ImageFrame(absolutePath + "sitPerson/1.jpg"),
                new ImageFrame(absolutePath + "sitPerson/2.jpg"),
                new ImageFrame(absolutePath + "sitPerson/3.jpg"),
                new ImageFrame(absolutePath + "sitPerson/4.jpg"),
                new ImageFrame(absolutePath + "sitPerson/5.jpg"),
                new ImageFrame(absolutePath + "sitPerson/6.jpg"),
                new ImageFrame(absolutePath + "sitPerson/7.jpg"),
                new ImageFrame(absolutePath + "sitPerson/8.jpg"),
                new ImageFrame(absolutePath + "sitPerson/9.jpg"),
                new ImageFrame(absolutePath + "sitPerson/10.jpg")
        };
        return framesSit;
    }

    private void playAnimationSit() {
        pauseAnimation();
        animationTimelineSit = new Timeline();
        animationTimelineSit.getKeyFrames().clear();
        KeyFrame keyFrameSit = new KeyFrame(Duration.millis(100), e -> animateSit());
        animationTimelineSit.getKeyFrames().add(keyFrameSit);
        animationTimelineSit.setCycleCount(Timeline.INDEFINITE);
        animationTimelineSit.play();
    }

    private void animateSit() {
        drawFrame(currentFrameSit, framesSit);
        currentFrameSit = (currentFrameSit + 1) % framesSit.length;
    }

    private ImageFrame[] createFramesFlag() {
        String absolutePath = Objects.requireNonNull(getClass().getResource("/images/")).toExternalForm();
        // создание кадров мультфильма
        framesFlag = new ImageFrame[] {
                new ImageFrame(absolutePath + "flagPerson/1.jpg"),
                new ImageFrame(absolutePath + "flagPerson/2.jpg"),
                new ImageFrame(absolutePath + "flagPerson/3.jpg"),
                new ImageFrame(absolutePath + "flagPerson/4.jpg"),
                new ImageFrame(absolutePath + "flagPerson/5.jpg"),
                new ImageFrame(absolutePath + "flagPerson/6.jpg"),
                new ImageFrame(absolutePath + "flagPerson/7.jpg"),
                new ImageFrame(absolutePath + "flagPerson/8.jpg"),
                new ImageFrame(absolutePath + "flagPerson/9.jpg"),
                new ImageFrame(absolutePath + "flagPerson/10.jpg"),
                new ImageFrame(absolutePath + "flagPerson/11.jpg"),
                new ImageFrame(absolutePath + "flagPerson/12.jpg")
        };
        return framesFlag;
    }

    private void playAnimationFlag() {
        pauseAnimation();
        animationTimelineFlag = new Timeline();
        animationTimelineFlag.getKeyFrames().clear();
        KeyFrame keyFrameFlag = new KeyFrame(Duration.millis(100), e -> animateFlag());
        animationTimelineFlag.getKeyFrames().add(keyFrameFlag);
        animationTimelineFlag.setCycleCount(Timeline.INDEFINITE);
        animationTimelineFlag.play();
    }

    private void animateFlag() {
        drawFrame(currentFrameFlag, framesFlag);
        currentFrameFlag = (currentFrameFlag + 1) % framesFlag.length;
    }

    private void drawFrame(int frameIndex, ImageFrame[] frames) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(frames[frameIndex].getImage(), 0, 0, WIDTH, HEIGHT);
    }

    private void pauseAnimation() {
        if (animationTimelineGo != null && animationTimelineGo.getStatus() == Timeline.Status.RUNNING) {
            animationTimelineGo.pause();
        }
        if (animationTimelineRun != null && animationTimelineRun.getStatus() == Timeline.Status.RUNNING) {
            animationTimelineRun.pause();
        }
        if (animationTimelineSit != null && animationTimelineSit.getStatus() == Timeline.Status.RUNNING) {
            animationTimelineSit.pause();
        }
        if (animationTimelineFlag != null && animationTimelineFlag.getStatus() == Timeline.Status.RUNNING) {
            animationTimelineFlag.pause();
        }
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
