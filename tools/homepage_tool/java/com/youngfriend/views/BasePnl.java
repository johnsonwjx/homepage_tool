package com.youngfriend.views;

import javax.swing.*;

/**
 * Created by pandongyu on 15/1/21.
 */
public interface BasePnl<T> {
    void loadBean(T bean) ;
    T getBean() ;
    JPanel getRootPnl() ;
}
