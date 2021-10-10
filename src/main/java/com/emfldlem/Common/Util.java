package com.emfldlem.Common;

import org.springframework.beans.factory.InitializingBean;

public class Util implements InitializingBean {

    public static final DateUtility date = new DateUtility();
    public static final NumberUtility number = new NumberUtility();
    public static final TextUtility text = new TextUtility();

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
