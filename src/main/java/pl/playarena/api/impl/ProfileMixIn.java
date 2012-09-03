package pl.playarena.api.impl;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

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

    @JsonProperty
    private String displayName;
    
    @JsonProperty
    private String familyName;
    
    @JsonProperty
    private Integer age;
    
    @JsonProperty
    @JsonDeserialize(using = PolishDateDeserializer.class)
    private Date dateOfBirth;
    
    @JsonProperty
    private Gender gender;
    
    @JsonProperty
    private String aboutMe;

}
