package com.tuvarna.mytu.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.tuvarna.mytu.R;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

import java.util.List;

public class ArrowPolyline extends Polyline {
    private Path arrowPath;
    private Paint arrowPaint;
    private Drawable arrowDrawable;
    private float density;

    @SuppressLint("UseCompatLoadingForDrawables")
    public ArrowPolyline(Context context, List<GeoPoint> geoPoints) {
        super();
        setPoints(geoPoints);
        getOutlinePaint().setColor(Color.parseColor(Constants.MAP_POLYGON_SELECT_STROKE_COLOR));
        getOutlinePaint().setStrokeWidth(Constants.MAP_ROUTE_POLYGON_STROKE_WIDTH);
        setInfoWindow(null); // Disable info window

        arrowPath = new Path();
        arrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arrowPaint.setStyle(Paint.Style.FILL);
        arrowPaint.setColor(Color.TRANSPARENT);

        arrowDrawable = context.getResources().getDrawable(R.drawable.ic_baseline_arrow_left);
        density = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        super.draw(canvas, mapView, shadow);

        if (!shadow) {
            drawArrows(canvas, mapView);
        }
    }

    private void drawArrows(Canvas canvas, MapView mapView) {
        if (getPoints().size() < 2) {
            return;
        }

        List<GeoPoint> geoPoints = getPoints();
        for (int i = 0; i < geoPoints.size() - 1; i++) {
            GeoPoint startPoint = geoPoints.get(i);
            GeoPoint endPoint = geoPoints.get(i + 1);

            Point startPixels = new Point();
            Point endPixels = new Point();
            mapView.getProjection().toPixels(startPoint, startPixels);
            mapView.getProjection().toPixels(endPoint, endPixels);

            float distance = calculateDistance(startPixels, endPixels);
            int numArrows = (int) (distance / Constants.ARROW_INTERVAL);

            float dx = endPixels.x - startPixels.x;
            float dy = endPixels.y - startPixels.y;
            float arrowAngle = (float) Math.atan2(dy, dx);

            for (int j = 1; j <= numArrows; j++) {
                float fraction = (float) j / (numArrows + 1);
                float arrowFraction = 1 - fraction;

                float arrowStartX = startPixels.x + arrowFraction * dx;
                float arrowStartY = startPixels.y + arrowFraction * dy;
                float arrowEndX = arrowStartX + (Constants.ARROW_SIZE_DP * density / distance) * dx;
                float arrowEndY = arrowStartY + (Constants.ARROW_SIZE_DP * density / distance) * dy;

                arrowPath.reset();
                arrowPath.moveTo(arrowEndX, arrowEndY);
                arrowPath.lineTo(arrowStartX, arrowStartY);
                arrowPath.lineTo(arrowEndX - (Constants.ARROW_SIZE_DP * density / distance) * dy,
                        arrowEndY + (Constants.ARROW_SIZE_DP * density / distance) * dx);
                arrowPath.lineTo(arrowEndX + (Constants.ARROW_SIZE_DP * density / distance) * dy,
                        arrowEndY - (Constants.ARROW_SIZE_DP * density / distance) * dx);
                arrowPath.close();

                canvas.drawPath(arrowPath, arrowPaint);

                int halfArrowWidth = arrowDrawable.getIntrinsicWidth() / 2;
                int halfArrowHeight = arrowDrawable.getIntrinsicHeight() / 2;

                float arrowRotation = (float) Math.toDegrees(arrowAngle);

                canvas.save();
                canvas.translate(arrowEndX - halfArrowWidth, arrowEndY - halfArrowHeight);
                canvas.rotate(arrowRotation, halfArrowWidth, halfArrowHeight);
                arrowDrawable.setBounds(0, 0, arrowDrawable.getIntrinsicWidth(), arrowDrawable.getIntrinsicHeight());
                arrowDrawable.draw(canvas);
                canvas.restore();
            }
        }
    }

    private float calculateDistance(Point p1, Point p2) {
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

}
