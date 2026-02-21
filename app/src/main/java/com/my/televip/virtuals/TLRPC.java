package com.my.televip.virtuals;

import com.my.televip.Utils;
import com.my.televip.utils.FieldUtils;

import java.util.ArrayList;

import de.robv.android.xposed.XposedHelpers;

public class TLRPC {
    public static class Peer {
        private final Object peer;

        public Peer(Object peer) {
            this.peer = peer;
        }
        public long getUser_id(){
            return XposedHelpers.getLongField(peer,"user_id");
        }
        public long getChat_id(){
            return XposedHelpers.getLongField(peer,"chat_id");
        }
        public long getChannel_id(){
            return XposedHelpers.getLongField(peer,"channel_id");
        }
    }
    public static class Message {
        public final Object message;
        private int id;
        public Message(Object message) {
            this.message = message;
        }
        public int getID(){
            if (id == 0){
                id = XposedHelpers.getIntField(message, "id");
            }
            return id;
        }
        public String getMessage(){
            return (String) XposedHelpers.getObjectField(message, "message");
        }
        public Object getMessages(){
            return XposedHelpers.getObjectField(message, "messages");
        }
        public Peer getFrom_id(){
            return new TLRPC.Peer(XposedHelpers.getObjectField(message, "from_id"));
        }
        public int getFlags(){
            return XposedHelpers.getIntField(message,"flags");
        }
        public void setFlags(int flags){
            XposedHelpers.setIntField(message, "flags", flags);
        }
    }

    public static class TL_updateDeleteChannelMessages {
        private final Object instance;

        public TL_updateDeleteChannelMessages(Object instance)
        {
            this.instance = instance;
        }

        public long getChannelID()
        {
            try
            {
                return FieldUtils.getFieldLongOfClass(this.instance, "channel_id");
            }
            catch (Throwable e)
            {
                Utils.log(e);
            }
            return Long.MIN_VALUE;
        }

        public ArrayList<Integer> getMessages()
        {
            try
            {

                return Utils.castList(FieldUtils.getFieldClassOfClass(this.instance, "messages"), Integer.class);
            }
            catch (Throwable e)
            {
                Utils.log(e);
            }
            return null;
        }
    }

    public static class TL_updateDeleteMessages {
        private final Object instance;

        public TL_updateDeleteMessages(Object instance)
        {
            this.instance = instance;
        }

        public ArrayList<Integer> getMessages()
        {
            try
            {

                return Utils.castList(FieldUtils.getFieldClassOfClass(this.instance, "messages"), Integer.class);
            }
            catch (Throwable e)
            {
                Utils.log(e);
            }
            return null;
        }
    }
}
