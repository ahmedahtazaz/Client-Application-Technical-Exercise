package com.ahtazaz.client_application_tech_exercise.registration;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class RequestRegisterParams {

    public LinkedHashMap<String, Object> getParams(String email, String uuid, String data, boolean tnc)
    {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("email", email);
        map.put("uuid", uuid);
        map.put("data", data);
        map.put("tnc", tnc);

        return map;
    }
}
