package com.raynigon.rqms.domain.online.entities;

import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ABTest {

    private String name;

    private OffsetDateTime start;

    private OffsetDateTime end;

    private final Set<TestVariant> variants = new HashSet<>();

    public ABTest(String name, OffsetDateTime start, OffsetDateTime end, TestVariant test, TestVariant... others) {
        this.name = name;
        this.start = start;
        this.end = end;
        // Generate Control Variant
        TestVariant control = new TestVariant(this.name + "-control", true);
        this.variants.add(control);
        // Add all other variants
        Set<TestVariant> variants = new HashSet<>(Set.of(others));
        variants.add(test);
        if (variants.stream().anyMatch(TestVariant::control)) {
            // TODO replace with proper exception
            throw new RuntimeException("Control variant is already defined");
        }
        this.variants.addAll(variants);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.variants.removeIf(TestVariant::control);
        this.variants.add(new TestVariant(this.name + "-control", true));
    }

    public OffsetDateTime getStart() {
        return start;
    }

    public void setStart(OffsetDateTime start) {
        if (!start.isBefore(end)) {
            // TODO use proper exception
            throw new RuntimeException("Start needs to be before end");
        }
        this.start = start;
    }

    public OffsetDateTime getEnd() {
        return end;
    }

    public void setEnd(OffsetDateTime end) {
        if (!end.isAfter(start)) {
            // TODO use proper exception
            throw new RuntimeException("End needs to be after start");
        }
        this.end = end;
    }

    public Set<TestVariant> getVariants() {
        return Collections.unmodifiableSet(variants);
    }

    public boolean isActive() {
        OffsetDateTime now = OffsetDateTime.now();
        return (now.isAfter(start) && now.isBefore(end));
    }

    public void addVariant(TestVariant variant) {
        if (variant.control()) {
            // TODO replace with proper exception
            throw new RuntimeException("Control variant is already defined");
        }
        variants.add(variant);
    }

    @Nullable
    public TestVariant findVariant(String name) {
        return variants.stream()
                .filter(variant -> variant.name().equals(name))
                .findFirst()
                .or(() -> variants.stream().filter(TestVariant::control).findFirst())
                .orElseThrow();
    }
}
