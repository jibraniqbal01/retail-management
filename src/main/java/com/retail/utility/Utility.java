package com.retail.utility;

import java.text.DecimalFormat;

public interface Utility {
	static double format(Double number) {
	    DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(number));
	}
}
