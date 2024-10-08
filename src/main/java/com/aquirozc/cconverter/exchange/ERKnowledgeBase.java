package com.aquirozc.cconverter.exchange;

import java.util.Map;
import com.google.gson.annotations.SerializedName;

public record ERKnowledgeBase(

    @SerializedName("conversion_rates")
    Map<String,Float> rates,

    @SerializedName("time_last_update_unix")
    long lastUpdatedOn,

    @SerializedName("time_next_update_unix")
    long scheduledUpdateOn,

    String result

) {

}