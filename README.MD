# generic_microservice_ecosystem_details_service

## Highlevel overview 
This service provides a RESTFul service that exposes an organization's microservice ecosystem details: server info, api info, etc... for system insights and api management purposes.   

Service has been implemented in Spring Boot and a GraphDB (Neo4J) and should provide a few crude examples on how to leverage a graphDB when modeling an api ecosystem.  Please check out the source code to see examples on Neo4J's cypher queries and Neo4J's built-in procedures (APOC) to see just how powerful and easy to use they are in order to traverse a complex ecosystem full of rich relationships.  
> You may find the more Neo4J specific operations in the /Repositories package

# Supported GET methods  
> See "Example Requests" section below for example requests and responses for each command
##### For info on apis
- /api/{apiName}
- /api/{apiName}/dependencies?returnType= OBJECT or LIST
- /api/{apiName}/servers

##### For info on servers
- /server/{serverName}
- /server/{serverName}/deployedApis?returnType= OBJECT or LIST


# Example Requests   
## For APIS:
##### /api/{apiName}  
url: http://127.0.0.1:8080/api/api-name-v1  

response:  
```
{
    "name": "api-name-v1",
    "serviceName": "api-name-v1",
    "deployed_servers": [
        "server-name-03",
        "server-name-02",
        "server-name-01"
    ],
    "dependencies": [
        "dependency-api-1",
        "dependency-api-2",
        "dependency-api-3",
        "dependency-api-4",
        "dependency-api-5",
        "dependency-api-6",
        "dependency-api-7"
    ]
}
```  

##### /api/{apiName}/servers
url: http://127.0.0.1:8080/api/api-name-v1/servers  

response:  
```
[
    "server-name-03",
    "server-name-02",
    "server-name-01"
]
```  

##### /api/{apiName}/dependencies (returnType = list)
url: http://127.0.0.1:8080/api/api-name-v1/dependencies?returnType=list  

response:  
```
[
    "dependency-api-1",
    "dependency-api-2",
    "dependency-api-3",
    "dependency-api-4",
    "dependency-api-5",
    "dependency-api-6",
    "dependency-api-7"
]
```  

##### /api/{apiName}/dependencies (returnType = object)  
> returnType object will return all path details (nodes, relationships, all properties, etc)  

url: http://127.0.0.1:8080/api/api-name-v1/dependencies?returnType=object  

response:  
```
{
    "_type": "Api",
    "name": "api-name-v1",
    "has_dependency": [
        {
            "_type": "Api",
            "name": "dependency-api-1",
            "has_dependency": [
                {
                    "_type": "Api",
                    "name": "dependency-api-2",
                    "_id": 42,
                    "service_name": "dependency-api-2"
                }
            ],
            "_id": 22,
            "service_name": "dependency-api-1"
        },
        {
            "_type": "Api",
            "name": "dependency-api-3",
            "has_dependency": [
                {
                    "_type": "Api",
                    "name": "dependency-api-1",
                    "has_dependency": [
                        {
                            "_type": "Api",
                            "name": "dependency-api-2",
                            "_id": 42,
                            "service_name": "dependency-api-2"
                        }
                    ],
                    "_id": 22,
                    "service_name": "dependency-api-1"
                },
                {
                    "_type": "Api",
                    "name": "dependency-api-4",
                    "_id": 27,
                    "service_name": "dependency-api-4"
                },
                {
                    "_type": "Api",
                    "name": "dependency-api-5",
                    "_id": 7,
                    "service_name": "dependency-api-5"
                }
            ],
            "_id": 21,
            "service_name": "dependency-api-3"
        },
        {
            "_type": "Api",
            "name": "dependency-api-6",
            "has_dependency": [
                {
                    "_type": "Api",
                    "name": "dependency-api-1",
                    "has_dependency": [
                        {
                            "_type": "Api",
                            "name": "dependency-api-2",
                            "_id": 42,
                            "service_name": "dependency-api-2"
                        }
                    ],
                    "_id": 22,
                    "service_name": "dependency-api-1"
                },
                {
                    "_type": "Api",
                    "name": "dependency-api-4",
                    "_id": 27,
                    "service_name": "dependency-api-4"
                },
                {
                    "_type": "Api",
                    "name": "dependency-api-5",
                    "_id": 7,
                    "service_name": "dependency-api-5"
                }
            ],
            "_id": 40,
            "service_name": "dependency-api-6"
        },
        {
            "_type": "Api",
            "name": "dependency-api-7",
            "has_dependency": [
                {
                    "_type": "Api",
                    "name": "dependency-api-1",
                    "has_dependency": [
                        {
                            "_type": "Api",
                            "name": "dependency-api-2",
                            "_id": 42,
                            "service_name": "dependency-api-2"
                        }
                    ],
                    "_id": 22,
                    "service_name": "dependency-api-1"
                },
                {
                    "_type": "Api",
                    "name": "dependency-api-4",
                    "_id": 27,
                    "service_name": "dependency-api-4"
                },
                {
                    "_type": "Api",
                    "name": "dependency-api-5",
                    "_id": 7,
                    "service_name": "dependency-api-5"
                }
            ],
            "_id": 41,
            "service_name": "dependency-api-7"
        }
    ],
    "_id": 0,
    "service_name": "api-name-v1"
}
```  

## For SERVERS:  
##### /server/{serverName}
url: http://127.0.0.1:8080/server/server-name-07  

response:  
```
{
    "name": "server-name-07",
    "osType": "ubuntu",
    "sshPort": "2222",
    "deployed_services": [
        "dependency-api-4",
        "dependency-api-5",
        "dependency-api-7",
        "dependency-api-6",
        "dependency-api-3",
        "dependency-api-1"
    ]
}
```   

##### /server/{serverName}/deployedApis (returnType = object)  
> returnType object will return all path details (nodes, relationships, all properties, etc)  

url: http://127.0.0.1:8080/server/server-name-07/deployedApis?returnType=object  

response:  
```
{
    "ssh_port": "2222",
    "os_type": "ubuntu",
    "_type": "Server",
    "name": "server-name-07",
    "_id": 25,
    "deployed_to": [
        {
            "_type": "Api",
            "name": "dependency-api-4",
            "_id": 27,
            "service_name": "dependency-api-4"
        },
        {
            "_type": "Api",
            "name": "dependency-api-5",
            "_id": 7,
            "service_name": "dependency-api-5"
        },
        {
            "_type": "Api",
            "name": "dependency-api-7",
            "_id": 41,
            "service_name": "dependency-api-7"
        },
        {
            "_type": "Api",
            "name": "dependency-api-6",
            "_id": 40,
            "service_name": "dependency-api-6"
        },
        {
            "_type": "Api",
            "name": "dependency-api-3",
            "_id": 21,
            "service_name": "dependency-api-3"
        },
        {
            "_type": "Api",
            "name": "dependency-api-1",
            "_id": 22,
            "service_name": "dependency-api-1"
        }
    ]
}
```   

##### /server/{serverName}/deployedApis (returnType = list)  
url: http://127.0.0.1:8080/server/server-name-07/deployedApis?returnType=list  

response:  
```
[
    "dependency-api-4",
    "dependency-api-5",
    "dependency-api-7",
    "dependency-api-6",
    "dependency-api-3",
    "dependency-api-1"
]
```   

## Technical components

1. Spring Boot
2. Neo4j (graphDb)


## Project and environment set-up (for collaborators)
#### Necessary components:  
- Java8 (AdoptOpenJDK)
- Neo4J (database)
- Cloned project repo

#### Download links  
- Java8: https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=hotspot
- Neo4J: https://neo4j.com/download-center/


## Configure Project and Mock Environment
#### Clone project  
```sh
$ git clone <github link>
```



