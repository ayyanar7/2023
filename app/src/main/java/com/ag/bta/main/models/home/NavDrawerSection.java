package com.ag.bta.main.models.home;

import java.util.ArrayList;

public class NavDrawerSection {
    ArrayList<Option> optionsList = null;
    public NavDrawerSection(){
        optionsList =  new ArrayList<Option>();
    }

    public ArrayList<Option> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(ArrayList<Option> optionsList) {
        this.optionsList = optionsList;
    }

    public void addOption(Option option){
        optionsList.add(option);
    }
}
