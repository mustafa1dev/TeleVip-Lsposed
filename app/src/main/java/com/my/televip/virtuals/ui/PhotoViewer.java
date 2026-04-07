package com.my.televip.virtuals.ui;

import android.app.Activity;

import com.my.televip.Class.ClassNames;
import com.my.televip.Class.ClassLoad;
import com.my.televip.obfuscate.AutomationResolver;
import com.my.televip.virtuals.messenger.ImageReceiver;
import com.my.televip.virtuals.messenger.MessageObject;

import de.robv.android.xposed.XposedHelpers;

public class PhotoViewer {

    Object photoViewer;

    public PhotoViewer(Object photoViewer){
        this.photoViewer = photoViewer;
    }

    public static PhotoViewer getInstance(){
        return new PhotoViewer(XposedHelpers.callStaticMethod(ClassLoad.getClass(ClassNames.PHOTO_VIEWER), AutomationResolver.resolve("PhotoViewer", "getInstance", AutomationResolver.ResolverType.Method)));
    }

    public void setParentActivity(Activity activity){
        XposedHelpers.callMethod(photoViewer,  AutomationResolver.resolve("PhotoViewer", "setParentActivity", AutomationResolver.ResolverType.Method), activity);
    }

    public void openPhoto(MessageObject messageObject, long l, long l2, long l3, PhotoViewerProvider provider, boolean b){
        XposedHelpers.callMethod(photoViewer,  AutomationResolver.resolve("PhotoViewer", "openPhoto", AutomationResolver.ResolverType.Method), messageObject.getMessageObject(),l, l2, l3, provider.getPhotoViewerProvider(), b);
    }

    public static class PhotoViewerProvider {
        Object photoViewerProvider;

        public PhotoViewerProvider(Object photoViewer) {
            photoViewerProvider = photoViewer;
        }

        public PlaceProviderObject getPlaceForPhoto(MessageObject messageObject, Object fileLocation, int index, boolean needPreview, boolean closing) {
            return new PlaceProviderObject(XposedHelpers.callMethod(photoViewerProvider,  AutomationResolver.resolve("PhotoViewer$PhotoViewerProvider", "getPlaceForPhoto", AutomationResolver.ResolverType.Method), messageObject.getMessageObject(), fileLocation, index, needPreview, closing));
        }

        public Object getPhotoViewerProvider() {
            return photoViewerProvider;
        }

    }

    public static class PlaceProviderObject {
        Object placeProviderObject;

        public PlaceProviderObject(Object placeProvider) {
            placeProviderObject = placeProvider;
        }

        public ImageReceiver getImageReceiver() {
            return new ImageReceiver(XposedHelpers.getObjectField(placeProviderObject,  AutomationResolver.resolve("PhotoViewer$PlaceProviderObject", "imageReceiver", AutomationResolver.ResolverType.Field)));
        }

    }

}
