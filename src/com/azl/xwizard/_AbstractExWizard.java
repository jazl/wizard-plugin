package com.azl.xwizard;

import com.azl.actions.WizardStateComponent;
import com.azl.common.WizardStep;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.ide.wizard.AbstractWizardEx;
import com.intellij.ide.wizard.AbstractWizardStepEx;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by jazl on 6/29/2017.
 */
public class _AbstractExWizard extends AbstractWizardEx implements PersistentStateComponent<WizardState> {
    public _AbstractExWizard(String title, @Nullable Project project, List<? extends AbstractWizardStepEx> steps) {
        super(title, project, steps);
        //init();
        //initValidation();
        PropertiesComponent instance = PropertiesComponent.getInstance();
        //WizardState state = new WizardState();

        //boolean b = instance.loadFields(state);
        String cntString = instance.getValue("COUNT");
        int cnt = 0;
        if(cntString != null){
            cnt = Integer.parseInt(cntString);
        }
        cnt++;
        instance.setValue("COUNT", String.valueOf(cnt));
        //state.setWizardCount(cnt);
        //instance.saveFields(state);

        WizardStateComponent component = ApplicationManager.getApplication().getComponent(WizardStateComponent.class);
        WizardState wizardState = component.getWizardState();
        System.out.println("wizardState.getWizardCount() = "+wizardState.getWizardCount());
    }

    @Override
    protected void doPreviousAction() {
        super.doPreviousAction();
    }

    @Override
    protected void doNextAction() {
        super.doNextAction();
    }

    @Override
    public void addStep(@NotNull AbstractWizardStepEx step) {
        super.addStep(step);
        ((WizardStep)step).setWizardRef(this);
        System.out.println("ADDING STEP "+step.getStepId()+" and setting wizard ref");
    }

    @Override
    protected boolean canGoNext() {
        //super.canGoNext();
        return false;
    }

    @Override
    protected boolean canFinish() {
        super.canFinish();
        return false;
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        //System.out.println("doValidate called from _AbstractExWizard");
        return super.doValidate();
    }

    @Nullable
    @Override
    public WizardState getState() {
        System.out.println("getState called!");
        return null;
    }

    @Override
    public void loadState(WizardState wizardState) {
        System.out.println("loadState called!");
    }

}
