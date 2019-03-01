package utils;

import input.g_input.CreatePythonScript;

import java.io.Serializable;

public class Information implements Serializable {
    private CreatePythonScript py;
    private String projDir;

    public String getProjDir() {
        return projDir;
    }

    private Information(){
    }

    public static Information getInstance(String projDir){
        var info =  new Information();
        info.projDir = projDir;
        return info;
    }
    public CreatePythonScript getPy() {
        return py;
    }

    public void setPy(CreatePythonScript py) {
        this.py = py;
    }
}
