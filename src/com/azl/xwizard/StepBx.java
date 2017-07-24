package com.azl.xwizard;

import com.azl.common.ApplicationStateComponent;
import com.azl.common.WizardStep;
import com.azl.panels.PanelB;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by jazl on 6/29/2017.
 */
public class StepBx extends WizardStep {

    private Project project;
    private PanelB panel = null;
    private boolean completeFlag = false;
    private boolean throwExceptionFlag = false;

    public StepBx(@Nullable String title, Project project) {
        super(title);

        System.out.println("In StepBx constructor");
        this.project = project;

        addStepListener(new Listener() {
            @Override
            public void doNextAction() {
                System.out.println("Doing next action");
            }

            @Override
            public void stateChanged() {
                System.out.println("State has changed!");
            }
        });
    }

    @Override
    public void goPrevious() {
        ((_AbstractExWizard)wizard).doPreviousAction();
    }

    @Override
    public void _init() {
        super._init();
    }

    @NotNull
    @Override
    public Object getStepId() {
        return StepIdentifiers.STEP_B;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        return StepIdentifiers.STEP_C;
    }

    @Nullable
    @Override
    public Object getPreviousStepId() {
        return StepIdentifiers.STEP_A;
    }

    @Override
    public boolean isComplete() {
        return completeFlag;
    }

    @Override
    public void commit(CommitType commitType) throws CommitStepException {
        System.out.println(this.getStepId() + " commit "+commitType);
        Integer cnt = sharedObject.getCounter();
        System.out.println("StepBx: sharedObject.getCounter() = "+cnt);
        sharedObject.setCounter(++cnt);
        sharedObject.setMessage("Last step: "+getStepId());
        if(throwExceptionFlag) {
            throw new CommitStepException("Stop it!");
        }
        project.getComponent(ApplicationStateComponent.class).addLogEntry("StepBx did a commit on with commitType "+commitType+" on "+new Date());
    }

    @Override
    public JComponent getComponent() {

        System.out.println("StepBx getting component...");

        if(panel != null) return panel;

        panel = new PanelB(sharedObject);
        panel.setWizardStepRef(this);

        JCheckBox checkbox = new JCheckBox("Check me!");
        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeFlag = checkbox.isSelected();
                //throwExceptionFlag = checkbox.isSelected();
                fireStateChanged();

                //fireGoNext();
            }
        });
        panel.add(checkbox);
        return panel;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return null;
    }

}
