package com.example.websocketproject.entity;

import com.example.websocketproject.entity.User;
public class UserDTO {
    private String user_id;
    private String pw;

    public static class LoginInfo{
        private String user_id;
        private String pw;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getPw() {
            return pw;
        }

        public void setPw(String pw) {
            this.pw = pw;
        }



    }
}
