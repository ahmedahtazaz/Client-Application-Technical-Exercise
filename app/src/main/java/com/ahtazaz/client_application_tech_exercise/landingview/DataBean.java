package com.ahtazaz.client_application_tech_exercise.landingview;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "data",strict = false)
public class DataBean {

    @Element(name = "buy", required = false)
    public String buy;
    @Element(name = "sell", required = false)
    public String sell;
    @Element(name = "spot_price", required = false)
    public String spot_price;
    @Element(name = "timestamp", required = false)
    public String timestamp;
}
