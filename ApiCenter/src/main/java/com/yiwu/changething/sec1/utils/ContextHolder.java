package com.yiwu.changething.sec1.utils;

import com.yiwu.changething.sec1.bean.WeChatInfo;
import org.springframework.core.NamedThreadLocal;

public class ContextHolder {

	private static final ThreadLocal<WeChatInfo> threadLocal = new NamedThreadLocal<WeChatInfo>("user");

	public static void bind(WeChatInfo value) throws IllegalStateException {
		threadLocal.set(value);
	}

	public static WeChatInfo get() throws IllegalStateException {
		return threadLocal.get();
	}

	public static void unbind() {
		threadLocal.remove();
	}

}
