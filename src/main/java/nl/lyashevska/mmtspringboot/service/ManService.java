package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.entity.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManService {

    @Autowired
    private ManRepo repo;

    public void addMan(Manuscript m){
        repo.save(m);

    }
}
