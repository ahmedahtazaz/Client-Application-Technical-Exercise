package com.ahtazaz.client_application_tech_exercise.registration;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "data",strict = false)
public class DataBean {

    @Element(name = "api_token", required = false)
    public String api_token;
    @Element(name = "public_key", required = false)
    public String public_key;
    @Element(name = "api_key", required = false)
    public String api_key;
    @Element(name = "account_number", required = false)
    public String account_number;
}
