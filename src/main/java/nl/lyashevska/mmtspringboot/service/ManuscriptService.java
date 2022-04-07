package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManuscriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManuscriptService {

    @Autowired
    private ManuscriptRepository repo;

    public void add(Manuscript m){
        repo.save(m);
    }

    public List<Manuscript> getAllManuscript(){
        return repo.findAll();
    }

    public Manuscript getManuscriptById(int id){
        Optional<Manuscript> m = repo.findById(id);
        if(m.isPresent()){
            return m.get();
        }
        return null;
    }

    public void deleteManuscript(int id){
        repo.deleteById(id);
    }
}
