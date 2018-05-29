package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController extends TechJobsController {

    /**
     * Displays the home page.
     *
     * @return The name of the view.
     */
    @RequestMapping(value = "")
    public String index() {

        return "index";
    }

}
