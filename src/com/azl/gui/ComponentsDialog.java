package com.azl.gui;

import com.azl.custom.FileTypesDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ComponentsDialog extends DialogWrapper {
    private Project project;
    public ComponentsDialog(@Nullable Project project, boolean canBeParent) {
        super(project, canBeParent);
        this.project = project;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500,600));

        FlowLayout layout = new FlowLayout();

        JButton btnShowPicker = new JButton("Show File Types Picker...");
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

        panel.setLayout(layout);
        return panel;
    }
}