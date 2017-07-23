package com.azl.common;

import com.azl.xwizard.WizardState;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name="fod.upload.wizard.state.")
public class ApplicationStateComponent implements ApplicationComponent, PersistentStateComponent {
    private WizardState wizardState;

    public ApplicationStateComponent() {
        wizardState = new WizardState();
        System.out.println("ApplicationStateComponent: constructor");
    }

    public WizardState getWizardState() {
        return wizardState;
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
        System.out.println("ApplicationStateComponent: initComponent");
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
        System.out.println("ApplicationStateComponent: disposeComponent");
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "ApplicationStateComponent";
    }

    @Nullable
    @Override
    public Object getState() {
        System.out.println("ApplicationStateComponent: getState");
        return null;
    }

    @Override
    public void loadState(Object state) {
        System.out.println("ApplicationStateComponent: loadState");
    }
}
