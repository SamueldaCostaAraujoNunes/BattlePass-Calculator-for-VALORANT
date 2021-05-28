package br.com.samuelnunes.valorantpassbattle.util

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {
    private val crashlytics = FirebaseCrashlytics.getInstance()
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }
        if (priority == Log.ERROR || priority == Log.WARN) {

            crashlytics.setCustomKey(CRASHLYTICS_KEY_PRIORITY, priority)
            if (tag != null) crashlytics.setCustomKey(CRASHLYTICS_KEY_TAG, tag)
            crashlytics.setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)

            val error = t ?: Exception(message)
            crashlytics.recordException(error)
        }
    }

    companion object {
        private const val CRASHLYTICS_KEY_PRIORITY = "priority"
        private const val CRASHLYTICS_KEY_TAG = "tag"
        private const val CRASHLYTICS_KEY_MESSAGE = "message"
    }

}