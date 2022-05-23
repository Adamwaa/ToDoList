package io.github.Adam.Lang;

import java.util.List;
import java.util.stream.Collectors;

public class LangService {

    private LangRepository repository;


    //constructor
    public LangService() {
        this(new LangRepository());

    }

    public LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(Collectors.toList());
    }
}
