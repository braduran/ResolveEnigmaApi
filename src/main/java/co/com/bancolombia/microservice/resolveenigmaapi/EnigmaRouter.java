package co.com.bancolombia.microservice.resolveenigmaapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
@CrossOrigin

public class EnigmaRouter {
    @Bean
    public RouterFunction monoEnigmaFunction(EnigmaHandler enigmaHandler){
        return  route(
                    GET("/enigma")
                            .and(accept(APPLICATION_JSON)),enigmaHandler::getAll)
                .andRoute(
                    GET("/content/{id}")
                            .and(accept(APPLICATION_JSON)),enigmaHandler::getAllPath)
                .andRoute(
                    POST("/enigma")
                            .and(accept(APPLICATION_JSON))
                            .and(contentType(APPLICATION_JSON)), enigmaHandler::save)
                .andRoute(
                    PUT("/enigma")
                            .and(accept(APPLICATION_JSON))
                            .and(contentType(APPLICATION_JSON)), enigmaHandler::update)
                .andRoute(
                    DELETE("/enigma")
                            .and(accept(APPLICATION_JSON))
                            .and(contentType(APPLICATION_JSON)), enigmaHandler::delete)
                ;
    }

}
