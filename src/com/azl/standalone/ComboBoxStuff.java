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

        String[] employees = { "Susan", "Heidi", "SharonG" };
        String[] pfemployees = { "Veronica", "Tina" };

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(employees);

        comboBox = new JComboBox(pfemployees);
        comboBox.setEditable(true);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //comboBox.addItem(comboBox.getSelectedItem());
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel) comboBox.getModel();
                int pos = model.getIndexOf(comboBox.getSelectedItem());
                if(pos == -1){
                    comboBox.insertItemAt(comboBox.getSelectedItem(), 0);
                }
                if(model != null) {
                    System.out.println("Content of model");
                    for(int i=0; i<model.getSize(); i++) {
                        System.out.println(i+" = "+model.getElementAt(i));
                    }
                }
                else {
                    System.out.println("MODEL IS NULL!!!");
                }
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
