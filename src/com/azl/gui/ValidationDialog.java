package com.azl.gui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ValidationDialog extends DialogWrapper {
    private JTextField txtFirstName;

    public ValidationDialog(@Nullable Project project) {
        super(project);
        setTitle("Validation");
        setSize(300,300);
        init();
        initValidation();
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if(txtFirstName.getText().isEmpty()) {
            return new ValidationInfo("First name is required!", txtFirstName);
        }
        return null;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("First Name"));
        txtFirstName = new JTextField(25);
        panel.add(txtFirstName);
        return panel;
    }

}
