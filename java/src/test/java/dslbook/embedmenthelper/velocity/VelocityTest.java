package dslbook.embedmenthelper.velocity;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;

import java.util.*;
import static java.util.Collections.singletonMap;
import java.io.StringWriter;

import static dslbook.embedmenthelper.velocity.Person.name;


/**
 * @author Michael Hunger
 * @since 17.05.2009
 */
public class VelocityTest {
    @Test
    public void testRenderPerson() {
        final String expected = "<ul>\n" +
                "<li><a href=\"http://me.com\">me</a></li>\n" +
                "<li><a href=\"mailto:myself@me.com\">myself</a></li>\n" +
                "<li>I</li>\n" +
                "</ul>";
        final Book book = new Book(name("me").url("http://me.com"), name("myself").email("myself@me.com"), name("I"));
        final String generated = generate("book", singletonMap("book", BookPM.present(book)));
        assertEquals(strip(expected), strip(generated));
    }

    private String strip(final String expected) {
        return expected.replaceAll("\\s+", "");
    }

    public String generate(final String templateFile, final Map<String, ? extends Object> inputs) {
        try {
            final VelocityEngine engine = initVelocity();
            final Template template = engine.getTemplate(templateFile + ".vm");
            final StringWriter writer = new StringWriter();
            final Context ctx = new VelocityContext(new HashMap<String, Object>(inputs));
            template.merge(ctx, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error rendering template " + templateFile + " with inputs" + inputs, e);
        }
    }

    private VelocityEngine initVelocity() throws Exception {
        final VelocityEngine engine = new VelocityEngine();
        final Properties props = new Properties();
        props.put("resource.loader", "file,class");
        props.put("class.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init(props);
        return engine;
    }

}



