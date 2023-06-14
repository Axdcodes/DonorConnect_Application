package com.efedaniel.bloodfinder.utils

class PrefKeys {
    companion object {
        const val LOGGED_IN_FIREBASE_AUTH_USER = "LOGGED_IN_FIREBASE_AUTH_USER"
        const val LOGGED_IN_USER_DATA = "LOGGED_IN_USER_DATA"
        const val PREVIOUSLY_USED_EMAIL_ADDRESS = "PREVIOUSLY_USED_EMAIL_ADDRESS"
        const val DEVICE_NOTIFICATION_TOKEN = "DEVICE_NOTIFICATION_TOKEN"
        const val IS_NOTIFICATION_SUBSCRIBED = "IS_NOTIFICATION_SUBSCRIBED"
    }
}

class APIDataKeys {
    companion object {
        const val INPUT_ERROR_CODE = 400
    }
}

class ApiKeys {
    companion object {
        const val AUTH_BASE_URL = "https://identitytoolkit.googleapis.com/"
        const val DATABASE_BASE_URL = "https://final-project-donorconnect-default-rtdb.asia-southeast1.firebasedatabase.app/"
        const val WEB_API_KEY = "AIzaSyArhSwYDrqIxR5vuh2raLe91MC79zmyn0U"
        const val NOTIFICATION_BASE_URL = "https://fcm.googleapis.com/"
        const val NOTIFICATION_SERVER_KEY = "key=AAAAAFnQMOY:APA91bFkeakkT7D04k-ELhLuUW9prE2FzlReHx31NHMxsj8U8Mf8dtgY90iDst_Ns09tpwBFG33UIch7_ku9HdXvN1o9JtWiZUD2Ua9CdII4Jt8hke6LDadjzinmJVmyrU7OLcp7r-dX"
        const val AUTHORIZATION = "Authorization"
        const val DONOR_ID = "donorID"
        const val BLOOD_SEEKER_ID = "bloodSeekerID"
        const val BLOOD_PROVIDER_ID = "bloodProviderID"
        const val BLOOD_TYPE = "bloodType"
        const val PENDING = "pending"
        const val ACCEPTED = "accepted"
        const val DECLINED = "declined"
        const val REQUEST_NOTIFICATION_TYPE = "request"
        const val ANSWER_NOTIFICATION_TYPE = "answer"
    }
}

class ErrorCodes {
    companion object {
        const val EMAIL_NOT_FOUND = "EMAIL_NOT_FOUND"
        const val MISSING_EMAIL = "MISSING_EMAIL"
        const val INVALID_EMAIL = "INVALID_EMAIL"
        const val EMAIL_EXISTS = "EMAIL_EXISTS"
        const val INVALID_PASSWORD = "INVALID_PASSWORD"
        const val USER_DISABLED = "USER_DISABLED"
        const val TOO_MANY_ATTEMPTS = "TOO_MANY_ATTEMPTS_TRY_LATER : Too many unsuccessful login attempts.  Please include reCaptcha verification or try again later"
    }
}

class Misc {
    companion object {
        const val DAY_MONTH_YEAR_AND_TIME = "dd MMMM yyyy 'by' hh:mma"
        const val DAY_MONTH_AND_TIME = "dd MMMM 'at' hh:mma"
        const val TIME = "hh:mma"
        const val REQUEST_NOTIFICATION_CHANNEL_ID = "blood_request_channel"
        const val ANSWER_NOTIFICATION_CHANNEL_ID = "blood_answer_channel"
        const val CALL_PERMISSION_REQUEST = 200
        const val LOCATION_PERMISSION_REQUEST = 201
        const val LOCATION_DEVICE_ACCESS_REQUEST = 202
        const val LOCATION_AUTOCOMPLETE_CODE = 203
        const val MAPS_APP_ID = "com.google.android.apps.maps"
        const val MAPS_PLAY_STORE_URL = "market://details?id=$MAPS_APP_ID"
    }
}
