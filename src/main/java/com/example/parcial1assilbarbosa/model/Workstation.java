package com.example.parcial1assilbarbosa.model;


public class Workstation {
    private String id;
    private String componentType;

    public Workstation(String id, String componentType) {
        this.id = id;
        this.componentType = componentType;
    }

    public String getId() {
        return id;
    }

    public String getComponentType() {
        return componentType;
    }
}
