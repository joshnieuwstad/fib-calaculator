package com.joshnieuwstad.fibapi.Entity;

public class Value {

    private long id;
	private int index;
    private int value;

    public Value() {}

    public Value(int index) {
        this.index = index;
    }

    public Value(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return this.index;
    }

    public int getValue() {
        return this.value;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
