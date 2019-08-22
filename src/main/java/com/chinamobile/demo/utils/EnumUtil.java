package com.chinamobile.demo.utils;

import com.chinamobile.demo.entities.BaseEnum;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class EnumUtil {
	public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, int code) {
		E[] enumConstants = enumClass.getEnumConstants();
		for (E e : enumConstants) {
			if (e.getCode() == code)
				return e;
		}
		return null;
	}
}
