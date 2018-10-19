/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.model;

public final class Variable<T> implements WithVisibility, Typed, Named, Valued<T> {
    private Visibility visibility;
    private Type type;
    private String name;
    private T value;
    private VariableType variableType;

    private Variable(Visibility visibility, Type type, String name, T value, VariableType variableType) {
        this.visibility = visibility;
        this.type = type;
        this.name = name;
        this.variableType = variableType;
        this.value = value;
    }

    public static <T> Variable<T> buildVar(Visibility visibility, Type type, String name, T value) {
        return new Variable<>(visibility, type, name, value, VariableType.VAR);
    }

    public static <T> Variable<T> buildVal(Visibility visibility, Type type, String name, T value) {
        return new Variable<>(visibility, type, name, value, VariableType.VAL);
    }

    public static <T> Variable<T> buildParam(Type type, String name) {
        return new Variable<>(null, type, name, null, VariableType.PARAM);
    }

    public static <T> Variable<T> buildParamWithValue(Type type, String name, T value) {
        return new Variable<>(null, type, name, value, VariableType.PARAM);
    }

    @Override
    public Visibility getVisibility() {
        return visibility;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    enum VariableType {
        VAR, VAL, PARAM
    }
}
