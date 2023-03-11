package com.ever.cent.domain.dto;

import java.util.List;

import lombok.Value;

@Value
public class UserInfo {
    private String id;
    private String displayName;
    private String email;
    private List<String> roles;
}
