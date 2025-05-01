package arm.android.rxjava.weathe

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import arm.android.rxjava.R

class WeatherWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { appWidgetId ->
            val views = RemoteViews(context.packageName, R.layout.widget_weather)

            views.setTextViewText(R.id.widget_text, "погода")
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}