/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * SpoutAPI is licensed under the Spout License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.api.util;

import java.util.Iterator;

import org.spout.api.math.IntVector3;

public class IntVector3CompositeIterator implements Iterable<IntVector3>, Iterator<IntVector3> {
	
	private final Iterable<IntVector3>[] iterables;
	private final Iterator<IntVector3>[] iterators;
	private int count;
	
	@SuppressWarnings("unchecked")
	public IntVector3CompositeIterator(Iterable<IntVector3>[] iterables) {
		int nonNull = 0;
		for (int i = 0; i < iterables.length; i++) {
			if (iterables[i] != null) {
				nonNull++;
			}
		}
		this.iterables = new Iterable[nonNull];
		int j = 0;
		for (int i = 0; i < iterables.length; i++) {
			if (iterables[i] != null) {
				this.iterables[j++] = iterables[i];
			}
		}
		this.iterators = new Iterator[nonNull];
		iterator();
		this.count = 0;
	}

	@Override
	public boolean hasNext() {
		if (count >= iterators.length) {
			return false;
		} else if (iterators[count].hasNext()) {
			return true;
		} else if (count + 1 < iterables.length) {
			return iterators[count+1].hasNext();
		} else {
			return false;
		}
	}

	@Override
	public IntVector3 next() {
		while (count < iterators.length) {
			if (iterators[count].hasNext()) {
				return iterators[count].next();
			}
			count++;
		}
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Removal is not supported");
	}

	@Override
	public Iterator<IntVector3> iterator() {
		count = 0;
		for (int i = 0; i < iterables.length; i++) {
			iterators[i] = iterables[i].iterator();
		}
		return this;
	}

}
