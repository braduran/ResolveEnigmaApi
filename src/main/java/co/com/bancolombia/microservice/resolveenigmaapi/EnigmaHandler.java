package co.com.bancolombia.microservice.resolveenigmaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Component
public class EnigmaHandler {
    @Autowired
    MockEnigmaRepository mockEnigmaRepository;

    public Mono<ServerResponse> getAll(ServerRequest request){
        Flux<Enigma> enigmaFlux= Flux
                .fromIterable(mockEnigmaRepository.getAll())
                ;
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(enigmaFlux, Enigma.class);
    }
    public Mono<ServerResponse> getAllPath(ServerRequest request){
        Flux<Enigma> enigmaFlux= Flux
                .fromIterable(mockEnigmaRepository.getAll())
                .filter(step -> step.getStep() == Integer.parseInt(request.pathVariable("id")));
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(enigmaFlux, Enigma.class)
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Enigma> enigmaMono = request.bodyToMono(Enigma.class);
        return ServerResponse.ok().build(mockEnigmaRepository.save(enigmaMono));
    }
    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<Enigma> enigmaMono = request.bodyToMono(Enigma.class);
        return ServerResponse.ok().build(mockEnigmaRepository.update(enigmaMono));
    }
    public Mono<ServerResponse> delete(ServerRequest request) {
        Mono<Enigma> enigmaMono = request.bodyToMono(Enigma.class);
        return ServerResponse.ok().build(mockEnigmaRepository.delete(enigmaMono));
    }
}
