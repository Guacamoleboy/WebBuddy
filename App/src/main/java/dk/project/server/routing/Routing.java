package dk.project.server.routing;

// Imports
import dk.project.controller.PageController;
import io.javalin.Javalin;

public class Routing {

    // Attributes

    // _________________________________________________

    public static void registerRoutes(Javalin app) {

        PageController.registerRoutes(app);

        // Last
        //registerErrorRoutes(app);

    }

} // Routing end