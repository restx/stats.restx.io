package restx.stats.central;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import restx.annotations.GET;
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

    @PermitAll
    @GET("/v1/stats/app/:appNameHash")
    public Optional<RestxStats> findStats(String appNameHash) {
        return Optional.fromNullable(Iterables.getFirst(
                statsCollection.get().find("{appNameHash: #}", appNameHash)
                        .sort("{timestamp: -1}").limit(1).as(RestxStats.class),
                null));
    }
}
