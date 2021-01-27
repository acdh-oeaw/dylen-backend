package acdh.oeaw.ac.at.dylenegonetworkservice;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchitectureTest {
   @Test
    public void hasNoCyclicDependencies() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("acdh.oeaw.ac.at.dylenegonetworkserice");
        ArchRule rule = slices().matching("acdh.oeaw.ac.at.dylenegonetworkservice.(*)").should().beFreeOfCycles();

        rule.check(importedClasses);
    }
}
