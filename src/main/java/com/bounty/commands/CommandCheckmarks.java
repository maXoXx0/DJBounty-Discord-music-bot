package com.bounty.commands;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class CommandCheckmarks {
    public static void checkPlayersLocation(SlashCommandInteractionEvent event){
        Member member = event.getMember();
        GuildVoiceState voiceState = member.getVoiceState();

        if (!voiceState.inAudioChannel()){
            event.reply("You need to be in a voice channel!").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()){
            event.reply("I am not in an audio channel!").queue();
            return;
        }

        if (selfVoiceState.getChannel() != voiceState.getChannel()){
            event.reply("You are not in the same channel as me!").queue();
        }
    }
}
