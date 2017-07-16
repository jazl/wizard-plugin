package com.azl;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.*;
import javafx.stage.Stage;
import org.w3c.dom.*;
import org.w3c.dom.html.*;

public class fxWebViewApp extends Application {
    public static void main(String[] args) { launch(args); }

    @Override public void start(Stage stage) {
        final TextField fxUsername = new TextField();
        final TextField fxPassword = new PasswordField();

        final BooleanProperty loginAttempted = new SimpleBooleanProperty(false);

        final WebView webView = new WebView();
        final WebEngine engine = webView.getEngine();
        engine.documentProperty().addListener(new ChangeListener<Document>() {
            @Override public void changed(ObservableValue<? extends Document> ov, Document oldDoc, Document doc) {
                if (doc != null && !loginAttempted.get()) {
                    if (doc.getElementsByTagName("form").getLength() > 0) {
                        HTMLFormElement form = (HTMLFormElement) doc.getElementsByTagName("form").item(0);
                        if ("/oam/server/sso/auth_cred_submit".equals(form.getAttribute("action"))) {
                            HTMLInputElement username = null;
                            HTMLInputElement password = null;
                            NodeList nodes = form.getElementsByTagName("input");
                            for (int i = 0; i < nodes.getLength(); i++) {
                                HTMLInputElement input = (HTMLInputElement) nodes.item(i);
                                switch (input.getName()) {
                                    case "ssousername":
                                        username = input;
                                        break;
                                    case "password":
                                        password = input;
                                        break;
                                }
                            }

                            if (username != null && password != null) {
                                loginAttempted.set(true);
                                username.setValue(fxUsername.getText());
                                password.setValue(fxPassword.getText());
                                form.submit();
                                System.out.println("Submitted login for user: " + username);
                            }
                        }
                    }
                }
            }
        });
        engine.getLoadWorker().exceptionProperty().addListener(new ChangeListener<Throwable>() {
            @Override public void changed(ObservableValue<? extends Throwable> ov, Throwable oldException, Throwable exception) {
                System.out.println("Load Exception: " + exception);
            }
        });

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.addRow(0, new Label("Username: "), fxUsername);
        inputGrid.addRow(0, new Label("Password: "), fxPassword);

        Button fxLoginButton = new Button("Login to Oracle Forums");
        fxLoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                if (notEmpty(fxPassword.getText()) && notEmpty(fxPassword.getText())) {
                    loginAttempted.set(false);
                    engine.load("https://forums.oracle.com/forums/login!withRedirect.jspa");
                }
            }
        });

        final VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
        layout.getChildren().addAll(
                new Label("Enter your Oracle Web Account credentials"),
                inputGrid,
                fxLoginButton,
                webView
        );

        stage.setScene(new Scene(layout));
        stage.show();
    }

    private boolean notEmpty(String s) {
        return s != null && !"".equals(s);
    }
}
