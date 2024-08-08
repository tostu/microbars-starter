package com.example.adapter.active.web;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class WebHomeController {

    @Get
    @View("home")
    Map<String, Object> index(@Nullable Principal principal) {
        Map<String, Object> data = new HashMap<>();
        data.put("loggedIn", principal != null);
        if (principal != null) {
            data.put("username", principal.getName());
        }
        return data;
    }

}
