package com.retail.utility;

import java.text.DecimalFormat;

public interface Utility {
	static float format(float number) {
	    DecimalFormat df = new DecimalFormat("0.00");
        return Float.parseFloat(df.format(number));
	}
}
