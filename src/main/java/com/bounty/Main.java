package com.bounty;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
public class Main {
    public static void main(String[] args) {
        String token = Token.token;
        JDA jda = JDABuilder.createDefault(token).build();
    }
}
