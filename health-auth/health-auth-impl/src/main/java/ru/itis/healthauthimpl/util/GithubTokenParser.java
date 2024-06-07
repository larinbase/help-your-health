package ru.itis.healthauthimpl.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.Date;

public class GithubTokenParser {
    public static String parseAccessToken(String response){
        String[] array = response.split("&");
        return StringUtils.delete(array[0], "access_token=");
    }

    public static UserInfo parseUserJson(String response){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, UserInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @ToString
    public static class UserInfo{
        private String login;
        private int id;
        private String node_id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;
        private Object name;
        private Object company;
        private String blog;
        private Object location;
        private Object email;
        private Object hireable;
        private Object bio;
        private Object twitter_username;
        private int public_repos;
        private int public_gists;
        private int followers;
        private int following;
        private Date created_at;
        private Date updated_at;
    }
}
