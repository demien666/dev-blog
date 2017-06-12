package com.demien.mongoblog.controller;

import com.demien.mongoblog.App;
import freemarker.template.SimpleHash;
import spark.Request;
import spark.Response;

import java.io.IOException;

/**
 * Created by demien on 1/31/16.
 */
public class PostController {

    public static class NewPostRoute extends AbstractRoute {

        public NewPostRoute(String templateName) throws IOException {
            super(templateName);
        }

        @Override
        public Object handle(Request request, Response response) throws Exception {
            // get cookie
            String username = App.getContext().getSessionDAO().findUserNameBySessionId(getSessionCookie(request));

            if (username == null) {
                // looks like a bad request. user is not logged in
                response.redirect("/login");
            }
            else {
                SimpleHash root = new SimpleHash();
                root.put("username", username);

                template.process(root, writer);
            }
            return writer;
        }
    }


}
