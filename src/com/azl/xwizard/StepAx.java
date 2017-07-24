package com.azl.xwizard;

import com.azl.common.ApplicationStateComponent;
import com.azl.common.SharedObject;
import com.azl.common.WizardStep;
import com.azl.gui.WebViewDialog;
import com.azl.panels.PanelA;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.ide.wizard.CommitStepException;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Date;

/**
 * Created by jazl on 6/29/2017.
 */
public class StepAx extends WizardStep {

    Project project = null;
    PanelA panel = null;

    public StepAx(@Nullable String title, Project project) {
        super(title);
        this.project = project;
    }

    @Override
    public void _init() {
        super._init();
    }

    @NotNull
    @Override
    public Object getStepId() {
        return StepIdentifiers.STEP_A;
    }

    @Nullable
    @Override
    public Object getNextStepId() {
        if(panel.skipNextStep()) {
            return StepIdentifiers.STEP_C;
        }
        if(panel.getDoNotGoNext()) {
            return getStepId();
        }
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
        if(sharedObject == null) {
            sharedObject = new SharedObject();
        }
        System.out.println(this.getStepId() + " commit "+commitType);
        Integer cnt = sharedObject.getCounter();
        System.out.println("StepAx: sharedObject.getCounter() = "+cnt);
        sharedObject.setCounter(++cnt);
        sharedObject.setMessage("Last step: "+getStepId());
        if (panel.showWebView()) {
            new WebViewDialog(null, null).show();
            //throw new CommitStepException("WebView is stopping wizard from moving next");
        }
        if(panel.getDoNotGoNext() == true) {
            //throw new CommitStepException("Not going next");
        }
        project.getComponent(ApplicationStateComponent.class).addLogEntry("StepAx commited on "+new Date());
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
