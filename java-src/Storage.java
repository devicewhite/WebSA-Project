package com.websa_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage<T> {
	private ArrayList<T> values;

	public Storage() {
		values = new ArrayList<>();
	}

	public void add(T value) {
		int index = values.indexOf(null);
		if(index == -1) {
			values.add(value);
		} else {
			values.set(index, value);
		}
	}
	
	public T get(int index) {
		return values.get(index);
	}

	public void remove(int index) {
		values.set(index, null);
		for(int x = values.size() - 1; x >= 0; x--) {
			if(values.get(x) == null) {
				values.remove(x);
			} else {
				break;
			}
		}
	}

	public boolean isEmpty(int index) {
		if(values.size() > index) {
			return values.get(index) == null;
		} else {
			return false;
		}
	}

	public int begin() {
		for(int i = 0; i < values.size(); i++) {
			if(values.get(i) != null) {
				return i;
			}
		}
		return -1;
	}

	public int end() {
		return values.size() - 1;
	}
	
	public int size() {
		int count = 0;
		for(int x = 0; x < values.size(); x++) {
			if(values.get(x) != null) {
				count++;
			}
		}
		return count;
	}

	public int indexOf(T value) {
		return values.indexOf(value);
	}

	public Integer[] toArray() {
		List<Integer> indices = new ArrayList<Integer>();
		for(int i = 0; i < values.size(); i++) {
			if(values.get(i) != null) {
				indices.add(i);
			}
		}
		Integer[] result = new Integer[indices.size()];
		for(int i = 0; i < indices.size(); i++) {
			result[i] = indices.get(i);
		}
		return result;
	}
}

/*
 * Copyright (C) 2023 DeviceBlack
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
