package com.tt.doit.business.monitoring.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.LongSummaryStatistics;

@Path("boundary-statistics")
@Stateless
public class BoundaryStatisticResource {
    @Inject
    MonitorSink ms;

    @GET
    public JsonObject get() {
        LongSummaryStatistics statistic = ms.getStatistic();

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObject object = objectBuilder.add("average-duration", statistic.getAverage())
                .add("count", statistic.getCount())
                .add("sum", statistic.getSum())
                .add("min-duration",statistic.getMin())
                .add("max-duration", statistic.getMax())
                .build();
        return object;
    }
}
