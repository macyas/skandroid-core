package com.samknows.ui2.activity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.util.Pair;

import com.samknows.libcore.SKCommon;
import com.samknows.libcore.SKLogger;

/**
 * This class is a helper to format values
 * 
 * All rights reserved SamKnows
 * @author pablo@samknows.com
 */

public class FormattedValues
{	
	// *** CONSTRUCTOR *** //
	public FormattedValues()
	{
		
	}
	
	/**
	 * Get an speed formatted value
	 * 
	 * @param pValue
	 * 
	 * @return
	 */
	public static Pair<Float,String> getFormattedSpeedValue(String pValue)
	{
		if (pValue.length() == 0) {
			return new Pair<Float,String>(0.0F, "");
		}
		if (pValue.equals("Failed")) {
			// Convenience when debugging...
			return new Pair<Float,String>(0.0F, "");
		}
		
		
		NumberFormat formatter = new DecimalFormat("00.0");
	
		String[] values = pValue.split(" ");
		SKLogger.sAssert(FormattedValues.class, values.length > 0);
		SKLogger.sAssert(FormattedValues.class, values.length <= 2);
		
		String unit = "";
		if (values.length > 1) {
			unit = values[1];
		}
		
		double value = SKCommon.sGetDecimalStringAnyLocaleAsDouble (values[0]);
		return new Pair<Float,String>((float)value, unit);
	
		/*
		// TODO??!?! Restore this code, somehow?!
		if (unit.equalsIgnoreCase("mbps"))
		{
			if (value >= 100) 
			{
				formatter = new DecimalFormat("000");				
			}
			else if (value >= 10)
			{
				formatter = new DecimalFormat("00.0");				
			}
			else if (value >= 0)
			{
				formatter = new DecimalFormat("0.00");				
			}										
		}
		else if (unit.equalsIgnoreCase("kbps"))
		{
			value = value / 1000;
			
			formatter = new DecimalFormat("0.00");			
		}		
		
		return Float.valueOf(formatter.format(value));
		*/
	}
	
	/**
	 * Get a formatted latency value
	 * 
	 * @param pValue
	 * 
	 * @return
	 */
	public static Pair<Float,String> getFormattedLatencyValue(String pValue)
	{
		String values[] = pValue.split(" ");
		
		String unit = "";
		if (values.length > 1) {
			unit = values[1];
		}
		
		if (unit.equals("s"))
		{
			return new Pair<Float, String>(1000 * Float.valueOf(new DecimalFormat("0.0").format(Float.valueOf(values[0]))), unit);
		}
		else
		{
			return new Pair<Float, String>(Float.valueOf(new DecimalFormat("000").format(Math.round(Float.valueOf(Float.valueOf(values[0]))))), unit);
		}				
	}
	
	/**
	 * Get a formatted packet loss value
	 * 
	 * @param pValue
	 * 
	 * @return
	 */
	public static Pair<Integer,String> getFormattedPacketLossValue(String pValue)
	{
		String values[] = pValue.split(" ");
		
		String unit = "";
		if (values.length > 1) {
			unit = values[1];
		}
		
		double value = SKCommon.sGetDecimalStringAnyLocaleAsDouble (values[0]);
		return new Pair<Integer,String> ((int)value, unit);
	
		//return Math.round(Float.valueOf(pValue.substring(0, pValue.length() - 2)));
	}
	
	/**
	 * Get a formatted jitter value
	 * 
	 * @param pValue
	 * 
	 * @return
	 */
	public static Pair<Float,String> getFormattedJitter(String pValue)
	{
		String values[] = pValue.split(" ");
		
		String unit = "";
		if (values.length > 1) {
			unit = values[1];
		}
		

		double value = SKCommon.sGetDecimalStringAnyLocaleAsDouble(values[0]);
		return new Pair<Float,String> ((float)value, unit);
		//return (int)Math.round(Float.valueOf(split[0]));
	}
	
	/**
	 * Get a formatted speed value
	 * 
	 * @param pValue
	 * 
	 * @return
	 */
	static public String sGet3DigitsNumber(float pValue)
	{
		NumberFormat formatter = new DecimalFormat("00.0");
		
		if (pValue < 10)
		{
			formatter = new DecimalFormat("0.00");				
		}
		else if (pValue < 100)
		{
			formatter = new DecimalFormat("00.0");				
		}
		else if (pValue >= 100) 
		{
			formatter = new DecimalFormat("000");				
		}
		
		return String.valueOf(formatter.format(pValue));
	}
	
	/**
     * Convert from time in milliseconds to date in a given format
     * 
     * @param pMilliSeconds
     * @param pDateFormat
     * 
     * @return
     */
    public String getDate(long pMilliSeconds, String pDateFormat)
 	{
 	    // Create a DateFormatter object for displaying date in specified format.
 	    DateFormat formatter = new SimpleDateFormat(pDateFormat);

 	    // Create a calendar object that will convert the date and time value in milliseconds to date. 
 	     Calendar calendar = Calendar.getInstance();
 	     calendar.setTimeInMillis(pMilliSeconds);
 	     
 	     return formatter.format(calendar.getTime());
 	}
}
