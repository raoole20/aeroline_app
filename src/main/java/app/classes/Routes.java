package app.classes;

import app.Models.types.InnerRoutes;
import app.Models.types.Payload;
import app.Models.types.VariableChangeListener;
import app.Models.utils.ObservableVariable;


public class Routes {
    private InnerRoutes ActiveRoute;
    private ObservableVariable<InnerRoutes> observableString;
    private Payload payload;
    private InnerRoutes prevRoutes;

    public Routes(
        InnerRoutes defaultRoute,
        VariableChangeListener routeCallback
    ) {
        this.ActiveRoute = defaultRoute;
        this.payload = new Payload();
      
        // init observer
        this.observableString = new ObservableVariable<InnerRoutes>(this.ActiveRoute);
        this.observableString.addListener(routeCallback);
    }

    public InnerRoutes getRoute() {
        return this.ActiveRoute;
    }

    public void setRoute(InnerRoutes route, Payload payload) {
        this.prevRoutes = this.ActiveRoute;
        this.payload = payload;
        if(this.ActiveRoute == route)
            return;
        this.ActiveRoute = route;
        this.observableString.setValue(route);

        System.out.println("Route changed to: " + route);
    }

    public void setRoute(InnerRoutes route) {
        this.prevRoutes = this.ActiveRoute;
        if(this.ActiveRoute == route)
            return;
        this.resetPayload(route);
        this.ActiveRoute = route;
        this.observableString.setValue(route);

        System.out.println("Route changed to: " + route);
    }

    private void resetPayload(Object payload) {
       this.payload.flighhtID = -1;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public InnerRoutes getPrevRoutes() {
        return this.prevRoutes;
    }
}
