package org.jboss.wolf.validator.impl.bom;

import static org.jboss.wolf.validator.impl.TestUtil.pom;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.maven.model.Model;
import org.jboss.wolf.validator.impl.AbstractTest;
import org.junit.Test;

public class TestBomFilterSimple extends AbstractTest {
    
    @Test
    public void shouldRecognizeBomByRegex1() {
        BomFilterSimple bomFilter = new BomFilterSimple("com.acme:foo:pom:.*");
        
        Model foo = pom().artifactId("foo").groupId("com.acme").packaging("pom").create(repoFooDir);
        Model bar = pom().artifactId("bar").groupId("com.acme").packaging("pom").create(repoFooDir);
        
        assertTrue(bomFilter.isBom(foo));
        assertFalse(bomFilter.isBom(bar));
    }
    
    @Test
    public void shouldRecognizeBomByRegex2() {
        BomFilterSimple bomFilter = new BomFilterSimple(".*:billOfMaterials:pom:.*");
        
        Model bom1 = pom().artifactId("billOfMaterials:pom").groupId("com.acme.foo").packaging("pom").create(repoFooDir);
        Model bom2 = pom().artifactId("billOfMaterials:pom").groupId("com.acme.bar").packaging("pom").create(repoFooDir);
        
        assertTrue(bomFilter.isBom(bom1));
        assertTrue(bomFilter.isBom(bom2));
    }

}
