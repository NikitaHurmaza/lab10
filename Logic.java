package com.example.lab10;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

import static java.lang.Math.tan;

@Named
@Data
@SessionScoped
public class Logic implements Serializable {
    double a;
    double step;
    double begin;
    double end;
    int countSteps;
    double valueMaxYIndex;
    double valueMaxYNumber;
    double valueMinYIndex;
    double valueMinYNumber;
    double valueMinXNumber;
    double valueMaxXNumber;
    double sum;
    double average;
    double x;
    double y;
    double eps = 1.0E-4;



    public int count(double begin, double end, double h) {
        return (int)Math.round((end - begin) / h) + 1;
    }

    public double[] creatFillX(double begin, double end, double h, double[] temp) {
        double a = begin;
        for(int i = 0; i < temp.length; ++i) {
            temp[i] = a + (double)i * h;
        }
        return temp;
    }
    public double calcY (double x,double a) {
        if (x <= 0.3 - eps)
            y = 1.5*a*Math.cos(x)*Math.cos(x);
        else if (x > 2.3 + eps)
            y = 3*a*tan(x);
        else y = (x-2)*(x-2)+6*a;
        return y;

    }

    public double[] creatFillY(double x, double a, double[] temp, double[] temp2) {
        for (int i = 0; i < temp.length; ++i) {
            x = temp[i];
            temp2[i] = calcY(x,a);
        }
        return temp2;
    }

    public double maxYIndex(double[] temp2) {
        int maxIndex = 0;

        for(int k = 0; k < temp2.length; ++k) {
            if (temp2[k] > temp2[maxIndex]) {
                maxIndex = k;
            }
        }
        return maxIndex;
    }


    public double maxYNumber(double[] temp2) {
        this.maxYIndex(temp2);
        double maxNumber = temp2[(int)this.maxYIndex(temp2)];
        return maxNumber;
    }
    public double maxXNumber(double[] temp, double[] temp2) {
        double maxNumber = maxYIndex(temp2);
        return temp[(int) maxNumber];
    }

    public double minYIndex(double[] temp2) {
        int minIndex=0;
        for(int i = 1; i < temp2.length; ++i) {
            if (temp2[i] < temp2[minIndex]) {
                minIndex = i;
            }
        }

        return (double)minIndex;
    }


    public double minYNumber(double[] temp2) {
        double minNumber = 0.0;
        this.minYIndex(temp2);
        minNumber = temp2[(int)this.minYIndex(temp2)];
        return minNumber;
    }
    public double minxXNumber(double[] temp, double[] temp2) {
        int minNumber = (int) minYIndex(temp2);
        return temp[(int) minNumber];
    }

    public double Sum(double[] temp2) {
        double Sum = 0.0;

        for(int i = 1; i < temp2.length; ++i) {
            Sum += temp2[i];
        }

        return Sum;
    }


    public double average(double[] temp2) {
        double avar = 0.0;

        for(int i = 0; i < temp2.length; ++i) {
            avar += temp2[i];
        }

        avar /= (double)temp2.length;
        return avar;
    }


    public String show() {
        double[] temp = new double[this.count(begin, end, step)];
        double[] temp2 = new double[this.count(begin, end, step)];
        countSteps= count(begin, end, step);
        creatFillX(begin, end, step, temp);
        creatFillY(x, a, temp,temp2);
        valueMaxYIndex = maxYIndex(temp2);
        valueMaxYNumber= maxYNumber(temp2);
        valueMinYIndex=  minYIndex(temp2);
        valueMinYNumber= minYNumber(temp2);
        valueMinXNumber= minxXNumber(temp, temp2);
        valueMaxXNumber=maxXNumber(temp, temp2);
        sum = Sum(temp2);
        average = average(temp2);
        a = 0;
        step= 0 ;
        begin= 0;
        end= 0;
        return "index";
    }
}