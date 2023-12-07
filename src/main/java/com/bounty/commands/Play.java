package com.bounty.commands;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Play implements ICommand {
    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String getDescription() {
        return "Will connect bot to the channel and play music";
    }

    @Override
    public List<OptionData> getOptions() {
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.STRING, "name", "Name or URL o the song to play", true));
        return options;
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        Member member = event.getMember();
        GuildVoiceState voiceState = member.getVoiceState();

        if (!voiceState.inAudioChannel()){
            event.reply("You need to be in a voice channel!").queue();
            return;
        }

        Member self = event.getGuild().getSelfMember();
        GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inAudioChannel()){
            event.getGuild().getAudioManager().openAudioConnection(voiceState.getChannel());
        }else if (selfVoiceState.getChannel() != voiceState.getChannel()){
            event.reply("You need to be in the same voice channel as me");
            return;
        }

        String name = event.getOption("name").getAsString();

        try {
            new URI(name);
        } catch (URISyntaxException e) {
            name = "yt:search:" + name;
        }



    }
}
