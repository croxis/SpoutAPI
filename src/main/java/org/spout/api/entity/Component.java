/*
 * This file is part of SpoutAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutAPI is licensed under the SpoutDev License Version 1.
 *
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spout.api.entity;

import org.spout.api.tickable.TickPriority;
import org.spout.api.tickable.Tickable;

/**
 * Represents an attachment to a controller that can respond to Ticks.
 */
public interface Component<T extends Controller> extends Tickable, Comparable<Component<T>> {
	/**
	 * Attaches this component to an entity.
	 * @param parent entity this component will be attached to.
	 */
	public void attachToController(T parent);

	/**
	 * Gets the parent controller associated with this component.
	 * @return the parent controller
	 */
	public T getParent();

	/**
	 * Called when this component is attached to an entity.
	 */
	public abstract void onAttached();

	/**
	 * Called when this component is detached from an entity.
	 */
	public void onDetached();

	public TickPriority getPriority();

	public void setPriority(TickPriority priority);

	public boolean runOnce();

	public void setRunOnce(boolean runOnce);

	public float getDelay();

	public void setDelay(float delay);

	public float getMaxDelay();

	public void setMaxDelay(float delay);

	public void tick(float dt);
}