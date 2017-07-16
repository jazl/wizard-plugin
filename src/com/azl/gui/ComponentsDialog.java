package com.azl.gui;

import com.azl.custom.FileTypesDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import oracle.jrockit.jfr.JFR;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;

import javax.print.Doc;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.awt.Event;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javafx.concurrent.Worker.State;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.*;

public class ComponentsDialog extends JFrame {

    private final static String url = "http://localhost:4200/";

    private Project project;
    final JFrame frameRef;

    public ComponentsDialog() {
        frameRef = this;

        setSize(new Dimension(500,500));
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        panel.setPreferredSize(new Dimension(500,600));

        //FlowLayout layout = new FlowLayout();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(layout);

        JButton btnShowPicker = new JButton("Show File Types Picker...");
        btnShowPicker.setSize(500,525);
        panel.add(btnShowPicker);
        btnShowPicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> list1 = new ArrayList<String>();
                FileTypesDialog dlg = new FileTypesDialog(null, list1);
                dlg.show();
                if(dlg.getSelectedExtensions().size()>0) {
                    JOptionPane.showConfirmDialog(panel,"Selected: "+dlg.getSelectedExtensions());
                }
            }
        });

        JButton btnShowGenericDialog = new JButton("Show Generic Dialog...");
        btnShowGenericDialog.setPreferredSize(new Dimension(200,25));
        panel.add(btnShowGenericDialog);
        btnShowGenericDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameRef.setTitle("Showing generic dialog");
                GenericDialog d = new GenericDialog(frameRef, "Generic Dialog (as a frame?)");
                d.setVisible(true);
            }
        });

        JButton btnShowWebView = new JButton("Show WebView...");
        btnShowWebView.setPreferredSize(new Dimension(200,25));
        panel.add(btnShowWebView);
        btnShowWebView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WebViewDialog d = new WebViewDialog(null, false);
                d.show();
            }
        });

        JButton btnEditorPane = new JButton("Show JEditorPane...");
        btnEditorPane.setPreferredSize(new Dimension(200,25));
        panel.add(btnEditorPane);
        btnEditorPane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JEditorPaneDialog d = new JEditorPaneDialog(frameRef, "With JEditorPane!");
                d.loadPage();
                d.setVisible(true);
            }
        });

        add(panel);
    }

    private class WebViewDialog extends DialogWrapper {

        final JFXPanel jfxPanel = new JFXPanel();
        WebView webView;
        WebEngine webEngine;

        protected WebViewDialog(@Nullable Project project, boolean canBeParent) {
            super(project, canBeParent);
            //setSize(500,500);
            init();
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
            //webEngine.executeScript("alert('hello!');");
            webEngine.load(url);
            //webEngine.loadContent(html);

            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                @Override
                public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                    System.out.println(observable.toString());
                    if(observable.getValue() == State.SUCCEEDED) {
                        System.out.println("READY!");
                        setupDocumentListener();
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

        private void setupDocumentListener() {
            EventListener listener = new EventListener() {
                @Override
                public void handleEvent(org.w3c.dom.events.Event evt) {
                    EventTarget currentTarget = evt.getCurrentTarget();
                    EventTarget target = evt.getTarget();
                    Document document = webEngine.getDocument();
                    Element errorMessageLabel = document.getElementById("errorMessageLabel");
                    String errorMsg = errorMessageLabel.getTextContent();
                    //Messages.showInfoMessage(errorMsg,"HTML");
                    //JOptionPane.showMessageDialog(null, errorMsg);
                    System.out.println("Thread = "+Thread.currentThread().toString());
                    closeDialog();
                }
            };

            Document document = webEngine.getDocument();
            NodeList nodeList = document.getElementsByTagName("input");

            if(nodeList.getLength() > 0){
                ((EventTarget) nodeList.item(0)).addEventListener("click", listener, false);
            }
            else {
                JOptionPane.showMessageDialog(null, "No submit button found!");
            }

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
    }

    private class GenericDialog extends JDialog {

        public GenericDialog(Frame owner, String title) {
            super(owner,title);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            setSize(500, 500);
            setModal(true);

            JPanel panel = new JPanel(new FlowLayout());
            panel.add(new JButton("Button 1"));
            panel.add(new JButton("Button 2"));

            add(panel);
        }
    }

    private class JEditorPaneDialog extends JDialog {
        JEditorPane jEditorPane;
        public JEditorPaneDialog(Frame owner, String title) {
            super(owner, title);
            setSize(500, 500);

            jEditorPane = new JEditorPane();
            //jEditorPane.setEditable(false);

            //JTextPane textPane = new JTextPane();

            //jEditorPane.add(textPane);
            add(jEditorPane);
        }

        public void loadPage() {
            try {
                System.out.println("Loading page from url: "+url);
                jEditorPane.setContentType("text/html");
                jEditorPane.setEditable(false);
                ((HTMLEditorKit)jEditorPane.getEditorKitForContentType("text/html")).setAutoFormSubmission(false);
                String html = "<html><body><h3>Oh hai there!</h3><a href=\"http://www.microsoft.com\">Link</a><input type=\"submit\"/></body></html>";
                //jEditorPane.setPage(html);
                jEditorPane.setText(html);
                jEditorPane.add(new JButton("Hello!"));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"Got error: "+e.getMessage());
            }

            jEditorPane.addHyperlinkListener(new HyperlinkListener()
            {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                        JEditorPane pane = (JEditorPane) e.getSource();
                        if (e instanceof HTMLFrameHyperlinkEvent) {
                            HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                            HTMLDocument doc = (HTMLDocument) pane.getDocument();
                            doc.processHTMLFrameHyperlinkEvent(evt);
                        } else {
                            try {
                                pane.setPage(e.getURL());
                            } catch (Throwable t) {
                                t.printStackTrace();
                            }
                        }
                    }
                }
            });

        }
    }

    public static void main(String[] args) {
        ComponentsDialog dlg = new ComponentsDialog();
        dlg.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dlg.setVisible(true);
    }
}