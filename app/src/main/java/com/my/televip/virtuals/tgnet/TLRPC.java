package com.my.televip.virtuals.tgnet;

import com.my.televip.Utils;
import com.my.televip.loadClass;
import com.my.televip.obfuscate.AutomationResolver;
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

    public static class User {
        Object user;

        public User(Object user) {
            this.user = user;
        }

        public String getPhone() {
            return (String) XposedHelpers.getObjectField(user, AutomationResolver.resolve("User", "phone", AutomationResolver.ResolverType.Field));
        }

        public void setPhone(String phone){
            XposedHelpers.setObjectField(user, AutomationResolver.resolve("User", "phone", AutomationResolver.ResolverType.Field), phone);
        }

        public Object getUser(){
            return user;
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

        public void setTtl(Object ttl){
            XposedHelpers.setObjectField(message, "ttl", ttl);
        }

        public static Message TLdeserialize(NativeByteBuffer stream, int constructor, boolean exception){
            return new Message(XposedHelpers.callStaticMethod(loadClass.getTLRPC$MessageClass(), AutomationResolver.resolve("TLRPC$Message", "TLdeserialize", AutomationResolver.ResolverType.Method), stream.nativeByteBuffer, constructor, exception));
        }

        public void readAttachPath(NativeByteBuffer stream, long currentUserId){
            XposedHelpers.callMethod(message, AutomationResolver.resolve("TLRPC$Message", "readAttachPath", AutomationResolver.ResolverType.Method), stream.nativeByteBuffer, currentUserId);
        }
    }
    public static class InputPeer {
        public final Object inputPeer;
        public InputPeer(Object message) {
            this.inputPeer = message;
        }
        public long getUser_id(){
            return XposedHelpers.getLongField(inputPeer,"user_id");
        }
        public long getChat_id(){
            return XposedHelpers.getLongField(inputPeer,"chat_id");
        }
        public long getChannel_id(){
            return XposedHelpers.getLongField(inputPeer,"channel_id");
        }

    }

    public static class messages_Messages {
        Object messages_Messages;

        public messages_Messages(Object messages) {
            messages_Messages = messages;
        }
        public ArrayList<Object> getMessages(){
            return (ArrayList<Object>) XposedHelpers.getObjectField(messages_Messages, AutomationResolver.resolve("TLRPC$messages_Messages", "messages", AutomationResolver.ResolverType.Field));
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

    public static class TL_messages_affectedMessages {
        final Object instance;

        public TL_messages_affectedMessages()
        {
            this.instance = XposedHelpers.newInstance(loadClass.getTL_messages_affectedMessagesClass());
        }
        public TL_messages_affectedMessages(Object instance)
        {
            this.instance = instance;
        }

        public int getPts(){
            return XposedHelpers.getIntField(instance, "pts");
        }

        public int getPtsCount(){
            return XposedHelpers.getIntField(instance, "pts_count");
        }
        public void setPts(int pts){
            XposedHelpers.setIntField(instance, "pts", pts);
        }

        public void setPtsCount(int pts_count){
            XposedHelpers.setIntField(instance, "pts_count", pts_count);
        }

        public Object getTL_messages_affectedMessages(){
            return instance;
        }

    }

    public static class TL_channels_readHistory {
        final Object instance;

        public TL_channels_readHistory()
        {
            this.instance = XposedHelpers.newInstance(loadClass.getTL_channels_readHistoryClass());
        }
        public TL_channels_readHistory(Object instance)
        {
            this.instance = instance;
        }

        public void setChannel(Object channel){
            XposedHelpers.setObjectField(instance, "channel", channel);
        }

        public void setMax_id(int max_id){
            XposedHelpers.setIntField(instance, "max_id", max_id);
        }

        public Object getTL_channels_readHistory(){
            return instance;
        }
    }

    public static class TL_messages_readHistory {
        final Object instance;

        public TL_messages_readHistory()
        {
            this.instance = XposedHelpers.newInstance(loadClass.getTL_messages_readHistoryClass());
        }

        public TL_messages_readHistory(Object instance)
        {
            this.instance = instance;
        }

        public void setPeer(InputPeer peer){
            XposedHelpers.setObjectField(instance, "peer", peer.inputPeer);
        }

        public void setMax_id(int max_id){
            XposedHelpers.setIntField(instance, "max_id", max_id);
        }

        public Object getTL_messages_readHistory(){
            return instance;
        }

    }
}
