package nl.lyashevska.mmtspringboot.hibernatesearch;

import nl.lyashevska.mmtspringboot.model.Manuscript;
import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import org.hibernate.search.query.dsl.QueryBuilder;


@Repository
public class HibernateSearchDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Manuscript> searchTitleByKeywordQuery(String text) {
        Query keywordQuery = getQueryBuilder()
                .keyword()
                .onField("title")
                .matching(text)
                .createQuery();

        List<Manuscript> results = getJpaQuery(keywordQuery).getResultList();

        return results;
    }

    public List<Manuscript> searchTitleByFuzzyQuery(String text) {
        Query fuzzyQuery = getQueryBuilder()
                .keyword()
                .fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("title")
                .matching(text)
                .createQuery();

        List<Manuscript> results = getJpaQuery(fuzzyQuery).getResultList();
        return results;
    }

    public List<Manuscript> searchProductNameAndDescriptionByKeywordQuery(String text) {

        Query keywordQuery = getQueryBuilder()
                .keyword()
                .onFields("author", "journal")
                .matching(text)
                .createQuery();

        List<Manuscript> results = getJpaQuery(keywordQuery).getResultList();

        return results;
    }

    private FullTextQuery getJpaQuery(org.apache.lucene.search.Query luceneQuery) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return (FullTextQuery) fullTextEntityManager.createFullTextQuery(luceneQuery, Manuscript.class);
    }

    private QueryBuilder getQueryBuilder() {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        return fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Manuscript.class)
                .get();
    }


//    @Autowired
//    public HibernateSearchDao(EntityManager entityManager) {
//        super();
//        this.centityManager = entityManager;
//    }
//
//    public void initializeHibernateSearch() {
//
//        try {
//            // trigger Lucene to build the index initially
//            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
//            fullTextEntityManager.createIndexer().startAndWait();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//    @Transactional
//    public List<Manuscript> fuzzySearch(String searchTerm) {
//
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
//
//        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Manuscript.class).get();
//
//        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("title")
//                .matching(searchTerm).createQuery();
//
//        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Manuscript.class);
//
//        // execute search
//        List<Manuscript> ManuscriptList = null;
//
//        try {
//            ManuscriptList = jpaQuery.getResultList();
//        } catch (NoResultException nre) {
//            ;// do nothing
//        }
//
//        return ManuscriptList;
//    }
}