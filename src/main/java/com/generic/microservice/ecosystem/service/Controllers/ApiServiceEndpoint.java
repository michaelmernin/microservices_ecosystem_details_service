package com.generic.microservice.ecosystem.service.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generic.microservice.ecosystem.service.Models.ApiInfo;
import com.generic.microservice.ecosystem.service.Services.ApiInfoService;
import com.generic.microservice.ecosystem.service.Services.DependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiServiceEndpoint {

    @Autowired
    DependencyService dependencyService;

    @Autowired
    ApiInfoService apiInfoService;

    @GetMapping(value = "/{apiName}/dependencies")
    public String getApiDependencyChain(@PathVariable("apiName") String apiName, @RequestParam("returnType") String returnType) throws Exception {
        switch (returnType.toLowerCase()) {
            case "object":
                return dependencyService.getAllDependenciesToJSON(apiName).toString();
            case "list":
                return new JSONArray(dependencyService.getAllDependenciesAsList(apiName)).toString();
            default:
                return "Invalid returnType param passed. Must be either object or list";
        }
    }

    @GetMapping(value = "/{apiName}/servers")
    public String getApiServerInfo(@PathVariable("apiName") String apiName) throws Exception {
        return new JSONArray(apiInfoService.getAllServersRunningApi(apiName)).toString();
    }

    @GetMapping(value = "/{apiName}")
    public String getApiInfo(@PathVariable("apiName") String apiName) throws Exception {
        ApiInfo apiInfo = apiInfoService.getApiInfoByName(apiName);
        apiInfo.setDeployed_servers(apiInfoService.getAllServersRunningApi(apiInfo.getName()));
        apiInfo.setDependencies(dependencyService.getAllDependenciesAsList(apiInfo.getName()));
        return new ObjectMapper().writeValueAsString(apiInfo);
    }


}
