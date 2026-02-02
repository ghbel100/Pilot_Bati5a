package hhu.propra.bati5a.rules;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "hhu.propra.bati5a", importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {


      @ArchTest
      static final ArchRule onion_architecture_is_respected = onionArchitecture()
      .domainModels("..domain.model..")
      .domainServices("..domain.service..")
      .applicationServices("..application..")
       .adapter("infrastructure", "..infrastructure..")
      .adapter("web", "..web..", "..controllers..");


    @ArchTest
    static final ArchRule domain_should_not_depend_on_spring = noClasses().that().resideInAPackage("..domain..")
            .should().dependOnClassesThat().resideInAPackage("org.springframework..");
}
