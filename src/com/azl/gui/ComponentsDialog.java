package com.azl.gui;

import com.azl.custom.FileTypesDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import oracle.jrockit.jfr.JFR;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
                d.setVisible(true);
            }
        });

        add(panel);
    }

    private class WebViewDialog extends DialogWrapper {

        protected WebViewDialog(@Nullable Project project, boolean canBeParent) {
            super(project, canBeParent);
            //setSize(500,500);
            init();
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {
            final JFXPanel jfxPanel = new JFXPanel();
            //jfxPanel.setSize(new Dimension(300,300));
            jfxPanel.setPreferredSize(new Dimension(300,450));
            final String content = "<html><body><h1>Hello</h1></body></html>";

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    WebView webView = new WebView();
                    jfxPanel.setScene(new Scene(webView));
                    webView.getEngine().loadContent(content);
                }
            });

            pack();
            return jfxPanel;
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

        public JEditorPaneDialog(Frame owner, String title) {
            super(owner, title);

            setSize(500, 500);

            JEditorPane jEditorPane = new JEditorPane();
            jEditorPane.setEditable(false);

            //JTextPane textPane = new JTextPane();

            try {
                jEditorPane.setPage(url);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,"Got error: "+e.getMessage());
            }
            jEditorPane.setContentType("text/html");
            jEditorPane.setEditable(false);
            ((HTMLEditorKit)jEditorPane.getEditorKitForContentType("text/html")).setAutoFormSubmission(false);
            jEditorPane.addHyperlinkListener(new HyperlinkListener()
            {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e)
                {
                    // this method will be called when user hits submit button on the form
                }
            });

            //jEditorPane.add(textPane);
            add(jEditorPane);
        }
    }

    public static void main(String[] args) {
        ComponentsDialog dlg = new ComponentsDialog();
        dlg.setVisible(true);
    }
}