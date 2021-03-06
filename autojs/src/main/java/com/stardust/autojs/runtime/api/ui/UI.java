package com.stardust.autojs.runtime.api.ui;

import android.content.Context;

import com.stardust.autojs.rhino.ProxyObject;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.UniqueTag;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stardust on 2017/5/14.
 */

public class UI extends ProxyObject {


    private Context mContext;
    private Map<String, Object> mProperties = new ConcurrentHashMap<>();

    public UI(Context context, JsLayoutInflater layoutInflater) {
        mContext = context;
        mProperties.put("layoutInflater", layoutInflater);
    }

    public UI(Context context) {
        this(context, new ConvertLayoutInflater());
    }


    @Override
    public String getClassName() {
        return UI.class.getSimpleName();
    }


    @Override
    public Object get(String name, Scriptable start) {
        Object value = super.get(name, start);
        if (value != null && value != UniqueTag.NOT_FOUND && !value.equals(org.mozilla.javascript.Context.getUndefinedValue())) {
            return value;
        }
        value = mProperties.get(name);
        if (value != null)
            return value;
        return UniqueTag.NOT_FOUND;
    }


}
