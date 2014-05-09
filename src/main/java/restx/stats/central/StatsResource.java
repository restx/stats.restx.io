package restx.stats.central;

import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import restx.security.PermitAll;
import restx.stats.RestxStats;

import javax.inject.Named;

/**
 * Date: 9/5/14
 * Time: 09:50
 */
@RestxResource
@Component
public class StatsResource {
    private final JongoCollection statsCollection;

    public StatsResource(@Named("stats") JongoCollection statsCollection) {
        this.statsCollection = statsCollection;
    }

    @PermitAll
    @POST("/v1/stats")
    public void addStats(RestxStats stats) {
        statsCollection.get().save(stats);
    }
}
