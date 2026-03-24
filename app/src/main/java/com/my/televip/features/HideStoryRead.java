package com.my.televip.features;

public class HideStoryRead {

    public static boolean isReadStoriesRequest(Object object){
        return object.getClass().getName().contains("TL_stories_readStories") ||
                object.getClass().getName().contains("TL_stories_incrementStoryViews");
    }
}
