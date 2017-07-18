package com.azl.panels;

import com.azl.common.SharedObject;
import com.azl.common.WizardStep;
import com.intellij.ide.wizard.AbstractWizardStepEx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jazl on 6/29/2017.
 */
public class PanelB extends JPanel {
    private SharedObject _sharedObject;
    private WizardStep wizardStep;

    public PanelB(SharedObject sharedObject) {
        this._sharedObject = sharedObject;
        setLayout(new FlowLayout());
        add(new Label("Panel B"));
        if(_sharedObject != null) {
            add(new Label("SharedObject.counter = "+_sharedObject.getCounter()));
            add(new Label("SharedObject.message = "+_sharedObject.getMessage()));
        }
        else{
            add(new Label("No SharedObject!"));
        }
        add(new Label("Panel B"));
        add(new JTextArea());
        add(new JButton("Press Me!"));
        add(new JButton("Me Too!"));
        JButton btnGoPrev = new JButton("Go Prev");

        btnGoPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wizardStep.goPrevious();
            }
        });
        add(btnGoPrev);

        setSize(new Dimension(500,500));
    }

    public void setWizardStepRef(WizardStep step) {
        this.wizardStep = step;
    }
}
