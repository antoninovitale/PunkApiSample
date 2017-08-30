package antoninovitale.dropcodechallenge.details.section.model.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import antoninovitale.dropcodechallenge.api.model.MashTemp;
import antoninovitale.dropcodechallenge.api.model.Method;
import antoninovitale.dropcodechallenge.api.model.Temp;
import antoninovitale.dropcodechallenge.details.section.model.MethodSectionModel;

/**
 * Created by antoninovitale on 30/08/2017.
 */
public class MethodSectionModelMapper {

    public static List<MethodSectionModel> convertMethods(Method method) {
        List<MethodSectionModel> models = Collections.emptyList();
        List<MashTemp> mashTemps = method.getMashTemp();
        if (mashTemps != null && !mashTemps.isEmpty()) {
            models = new ArrayList<>(mashTemps.size());
            for (MashTemp mashTemp : mashTemps) {
                MethodSectionModel model = new MethodSectionModel(convertTemp(mashTemp.getTemp())
                        , TimeUnit.MINUTES.toMillis((long) mashTemp.getDuration()));
                models.add(model);
            }
        }
        return models;
    }

    private static String convertTemp(Temp temp) {
        return temp != null ? String.format("%s %s", temp.getValue(), temp.getUnit().toUpperCase
                ().charAt(0)) : null;
    }

}
