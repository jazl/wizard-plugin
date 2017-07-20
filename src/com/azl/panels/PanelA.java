package com.azl.panels;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jazl on 6/29/2017.
 */
public class PanelA extends JPanel {
    private boolean skip = false;
    private boolean showWebview = true;
    private Project project;
    private boolean doNotGoNext = false;

    public PanelA() {
        final FileChooserDescriptor fcd = new FileChooserDescriptor(true,true,true,true,true,true);

        //add(new Label("Panel A"));
        JButton button = new JButton("Choose...");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VirtualFile[] virtualFile = FileChooser.chooseFiles(fcd,null,null);
                ConsoleView console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
                ConsoleViewContentType contentType = new ConsoleViewContentType("someName", new TextAttributes());
                console.print("oh hai sharon!", contentType);
            }
        });
        add(button);
        JTextField fileInfo = new JTextField();
        TextFieldWithBrowseButton textFieldWithBrowseButton = new TextFieldWithBrowseButton(fileInfo);
        textFieldWithBrowseButton.addBrowseFolderListener(new TextBrowseFolderListener(fcd));

        add(textFieldWithBrowseButton);
        setSize(new Dimension(500,500));
        add(textFieldWithBrowseButton);

        JCheckBox chkSkip = new JCheckBox("Skip next step");
        chkSkip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                skip = chkSkip.isSelected();
            }
        });
        add(chkSkip);

        JCheckBox chkShowWebView = new JCheckBox("Show WebView");
        chkShowWebView.setSelected(true);
        chkShowWebView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWebview = chkShowWebView.isSelected();
            }
        });
        add(chkShowWebView);

        JCheckBox chkDoNotGo = new JCheckBox("Do not go next");
        chkDoNotGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doNotGoNext = chkDoNotGo.isSelected();
            }
        });
        add(chkDoNotGo);

        setSize(new Dimension(500,500));
    }
    public void setProject(Project project) {
        this.project = project;
    }

    public boolean skipNextStep() {
        return skip;
    }

    public boolean showWebView() {
        return showWebview;
    }

    public boolean getDoNotGoNext() { return doNotGoNext;}
}
