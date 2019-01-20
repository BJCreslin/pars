package ru.bjcreslin.pars.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bjcreslin.pars.model.UrlGroup;
import ru.bjcreslin.pars.repository.UrlGroupeRepository;

import java.util.List;

@Service
public class URLGroupeServiceImpl implements URLGroupeService {
    @Autowired
    UrlGroupeRepository urlGroupeRepository;

    @Override
    public UrlGroup getById(Long id) {
        return urlGroupeRepository.getOne(id);
    }

    @Override
    public void deleteById(Long id) {
        urlGroupeRepository.deleteById(id);
    }

    @Override
    public void delete(UrlGroup urlGroup) {
        urlGroupeRepository.delete(urlGroup);
    }

    @Override
    public void save(UrlGroup urlGroup) {
        urlGroupeRepository.save(urlGroup);
    }

    @Override
    public List<UrlGroup> getAll() {
        return urlGroupeRepository.findAll();
    }
}
