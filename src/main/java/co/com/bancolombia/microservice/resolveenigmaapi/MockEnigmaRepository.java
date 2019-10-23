package co.com.bancolombia.microservice.resolveenigmaapi;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MockEnigmaRepository {
    private Map<Integer, Enigma> enigmaStore = new HashMap<Integer, Enigma>();

    @PostConstruct
    public void initIt() throws Exception {
        enigmaStore.put(1001,new Enigma(1,"Step 1: Open Refrigerator"));
        enigmaStore.put(1002,new Enigma(2,"Step 2: Put the giraffe in"));
        enigmaStore.put(1003,new Enigma(3,"Step 3: Close Refrigerator"));
    }

    public Collection<Enigma> getAll() {
        return enigmaStore.values();
    }

    public Mono<Void> save(Mono<Enigma> enigma){
        return enigma.doOnNext(c -> {
            Integer id = c.getStep();
            enigmaStore.put(id, c);
        }).thenEmpty(Mono.empty());
    }

    public Mono<Void> update(Mono<Enigma> enigma){
        return enigma.doOnNext(c -> {
            Integer id = c.getStep();
            enigmaStore.replace(id, c);
        }).thenEmpty(Mono.empty());
    }

    public Mono<Void> delete(Mono<Enigma> enigma){
        return enigma.doOnNext(c->{
            Integer id = c.getStep();
            enigmaStore.remove(id);
        }).thenEmpty(Mono.empty());
    }
}
