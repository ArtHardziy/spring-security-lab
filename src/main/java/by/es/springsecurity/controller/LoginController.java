package by.es.springsecurity.controller;

import by.es.springsecurity.model.dto.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @GetMapping("/login")
    public ModelAndView getLoginPage(Model model) {
        var loginModelAndView = new ModelAndView("login");
        loginModelAndView.addObject("loginForm", new LoginForm());
        return loginModelAndView;
    }

    @GetMapping("/success-login")
    public ModelAndView getSuccessLoginPage(Model model) {
        var successLoginPage = new ModelAndView("success-login");
        return successLoginPage;
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        return "home";
    }
}
