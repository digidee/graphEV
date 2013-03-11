package com.example.androidplot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

/**
 * The simplest possible example of using AndroidPlot to plot some data.
 */
public class MainActivity extends Activity {

	private XYPlot mySimpleXYPlot;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

		// Create a couple arrays of y-values to plot:
		// Number[] series1Numbers = {1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3,
		// 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3,
		// 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3,
		// 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3,
		// 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3,
		// 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5,
		// 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5,
		// 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2,
		// 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2,
		// 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2,
		// 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2,
		// 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2,
		// 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2,1.5,
		// 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2,1.5,
		// 2, 2.5, 3, 2.5, 2,1.5, 2, 2.5, 3, 2.5, 2};
		// Number[] series2Numbers = { 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2, 1.5,
		// 2,
		// 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2,
		// 1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5, 3, 2.5, 2, 1.5, 2, 2.5 };
		// double[] series1Numbers = new double [10];

		List<Float> stephList = new ArrayList<Float>();
		List<Float> austinList = new ArrayList<Float>();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getAssets().open("stephControlLoopwithSystem.txt")));
			// int i = 0;
			// do reading, usually loop until end of file reading
			String mLine = reader.readLine();
			String[] parts;
			while (mLine != null) {
				// process line

				parts = mLine.split("\\s+");
				float m = Float.parseFloat(parts[1]);
				Log.d("plot", "" + m);
				mLine = reader.readLine();

				// series1Numbers[i] = m;
				stephList.add(m);
				// i++;
			}

			reader.close();
		} catch (IOException e) {
			// log the exception
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getAssets().open("austinControlLoopAccel.txt")));
			// int i = 0;
			// do reading, usually loop until end of file reading
			String mLine = reader.readLine();
			String[] parts;
			while (mLine != null) {
				// process line

				parts = mLine.split("\\s+");
				float m = Float.parseFloat(parts[1]);
				Log.d("plot", "" + m);
				mLine = reader.readLine();

				// series1Numbers[i] = m;
				austinList.add(m);
				// i++;
			}

			reader.close();
		} catch (IOException e) {
			// log the exception
		}

		// Turn the above arrays into XYSeries':
		XYSeries series1 = new SimpleXYSeries(austinList, // SimpleXYSeries
															// takes
															// a List so turn
															// our array into a
															// List
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use
														// the element index as
														// the x value
				"Austin"); // Set the display title of the series

		// same as above
		XYSeries series2 = new SimpleXYSeries(stephList,
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

		// Create a formatter to use for drawing a series using
		// LineAndPointRenderer:
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Color.rgb(0, 200, 0), // line color
				Color.rgb(0, 100, 0), // point color
				null); // fill color (none)

		// add a new series' to the xyplot:
		mySimpleXYPlot.addSeries(series1, series1Format);

		// same as above:
		mySimpleXYPlot.addSeries(
				series2,
				new LineAndPointFormatter(Color.rgb(0, 0, 200), Color.rgb(0, 0,
						100), null));

		// reduce the number of range labels
		mySimpleXYPlot.setTicksPerRangeLabel(3);

		// by default, AndroidPlot displays developer guides to aid in laying
		// out your plot.
		// To get rid of them call disableAllMarkup():
		mySimpleXYPlot.disableAllMarkup();
	}
}