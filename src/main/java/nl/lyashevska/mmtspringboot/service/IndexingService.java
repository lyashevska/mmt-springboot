/**
 * Before we perform any queries, we first need to load data into Lucene/Elasticsearch
 * https://reflectoring.io/hibernate-search/
 * https://github.com/thombergs/code-examples/blob/master/spring-boot/hibernate-search/src/main/java/io/reflectoring/hibernatesearch/service/SearchService.java
 */

package nl.lyashevska.mmtspringboot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class IndexingService {

    private final EntityManager em;

    @Transactional
    public void initiateIndexing() throws InterruptedException {
        log.info("Initiating indexing...");
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
        fullTextEntityManager.createIndexer().startAndWait();
        log.info("All entities indexed");
    }

}

