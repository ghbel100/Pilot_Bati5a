package hhu.propra.bati5a.infrastructure.repositories;

import hhu.propra.bati5a.domain.model.Tag;
import hhu.propra.bati5a.domain.model.repository.TopicRepository;
import hhu.propra.bati5a.domain.model.topic.Markdown;
import hhu.propra.bati5a.domain.model.topic.Topic;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class InMemoryTopicRepository implements TopicRepository {

        private final List<Topic> topics = new ArrayList<>();

        public InMemoryTopicRepository() {
                topics.add(new Topic("Algorithmen und Datenstrukturen (E. Wanke)",
                                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/algorithmen-und-datenstrukturen/lehre-und-abschlussarbeiten",
                                new Markdown("Arbeitsgruppe von Prof. Dr. Eimo Wanke."), Collections.emptySet(),
                                Set.of(new Tag("Algorithmen"), new Tag("Datenstrukturen"))));

                topics.add(new Topic("Algorithmen und Datenstrukturen (M. Schmidt)",
                                "https://www.algo.hhu.de/lehre-und-abschlussarbeiten/abschlussarbeiten",
                                new Markdown("Arbeitsgruppe von Prof. Dr. Melanie Schmidt."), Collections.emptySet(),
                                Set.of(new Tag("Algorithmen"), new Tag("Datenstrukturen"))));

                topics.add(new Topic("Algorithmische Bioinformatik",
                                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/algorithmische-bioinformatik/lehre-und-abschlussarbeiten/abschlussarbeiten",
                                new Markdown("Algorithmische Bioinformatik Lehrstuhl."), Collections.emptySet(),
                                Set.of(new Tag("Bioinformatik"), new Tag("Algorithmen"))));

                topics.add(new Topic("Betriebsysteme",
                                "https://coconucos.cs.uni-duesseldorf.de/lehre/home/info/thesis/planning",
                                new Markdown("Arbeitsgruppe für Betriebssysteme."), Collections.emptySet(),
                                Set.of(new Tag("OS"), new Tag("Systems"))));

                topics.add(new Topic("Big Data Analytics for Microscopic Images",
                                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/big-data-analytics-for-microscopic-images/lehre-und-abschlussarbeiten/abschlussarbeiten",
                                new Markdown("Big Data Analytics Fokus."), Collections.emptySet(),
                                Set.of(new Tag("Big Data"), new Tag("Imaging"))));

                topics.add(new Topic("Computational Cell Biology",
                                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/computational-cell-biology/lehre-und-abschlussarbeiten/abschlussarbeiten",
                                new Markdown("Fokus auf computergestützte Zellbiologie."), Collections.emptySet(),
                                Set.of(new Tag("Biologie"), new Tag("Computational"))));

                topics.add(new Topic("Data & Knowledge Engineering",
                                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/data-knowledge-engineering/lehre-und-abschlussarbeiten/abschlussarbeiten",
                                new Markdown("Daten- und Wissensmanagement."), Collections.emptySet(),
                                Set.of(new Tag("Data Engineering"), new Tag("Wissen"))));

                topics.add(new Topic("Datenbanken",
                                "https://dbs.cs.uni-duesseldorf.de/lehre/bmarbeit/index.php",
                                new Markdown("Datenbankmanagementsysteme."), Collections.emptySet(),
                                Set.of(new Tag("Datenbanken"), new Tag("SQL"))));

                topics.add(new Topic("Dialog Systems and Machine Learning",
                                "https://www.die.hhu.de/unsere-lehre/abschlussarbeiten",
                                new Markdown("Dialogsysteme und maschinelles Lernen."), Collections.emptySet(),
                                Set.of(new Tag("AI"), new Tag("Machine Learning"), new Tag("NLP"))));

                topics.add(new Topic("Digitale Innovation und Entrepreneurship",
                                "https://ccc.cs.hhu.de/index.php",
                                new Markdown("Digitale Innovationen."), Collections.emptySet(),
                                Set.of(new Tag("Innovation"), new Tag("Startups"))));

                topics.add(new Topic("Komplexitätstheorie und Kryptologie",
                                "https://www.sarmata.hhu.de/lehre-und-abschlussarbeiten",
                                new Markdown("Theoretische Informatik und Sicherheit."), Collections.emptySet(),
                                Set.of(new Tag("Kryptographie"), new Tag("Theorie"))));

                topics.add(new Topic("Machine Learning",
                                "https://www.cn.hhu.de/lehre-und-abschlussarbeiten/abschlussarbeiten",
                                new Markdown("Maschinelles Lernen."), Collections.emptySet(),
                                Set.of(new Tag("Machine Learning"), new Tag("AI"))));

                topics.add(new Topic("Softwaretechnik und Programmiersprachen",
                                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/softwaretechnik-und-programmiersprachen/lehre-und-abschlussarbeiten/abschlussarbeiten",
                                new Markdown("Software Engineering und PL."), Collections.emptySet(),
                                Set.of(new Tag("Java"), new Tag("Software Engineering"))));
        }

        @Override
        public List<Topic> findAll() {
                return Collections.unmodifiableList(topics);
        }
}
