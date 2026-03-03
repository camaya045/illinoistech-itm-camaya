package io.github.camaya045.cdiinjections.cdi;

import jakarta.enterprise.context.RequestScoped;
import java.time.Instant;
import java.util.UUID;

@RequestScoped
public class Requestinfo {
    private final String requestId = UUID.randomUUID().toString();
    private final Instant startedAt = Instant.now();

    public String getRequestId() {return requestId;}
    public Instant getStartedAt() {return startedAt;}
}