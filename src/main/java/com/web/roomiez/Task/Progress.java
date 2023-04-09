package com.web.roomiez.Task;

public enum Progress {
    NOT_STARTED(0),
    IN_PROGRESS(1),
    COMPLETED(2);

    private int value;

    Progress(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
