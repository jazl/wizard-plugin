package com.azl.gui;

import com.azl.common.Result;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Window;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.util.regex.Pattern;

public class SSOLoginDialog {
    private IntelliJSSOLoginDialog dialog;
    private final Window window;
    private String ssoUrl;
    final JFXPanel jfxPanel = new JFXPanel();
    WebView webView;
    WebEngine webEngine;

    private final String SAML_REQUEST_SUFFIX_OLD = "";
    private final String SAML_REQUEST_SUFFIX = "";

    public SSOLoginDialog(Window window, String ssoUrl, String fodUrl) {
        //super(ssoUrl, fodUrl);
        this.window = window;
        this.ssoUrl = ssoUrl;
    }

    //@Override
    public Result open() {
        ApplicationManager.getApplication().invokeAndWait(new Runnable() {
            @Override
            public void run() {
                dialog = new IntelliJSSOLoginDialog(null);
                dialog.show();
            }
        }, ModalityState.defaultModalityState());
        return dialog.isOK() ? Result.OK : Result.CANCEL;
    }

    public String getSamlToken() {
        return "xxx";
    }

    private void setupWebContent(JFXPanel panel) {
        webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.setJavaScriptEnabled(true);
        CookieHandler.setDefault(new CookieManager());

        if ((ssoUrl != null) && (ssoUrl.length() > 0)) {
            Pattern oldSSORegEx = Pattern.compile("/SSO/IdPRedirect\\?token=[^&/]+"); //$NON-NLS-1$
            boolean oldFormat = oldSSORegEx.matcher(ssoUrl).find();
            Pattern newSSORegEx = Pattern.compile("/SSO/Login/[^&/]+"); //$NON-NLS-1$
            boolean newFormat = newSSORegEx.matcher(ssoUrl).find();
            String url;
            if (oldFormat && !ssoUrl.endsWith(SAML_REQUEST_SUFFIX_OLD)) {
                url = ssoUrl + SAML_REQUEST_SUFFIX_OLD;
            } else if (newFormat && !ssoUrl.endsWith(SAML_REQUEST_SUFFIX)) {
                url = ssoUrl + SAML_REQUEST_SUFFIX;
            } else {
                url = ssoUrl;
            }
            webEngine.load(url);
        }
        else {
            handleError("Please provide a valid SSO URL");
        }

        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                System.out.println("observable.getValue() = "+observable.getValue());
                if(observable.getValue() == Worker.State.SUCCEEDED) {
                    handleDocumentLoaded();
                }
                else if(observable.getValue() == Worker.State.FAILED){
                    handleError("Unable to load page from URL "+webEngine.getLocation());
                }
            }
        });

        panel.setScene(new Scene(webView));
        panel.requestFocus();
    }

    private void handleDocumentLoaded() {
        Document document = webEngine.getDocument();
        String currentUrl = webEngine.getLocation();
        try {
//            if (isSamlAssertionResponsePage(new URI(fodUrl), currentUrl) || isSamlAssertionResponsePage(new URI(ssoUrl), currentUrl)) {
//                samlToken = getTokenFromDocument(document);
//                if(dialogListener != null){
//                    dialogListener.closeDialog();
//                }
//            }
        } catch (Exception e) {
            handleError("Error occurred while processing response. URL: "+currentUrl);
        }
    }

    private void handleError(String message) {
        final String messageDiv = "{message}";
        final String template = "<html>\n" +
                "<body>\n" +
                "<div style=\"background-color:black;color:white;padding:15px 15px;font-weight:bold;\">\n" +
                "ERROR\n" +
                "</div>\n" +
                "<div style=\"padding:5px 5px;\">"+messageDiv+"</div>\n" +
                "</body>\n" +
                "</html>";

        webEngine.loadContent(template.replace(messageDiv,message));
    }

    private String getTokenFromDocument(Document document) {
        String tokenText = null;
        NodeList nodeList = document.getElementsByTagName("pre");
        if(nodeList.getLength()==1) {
            tokenText = nodeList.item(0).getTextContent();
        }
        return tokenText;
    }

//    @Override
    public void closeDialog() {
        SwingUtilities.invokeLater(new Runnable() {
               @Override
               public void run() {
                   dialog.close(DialogWrapper.OK_EXIT_CODE);
               }
           }
        );
    }

    private class IntelliJSSOLoginDialog extends DialogWrapper {
        final JFXPanel jfxPanel = new JFXPanel();
        //DialogListener dialogListener;

        public IntelliJSSOLoginDialog(@Nullable Project project) {
            super(project);
            setTitle("SSO");
            Platform.setImplicitExit(false);
            //this.dialogListener = dialogListener;
            init();
        }

        @Nullable
        @Override
        protected JComponent createCenterPanel() {

            jfxPanel.setPreferredSize(new Dimension(300,300));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    setupWebContent(jfxPanel);
                }
            });
            return jfxPanel;
        }

        @NotNull
        @Override
        protected Action[] createActions() {
            // Get rid of default dialog buttons
            return new Action[]{};
        }
    }
}