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
        Random random = new Random();
        String randomString = random.ints(48, 123)
                .filter(num -> num <= 57 || num >= 97) // 0 (48) -> 9 (57), a (97) -> 122 (z)
                .limit(8)
                .mapToObj(c -> (char) c)
                .collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();

        return slugify.slugify(fromThis + " " + randomString);
    }
}
