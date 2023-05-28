package org.utfpr.client.util;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Configure {

    public static void configureDateFormatted(JFormattedTextField jFormattedTextField) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        DateFormatter formatter = new DateFormatter(format);
        jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
    }

    public static void configureHourFormatted(JFormattedTextField jFormattedTextField) {
        DateFormat format = new SimpleDateFormat("hh:mm");
        DateFormatter formatter = new DateFormatter(format);
        jFormattedTextField.setFormatterFactory(new DefaultFormatterFactory(formatter));
    }
}
