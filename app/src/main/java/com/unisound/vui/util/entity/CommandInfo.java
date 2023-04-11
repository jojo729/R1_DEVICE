package com.unisound.vui.util.entity;

public class CommandInfo {
    private String command;
    private String operands;
    private String operator;
    private String value;

    public CommandInfo() {
    }

    public CommandInfo(String mCommand, String mOperands, String mOperator, String mValue) {
        this.command = mCommand;
        this.operands = mOperands;
        this.operator = mOperator;
        this.value = mValue;
    }

    public String getCommand() {
        return this.command;
    }

    public String getOperands() {
        return this.operands;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getValue() {
        return this.value;
    }

    public void setCommand(String command2) {
        this.command = command2;
    }

    public void setOperands(String operands2) {
        this.operands = operands2;
    }

    public void setOperator(String operator2) {
        this.operator = operator2;
    }

    public void setValue(String value2) {
        this.value = value2;
    }
}
