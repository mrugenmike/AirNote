package com.airnote.services.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class UserDetails {

    @JsonProperty("referral_link")String referral_link;
    @JsonProperty("display_name")String display_name;
    @JsonProperty("uid")Integer uid;
    @JsonProperty("email_verified")Boolean email_verfied;
    @JsonProperty("team")String team;
    @JsonProperty("quota_info")Map <String ,String> quota_info;
    @JsonProperty("country")String country;
    @JsonProperty("email")String email;


    /**
     * {"referral_link": "https://db.tt/E0Fcu8lJ",
     * "display_name": "Air Note",
     * "uid": 351441185,
     * "email_verified": true,
     * "team": null,
     * "quota_info": {"datastores": 0, "shared": 0, "quota": 2684354560, "normal": 249159},
     * "country": "US",
     * "email": "airnote273@gmail.com"}
     * */
}
