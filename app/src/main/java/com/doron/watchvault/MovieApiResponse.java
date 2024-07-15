package com.doron.watchvault;

import com.doron.watchvault.network.models.RVModel;

import java.util.List;

public class MovieApiResponse {
    private List<RVModel> results;

    public List<RVModel> getResults() {
        return results;
    }
}