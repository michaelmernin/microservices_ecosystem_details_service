package com.generic.microservice.ecosystem.service.Services;

import com.generic.microservice.ecosystem.service.Repositories.DependenciesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DependencyService {

    @Autowired
    DependenciesRepo dependenciesRepo;

    public List<String> getAllDependenciesAsList(String name) {
        return dependenciesRepo.getAllDependenciesAsList(name);
    }

    public Map<String, String> getAllDependenciesWithAPOC(String name) {
        return dependenciesRepo.getAllDependenciesWithAPOC(name);
    }

    public JSONObject getAllDependenciesToJSON(String name) {
        return new JSONObject(dependenciesRepo.getAllDependenciesWithAPOC(name));
    }
}
