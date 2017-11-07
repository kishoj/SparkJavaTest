package com.test.sparks.common;

import java.io.Serializable;

public interface Converter <K, V> extends Serializable {
	K convert(V v, Class<?> beanClass);
}
