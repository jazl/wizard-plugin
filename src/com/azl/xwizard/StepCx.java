package com.azl.xwizard;

import com.azl.common.WizardStep;
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
public class StepCx extends WizardStep {

    PanelC panel = null;

    public StepCx(@Nullable String title) {
        super(title);
    }

    @Override
    public void _init() {
        super._init();
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
        Integer cnt = sharedObject.getCounter();
        System.out.println("StepCx: sharedObject.getCounter() = "+cnt);
        sharedObject.setCounter(++cnt);
        sharedObject.setMessage("Last step: "+getStepId());
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("DISPOSING STEPC!");
    }

    @Override
    public JComponent getComponent() {
        if(panel != null) {
            panel.setLabelText(sharedObject.getMessage() + ", counter "+sharedObject.getCounter());
            return panel;
        }

        panel = new PanelC();
        return panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }
}
