//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.1.
//


package com.useridentify.service;

import android.app.NotificationManager;
import android.content.Context;
import org.androidannotations.api.builder.ServiceIntentBuilder;

public final class IdenInformService_
    extends IdenInformService
{


    public static IdenInformService_.IntentBuilder_ intent(Context context) {
        return new IdenInformService_.IntentBuilder_(context);
    }

    private void init_() {
        notificationManager = ((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE));
    }

    @Override
    public void onCreate() {
        init_();
        super.onCreate();
    }

    public static class IntentBuilder_
        extends ServiceIntentBuilder<IdenInformService_.IntentBuilder_>
    {


        public IntentBuilder_(Context context) {
            super(context, IdenInformService_.class);
        }

    }

}