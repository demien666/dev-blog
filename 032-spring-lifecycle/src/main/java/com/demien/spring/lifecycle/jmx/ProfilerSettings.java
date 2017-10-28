package com.demien.spring.lifecycle.jmx;

public class ProfilerSettings implements ProfilerSettingsMBean {

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
