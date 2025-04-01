package com.danielang.productfacility.domain.util;

import java.util.Collections;
import java.util.List;

/**
 * @program: awsurance-product-facility
 * @author: Daniel
 * @create: 2025-03-30 16:57
 **/
public class ImmutableUtil {
	private ImmutableUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static <T> List<T> createUnmodifiableList(List<T> collection) {
		if (collection != null && !collection.isEmpty()) {
			collection = Collections.unmodifiableList(collection);
		} else {
			collection = List.of();
		}

		return collection;
	}
}
