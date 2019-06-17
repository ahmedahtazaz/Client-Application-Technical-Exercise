package com.ahtazaz.client_application_tech_exercise.registration;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


public class RegisterNetworkResponse
{
    @Element(name = "result", required = false)
    public String result;
    @Element(name = "data", required = false)
    public DataBean data;
}
