package com.demien.mongoblog.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by demien on 1/31/16.
 */
public abstract class AbstractRoute implements spark.Route {
    final Template template;
    StringWriter writer = new StringWriter();
    static  final Configuration cfg;

    static {
        cfg = new Configuration();
        cfg.setClassForTemplateLoading(AbstractRoute.class, "/freemarker");
    }

    public AbstractRoute(final String templateName) throws IOException {
        template = cfg.getTemplate(templateName);
    }
}
