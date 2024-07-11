package app.classes;

import app.Models.types.InnerRoutes;
import app.Models.types.VariableChangeListener;
import app.Models.utils.ObservableVariable;


public class Routes {
    private InnerRoutes ActiveRoute;
    private ObservableVariable<InnerRoutes> observableString;

    public Routes(
        InnerRoutes defaultRoute,
        VariableChangeListener routeCallback
    ) {
        this.ActiveRoute = defaultRoute;
      
        // init observer
        this.observableString = new ObservableVariable<InnerRoutes>(this.ActiveRoute);
        this.observableString.addListener(routeCallback);
    }

    public InnerRoutes getRoute() {
        return this.ActiveRoute;
    }

    public void setRoute(InnerRoutes route) {
        if(this.ActiveRoute == route)
            return;
        
        this.ActiveRoute = route;
        this.observableString.setValue(route);

        System.out.println("Route changed to: " + route);
    }
}
