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
public class SearchController extends TechJobsController {

    /**
     * Displays the search form.
     *
     * @param model   Model used for passing data to the form.
     * @return The name of the view.
     */
    @RequestMapping(value = "")
    public String search(Model model) {

        //send the search column to the view so that it is retained when the results are displayed.
        model.addAttribute("choice", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    /**
     * Displays the results of a search below the search form.
     *
     * The search type from the previous is retained by form.
     *
     * @param model   Model used for passing data to the form.
     * @param searchType The column that was searched previously.
     * @param searchTerm The term to search for.
     * @return The name of the view.
     */
    @RequestMapping(value = "results")
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm)
    {
        ArrayList<HashMap<String, String>> jobs;

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);

            //If the search term is left blank, display all jobs.
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

            //If the search term is left blank, display all jobs.
            if (searchTerm.equals(""))
            {
                model.addAttribute("heading", "All Jobs");
            }
            else {
                model.addAttribute("heading", "Jobs with " + searchTerm + " in the " + TechJobsController.getColumnChoices().get(searchType) + " field");
            }
        }

        //send the search column to the view so that it is retained when the results are displayed.
        model.addAttribute("choice", searchType);
        model.addAttribute("jobs", jobs);

        return "search";
    }
}
