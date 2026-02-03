package hhu.propra.bati5a.infrastructure.repositories;

import hhu.propra.bati5a.domain.model.Course;
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
                new Markdown("""
                        <h3>Themen</h3>
                        <p>Wir bieten hauptsächlich Themen aus dem Bereich der algorithmischen Clusteranalyse an. Diese haben entweder einen theoretischen (Entwurf und theoretische Analyse von Algorithmen) oder einen praktischen Fokus (Entwurf und experimentelle Analyse von Algorithmen). In beiden Fällen beinhaltet die Arbeit einen theoretischen Grundlagenteil, in dem ein bekanntes Forschungsergebnis besprochen wird, sowie den Eigenanteil bestehend aus Entwurf und Analyse.</p>
                        
                        <h3>Grundlegende Voraussetzungen</h3>
                        <ul>
                            <li>sehr gute mathematische Kenntnisse</li>
                            <li>Interesse an Algorithmik, insbesondere am Entwurf von Algorithmen und ihrer Analyse</li>
                            <li>Vorkenntnisse in LaTeX, z.B. erworben durch Pflichtseminar am Lehrstuhl, oder die Bereitschaft, sich LaTeX-Kenntnisse zeitnah anzueignen</li>
                        </ul>
                        
                        <h4>Bachelor</h4>
                        <p>Erfolgreicher Abschluss von Modulen im Gesamtumfang von 10 LP bei Lehrenden des Lehrstuhls, hierbei zählt die AlDat als grundlegendes Pflichtmodul nicht mit. Es eignet sich insbesondere die Kombination "Graphenalgorithmen 1" + "Kombinatorische Algorithmen für Clusteringprobleme".</p>
                        
                        <h4>Master</h4>
                        <p>Idealerweise der Besuch der Vorlesungen "Approximationsalgorithmen", "Approximationsalgorithmen für Clusteringprobleme" und Projektarbeit am Lehrstuhl. Voraussetzungen im Masterbereich können aber aktuell auch individuell besprochen werden, da die Veranstaltung "Approximationsalgorithmen für Clusteringprobleme" noch unregelmäßig angeboten wird.</p>
                        
                        <hr>
                        <p><strong>Verantwortlichkeit:</strong> Lehrstuhl Algorithmen und Datenstrukturen</p>
                        """),
                Set.of(new Course("Graphenalgorithmen 1"), new Course("Kombinatorische Algorithmen für Clusteringprobleme"), new Course("Approximationsalgorithmen")),
                Set.of(new Tag("Algorithmen"), new Tag("Datenstrukturen"), new Tag("Clusteranalyse"), new Tag("Theorie"), new Tag("Praxis"))));

        topics.add(new Topic("Algorithmische Bioinformatik (G. Klau)",
                "https://www.cs.hhu.de/en/research-groups/algorithmic-bioinformatics/our-teaching/abschlussarbeiten/bachelor/masterarbeit-schreiben",
                new Markdown("""
                        <h3>Grundlegende Voraussetzungen/Regeln</h3>
                        <ul>
                            <li>Interesse an Algorithmen und Bioinformatik</li>
                            <li>Während der Bearbeitung: Verpflichtende Teilnahme (Präsenz) an den wöchentlichen Projekttreffen (Di, 14:30 h, ZDM, Henkelstr. 230, 40599 Düsseldorf) der Arbeitsgruppe</li>
                            <li>Kenntnisse von Unix und git notwendig. LaTeX-Kenntnisse erwünscht</li>
                            <li>Kenntnisse einer Programmiersprache (abhängig vom Projekt)</li>
                            <li>Englischkenntnisse (in den regelmäßigen Update Meetings reden wir Englisch)</li>
                            <li>Weitere projektabhängige Kenntnisse (z. B. conda, Snakemake, Grundlagen der Genetik, ...)</li>
                        </ul>
                        
                        <h4>Für BSc-Arbeiten</h4>
                        <p>Üblicherweise Halbmodul "Algorithmen in der Bioinformatik"</p>
                        
                        <h4>Für MSc-Arbeiten</h4>
                        <p>10 Leistungspunkte aus den vom Lehrstuhl für den Masterstudiengang angebotenen Veranstaltungen</p>
                        
                        <h3>Themen</h3>
                        <p>Wir bieten hauptsächlich Themen aus dem Bereich Algorithmische Bioinformatik an. Diese reichen von theoretischen algorithmischen Fragestellungen bis hin zu Workflowentwicklung für konkrete bioinformatische Fragestellungen. Auch eigene Themenvorschläge können berücksichtigt werden, sofern sie thematisch zum Profil der Arbeitsgruppe passen.</p>
                        <p>Wenn Sie sich bezüglich eines möglichen Themas informieren möchten oder einen eigenen Themenvorschlag haben, sprechen Sie uns an!</p>
                        
                        <h3>Empfehlung</h3>
                        <p>Die Bachelorarbeit ist die erste schriftliche Arbeit im Studienfach Informatik an der HHU. Drei Monate sind keine lange Zeit, insbesondere, wenn das Schreiben nicht leicht von der Hand geht (das ist bei den wenigsten der Fall). Wir empfehlen daher, auch für Studeirende im Master, das Buch "Writing for Computer Science" von Justin Zobel, das in der Universitätsbibliothek zur Verfügung steht. Es empfiehlt sich, die Hinweise in dem Buch zu beachten und frühzeitig mit dem wissenschaftlichen Schreiben zu beginnen.</p>
                        
                        <h3>Während des Projekts</h3>
                        <ul>
                            <li>Regelmäßige Treffen von A und B</li>
                            <li>Wöchentliche Update-Meetings im Oberseminar: Jeden Dienstag um 14:30 Uhr in Gebäude 97.45 (Henkelstraße 230), Erdgeschoss, Raum 032 statt. Diese Meetings dienen dazu, den Stand der laufenden Projekte zu besprechen. Aktuelle Details und Änderungen werden immer im Mattermost-Channel Oberseminar kommuniziert.</li>
                            <li>Bitte lesen Sie unbedingt die ausführlichen Regeln des Oberseminars!</li>
                            <li>Am Ende jeder Woche: A pusht ein kleines Text- oder Markdown-File in das Verzeichnis Journal des Repositories. Dies enthält eine stichpunktartige Beschreibung der verrichteten Tätigkeiten der abgelaufenen Woche und der für das Projekt aufgewendeten Zeiten, Protokolle der Projektreffen sowie sonstige relevante Informationen und Entwicklungen. Diese Einträge dienen der Dokumentation des Projektfortschritts und helfen, mögliche unerwartete Wendungen oder Probleme früh zu erkennen.</li>
                            <li>A ist für das Journal verantwortlich</li>
                        </ul>
                        
                        <h3>Die Ausarbeitung</h3>
                        <ul>
                            <li>Sprache: englisch oder deutsch</li>
                            <li>Hier ist eine LaTeX-Vorlage, die Sie benutzen und nach Ihren Wünschen anpassen können, aber nicht müssen.</li>
                            <li>Beachten Sie die Regeln wissenschaftlichen Schreibens (siehe zum Beispiel das Buch "Writing for Computer Science" von Justin Zobel)</li>
                            <li>Wenn Sie Programmcode entwickelt haben: Benutzen Sie das von B bereitgestellte git-Repository. Dort sollten sich, wenn möglich, auch alle Daten befinden. Kommentieren Sie den Code ausreichend und schreiben Sie ins README.md des Repositories, wie man den Code ausführen kann, um die in Ihrer Arbeit beschriebenen Ergebnisse zu reproduzieren. Nutzen Sie nach Verfügbarkeit Long-Term-Support-Versionen der Tools und Bibliotheken. Es gilt die Version des Repositories zum Zeitpunkt der offiziellen Abgabe.</li>
                            <li>Für Feedback schickt A mindestens 2 Wochen vor offizieller Abgabe einen Entwurf an B</li>
                        </ul>
                        
                        <h3>Der Vortrag</h3>
                        <p>Nach Abgabe der Arbeit wird ein Termin für den Vortrag organisiert. Dieser sollte nicht länger als 20 (BSc) bzw. 30 Minuten (MSc) dauern.</p>
                        <ul>
                            <li>A kümmert sich um den Termin: Dieser muss mit dem Betreuer, dem Zweitgutachter und Prof. Klau abgestimmt werden. Idealerweise sollte der Termin 2-5 Wochen nach Abgabe liegen, idealierweise Dienstags um 14:30 Uhr (wenn möglich). Dazu auch mit Philipp Spohr, der das Oberseminar organisiert, absprechen. Steht der Termin, bitte an webseiten(at)cs.hhu.de mitteilen (mind. 1 Woche vor dem Termin), damit der Vortrag auf den Webseiten der Informatik ankündigt werden kann. Die Mitteilung muss den Namen von A, den Titel der Arbeit, den Ort für den Vortrag (i.d.R. der Albi-Seminarraum 25.12.01.51) enthalten. Bitte auch dazu schreiben, dass die Arbeit am Lehrstuhl für algorithmische Bioinformatik geschrieben wurde.</li>
                            <li>Hier ist eine Vorlage für Vortragsfolien, die benutzt werden kann (aber nicht muss).</li>
                            <li>Die Folien sollten vorher mit B besprochen werden</li>
                            <li>Spätestens einen Tag vor dem Vortrag: A schickt die Folien per e-Mail an B</li>
                            <li>Es empfiehlt sich, sich die Vorträge zu den anderen Projekten anzuschauen (diese sind öffentlich). Auf dieser Seite werden die Vorträge angekündigt.</li>
                        </ul>
                        
                        <hr>
                        <p><strong>Verantwortlichkeit:</strong> Lehrstuhl Algorithmische Bioinformatik (Prof. Dr. Gunnar W. Klau)</p>
                        """),
                Set.of(new Course("Algorithmen in der Bioinformatik")),
                Set.of(new Tag("Bioinformatik"), new Tag("Algorithmen"), new Tag("Genetik"), new Tag("Unix"), new Tag("git"), new Tag("Python"), new Tag("Snakemake"), new Tag("conda"))));

        topics.add(new Topic("Betriebsysteme",
                "https://coconucos.cs.uni-duesseldorf.de/lehre/home/info/thesis/planning",
                new Markdown("""
                        <h3>Abschlussarbeiten (Bachelor und Master)</h3>
                        <p>Abschlussarbeiten (Bachelor und Master) umfassen viele wichtige Entscheidungen und Schritte, mit welchen Sie sich im Vorfeld vertraut machen sollten. Zu diesem Zweck finden Sie in den folgenden Abschnitten einen Leitfaden, welcher Ihnen die wichtigsten Abläufe und Formalitäten näher bringen soll. Die Laufzeiten von Abschlussarbeiten betragen zur Zeit 3 Monate bei Bachelorarbeiten und 6 Monate bei Masterarbeiten.</p>
                        
                        <h4>Fristen für Bachelorarbeiten</h4>
                        <p>Diese gelten derzeit nur für Bachelorarbeiten, siehe separate Seite.</p>
                        
                        <h3>Themenfindung</h3>
                        <p>Themen suchen wir in einem Gespräch mit Ihnen, in Abhängigkeit von Ihren Vorkenntnissen, Interessen und unseren offenen Arbeitspunkten. Hierbei gehen wir top-down vor, versuchen zunächst in einem ersten Gespräch eine Themenrichtung zu finden und danach ein konkretes Thema in einem zweiten Gespräch. Auch eigene Themenvorschläge sind möglich.</p>
                        <p>Bei Interesse kommen Sie einfach zu unseren wöchentlichen Treffen, siehe hier.</p>
                        
                        <div class="alert alert-warning">
                            <strong>Wichtig:</strong> Wir vergeben ab sofort nur noch Abschlussarbeiten in D3OS (Rust).
                        </div>
                        
                        <p>Es ist nicht notwendig, dass Sie Rust komplett beherrschen. Grundlegende Kenntnisse reichen und diese kann man vor der Anmeldung, in zwei bis drei Wochen, erlernen. Hier finden Sie einige nützliche Links zum Einstieg, aber Sie müssen das Rust-Buch nicht komplett durcharbeiten. Sprechen Sie uns einfach an, was hier sinnvoll ist.</p>
                        
                        <h4>Nützliche Quellen zum Einstieg in Rust:</h4>
                        <ul>
                            <li><a href="https://rust-book.cs.brown.edu" target="_blank">The Rust Programming Language (Brown Univ. version)</a></li>
                            <li><a href="https://rustlings.rust-lang.org" target="_blank">Rustlings</a></li>
                        </ul>
                        
                        <h4>Nützliche Quelle zum Einstieg in Betriebssystem-Entwicklung in Rust:</h4>
                        <ul>
                            <li><a href="https://os.phil-opp.com" target="_blank">Writing an OS in Rust</a></li>
                        </ul>
                        """),
                Collections.emptySet(),
                Set.of(new Tag("OS"), new Tag("Systems"), new Tag("Rust"), new Tag("D3OS"), new Tag("Betriebssysteme"))));

        topics.add(new Topic("Big Data Analytics for Microscopic Images (T. Dickscheid)",
                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/big-data-analytics-for-microscopic-images/lehre-und-abschlussarbeiten/abschlussarbeiten",
                new Markdown("""
                        <p>Wir bieten Bachelor- und Masterarbeiten sowie lab rotations in unserer Forschungsgruppe am Forschungszentrum Jülich an. Im Folgenden finden Sie eine Liste aktuell offener Projekte. Sprechen Sie uns gern an.</p>
                        
                        <h3>Aktuell offene Projekte</h3>
                        
                        <h4>Factorization of learned cytoarchitectonic features into disentangled features</h4>
                        <p>We have deep neural networks to learn features from high-resolution microscopic images. These features encode the cytoarchitecture of the tissue, but they are inherently entangled. This makes them difficult to interpret. The goal of this project is to evaluate factorization/disentanglement methods to learn disentangled deep learning features.</p>
                        
                        <h4>Self-supervised Segmentation of Blood Vessels in 3D-PLI Images</h4>
                        <p>3D-PLI images provide a comprehensive view of brain tissue with high resolution. In addition to nerve fibers, blood vessels and even the smallest capillaries that interfere with the signal of the nerve fibers can be observed in the images. For an automatic analysis of 3D PLI images, the identification of such vascular structures is a crucial step. Initial results show that the structure of larger blood vessels can be consistently detected using deep learning segmentation models. However, due to the high annotation overhead, the recognition of the small capillaries remains an open challenge. This thesis will investigate recent advances in self-supervised pre-training of deep neural networks for image segmentation in order to obtain a segmentation of the vascular structures in the brain down to the smallest capillaries with minimal human effort.</p>
                        
                        <h4>Tackling the open set problem in cytoarchitectonic mapping with deep learning</h4>
                        <p>We have deep neural networks to predict cytoarchitectonic brain areas from microscopic images. However, the set of classes is incomplete: There are brain areas which are not yet named or studied. Models need to account for this, e.g., by being able to let users know when they "don't know" an observed area. The goal of this project is to evaluate methods from the field of open-set classification for the task.</p>
                        
                        <h4>Unsupervised deep learning for artifact detection in histological brain images</h4>
                        <p>Preparing and scanning histological very thin brain sections is a sensitive task. Many, but not all, artifacts (for examples tears in the tissue or crystallization of the staining) can be avoided. In order to get the best results these artifacts need to be detected (and later on removed). The goal of this project is to evaluate unsupervised deep learning methods for the detection of artifacts.</p>
                        
                        <hr>
                        <p><strong>Verantwortlichkeit:</strong> WE Informatik (Mail an die Webredaktion)</p>
                        """),
                Collections.emptySet(),
                Set.of(new Tag("Big Data"), new Tag("Imaging"), new Tag("Deep Learning"), new Tag("Microscopy"), new Tag("Neural Networks"), new Tag("Brain"), new Tag("Jülich"))));

        topics.add(new Topic("Computational Cell Biology (M. Lercher)",
                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/computational-cell-biology/lehre-und-abschlussarbeiten/abschlussarbeiten",
                new Markdown("""
                        <h3>Bachelor/Masterarbeiten</h3>
                        <p>In der Arbeitsgruppe Computational Cell Biology gibt es ständig eine Auswahl von Themen, die sich für Projekt-, Bachelor- und Masterarbeiten eignen - nicht nur für Informatiker, sondern auch für Biologen oder Physiker. Dabei handelt es sich teils um angewandte, teils aber auch um eher algorithmisch angelegte Projekte.</p>
                        
                        <p>Falls Sie Interesse haben, Ihre Arbeit in unserer Abteilung durchzuführen, dann vereinbaren Sie einfach per Mail einen Termin mit Prof. Martin Lercher.</p>
                        
                        <hr>
                        <p><strong>Verantwortlichkeit:</strong> Arbeitsgruppe Computational Cell Biology (Prof. Dr. Martin Lercher)</p>
                        """),
                Collections.emptySet(),
                Set.of(new Tag("Biologie"), new Tag("Computational"), new Tag("Zellbiologie"), new Tag("Physik"), new Tag("Interdisziplinär"))));

        topics.add(new Topic("Data & Knowledge Engineering (S. Cohen)",
                "https://www.cs.hhu.de/lehrstuehle-und-arbeitsgruppen/data-knowledge-engineering/lehre-und-abschlussarbeiten/abschlussarbeiten",
                new Markdown("""
                        <h3>Bachelor/Masterarbeiten</h3>
                        <p>Auf dieser Seite finden Sie nützliche Informationen rund um Bachelor- und Masterarbeiten hier an unserem Lehrstuhl.</p>
                        
                        <h3>Themen für Bachelor- und Masterarbeiten</h3>
                        <p>We offer topics related to the topics of the research group. Please get in touch for information about up-to-date open topics.</p>
                        
                        <hr>
                        <p><strong>Verantwortlichkeit:</strong> WE Informatik (Mail an die Webredaktion)</p>
                        """),
                Collections.emptySet(),
                Set.of(new Tag("Data Engineering"), new Tag("Knowledge Engineering"), new Tag("Wissen"), new Tag("Daten"))));

        topics.add(new Topic("Datenbanken und Informationssysteme (S. Conrad)",
                "https://dbs.cs.uni-duesseldorf.de/lehre/bmarbeit/bathemen.php",
                new Markdown("""
                        <h3>Bachelor/Masterarbeiten</h3>
                        <p>Wenn Sie sich für eine Arbeit an unserem Lehrstuhl interessieren, empfehlen wir Ihnen sich rechtzeitig vor dem beabsichtigen Anfangstermin mit uns in Verbindung zu setzen. Der Beginn einer Bachelor- oder Masterarbeit ist nur dann sinnvoll, wenn Sie bereits alle Voraussetzungen, insbesondere die benötigten Kreditpunkte, erfüllt haben oder im Begriff sind diese in naher Zukunft zu erfüllen.</p>
                        
                        <p>Die Arbeit kann in einem vorgestellten Themengebiet der Betreuer angefertigt werden, wobei das Thema idealerweise zusammen mit dem Studierenden konkretisiert wird. Bei den folgenden Themen handelt es sich lediglich um Vorschläge, die je nach Wünschen und Interessen im Rahmen des Themengebietes angepasst werden können. Eigene Themenvorschläge können gerne eingebracht werden. Dabei sind rein theoretische Arbeiten, also ohne Programmieren, nur in Ausnahmefällen möglich.</p>
                        
                        <p>Wir empfehlen Ihnen, sich vor dem Gespräch mit einem Betreuer über die jeweiligen Themengebiete zu informieren, um Ihre Vorstellungen zu konkretisieren.</p>
                        
                        <h3>Abgeschlossene Arbeiten (Auszug aus den Jahren 2023-2024)</h3>
                        <p>Hier finden Sie eine Auswahl kürzlich abgeschlossener Arbeiten am Lehrstuhl:</p>
                        
                        <h4>Bachelorarbeiten 2024</h4>
                        <ul>
                            <li>Verbesserter Bildmatting-Algorithmus mittels lokaler und nichtlokaler Nachbarn: Eine K-NN-basierte Farbmodellierung</li>
                            <li>Can FIRE Keep Up With BERT? Testing FIRE Embeddings on Different NLP Tasks</li>
                            <li>Entwicklung einer Python-Web-Anwendung zur flexiblen Bearbeitung von SQL-Datenbanken</li>
                            <li>Entwicklung und Validierung von Klassifikationsmodellen für die Extraktion von Mängelbeschreibungen aus PDF-Dokumenten</li>
                            <li>Entwicklung einer cross-Platform App, um Kennzeichen zu reservieren</li>
                            <li>Analyse und Visualisierung von Verfügbarkeiten in Terminbuchungsprozessen</li>
                        </ul>
                        
                        <h4>Masterarbeiten 2024</h4>
                        <ul>
                            <li>Evaluation of automated instruction text simplification: comparing human performance and LLMs</li>
                            <li>An Explainable Deep Learning Approach for Mortality Prediction in Allogeneic Stem Cell Transplantation</li>
                            <li>Extraktion von Argumenten mit Large Language Models: Eine vergleichende Analyse</li>
                            <li>Debiasing Text-to-Image Generative Models</li>
                            <li>Topic Detection and Sentiment Analysis in Customer Reviews</li>
                            <li>Approaching Multimodal Embeddings for Tabular Data and Text</li>
                            <li>Clustering of Time Series Using Time Point Clusters</li>
                        </ul>

                        <p>Eine vollständige Liste aller abgeschlossenen Themen (ab 2005) finden Sie im Archiv:</p>
                        <p><a href="https://dbs.cs.uni-duesseldorf.de/lehre/bmarbeit/bathemen.php" target="_blank">Alte Themen (Archiv)</a></p>
                        
                        <hr>
                        <p><strong>Verantwortlichkeit:</strong> Lehrstuhl für Datenbanken und Informationssysteme (Prof. Dr. Stefan Conrad)</p>
                        """),
                Collections.emptySet(),
                Set.of(new Tag("Datenbanken"), new Tag("SQL"), new Tag("Informationssysteme"), new Tag("NLP"), new Tag("Deep Learning"), new Tag("Data Science"))));

        topics.add(new Topic("Digital Innovation and Entrepreneurship (S. Haag)",
                "https://www.die.hhu.de/unsere-lehre/abschlussarbeiten",
                new Markdown("""
                        <h3>Abschlussarbeiten</h3>
                        <p>Wir suchen engagierte und motivierte Studierende, die Interesse an empirischer Forschung mitbringen und bereit sind, sich intensiv mit einem spannenden Thema auseinanderzusetzen.</p>
                        
                        <p>Wenn Sie eine Abschlussarbeit (Bachelor-, Master- oder Projektarbeit) an unserem Lehrstuhl schreiben möchten, beginnen Sie rechtzeitig mit der Auswahl eines passenden Themas und folgen Sie den Schritten, die wir für Sie zusammengestellt haben.</p>
                        
                        <p>Auf dieser Seite finden Sie:</p>
                        <ul>
                            <li>eine Schritt-für-Schritt-Anleitung, die Ihnen den Bewerbungsprozess erklärt</li>
                            <li>eine Übersicht unserer ausgeschriebenen Themengebiete</li>
                            <li>Informationen zu aktuellen Kooperationsthemen mit externen Institutionen, Unternehmen und Startups</li>
                            <li>sowie Links zu den Prüfungsordnungen der Studiengänge, die eine Abschlussarbeit an unserem Lehrstuhl erstellen können.</li>
                        </ul>
                        
                        <p>Bitte beachten Sie unsere Anleitung sorgfältig, um sicherzustellen, dass Ihre Bewerbung vollständig ist und berücksichtigt werden kann. Falls Sie weitere Fragen haben, schauen Sie gerne in die FAQs.</p>
                        
                        <h3>Folgende Themengebiete für Bachelor- und Masterarbeiten stehen aktuell für das Jahr 2026 zur Auswahl:</h3>
                        
                        <div class="table-responsive">
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Thema</th>
                                        <th>Zeitraum (appx.)</th>
                                        <th>Betreuerin</th>
                                        <th>Sprache</th>
                                        <th>freie Plätze</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Cybervictims: Wahrnehmung, Verarbeitung und Bewältigungsstrategien von Betroffenen nach Cyberangriffen</td>
                                        <td>ab April</td>
                                        <td>Prof. Dr. Steffi Haag</td>
                                        <td>Deutsch/Englisch</td>
                                        <td>1-2 (Bachelor)</td>
                                    </tr>
                                    <tr>
                                        <td>Security vs. Usability: Herausforderungen in der Entwicklung sicherer und benutzungsfreundlicher Software</td>
                                        <td>ab März/April</td>
                                        <td>Prof. Dr. Steffi Haag</td>
                                        <td>Deutsch/Englisch</td>
                                        <td>1 (Master / Bachelor)</td>
                                    </tr>
                                    <tr>
                                        <td>Evaluating Fairness Behavior in Human-AI Interaction (LLMs)</td>
                                        <td>ab April</td>
                                        <td>Sumin Kim</td>
                                        <td>Englisch</td>
                                        <td>TBD</td>
                                    </tr>
                                    <tr>
                                        <td>Sustainable Large Language Model Design and Evaluation</td>
                                        <td>ab März</td>
                                        <td>Sumin Kim</td>
                                        <td>Englisch</td>
                                        <td>TBD</td>
                                    </tr>
                                    <tr>
                                        <td>Barriers to Digital Innovation: A Systematic Literature Review</td>
                                        <td>ab Feburar/März</td>
                                        <td>Khalid Durani</td>
                                        <td>Englisch (oder Deutsch)</td>
                                        <td>1</td>
                                    </tr>
                                    <tr>
                                        <td>Barriers to Digital Innovation on Social Media: Qualitative Insights from Artists</td>
                                        <td>ab Feburar/März</td>
                                        <td>Khalid Durani</td>
                                        <td>Englisch (oder Deutsch)</td>
                                        <td>1</td>
                                    </tr>
                                    <tr>
                                        <td>Barriers to Digital Innovation on Social Media: Qualitative Insights from Scientists</td>
                                        <td>ab Feburar/März</td>
                                        <td>Khalid Durani</td>
                                        <td>Englisch (oder Deutsch)</td>
                                        <td>1</td>
                                    </tr>
                                    <tr>
                                        <td>Habitual Smartwatch Engagement: The Influence of Normative Considerations on Routinized Data-Driven Behaviors</td>
                                        <td>ab Feburar/März</td>
                                        <td>Khalid Durani</td>
                                        <td>Englisch (oder Deutsch)</td>
                                        <td>1</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                        <hr>
                        <h4>Ansprechperson</h4>
                        <p><strong>Koordination Abschlussarbeiten</strong><br>
                        Nina Grigoriou<br>
                        <a href="mailto:die-thesis@hhu.de">die-thesis@hhu.de</a></p>
                        """),
                Collections.emptySet(),
                Set.of(new Tag("Digital Innovation"), new Tag("Entrepreneurship"), new Tag("Human-AI Interaction"), new Tag("LLMs"), new Tag("Security"), new Tag("Usability"))));

        topics.add(new Topic("Machine Learning (P. Swoboda)",
                "https://www.sarmata.hhu.de/en/teaching-and-theses",
                new Markdown("""
                        <h3>General Expectations</h3>
                        <p>Machine learning is a work-intensive research area. We expect considerable time and effort to keep up, make progress, and learn.</p>
                        <ul>
                            <li><strong>Workload:</strong> Expect 30 hours per week of work.</li>
                            <li><strong>Coding:</strong> Start coding from day one. Reproduce results, get simple baselines running early.</li>
                            <li><strong>Support:</strong> PhDs are available for contact/help, but not for "babysitting". Be persistent but respectful of their time.</li>
                        </ul>
                        
                        <h3>Interaction</h3>
                        <ul>
                            <li><strong>Communication:</strong> Available via Discord (account: <code>sarmacki_nacjonalista</code>).</li>
                            <li><strong>Seminar:</strong> Weekly internal seminar attendance is mandatory. Bachelor/Master students present their work (project idea, intermediate results, final thesis).</li>
                            <li><strong>Meetings:</strong> Bi-weekly meetings to discuss ideas, architecture, and experiments. Protocols of meetings are required.</li>
                        </ul>
                        
                        <h3>Requirements</h3>
                        <ul>
                            <li><strong>Bachelor Students:</strong> Introduction to Deep Learning, Machine Learning.</li>
                            <li><strong>Master Students:</strong> Deep Learning, Advanced Deep Learning (from Summer Semester 2025).</li>
                            <li><strong>Beneficial:</strong> Background in optimization (linear programming, convex/combinatorial optimization).</li>
                        </ul>
                        
                        <h3>Bachelor/Master Theses Topics (2025/2026)</h3>
                        <ul>
                            <li><strong>Multi-Camera Multiple Object Tracking:</strong> Improving trackformer methods and extending to multi-camera settings.</li>
                            <li><strong>Game Playing Agents:</strong> Reinforcement learning for games like Backgammon or Super Mario.</li>
                            <li><strong>Improving Optimization Solvers with Machine Learning:</strong> Reimplementing solvers in a differentiable way, learning parameters.</li>
                            <li><strong>Robotic Simulation:</strong> Learning control policies using simulators (ManiSkills, genesis).</li>
                            <li><strong>Splice-CNN with rational functions:</strong> Using rational functions in Graph Neural Networks for convolution.</li>
                            <li><strong>Reasoning traces:</strong> Training reasoning models (like S1) with traces from stronger models (e.g. R1).</li>
                            <li><strong>Learn approximate decision diagrams:</strong> For combinatorial problems with Reinforcement Learning.</li>
                            <li><strong>Vertex reconstruction:</strong> Reconstruct decaying particles from CERN detection data.</li>
                            <li><strong>Particle Image Velocimetry:</strong> Track particles in fluids for fluid flow measurement using event-based cameras.</li>
                        </ul>
                        
                        <h3>Own Ideas</h3>
                        <p>If you have your own ideas, we are generally open to supervising them if they fall within our expertise. Hints for choosing a topic:</p>
                        <ul>
                            <li>Is the goal clearly defined?</li>
                            <li>Can it be done in 3 (Bachelor) or 6 (Master) months?</li>
                            <li>Is it technically feasible?</li>
                            <li>Is there a technical contribution or novel aspect expected?</li>
                        </ul>
                        
                        <hr>
                        <p><strong>Contacting:</strong> If interested, please email with: which topics you are interested in, a transcript of records, and other relevant info (Github account, previous ML projects).</p>
                        """),
                Set.of(new Course("Machine Learning"), new Course("Deep Learning")),
                Set.of(new Tag("Machine Learning"), new Tag("Deep Learning"), new Tag("Optimization"), new Tag("Computer Vision"), new Tag("Robotics"), new Tag("AI"), new Tag("Reinforcement Learning"))));

        topics.add(new Topic("Softwaretechnik und Programmiersprachen (M. Leuschel)",
                "https://stups.pages.cs.uni-duesseldorf.de/abschlussarbeiten/offene-themen/",
                new Markdown("""
                        <h3>Überblick</h3>
                        <p>Unten finden Sie einige konkrete Themenvorschläge für Abschluss- und Projektarbeiten bei der Arbeitsgruppe für Softwaretechnik und Programmiersprachen. Beachten Sie unseren Betreuungsleitfaden, der Sie über den Ablauf der initialen Kontaktphase sowie der Abschlussarbeit selbst informiert.</p>
                        
                        <p>Die Themen aus unserer Gruppe versuchen meist, Ihre Abschlussarbeit in unsere Forschung zu integrieren. Hauptfokus unserer Forschung ist das Tool <strong>ProB</strong>, ein Model Checker und Constraint Solver für die B-Methode. ProB ist in Prolog geschrieben, aber Erweiterungen dafür sind auch bspw. in C, C++, Rust oder Python möglich. Es existiert zudem eine Java-API und eine Einbettung in Clojure.</p>
                        
                        <p>Die Abschlussarbeiten müssen nicht direkt mit Model-Checking-Algorithmen oder Modellierungsarbeiten in B zu tun haben. Beispielsweise sind auch Arbeiten möglich, die andere Werkzeuge integrieren, Programmzustände visualisieren, Programmanalyse- und Compilertechniken implementieren oder Erkenntnisse mittels Machine Learning gewinnen.</p>
                        
                        <p>Wir sind auch an Werkzeugen interessiert, die unsere Entwicklung und Lehre unterstützen (z.B. Typsysteme, Linter, Parser, Codegeneratoren, Jupyter Notebooks Kernel, Visualisierungen).</p>
                        
                        <p>Sie dürfen auch gerne selbst kreative Vorschläge für Abschlussarbeitsthemen einbringen; je näher diese an unserer Arbeit sind, desto wahrscheinlicher können wir Sie dabei sinnvoll betreuen.</p>
                        
                        <h3>Themen</h3>
                        
                        <h4>B / Event-B / Sicherheitskritische Systeme</h4>
                        <ul>
                            <li><strong>ProB Unterstützung für die CSSP</strong> (Projektarbeit, Masterarbeit) [B, Java]</li>
                            <li><strong>TLA2B Übersetzung von TLA+ nach B erweitern</strong> (Projektarbeit, Masterarbeit) [Java, B, TLA]</li>
                            <li><strong>TLC4B Übersetzung von B nach TLA+ erweitern</strong> (Projektarbeit, Masterarbeit) [Java, B, TLA]</li>
                            <li><strong>Analyse des Designs von Git / Mercurial mit Hilfe von B</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [Prolog, B]</li>
                            <li><strong>Simplifier für B-Prädikate</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [Prolog, B]</li>
                            <li><strong>Eine Übersetzung von Smart Contracts nach B</strong> (Projektarbeit, Masterarbeit) [B, Event-B]</li>
                            <li><strong>Model Checking von generiertem Code / Automatisches Refinement</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [B, Misc.]</li>
                            <li><strong>Migration von B2Program von ANTLR nach SableCC</strong> (Bachelorarbeit) [Java, B]</li>
                            <li><strong>Codegenerierung von B nach Clojure</strong> (Bachelorarbeit, Projektarbeit) [Java, Clojure, B]</li>
                            <li><strong>Generierung von Proof Obligations (POs) für B und Event-B innerhalb von ProB</strong> (Projektarbeit, Masterarbeit) [Java, Prolog, Event-B, B]</li>
                            <li><strong>Tool Support for Event-B Abstractions</strong> (Bachelorarbeit, Projektarbeit) [Java, Event-B]</li>
                            <li><strong>FRETish für ProB2-UI</strong> (Projektarbeit, Masterarbeit) [Java]</li>
                        </ul>
                        
                        <h4>Logische Programmierung</h4>
                        <ul>
                            <li><strong>plspec: Test-Case Generierung</strong> (Bachelorarbeit, Projektarbeit) [Prolog]</li>
                            <li><strong>Modellierung des Parallel Climbers Puzzles</strong> (Projektarbeit) [Prolog, B]</li>
                            <li><strong>Refactoring des partiellen Auswerters Ecce für Sicstus 4</strong> (Bachelorarbeit, Projektarbeit) [Prolog, B]</li>
                            <li><strong>DPLL(T)-basierter Solver für B bzw. B Logik für DPLL-basierten Solver</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [Prolog, B]</li>
                            <li><strong>Prolog Parser und Interpreter für Gödel</strong> (Bachelorarbeit, Masterarbeit) [Prolog]</li>
                            <li><strong>Open-Spiel Anbindung an Prolog</strong> (Bachelorarbeit, Projektarbeit) [Prolog]</li>
                            <li><strong>Animation, Model Checking, und Simulation von Robotersystemen in ProB</strong> (Projektarbeit, Masterarbeit) [Prolog, Java, B, CSP, PRISM]</li>
                        </ul>
                        
                        <h4>Künstliche Intelligenz und Machine Learning</h4>
                        <ul>
                            <li><strong>Expertensystem zur Validierung mit ProB</strong> (Projektarbeit, Masterarbeit) [Prolog, B]</li>
                            <li><strong>Formal Methods meet Data Analysis</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [Java]</li>
                            <li><strong>Validierung von Spiele KI durch Simulation</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [Java]</li>
                        </ul>
                        
                        <h4>Compilerbau</h4>
                        <ul>
                            <li><strong>Build Spy</strong> (Bachelorarbeit) [C++]</li>
                            <li><strong>DSL für semantische Informationen</strong> (Masterarbeit) [C++]</li>
                            <li><strong>Language Server Protocol</strong> (Masterarbeit) [C++]</li>
                        </ul>
                        
                        <h4>Misc.</h4>
                        <ul>
                            <li><strong>lisb: mehr Front- und Backends</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [Clojure]</li>
                            <li><strong>Modellierung von Aspekten einer Blockchain</strong> (Projektarbeit) [B, Event-B]</li>
                            <li><strong>Domänenspezifische 3D Visualisierung</strong> (Bachelorarbeit, Projektarbeit, Masterarbeit) [Java]</li>
                        </ul>
                        
                        <hr>
                        <p><strong>Kontakt</strong><br>
                        Prof. Dr. Michael Leuschel<br>
                        <a href="mailto:michael.leuschel@hhu.de">michael.leuschel@hhu.de</a></p>
                        """),
                Set.of(new Course("Einführung in die logische Programmierung"), new Course("Sicherheitskritische Systeme"), new Course("Compilerbau"), new Course("Softwaretechnik")),
                Set.of(new Tag("Software Engineering"), new Tag("B-Method"), new Tag("ProB"), new Tag("Prolog"), new Tag("Java"), new Tag("C++"), new Tag("Clojure"), new Tag("Compiler Construction"), new Tag("Formal Methods"), new Tag("Safety Critical Systems"), new Tag("AI"), new Tag("Machine Learning"))));
    }

    @Override
    public List<Topic> findAll() {
        return Collections.unmodifiableList(topics);
    }
}
