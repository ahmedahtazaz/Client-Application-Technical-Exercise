package com.ahtazaz.client_application_tech_exercise.landingview;

import org.simpleframework.xml.Element;

public class PriceNetworkResponse {

    @Element(name = "result", required = false)
    public String result;
    @Element(name = "data", required = false)
    public DataBean data;
}
