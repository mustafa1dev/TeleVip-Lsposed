package com.my.televip.virtuals.tgnet;

import com.my.televip.Class.ClassLoad;
import com.my.televip.Class.ClassNames;
import com.my.televip.logging.Logger;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.utils.Utils;

import java.util.ArrayList;

import com.my.televip.reflect.XReflect;

public class TLRPC {
    public static class Peer {
        private final Object peer;

        public Peer(Object peer) {
            this.peer = peer;
        }
        public long getUser_id(){
            return XReflect.getLongField(peer, AutomationResolver.resolve("TLRPC$Peer", "user_id", AutomationResolver.ResolverType.Field));
        }
        public long getChat_id(){
            return XReflect.getLongField(peer, AutomationResolver.resolve("TLRPC$Peer", "chat_id", AutomationResolver.ResolverType.Field));
        }
        public long getChannel_id(){
            return XReflect.getLongField(peer, AutomationResolver.resolve("TLRPC$Peer", "channel_id", AutomationResolver.ResolverType.Field));
        }
    }

    public static class User {
        Object user;

        public User(Object user) {
            this.user = user;
        }

        public String getPhone() {
            return (String) XReflect.getObjectField(user, AutomationResolver.resolve("TLRPC$User", "phone", AutomationResolver.ResolverType.Field));
        }

        public void setPhone(String phone){
            XReflect.setObjectField(user, AutomationResolver.resolve("TLRPC$User", "phone", AutomationResolver.ResolverType.Field), phone);
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
                id = XReflect.getIntField(message, AutomationResolver.resolve("TLRPC$Message", "id", AutomationResolver.ResolverType.Field));
            }
            return id;
        }

        public String getMessage(){
            return (String) XReflect.getObjectField(message, AutomationResolver.resolve("TLRPC$Message", "message", AutomationResolver.ResolverType.Field));
        }

        public Peer getFrom_id(){
            return new TLRPC.Peer(XReflect.getObjectField(message, AutomationResolver.resolve("TLRPC$Message", "from_id", AutomationResolver.ResolverType.Field)));
        }

        public int getFlags(){
            return XReflect.getIntField(message, AutomationResolver.resolve("TLRPC$Message", "flags", AutomationResolver.ResolverType.Field));
        }
        public void setFlags(int flags){
            XReflect.setIntField(message, AutomationResolver.resolve("TLRPC$Message", "flags", AutomationResolver.ResolverType.Field), flags);
        }

        public int getTtl(){
            return XReflect.getIntField(message, AutomationResolver.resolve("TLRPC$Message", "ttl", AutomationResolver.ResolverType.Field));
        }
        public void setTtl(Object ttl){
            XReflect.setObjectField(message, AutomationResolver.resolve("TLRPC$Message", "ttl", AutomationResolver.ResolverType.Field), ttl);
        }

        public static Message TLdeserialize(NativeByteBuffer stream, int constructor, boolean exception){
            return new Message(XReflect.callStaticMethod(ClassLoad.getClass(ClassNames.TL_MESSAGE), AutomationResolver.resolve("TLRPC$Message", "TLdeserialize", AutomationResolver.ResolverType.Method), stream.nativeByteBuffer, constructor, exception));
        }

        public void readAttachPath(NativeByteBuffer stream, long currentUserId){
            XReflect.callMethod(message, AutomationResolver.resolve("TLRPC$Message", "readAttachPath", AutomationResolver.ResolverType.Method), stream.nativeByteBuffer, currentUserId);
        }
    }
    public static class InputPeer {
        public final Object inputPeer;
        public InputPeer(Object message) {
            this.inputPeer = message;
        }
        public long getUser_id(){
            return XReflect.getLongField(inputPeer, AutomationResolver.resolve("TLRPC$InputPeer", "user_id", AutomationResolver.ResolverType.Field));
        }
        public long getChat_id(){
            return XReflect.getLongField(inputPeer,AutomationResolver.resolve("TLRPC$InputPeer", "chat_id", AutomationResolver.ResolverType.Field));
        }
        public long getChannel_id(){
            return XReflect.getLongField(inputPeer,AutomationResolver.resolve("TLRPC$InputPeer", "channel_id", AutomationResolver.ResolverType.Field));
        }

    }

    public static class messages_Messages {
        Object messages_Messages;

        public messages_Messages(Object messages) {
            messages_Messages = messages;
        }
        public ArrayList<Object> getMessages(){
            return (ArrayList<Object>) XReflect.getObjectField(messages_Messages, AutomationResolver.resolve("TLRPC$messages_Messages", "messages", AutomationResolver.ResolverType.Field));
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
                return XReflect.getLongField(this.instance,  AutomationResolver.resolve("TL_update$TL_updateDeleteChannelMessages", "channel_id", AutomationResolver.ResolverType.Field));
            }
            catch (Throwable e)
            {
                Logger.e(e);
            }
            return Long.MIN_VALUE;
        }

        public ArrayList<Integer> getMessages()
        {
            try
            {

                return Utils.castList(XReflect.getObjectField(this.instance, AutomationResolver.resolve("TL_update$TL_updateDeleteChannelMessages", "messages", AutomationResolver.ResolverType.Field)), Integer.class);
            }
            catch (Throwable e)
            {
                Logger.e(e);
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
                return Utils.castList(XReflect.getObjectField(this.instance, AutomationResolver.resolve("TL_update$TL_updateDeleteMessages", "messages", AutomationResolver.ResolverType.Field)), Integer.class);
            }
            catch (Throwable e)
            {
                Logger.e(e);
            }
            return null;
        }
    }

    public static class TL_messages_affectedMessages {
        final Object instance;

        public TL_messages_affectedMessages()
        {
            this.instance = XReflect.newInstance(ClassLoad.getClass(ClassNames.TL_MESSAGES_AFFECTED));
        }
        public TL_messages_affectedMessages(Object instance)
        {
            this.instance = instance;
        }

        public int getPts(){
            return XReflect.getIntField(instance, AutomationResolver.resolve("TLRPC$TL_messages_affectedMessages", "pts", AutomationResolver.ResolverType.Field));
        }

        public int getPtsCount(){
            return XReflect.getIntField(instance, AutomationResolver.resolve("TLRPC$TL_messages_affectedMessages", "pts_count", AutomationResolver.ResolverType.Field));
        }
        public void setPts(int pts){
            XReflect.setIntField(instance, AutomationResolver.resolve("TLRPC$TL_messages_affectedMessages", "pts", AutomationResolver.ResolverType.Field), pts);
        }

        public void setPtsCount(int pts_count){
            XReflect.setIntField(instance, AutomationResolver.resolve("TLRPC$TL_messages_affectedMessages", "pts_count", AutomationResolver.ResolverType.Field), pts_count);
        }

        public Object getTL_messages_affectedMessages(){
            return instance;
        }

    }

    public static class TL_channels_readHistory {
        final Object instance;

        public TL_channels_readHistory()
        {
            this.instance = XReflect.newInstance(ClassLoad.getClass(ClassNames.TL_CHANNELS_READ_HISTORY));
        }
        public TL_channels_readHistory(Object instance)
        {
            this.instance = instance;
        }

        public void setChannel(Object channel){
            XReflect.setObjectField(instance, AutomationResolver.resolve("TLRPC$TL_channels_readHistory", "channel", AutomationResolver.ResolverType.Field), channel);
        }

        public void setMax_id(int max_id){
            XReflect.setIntField(instance, AutomationResolver.resolve("TLRPC$TL_channels_readHistory", "max_id", AutomationResolver.ResolverType.Field), max_id);
        }

        public Object getTL_channels_readHistory(){
            return instance;
        }
    }

    public static class TL_messages_readHistory {
        final Object instance;

        public TL_messages_readHistory()
        {
            this.instance = XReflect.newInstance(ClassLoad.getClass(ClassNames.TL_MESSAGES_READ_HISTORY));
        }

        public TL_messages_readHistory(Object instance)
        {
            this.instance = instance;
        }

        public void setPeer(InputPeer peer){
            XReflect.setObjectField(instance, AutomationResolver.resolve("TLRPC$TL_messages_readHistory", "peer", AutomationResolver.ResolverType.Field), peer.inputPeer);
        }

        public void setMax_id(int max_id){
            XReflect.setIntField(instance, AutomationResolver.resolve("TLRPC$TL_messages_readHistory", "max_id", AutomationResolver.ResolverType.Field), max_id);
        }

        public Object getTL_messages_readHistory(){
            return instance;
        }

    }
}
