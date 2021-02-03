package com.generic.microservice.ecosystem.service.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generic.microservice.ecosystem.service.Models.ServerInfo;
import com.generic.microservice.ecosystem.service.Services.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/server")
public class ServerServiceEndpoint {

    @Autowired
    ServerInfoService serverInfoService;

    @GetMapping(value = "/{serverName}/deployedApis")
    public String getServerInfo(@PathVariable("serverName") String serverName, @RequestParam("returnType") String returnType) throws Exception {
        switch (returnType.toLowerCase()) {
            case "object":
                return serverInfoService.getAllApisDeployedToServerAsJSON(serverName).toString();
            case "list":
                return new JSONArray(serverInfoService.getAllApisDeployedToServerAsList(serverName)).toString();
            default:
                return "Invalid returnType param passed. Must be either object or list";
        }
    }

    @GetMapping(value = "/{serverName}")
    public String getServerInfo(@PathVariable("serverName") String serverName) throws Exception {
        ServerInfo serverInfo = serverInfoService.getServerInfoByServerName(serverName);
        serverInfo.setDeployed_services(serverInfoService.getAllApisDeployedToServerAsList(serverInfo.getName()));
        return new ObjectMapper().writeValueAsString(serverInfo);
    }
}
