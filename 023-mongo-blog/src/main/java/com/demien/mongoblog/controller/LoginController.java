package com.demien.mongoblog.controller;

import freemarker.template.SimpleHash;
import spark.Request;
import spark.Response;

import java.io.IOException;

/**
 * Created by demien on 1/31/16.
 */
public class LoginController {

    public static class SignUpRoute extends AbstractRoute {

        public SignUpRoute(String templateName) throws IOException {
            super(templateName);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            SimpleHash root = new SimpleHash();

            // initialize values for the form.
            root.put("username", "");
            root.put("password", "");
            root.put("email", "");
            root.put("password_error", "");
            root.put("username_error", "");
            root.put("email_error", "");
            root.put("verify_error", "");
            template.process(root, writer);

            return writer;
        }
    }
}
