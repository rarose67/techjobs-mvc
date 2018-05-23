package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("choice", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm)
    {
        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);

            if (searchTerm.equals(""))
            {
                model.addAttribute("heading", "All Jobs");
            }
            else {
                model.addAttribute("heading", "Jobs with " + searchTerm);
            }
        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);

            if (searchTerm.equals(""))
            {
                model.addAttribute("heading", "All Jobs");
            }
            else {
                model.addAttribute("heading", "Jobs with " + searchTerm + " in the " + ListController.columnChoices.get(searchType) + " field");
            }
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("choice", searchType);
        model.addAttribute("jobs", jobs);

        return "search";
    }
}
