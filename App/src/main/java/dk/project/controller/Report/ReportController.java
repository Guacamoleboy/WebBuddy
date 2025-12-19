// Package
package dk.project.controller.Report;

// Imports
import dk.project.server.ThymeleafSetup;
import io.javalin.Javalin;

public class ReportController {

    // Attributes

    // _______________________________________________

    public static void registerRoutes(Javalin app) {

        app.get("/report", ctx -> ctx.html(ThymeleafSetup.render("report.html", null)));;

    }

} // PageController end