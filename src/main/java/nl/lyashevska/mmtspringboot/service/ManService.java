package nl.lyashevska.mmtspringboot.service;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import nl.lyashevska.mmtspringboot.repository.ManRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManService {

    @Autowired
    private ManRepo repo;

    public void addMan(Manuscript m){
        repo.save(m);
    }

    public List<Manuscript> getAllMan(){
        return repo.findAll();
    }

    public Manuscript getManById(int id){
        Optional<Manuscript> m = repo.findById(id);
        if(m.isPresent()){
            return m.get();
        }
        return null;
    }

    public void deleteMan(int id){
        repo.deleteById(id);
    }
}
