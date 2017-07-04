package com.azl.xwizard;

import com.azl.panels.PanelA;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by jazl on 6/29/2017.
 */
public class StepAx extends AbstractWizardStepEx {

    Project project = null;
    PanelA panel = null;

    public StepAx(@Nullable String title, Project project) {
        super(title);
        this.project = project;
    }

    @NotNull
    @Override
    public Object getStepId() {
        return StepIdentifiers.STEP_A;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        return StepIdentifiers.STEP_B;
    }

    @Nullable
    @Override
    public Object getPreviousStepId() {
        return null;
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

        panel = new PanelA();
        panel.setProject(project);
        return panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }
}
