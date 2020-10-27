package com.DesignPattern.patterns.Adapter_Pattern;

import com.DesignPattern.patterns.Adapter_Pattern.mediaPlayer.AdvancedMediaPlayer;
import com.DesignPattern.patterns.Adapter_Pattern.mediaPlayer.Impl.Mp4Player;
import com.DesignPattern.patterns.Adapter_Pattern.mediaPlayer.Impl.VlcPlayer;
import com.DesignPattern.patterns.Adapter_Pattern.mediaPlayer.MediaPlayer;

public class MediaAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc") ){
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advancedMusicPlayer.playVlc(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}