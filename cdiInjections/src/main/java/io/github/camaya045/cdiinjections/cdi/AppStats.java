package io.github.camaya045.cdiinjections.cdi;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class AppStats {
    private final AtomicInteger totalHits = new AtomicInteger(0);

    public int hit(){
        return totalHits.incrementAndGet();
    }
}
