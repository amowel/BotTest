package com.weblab.configuration.vk;

public enum VkDisplay {
    POPUP("popup"),
    PAGE("page"),
    MOBILE("mobile");

    private String value;

    VkDisplay(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

