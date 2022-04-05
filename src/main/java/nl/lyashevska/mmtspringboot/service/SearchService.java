package nl.lyashevska.mmtspringboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.lyashevska.mmtspringboot.model.Manuscript;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final EntityManager entityManager;

    public List<Manuscript> getManuscriptAuthor(String author){

        // create an object of FullTextEntityManager
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        // create QueryBuilder for the index on which we want to perform a search. We also need to pass the entity class object in it
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(Manuscript.class)
                .get();

        // make use of the keyword query keyword() which allows us to look for a specific word in a field or fields.
        // pass the word that we want to search in the matching function.
        Query authorQuery = qb.keyword().onFields("author").matching(author).createQuery();

        //  wrap everything in FullTextQuery
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(authorQuery, Manuscript.class);

        // fetch the result list by calling getResultList()
        return (List<Manuscript>) fullTextQuery.getResultList();
    }

}