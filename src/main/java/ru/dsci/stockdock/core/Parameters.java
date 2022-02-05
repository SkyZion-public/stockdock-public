package ru.dsci.stockdock.core;

import lombok.Data;

@Data
public class Parameters {

    private final static int ARGUMENTS_NUMBER = 2;
    private String token;
    private boolean sandBoxMode;

    public Parameters(String[] args) {
        if (args.length < 2)
            throw new IllegalArgumentException(String.format(
                    "Invalid number of arguments [%d], expected [%d]",
                    args.length, ARGUMENTS_NUMBER));
        setParameters(args[0], Boolean.parseBoolean(args[1]));
    }

    public Parameters(String token, boolean sandBoxMode) {
        setParameters(token, sandBoxMode);
    }

    private void setParameters(String token, boolean sandBoxMode) {
        this.token = token;
        this.sandBoxMode = sandBoxMode;
    }

    @Override
    public final String toString() {
        return String.format("Parameters: sandBoxMode = %s", sandBoxMode ? "true" : "false");
    }

}
