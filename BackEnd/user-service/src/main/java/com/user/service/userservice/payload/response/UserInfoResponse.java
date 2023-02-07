package com.user.service.userservice.payload.response;

import java.util.List;

public class UserInfoResponse {
  private String id;
  private String username;
  private String fullname;
  private String email;
  private List<String> roles;

  public UserInfoResponse(String id, String username, String fullname,  String email, List<String> roles) {
    this.id = id;
    this.username = username;
    this.fullname = fullname;
    this.email = email;
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullname() {return fullname;}

  public void  setFullname(String fullname) {this.fullname = fullname;}

  public List<String> getRoles() {
    return roles;
  }
}
