package com.azl.abstractwizard;

import com.azl.panels.PanelC;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.ide.wizard.Step;

import javax.swing.*;

/**
 * Created by jazl on 6/29/2017.
 */
public class StepC implements Step {
    private JPanel panel;

    @Override
    public void _init() {

    }

    @Override
    public void _commit(boolean b) throws CommitStepException {

    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public JComponent getComponent() {
        if(panel != null) return panel;

        panel = new PanelC();
        return panel;
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }
}
