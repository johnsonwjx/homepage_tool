package com.youngfriend.views;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by pandongyu on 15/1/7.
 */
public interface Editor {
    void eidt(Window owner, HashMap<String, Object> map) ;
    boolean isSubmit() ;
}
