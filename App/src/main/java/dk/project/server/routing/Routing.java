package dk.project.server.routing;

// Imports
import dk.project.controller.PageController;
import dk.project.controller.Search.SearchController;
import io.javalin.Javalin;

public class Routing {

    // Attributes

    // _________________________________________________

    public static void registerRoutes(Javalin app) {

        PageController.registerRoutes(app);
        SearchController.registerRoutes(app);

    }

} // Routing end