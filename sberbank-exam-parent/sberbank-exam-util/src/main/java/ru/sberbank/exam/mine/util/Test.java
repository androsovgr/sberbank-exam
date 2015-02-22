package ru.sberbank.exam.mine.util;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static Map<String, Integer> map;

	public static void main(String[] args) {
		for (int z = 0; z < 100; z++) {
			map = new HashMap<String, Integer>();
			for (int i = 0; i < 100; i++) {
				(new Thread(new Runnable() {

					@Override
					public void run() {
						for (int j = 0; j < 10000; j++) {
							map.put("aaa", 1);
							map.get("aaa");
							map.remove("aaa");
						}
					}
				})).start();

			}
			System.out.println(map.size());
			if (map.size() != 1) {
				System.out.println(map);
			}
		}
	}
}
