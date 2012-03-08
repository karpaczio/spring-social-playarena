package pl.playarena.api.impl;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
@JsonDeserialize(as = Profile.class)
public abstract class ProfileMixIn {

    @JsonProperty
    private String name;
    
    @JsonProperty
    private String id;
    
    @JsonProperty
    private String profileUrl;
    
    @JsonProperty
    private String thumbnailUrl;

}
