package com.azl.xwizard;

import com.azl.panels.PanelB;
import com.azl.panels.PanelC;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.ide.wizard.CommitStepException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by jazl on 6/29/2017.
 */
public class StepCx extends AbstractWizardStepEx {

    JPanel panel = null;

    public StepCx(@Nullable String title) {
        super(title);
    }

    @NotNull
    @Override
    public Object getStepId() {
        return StepIdentifiers.STEP_C;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        return null;
    }

    @Nullable
    @Override
    public Object getPreviousStepId() {
        return StepIdentifiers.STEP_B;
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public void commit(CommitType commitType) throws CommitStepException {
        System.out.println(this.getStepId() + " commit "+commitType);
    }

    @Override
    public JComponent getComponent() {
        if(panel != null) return panel;

        panel = new PanelC();
        return panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }
}
