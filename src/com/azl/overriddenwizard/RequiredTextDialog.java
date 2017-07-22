package com.azl.overriddenwizard;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jazl on 7/22/2017.
 */
public class RequiredTextDialog extends DialogWrapper {
    JTextField textField;

    protected RequiredTextDialog(@Nullable Project project) {
        super(project);
        setTitle("Required Text Dialog");
        init();
        //initValidation();

    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        setOKActionEnabled(textField.getText().trim().length() > 1);
        return null;
    }

    @Override
    public void setValidationDelay(int delay) {
        super.setValidationDelay(1);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(0,100));
        textField = new JTextField(50);
        panel.add(new JLabel("Required Text Field: "));
        panel.add(textField);
        return panel;
    }

    @Override
    public void setOKActionEnabled(boolean isEnabled) {
        super.setOKActionEnabled(textField.getText().trim().length() > 0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RequiredTextDialog dialog = new RequiredTextDialog(null);
                dialog.show();
            }
        });
    }
}
