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
@RequestMapping(value = "list")
public class ListController extends TechJobsController {

    /**
     * Displays a view that contains the list of fields which jobs can be categorized by.
     *
     * @return The name of the view.
     */
    @RequestMapping(value = "")
    public String list() {

        return "list";
    }

    /**
     * Displays the values in a given column, or a list of job if the column field is 'all'.
     *
     * @param model   Model used for passing data to the form.
     * @param column  The column whose values are to be listed.
     * @return The name of the view.
     */
    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam String column) {

        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
            model.addAttribute("jobs", jobs);
            return "list-jobs";
        } else {
            ArrayList<String> items = JobData.findAll(column);
            model.addAttribute("title", "All " + TechJobsController.getColumnChoices().get(column) + " Values");
            model.addAttribute("column", column);
            model.addAttribute("items", items);
            return "list-column";
        }

    }

    /**
     * Displays the job(s) matching a given value in a given column.
     *
     * @param model   Model used for passing data to the form.
     * @param column  The column containing value
     * @param value   The value to match against the list of jobs.
     * @return The name of the view.
     */
    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model,
            @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
        model.addAttribute("title", "Jobs with " + TechJobsController.getColumnChoices().get(column) + ": " + value);
        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}
