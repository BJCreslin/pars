package ru.bjcreslin.pars.Service;

import ru.bjcreslin.pars.model.UrlGroup;

import java.util.List;

public interface URLGroupeService {
    UrlGroup getById(Long id);

    void deleteById(Long id);

    void delete(UrlGroup urlGroup);

    void save(UrlGroup urlGroup);

    List<UrlGroup> getAll();
}
