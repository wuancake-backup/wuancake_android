package com.example.frostmaki.mvpdemo.bean;

/**
 * Created by Frostmaki on 2016/6/2.
 */
public class User {

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        private String username;
        private String password;
    }

