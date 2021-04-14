package com.personal.knowledge.personalknowledge.commons;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.processor.Processor;

import java.util.List;

public class CommonsTest {

    public <T> T fixture(final Class<T> clazz, final String templateName) {
        return Fixture.from(clazz).gimme(templateName);
    }

    public <T> T fixture(final Class<T> clazz, final Processor processor, final String templateName) {
        return Fixture.from(clazz).uses(processor).gimme(templateName);
    }

    public <T> List<T> fixtures(final Class<T> clazz, final Processor processor, final String... templateNames) {
        return Fixture.from(clazz).uses(processor).gimme(templateNames.length, templateNames);
    }

    public <T> List<T> fixtures(final Class<T> clazz, final String... templateNames) {
        return Fixture.from(clazz).gimme(templateNames.length, templateNames);
    }

}
