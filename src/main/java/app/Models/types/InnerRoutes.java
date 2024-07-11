package app.Models.types;

public enum InnerRoutes {
    HOME("Home"),
    FLIGHTS("Flights"),
    PASSENGERS("Passengers"),
    TICKETS("Tickets"),
    REPORTS("Reports");

    private final String route;

    InnerRoutes(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}