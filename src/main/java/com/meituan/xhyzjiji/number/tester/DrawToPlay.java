package com.meituan.xhyzjiji.number.tester;

import com.meituan.xhyzjiji.number.std.Draw;
import java.awt.Color;
import java.util.List;

public class DrawToPlay {

    public static void main(String args[]) {
        Draw draw = new Draw();
        draw.setPenColor(Color.RED);
        draw.setXscale(0, 2);
        draw.setYscale(0, 2);

        /*虫洞*/
        int pointNum = 18;
        double rad = 2 * Math.PI / pointNum;
        double leftRad = 2 * Math.PI;
        while (leftRad > 0) {
            double x = 0.1 * Math.cos(leftRad) + 0.75;
            double y = 0.1 * Math.sin(leftRad) + 0.75;
            draw.circle(x, y, 0.5);
            leftRad = leftRad - rad;
            draw.pause(50);
        }
        draw.pause(2000);
        draw.clear();

        /*六芒星*/
        double x = 1;
        double y = 1;
        double radius = 0.5;
        draw.circle(x, y, radius);
        draw.line(x - radius*Math.sqrt(3)/2, y + radius/2, x + radius*Math.sqrt(3)/2, y + radius/2);
        draw.line(x - radius*Math.sqrt(3)/2, y + radius/2, x, y - radius);
        draw.line(x + radius*Math.sqrt(3)/2, y + radius/2, x, y - radius);
        draw.line(x - radius*Math.sqrt(3)/2, y - radius/2, x + radius*Math.sqrt(3)/2, y - radius/2);
        draw.line(x - radius*Math.sqrt(3)/2, y - radius/2, x, y + radius);
        draw.line(x + radius*Math.sqrt(3)/2, y - radius/2, x, y + radius);
        draw.pause(2000);
        draw.clear();

        /*正弦波*/
        double scale = Math.PI * 4;
        int points = 360;
        boolean isInit = false;
        double x0,y0,x1,y1;
        x0 = y0 = x1 = y1 = 0D;
        for (int i = 0; i < points; i++) {
            if (isInit) {
                x1 = x0;
                y1 = y0;
                x0 = 1D / 180 * i;
                y0 = Math.sin(scale * i / 180) + 1;
                draw.line(x0, y0, x1, y1);
            } else {
                x0 = 1D / 180 * i;
                y0 = Math.sin(scale * i / 180) + 1;
                isInit = true;
            }
        }
    }
}
