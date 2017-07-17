package com.azl.gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.html.HTMLFormElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WebViewDialog extends DialogWrapper {

    private final JFXPanel jfxPanel = new JFXPanel();
    private WebView webView;
    private WebEngine webEngine;
    private String url;
    private final String noUrlHtml = "<html><body><h3>No URL provided!</body></html>";

    public WebViewDialog(@Nullable Project project, boolean canBeParent) {
        super(project, canBeParent);
        init();
    }

    public WebViewDialog(@Nullable Project project, String url) {
        super(project);
        init();
        this.url = url;
    }

    protected void closeDialog(){
        // TODO: wow, this is terrible! Figure out right way to do this...
        Container parent = jfxPanel.getParent();
        int maxLoop = 0;
        while(parent != null) {
            System.out.println("parent = "+parent.getClass().getTypeName());
            if(parent.getClass().getTypeName().endsWith("$MyDialog") || ++maxLoop>10) {
                parent.setVisible(false);
                break;
            }
            parent = parent.getParent();
        }
        //close(1);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {

        //jfxPanel.setSize(new Dimension(300,300));
        jfxPanel.setPreferredSize(new Dimension(300,450));
        String html = "<html><body><h3>Oh hai there!</h3><a href=\"http://www.microsoft.com\">Link</a><input id=\"btnSubmit\" type=\"submit\"/></body></html>";

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread.currentThread() = "+Thread.currentThread().toString());
                loadViewContent(jfxPanel);
                jfxPanel.requestFocus();
            }
        });

        jfxPanel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keytyped!");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed! "+e);
                if(e.getKeyChar() == 'Q'){
                    closeDialog();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased!");
            }
        });

        pack();
        return jfxPanel;
    }

    private void loadViewContent(JFXPanel panel) {
        webView = new WebView();
        webEngine = webView.getEngine();

        panel.setScene(new Scene(webView));

        if(url != null){
            webEngine.load(url);
        }
        else {
            webEngine.loadContent(noUrlHtml);
        }

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                System.out.println(observable.toString());
                if(observable.getValue() == Worker.State.SUCCEEDED) {
                    System.out.println("READY!");
                    handleDocumentLoaded();
                }
            }
        });
    }
    @NotNull
    @Override
    protected Action[] createActions() {
        // return super.createActions();
        return new Action[]{};
    }

    private void handleDocumentLoaded() {

        Document document = webEngine.getDocument();

        //HTMLFormElement form = (HTMLFormElement) document.getElementsByTagName("form");
        NodeList forms = document.getElementsByTagName("form");

        if(forms.getLength() > 0) {
            if (((HTMLFormElement)forms.item(0)).getAttribute("action").contains("test")) {
                NodeList nodeList = document.getElementsByTagName("input");
                if(nodeList.getLength() > 0){
                    ((EventTarget) nodeList.item(0)).addEventListener("click", new EventListener() {
                        @Override
                        public void handleEvent(org.w3c.dom.events.Event evt) {
                            EventTarget currentTarget = evt.getCurrentTarget();
                            EventTarget target = evt.getTarget();
                            Document document = webEngine.getDocument();
                        }
                    }, false);
                    webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
                        @Override
                        public void handle(WebEvent<String> event)
                        {
                            System.out.println("setOnAlert: "+event);
                            //JOptionPane.showMessageDialog(null, event);
                        }
                    });
                    webEngine.setOnStatusChanged(new EventHandler<WebEvent<String>>() {
                        @Override
                        public void handle(WebEvent<String> event) {
                            System.out.println("setOnStatusChanged: "+event);
                            //JOptionPane.showMessageDialog(null, event);
                        }
                    });
                }
                else {
                    JOptionPane.showMessageDialog(null, "No submit button found!");
                }
            }
        }
        else {
            System.out.println("document = "+document.toString());

            Element element = (Element) document.getElementsByTagName("body").item(0);
            System.out.println("Response = "+element.getTextContent());
            closeDialog();
        }
    }
}
