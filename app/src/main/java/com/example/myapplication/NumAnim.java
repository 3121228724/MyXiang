package com.example.myapplication;

import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by 马振雷 on 2018/3/20.
 */

public class NumAnim {
    //每秒刷新多少次
    //每秒刷新多少次
    private static final int COUNTPERS = 100;

    private static boolean stopAnim = false;


    public static void startAnim(TextView textV, String num) {
        startAnim(textV, num, 350);
        textV.setText(num);
    }

    public static void
    startAnim(TextView textV, String num, long time) {

        int l = 0;
        stopAnim = false;
        if (num.contains(".")) {
            //l是小数点后面位数
            int l0 = num.indexOf(".");
            l = num.length() - l0 - 1;
            float fNum = Float.parseFloat(num);
            if (fNum <= 1) {
                textV.setText(NumberFormat(fNum, l));
                return;
            }
            Float[] nums = splitnum(fNum, (int) ((time / 1000f) * COUNTPERS), l);
            CounterFloat counter = new CounterFloat(textV, nums, time,l);
            textV.removeCallbacks(counter);
            textV.post(counter);
        } else {
            int fNum = Integer.parseInt(num);
            if (fNum == 0) {
                textV.setText(NumberFormat(fNum, l));
                return;
            }
            Integer[] nums = splitnum(fNum, (int) ((time / 1000f) * COUNTPERS), l);
            CounterInt counter = new CounterInt(textV, nums, time,l);
            textV.removeCallbacks(counter);
            textV.post(counter);
        }


    }

    public static void stopAnim() {
        stopAnim = true;
    }


    private static Float[] splitnum(float num, int count, int l) {
        Random random = new Random();
        float numtemp = num;
        float sum = 0;
        LinkedList<Float> nums = new LinkedList<Float>();
        nums.add(0f);
        while (true) {
            float nextFloat = NumberFormatFloat(
                    (random.nextFloat() * num * 2f) / (float) count,
                    l);
            if (numtemp - nextFloat >= 0) {
                sum = NumberFormatFloat(sum + nextFloat, l);
                nums.add(sum);
                numtemp -= nextFloat;
            } else {
                nums.add(num);
                return nums.toArray(new Float[0]);
            }
        }
    }

    private static Integer[] splitnum(int num, int count, int l) {
        Random random = new Random();
        int numtemp = num;
        int sum = 0;
        LinkedList<Integer> nums = new LinkedList<Integer>();
        nums.add(0);
        while (true) {
            int nextFloat = NumberFormatInteger((int) ((random.nextFloat() * num * 2) / count), l);
            if (numtemp - nextFloat >= 0) {
                sum = NumberFormatInteger(sum + nextFloat, l);
                nums.add(sum);
                numtemp -= nextFloat;
            } else {
                nums.add(num);
                return nums.toArray(new Integer[0]);
            }
        }
    }

    static class CounterFloat implements Runnable {

        private final TextView view;
        private Float[] nums;
        private long pertime;
        private int l;
        private int i = 0;

        CounterFloat(TextView view, Float[] nums, long time, int l ) {
            this.view = view;
            this.nums = nums;
            this.pertime = time / nums.length;
            this.l = l;
        }

        @Override
        public void run() {
            if (stopAnim || i > nums.length - 1) {
                view.removeCallbacks(CounterFloat.this);
                return;
            }
            view.setText(NumberFormat(nums[i++], l));
            view.removeCallbacks(CounterFloat.this);
            view.postDelayed(CounterFloat.this, pertime);
        }
    }

    static class CounterInt implements Runnable {

        private final TextView view;
        private Integer[] nums;
        private long pertime;
        private int l;

        private int i = 0;

        CounterInt(TextView view, Integer[] nums, long time,int l ) {
            this.view = view;
            this.nums = nums;
            this.pertime = time / nums.length;
            this.l = l;
        }

        @Override
        public void run() {
            if (stopAnim || i > nums.length - 1) {
                view.removeCallbacks(CounterInt.this);
                return;
            }
            view.setText(NumberFormat(nums[i++], l));
            view.removeCallbacks(CounterInt.this);
            view.postDelayed(CounterInt.this, pertime);
        }
    }

    private static String NumberFormat(float f, int m) {

        return String.format("%." + m + "f", f);
    }

    private static float NumberFormatFloat(float f, int m) {
        String strfloat = NumberFormat(f, m);
        return Float.parseFloat(strfloat);
    }

    private static int NumberFormatInteger(int f, int m) {
        String strfloat = NumberFormat(f, m);
        return Integer.parseInt(strfloat);
    }

}
