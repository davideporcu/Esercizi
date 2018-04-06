package com.porcu.davide.campionato.pojo;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by genji on 4/2/17. MODEL created using
 * http://www.jsonschema2pojo.org/
 * settings
 * - Source type: JSON
 * - Annotation style: GSON
 */

// il campionato complessivo

public class Pojo {
    @SerializedName("name")
    @Expose
    private String name; // es.  "Serie A 2016/17"
    @SerializedName("rounds")
    @Expose
    private List<Round> rounds = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}

// la singola partita

// la giornata


