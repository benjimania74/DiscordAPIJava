package fr.benjimania74.daj.bot.object.channel;

import lombok.Getter;

import java.util.EnumSet;

public interface IVoiceChannel extends IChannel{
    int getBitRate();
    RTCRegion getRTCRegion();
    VideoQualityMode getVideoQualityMode();

    @Getter
    enum VideoQualityMode {
        AUTO(1),
        FULL(2);

        private final int id;

        VideoQualityMode(int id){
            this.id = id;
        }

        public static VideoQualityMode get(int id){
            for (VideoQualityMode vqm : EnumSet.allOf(VideoQualityMode.class)){
                if(vqm.getId() == id){
                    return vqm;
                }
            }
            return AUTO;
        }
    }

    @Getter
    class RTCRegion {
        private final String regionId;
        private final String regionName;

        public RTCRegion(String regionId, String regionName){
            this.regionId = regionId;
            this.regionName = regionName;
        }
    }
}
