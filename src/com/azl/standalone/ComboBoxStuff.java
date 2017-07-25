package com.azl.standalone;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jazl on 7/22/2017.
 */
public class ComboBoxStuff extends DialogWrapper {
    JTextField textField;
    JComboBox comboBox;

    public ComboBoxStuff(@Nullable Project project) {
        super(project);
        setTitle("ComboBox stuff");
        init();
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
        String[] employees = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        comboBox = new JComboBox(employees);
        comboBox.setSelectedItem("sharon");
        comboBox.setEditable(true);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //comboBox.addItem(comboBox.getSelectedItem());
                comboBox.insertItemAt(comboBox.getSelectedItem(), 0);
            }
        });

        panel.add(new JLabel("ComboBox: "));
        panel.add(comboBox);
        panel.add(btnAdd);
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
                ComboBoxStuff dialog = new ComboBoxStuff(null);
                dialog.show();
            }
        });
    }
}
