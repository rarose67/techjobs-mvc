package org.launchcode.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;

public class TechJobsController {

    //List of actions that that can be taken from the home page or navbar.
    static HashMap<String, String> actionChoices = new HashMap<>();

    //List of columns to be passed to views.
    static HashMap<String, String> columnChoices = new HashMap<>();

    /**
     * Adds entries to the actionChoices and columnChoices HashMaps.
     */
    public TechJobsController()
    {
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");
    }

    /**
     * Called by view thanks to the @ModelAttribute("columns") annotation to pull in the list of column choices.
     *
     * @return The HashMap containing the list of column choices
     */
    @ModelAttribute("columns")
    public static HashMap<String, String> getColumnChoices() {
        return columnChoices;
    }

    /**
     * Called by view thanks to the @ModelAttribute("actions") annotation to pull in the list of action choices.
     *
     * @return The HashMap containing the list of actions choices
     */
    @ModelAttribute("actions")
    public static HashMap<String, String> getActionChoices() {
        return actionChoices;
    }
}
