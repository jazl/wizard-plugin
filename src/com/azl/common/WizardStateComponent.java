package com.azl.common;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name="fod.upload.wizard.state.")
public class WizardStateComponent implements ProjectComponent, PersistentStateComponent {
    private WizardState wizardState;
    public WizardStateComponent(Project project) {
        System.out.println("WizardStateComponent: constructor");
        wizardState = new WizardState();
    }

    public WizardState getWizardState(){
        return wizardState;
    };

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
        System.out.println("WizardStateComponent: initComponent");
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
        System.out.println("WizardStateComponent: disposeComponent");
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "WizardStateComponent";
    }

    @Override
    public void projectOpened() {
        // called when project is opened
        System.out.println("WizardStateComponent: projectOpened");
    }

    @Override
    public void projectClosed() {
        // called when project is being closed
        System.out.println("WizardStateComponent: projectClosed");
    }

    @Nullable
    @Override
    public Object getState() {
        System.out.println("WizardStateComponent: getState");
        return null;
    }

    @Override
    public void loadState(Object o) {
        System.out.println("WizardStateComponent: loadState");
    }
}
