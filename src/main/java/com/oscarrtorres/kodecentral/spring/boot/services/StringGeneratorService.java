package com.oscarrtorres.kodecentral.spring.boot.services;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StringGeneratorService {

    private final Slugify slugify;

    public StringGeneratorService(Slugify slugify) {
        this.slugify = slugify;
    }

    public String generateSlug(String fromThis) {
        String randomString = new Random().ints(97, 122 + 1) // 97 (a) -> 122 (z)
                .limit(16) // string length
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return slugify.slugify(fromThis + " " + randomString);
    }
}
