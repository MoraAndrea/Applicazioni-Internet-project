package it.polito.ai.labs.lab2.models.json;

import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@JsonComponent
public class Line {
    public String name;
    public ArrayList<PediStop> outboundStops;
    public ArrayList<PediStop> returnStops;

    public Line() {
    }
}
