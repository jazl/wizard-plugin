package com.azl.xwizard;

import com.azl.common.WizardStep;
import com.azl.panels.PanelC;
import com.azl.panels.PanelD;
import com.intellij.ide.wizard.CommitStepException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StepDx extends WizardStep {
    PanelD panel = null;

    public StepDx(@Nullable String title) {
        super(title);
    }

    @Override
    public void _init() {
        super._init();
    }

    @NotNull
    @Override
    public Object getStepId() {
        return StepIdentifiers.STEP_D;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        return null;
    }

    @Nullable
    @Override
    public Object getPreviousStepId() {
        return StepIdentifiers.STEP_C;
    }

    @Override
    public boolean isComplete() {
        return true;
    }

    @Override
    public void commit(CommitType commitType) throws CommitStepException {
    }

    @Override
    public JComponent getComponent() {
        if(panel != null) {
            return panel;
        }

        panel = new PanelD();
        return panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }
}
