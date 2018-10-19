/*
 * Copyright 2018 SimpleJ's author : Frédéric Montariol. Use of this source code is governed by the Apache 2.0 license.
 */

package org.simplej.model;

import java.util.List;

public final class Function implements WithVisibility, Typed, Named, WithVariables, Block {
    private Visibility visibility;
    private Type type;
    private String name;
    private boolean isAbstract;
    private List<Variable> variables;
    private List<Instruction> instructions;

    private Function(Visibility visibility, Type type, String name, List<Variable> variables, boolean isAbstract, List<Instruction> instructions) {
        this.visibility = visibility;
        this.type = type;
        this.name = name;
        this.variables = variables;
        this.isAbstract = isAbstract;
        this.instructions = instructions;
    }

    public static Function buildAbstractFunction(Visibility visibility, Type type, String name, List<Variable> variables) {
        return new Function(visibility, type, name, variables, true, null);
    }

    public static Function buildFunction(Visibility visibility, Type type, String name, List<Variable> variables, List<Instruction> instructions) {
        return new Function(visibility, type, name, variables, false, instructions);
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

    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public List<Variable> getVariables() {
        return variables;
    }

    @Override
    public List<Instruction> getInstructions() {
        return instructions;
    }
}
