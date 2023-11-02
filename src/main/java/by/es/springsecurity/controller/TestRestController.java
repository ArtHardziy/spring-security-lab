package by.es.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestRestController {

    @GetMapping("/info")
    public String getInfoAboutService() {
        return """
                {
                    "description": "That service provides some endpoints",
                    "endpoints": [
                        {
                            "endpoint": "/api/v1/info",
                            "method": "GET",
                            "description": "return list of available endpoints",
                            "requirement": null
                        },
                        {
                            "endpoint": "/api/v1/developers",
                            "method": "GET",
                            "description": "return list of developer",
                            "requirement": "require authorization as user"
                        },
                        {
                            "endpoint": "/api/v1/developers/{id}",
                            "method": "GET",
                            "description": "return developer if exist",
                            "requirement": "require authorization as user"
                        },
                        {
                            "endpoint": "/api/v1/developers",
                            "method": "POST",
                            "description": "accept json body represented developer to create single developer",
                            "requirement": "require authorization as manager or admin"
                        },
                        {
                            "endpoint": "/api/v1/developers/{id}",
                            "method": "DELETE",
                            "description": "delete developer with defined {id}",
                            "requirement": "require authorization as admin"
                        },
                        {
                            "endpoint": "/api/v1/signin",
                            "method": "POST",
                            "description": "method to get creds",
                            "requirement": null
                        },
                        {
                            "endpoint": "/api/v1/signup",
                            "method": "POST",
                            "description": "method to signup",
                            "requirement": null
                        }
                    ]
                }
                """;
    }

}
