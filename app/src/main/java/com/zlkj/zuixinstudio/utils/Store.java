package com.zlkj.zuixinstudio.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;

public class Store {
	private SharedPreferences sp;
	private Editor editor;

	public static Store init(Context context, String name, int mode) {
		return new Store(context, name, mode);
	}

	/**
	 * 构造方法。
	 * 
	 * @param context
	 *            键值表名称。
	 * @param mode
	 *            打开的模式。值为Context.MODE_APPEND, Context.MODE_PRIVATE,
	 *            Context.WORLD_READABLE, Context.WORLD_WRITEABLE.
	 */
	public Store(Context context, String name, int mode) {
		sp = context.getSharedPreferences(name, mode);
		editor = sp.edit();
	}

	/**
	 * 获取保存着的boolean对象。
	 *
	 * @param key
	 *            键名
	 * @param value
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public boolean getBoolean(String key, boolean value) {
		return sp.getBoolean(key, value);
	}

	public boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	/**
	 * 获取保存着的int对象。
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public int getInt(String key, int value) {
		return sp.getInt(key, value);
	}

	public int getInt(String key) {
		return getInt(key, 0);
	}

	/**
	 * 获取保存着的long对象。
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public long getLong(String key, long value) {
		return sp.getLong(key, value);
	}

	public long getLong(String key) {
		return getLong(key, 0);
	}

	/**
	 * 获取保存着的float对象。
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public float getFloat(String key, float value) {
		return sp.getFloat(key, value);
	}

	public float getFloat(String key) {
		return getFloat(key, 0);
	}

	/**
	 * 获取保存着的String对象。
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            当不存在时返回的默认值。
	 * @return 返回获取到的值，当不存在时返回默认值。
	 */
	public String getString(String key, String value) {
		return sp.getString(key, value);
	}

	public String getString(String key) {
		return getString(key, null);
	}

	/**
	 * 获取所有键值对。
	 * 
	 * @return 获取到的所胡键值对。
	 */
	public Map<String, ?> getAll() {
		return sp.getAll();
	}

	/**
	 * 设置一个键值对，它将在{@linkplain #commit()}被调用时保存。<br/>
	 * 注意：当保存的value不是boolean, byte(会被转换成int保存),int, long, float,
	 * String等类型时将调用它的toString()方法进行值的保存。
	 * 
	 * @param key
	 *            键名称。
	 * @param value
	 *            值。
	 * @return 引用的KV对象。
	 */
	public Store put(String key, Object value) {
		if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);
		} else if (value instanceof Integer || value instanceof Byte) {
			editor.putInt(key, (Integer) value);
		} else if (value instanceof Long) {
			editor.putLong(key, (Long) value);
		} else if (value instanceof Float) {
			editor.putFloat(key, (Float) value);
		} else if (value instanceof String) {
			editor.putString(key, (String) value);
		} else {
			editor.putString(key, value.toString());
		}
		return this;
	}

	/**
	 * 移除键值对。
	 * 
	 * @param key
	 *            要移除的键名称。
	 * @return 引用的KV对象。
	 */
	public Store remove(String key) {
		editor.remove(key);
		return this;
	}

	/**
	 * 清除所有键值对。
	 * 
	 * @return 引用的KV对象。
	 */
	public Store clear() {
		editor.clear();
		return this;
	}

	/**
	 * 是否包含某个键。
	 * 
	 * @param key
	 *            查询的键名称。
	 * @return 当且仅当包含该键时返回true, 否则返回false.
	 */
	public boolean contains(String key) {
		return sp.contains(key);
	}

	/**
	 * 返回是否提交成功。
	 * 
	 * @return 当且仅当提交成功时返回true, 否则返回false.
	 */
	public boolean commit() {
		return editor.commit();
	}
}