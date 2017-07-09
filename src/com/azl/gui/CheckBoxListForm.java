package com.azl.gui;

import javax.swing.*;

/**
 * Created by jazl on 7/9/2017.
 */
public class CheckBoxListForm {
    private JList lstPersons;
    private JPanel panel1;

    public CheckBoxListForm() {
        DefaultListModel<String> model = new DefaultListModel<>();
        model.add(1, "Sharon");
        model.add(2, "Becca");
        model.add(3, "Sarah");
        model.add(4, "Veronica");
        lstPersons.setModel(model);
    }
}
