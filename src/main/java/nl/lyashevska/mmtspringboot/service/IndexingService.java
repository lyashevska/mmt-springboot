/**
 * load data into Lucene/Elasticsearch
 * https://reflectoring.io/hibernate-search/
 * https://github.com/thombergs/code-examples/blob/master/spring-boot/hibernate-search/src/main/java/io/reflectoring/hibernatesearch/service/SearchService.java
 * https://blog.netgloo.com/2014/11/23/spring-boot-and-hibernate-search-integration/
 * https://github.com/netgloo/spring-boot-samples/tree/master/spring-boot-hibernate-search
 * https://wkrzywiec.medium.com/full-text-search-with-hibernate-search-lucene-part-1-e245b889aa8e
 * https://github.com/wkrzywiec/Library-Spring/blob/163fbbac65750b199cc665a2ba61fd4b80fc2ff6/src/main/webapp/WEB-INF/views/admin-panel.jsp
 *
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

