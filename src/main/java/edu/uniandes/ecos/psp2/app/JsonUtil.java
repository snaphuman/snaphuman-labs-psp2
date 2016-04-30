package edu.uniandes.ecos.psp2.app;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by snaphuman on 4/29/16.
 */

// Gracias a https://dzone.com/articles/building-simple-restful-api
public class JsonUtil {
    public static String toJson(Object object){

        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {

        return JsonUtil::toJson;
    }
}
